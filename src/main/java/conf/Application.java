package conf;

import org.apache.camel.CamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"conf", "web"})
public class Application {
    @Autowired
    CamelContext camelContext;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
