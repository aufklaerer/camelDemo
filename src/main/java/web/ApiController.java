package web;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class ApiController {

    @Autowired
    private
    ProducerTemplate producerTemplate;

    @RequestMapping(value = "/receiver", method = RequestMethod.POST)
    public ResponseEntity receiveFile(@RequestBody String body) {
        try {
            producerTemplate.sendBody("direct:receiver", body);
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
        return ResponseEntity
                .ok().body("ok");
    }
}
