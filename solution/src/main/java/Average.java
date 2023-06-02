
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
public class Average {
    String output = "";
    String function = "average";

    int index = -1;
    String temp1 = "";

    String name = "";
    String[] field;
    String filter = "";

    String ergebnisTemp = "";
    Double ergebnis = 0.0;

    /**
     * gibt den Namen aus, der aus der operations Datei gelesen wurde
     * @return name, Name aus der Operationsdatei, wichtig für OutputDatei
     */
    public String getName(){
        return this.name;
    }


    /**
     * extrahiert die einzelnen Operatoren bspw. Filter und Name für die Funktion average
     * @param arrayNode, ain Array aus JsonNodes mit den Daten der Datei operations.json
     */
    public void setOperationVariables(ArrayNode arrayNode){
        for(JsonNode node : arrayNode){
            if(node.get("function").asText().equals(function)){
                name = node.get("name").asText();
                filter = node.get("filter").asText();

                int indexFieldArray = 0;
                field = new String[node.get("field").size()];
                ArrayNode fieldNode = (ArrayNode) node.get("field");
                for(JsonNode nodeX: fieldNode){
                    field[indexFieldArray] = nodeX.asText();
                    indexFieldArray++;
                }   
            }
        }
    }

    /**
     * filter die Daten aus der datei data.json entsprechend dem oben ausgewählten filter
     * @param dataNode, ArrayNode aus der data.json
     * @return ergebnis String aus den jeweiligen Berechnungen
     */
    public String operate(ArrayNode dataNode){
        int counter = 0;
        Pattern pattern = Pattern.compile(filter);
        int index = 0;
        for(JsonNode node : dataNode){
            Matcher matcher = pattern.matcher(node.get("name").asText());
            boolean matchFound = matcher.find();
            if(matchFound) {
                counter++;
                if(field.length > 1){
                    JsonNode temp = dataNode.get(index).get(field[0]);
                    ergebnisTemp = temp.get(field[field.length - 1]).asText();

                }
                else{
                    ergebnisTemp = node.get(field[0]).asText();
                }
                if(!ergebnisTemp.equals("")){
                    if(ergebnis == 0){
                        ergebnis = Double.parseDouble(ergebnisTemp);
                        ergebnisTemp = "";
                    }
                    else{
                        ergebnis = ergebnis + Double.parseDouble(ergebnisTemp);
                        ergebnisTemp = "";
                    }
                }
            } 
            index++;
        }

        //Bildung des Durschschnitts
        ergebnis = ergebnis / counter;
        return Double.toString(ergebnis);
    }
}
