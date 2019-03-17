package web;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public final class ApiController {

    @Autowired
    private ProducerTemplate producerTemplate;

    @Value("${camelApp.startRoute}")
    private String startRoute;

    @RequestMapping(value = "/receiver", method = RequestMethod.POST)
    public ResponseEntity receiveFile(@RequestBody final String body) {
        try {
            producerTemplate.sendBody("direct:" + startRoute, body);
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
        return ResponseEntity
                .ok().body("ok");
    }
}
