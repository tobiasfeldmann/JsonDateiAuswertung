
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
public class Min {
        String output = "";
        String function = "min";
        String temp1 = "";

        String name = "";
        String[] field;
        String filter = "";

        String ergebnisTemp = "";
        double ergebnis = 0.0f;

    /**
     * gibt den Namen aus, der aus der operations Datei gelesen wurde
     * @return name, Name aus der Operationsdatei, wichtig für OutputDatei
     */
    public String getName(){
        return this.name;
    }
        
    /**
     * extrahiert die einzelnen Operatoren bspw. Filter und Name für die Funktion min
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
        Pattern pattern = Pattern.compile(filter);
        int index = 0;
        for(JsonNode node : dataNode){
            Matcher matcher = pattern.matcher(node.get("name").asText());
            boolean matchFound = matcher.find();
            if(matchFound) {
                if(field.length > 1){
                    JsonNode temp = dataNode.get(index).get(field[0]);
                    ergebnisTemp = temp.get(field[field.length - 1]).asText();

                }
                else{
                    ergebnisTemp = node.get(field[0]).asText();
                }
            } 
            index++;
            //Wertet aus, ob der neue Wert höher als der alte ist
            if(!ergebnisTemp.equals("")){
                if(Double.parseDouble(ergebnisTemp) < ergebnis){
                    ergebnis = Double.parseDouble(ergebnisTemp);
                    ergebnisTemp = "";
                }
                else if(ergebnis == 0){
                    ergebnis = Double.parseDouble(ergebnisTemp);
                    ergebnisTemp = "";
                }
            }
        }
        return Double.toString(ergebnis);
    }

}
