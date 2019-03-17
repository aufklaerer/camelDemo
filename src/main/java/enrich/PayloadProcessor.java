package enrich;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("payloadProcessor")
public class PayloadProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        String payload = exchange.getIn().getBody(String.class);
        System.out.println(payload);
    }
}
