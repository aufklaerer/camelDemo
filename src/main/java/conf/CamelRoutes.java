package conf;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CamelRoutes extends RouteBuilder {

    public static final String RECEIVER_ROUTE = "RECEIVER_ROUTE";
    public static final String PROCESS_ROUTE = "PROCESS_ROUTE";
    public static final String MOVE_ROUTE = "MOVE_ROUTE";
    public static final String ENRICH_ROUTE = "ENRICH_ROUTE";

    @Override
    public void configure() throws Exception {

        from("direct:receiver")
                .routeId(RECEIVER_ROUTE)
                .log("Received data ${body}")
                .to("file:prepare?fileName=json_${date:now:ddmmyyyy_hhmm}.txt" +
                        "&fileExist=Move" +
                        "&moveExisting=${file:name.noext}_${date:now:ssSSS}.${file:ext}");

        from("file:prepare?delete=true&delay=5000")
                .routeId(MOVE_ROUTE)
                .to("file:work")
                .to("file:work?doneFileName=${file:name.noext}.ready")
                .to("log:file moved");

        from("file:work?delete=true" +
                    "&doneFileName=${file:name.noext}.ready&delay=5000")
                .routeId(PROCESS_ROUTE)
                .doTry()
                    .to("json-validator:schems/abonent.json")
                    .log("Processed successfully.")
                    .to("file:data")
                .endDoTry()
                .doCatch(Exception.class)
                    .log("Filed to process. File filed to validate.")
                    .to("file:invalid");

        from ("file:data?delay=5000")
                .routeId(ENRICH_ROUTE)
                .process("payloadProcessor");

    }
}