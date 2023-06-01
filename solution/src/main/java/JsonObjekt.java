public class JsonObjekt {
   String name;
   String roundedValue;

    //Konstruktur um den Attributen direkt Werte zuzuweisen
    public JsonObjekt(String name, String rv){
        this.name = name;
        this.roundedValue = rv;
    }


    //Getter- und Setter-Methoden für die einzelnen Attribute
    public String getName(){
        return this.name;
    }
    
    public void setName(String name){
        this.name = name;
    }

   public String getRoundedValue(){
    return this.roundedValue;
   }

   public void setRoundedValue(String rv){
    this.roundedValue = rv;
   }
}
