package conf;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CamelRoutes extends RouteBuilder {

    public static final String RECEIVER_ROUTE = "RECEIVER_ROUTE";
    public static final String PROCESS_ROUTE = "PROCESS_ROUTE";
    public static final String MOVE_ROUTE = "MOVE_ROUTE";
    public static final String ENRICH_ROUTE = "ENRICH_ROUTE";

    @Value("${camelApp.startRoute}")
    private String startRoute;
    @Value("${camelApp.folders.prepareDir}")
    private String prepareDir;
    @Value("${camelApp.folders.workDir}")
    private String workDir;
    @Value("${camelApp.folders.dataDir}")
    private String dataDir;
    @Value("${camelApp.folders.invalidDir}")
    private String invalidDir;
    @Value("${camelApp.folders.doneDir}")
    private String doneDir;

    @Override
    public final void configure() throws Exception {

        from("direct:" + startRoute)
                .routeId(RECEIVER_ROUTE)
                .log("Received data ${body}")
                .to("file:" + prepareDir
                        + "?fileName=json_${date:now:ddmmyyyy_hhmm}.txt"
                        + "&fileExist=Move"
                        + "&moveExisting=${file:name.noext}_${date:now:ssSSS}"
                        + ".${file:ext}");

        from("file:" + prepareDir + "?delete=true")
                .routeId(MOVE_ROUTE)
                .to("file:" + workDir)
                .to("file:" + workDir
                        + "?doneFileName=${file:name.noext}.ready")
                .to("log:file moved");

        from("file:" + workDir + "?delete=true"
                    + "&doneFileName=${file:name.noext}.ready")
                .routeId(PROCESS_ROUTE)
                .doTry()
                    .to("json-validator:schems/abonent.json")
                    .log("Processed successfully.")
                    .to("file:" + dataDir)
                .endDoTry()
                .doCatch(Exception.class)
                    .log("Filed to process. File filed to validate.")
                    .to("file:" + invalidDir);

        from("file:" + dataDir + "?delete=true")
                .routeId(ENRICH_ROUTE)
                .process("payloadProcessor")
                .to("file:" + doneDir)
                .log("Processed data ${body}");
    }
}