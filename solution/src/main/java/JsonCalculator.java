import java.io.IOException;
import com.fasterxml.jackson.core.JsonProcessingException;


public class JsonCalculator {

    public static void main(String[] args) throws JsonProcessingException, IOException {
        //Don't change this part
        if (args.length == 3) {
            //Path to the data file, e.g. data/data.xml
            final String DATA_FILE = args[0];
            //Path to the data file, e.g. operations/operations.xml
            final String OPERATIONS_FILE = args[1];
            //Path to the output file
            final String OUTPUT_FILE = args[2];

            //Aufruf der Methode zur Verarbeitung der Dateien
            JsonProcess.calculate(DATA_FILE, OPERATIONS_FILE,OUTPUT_FILE);
        } else {
            System.exit(1);
        }
    }
}
