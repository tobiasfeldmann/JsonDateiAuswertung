public class ErgebnisRunden {
    /**
     * Diese Methode soll das Ergebnis runden und die Nachkommastellen auf genau zwei einstellen, basierend auf der letzten Zahl
     * @param ergebnis, der String mit dem Ergebnis aus den Klassen Min,Max,Sum oder Average
     * @return ergebnis, als gerundetes Ergebnis
     */
    public static String rundeErgebnis(String ergebnis){

        int indexPunkt = ergebnis.indexOf(".");

        if(indexPunkt == ergebnis.length() - 3){
            return ergebnis;
        }
        else if(indexPunkt == ergebnis.length() - 2){
            ergebnis = ergebnis + "0";
            return ergebnis;
        }
        else{
            int letzteZahl = Integer.parseInt(Character.toString(ergebnis.charAt(ergebnis.length() - 1)));
            int zahlAnZweiterNachkommaStelle = Integer.parseInt(Character.toString(ergebnis.charAt(indexPunkt + 2)));
            if(letzteZahl < 5){
                ergebnis = ergebnis.substring(0, indexPunkt + 3);
            }
            else{
                zahlAnZweiterNachkommaStelle++;
                ergebnis = ergebnis.substring(0, indexPunkt + 2) + zahlAnZweiterNachkommaStelle;
                return ergebnis;
            }
        }
        return ergebnis;
    }
}
