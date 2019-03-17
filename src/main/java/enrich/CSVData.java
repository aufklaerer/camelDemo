package enrich;

import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Files;
import java.util.List;

@Component
@Qualifier("csvData")
public class CSVData {
    public CSVData() throws Exception {
        Reader reader = new FileReader("csv/abonents.csv");
        System.out.print(readAll(reader).toString());
    }

    private List<String[]> readAll(Reader reader) throws Exception {
        CSVReader csvReader = new CSVReader(reader);
        List<String[]> list = csvReader.readAll();
        reader.close();
        csvReader.close();
        return list;
    }
}
