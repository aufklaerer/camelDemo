package enrich;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("payloadProcessor")
public class PayloadProcessor implements Processor {
    private static final String LAST_NAME = "lastName";
    private static final String FIRST_NAME = "firstName";
    private static final String WORK_ADDRESS = "workAddress";

    @Autowired
    @Qualifier("csvData")
    private CSVData csvData;

    @Override
    public final void process(final Exchange exchange) throws Exception {
        String payload = exchange.getIn().getBody(String.class);
        JSONObject json = new JSONObject(payload);
        String address = csvData.getAddress(json.get(FIRST_NAME),
                                            json.get(LAST_NAME));
        if (address != null) {
            json.put(WORK_ADDRESS, address);
        }
        exchange.getIn().setBody(json.toString());
    }
}
