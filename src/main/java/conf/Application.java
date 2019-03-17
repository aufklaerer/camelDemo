package conf;

import org.apache.camel.CamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"conf", "web", "enrich"})
public class Application {
    @Autowired
    private CamelContext camelContext;

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
