package enrich;

import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;

@Component
@Qualifier("csvData")
class CSVData {

    private HashMap<String, String> abonents = new HashMap<String, String>();

    CSVData() throws Exception {
        File file = new File("abonents.csv");
        if (file.exists()) {
            Reader reader = new FileReader(file);
            List<String[]> list = readAll(reader);
            list.forEach(element -> {
                abonents.put(getKey(element[0], element[1]), element[2]);
            });
        }
    }

    private List<String[]> readAll(final Reader reader) throws Exception {
        CSVReader csvReader = new CSVReader(reader);
        List<String[]> list = csvReader.readAll();
        reader.close();
        csvReader.close();
        return list;
    }

    private String getKey(final String firstName, final String lastName) {
        return firstName + ":" + lastName;
    }

    String getAddress(final Object firstName, final Object lastName) {
        try {
            return abonents.getOrDefault(getKey((String) firstName,
                    (String) lastName), null);
        } catch (Exception e) {
            return null;
        }
    }

}
