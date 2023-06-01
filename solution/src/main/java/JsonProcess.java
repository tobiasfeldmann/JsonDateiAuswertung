import java.io.File;
import java.io.IOException;
import java.util.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class JsonProcess {
    public static void calculate(String dataFile, String operationsFile, String outputFile) throws IOException{

        ObjectMapper mapper = new ObjectMapper();
        File file = new File(dataFile);
        //JsonNode mit Entries als Wurzel
        JsonNode data = mapper.readTree(file);
        //ArrayNode der einzelnen Datenpunkte bzw. der Entries untergeordneteten Datenpunkte
        ArrayNode arrayNodeData = (ArrayNode) data.get("entries");

        File fileOperations = new File(operationsFile);
        //Node mit Operations als Wurzel
        JsonNode operations = mapper.readTree(fileOperations);
        //ArrayNode mit den einzelnen Datenpunkten die Operations untergeordnet sind, hier lassen sich von jedem Node die jeweiligen field auslesen
        ArrayNode arrayNodeOperations = (ArrayNode) operations.get("operations");

        /**
         * für die Instanz min der Klasse min werden die Methoden aufgerufen um die Operatoren zu setzen
         * sowie das letztliche ergebnis auszugeben.
         * Das Ergebnis der methode min.operate wird dann gerundet und anschließend in eine Instanz der Klasse JsonObjekt gewandelt
         */
        Min min = new Min();
        min.setOperationVariables(arrayNodeOperations);
        String ergebnisMin = min.operate(arrayNodeData);
        String nameMin = min.getName();
        String gerundetesErgebnisMin = ErgebnisRunden.rundeErgebnis(ergebnisMin);
        JsonObjekt objektMin = new JsonObjekt(nameMin, gerundetesErgebnisMin);

        /**
         * für die Instanz max der Klasse Max werden die Methoden aufgerufen um die Operatoren zu setzen
         * sowie das letztliche ergebnis auszugeben.
         * Das Ergebnis der methode max.operate wird dann gerundet und anschließend in eine Instanz der Klasse JsonObjekt gewandelt
         */
        Max max = new Max();
        max.setOperationVariables(arrayNodeOperations);
        String ergebnisMax = max.operate(arrayNodeData);
        String nameMax = max.getName();
        String gerundetesErgebnisMax = ErgebnisRunden.rundeErgebnis(ergebnisMax);
        JsonObjekt objektMax = new JsonObjekt(nameMax, gerundetesErgebnisMax);

        /**
         * für die Instanz sum der Klasse Sum werden die Methoden aufgerufen um die Operatoren zu setzen
         * sowie das letztliche ergebnis auszugeben.
         * Das Ergebnis der methode sum.operate wird dann gerundet und anschließend in eine Instanz der Klasse JsonObjekt gewandelt
         */
        Sum sum = new Sum();
        sum.setOperationVariables(arrayNodeOperations);
        String ergebnisSum = sum.operate(arrayNodeData);
        String nameSum = sum.getName();
        String gerundetesErgebnisSum = ErgebnisRunden.rundeErgebnis(ergebnisSum);
        JsonObjekt objektSum = new JsonObjekt(nameSum, gerundetesErgebnisSum);

        /**
         * für die Instanz average der Klasse Average werden die Methoden aufgerufen um die Operatoren zu setzen
         * sowie das letztliche ergebnis auszugeben.
         * Das Ergebnis der methode average.operate wird dann gerundet und anschließend in eine Instanz der Klasse JsonObjekt gewandelt
         */
        Average average = new Average();
        average.setOperationVariables(arrayNodeOperations);
        String ergebnisAverage = average.operate(arrayNodeData);
        String nameAverage = average.getName();
        String gerundetesErgebnisAverage = ErgebnisRunden.rundeErgebnis(ergebnisAverage);
        JsonObjekt objektAverage = new JsonObjekt(nameAverage, gerundetesErgebnisAverage);
        

        /**
         * die Werte der einzelnen JsonObjekte werden in eine JsonNode umgewandelt
         */
        JsonNode outputNodeMin = mapper.valueToTree(objektMin);
        JsonNode outputNodeMax = mapper.valueToTree(objektMax);
        JsonNode outputNodeSum = mapper.valueToTree(objektSum);
        JsonNode outputNodeAverage = mapper.valueToTree(objektAverage);

        /**
         * Zusammenfassung der JsonNodes
         */
        List<JsonNode> list = new ArrayList<JsonNode>();
        list.add(outputNodeAverage);
        list.add(outputNodeSum);
        list.add(outputNodeMin);
        list.add(outputNodeMax);

        /**
         * Erzeugung eines ArrayNodes zur Erstellung der OutputDatei
         */
        ArrayNode outputArrayNode = new ArrayNode(null, list);
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(outputFile), outputArrayNode);
    }
}
