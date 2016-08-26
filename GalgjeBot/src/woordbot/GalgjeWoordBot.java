/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package woordbot;


/**
 * Dit is de klasse die het woord wat de speler invult bijhoudt, en vergelijkt met het gegenereerde woord
 * @author Ian
 */
public class GalgjeWoordBot {
    private final WoordlijstProcessor woordlijstProcessor;
    private String woord;
    public StringBuffer geradenWoord;

    public GalgjeWoordBot() {
        woordlijstProcessor = new WoordlijstProcessor();
        geradenWoord = new StringBuffer();
    }
    //methode om het woord om te raden te generaten, en het geradenWoord aan te maken
    public void generateWoordOmTeRaden(){
        setWoord(woordlijstProcessor.kiesRandomWoord());//kies een random woord uit de woordenlijst
        System.out.println("Woord is gekozen! Grootte: " + woord.length() + " letters");
        for (int i = 0; i < woord.length(); i++){
            geradenWoord.append("-");//zet placeholders neer in het woord die de speler moet invullen
        }
    }
    //kijk of de letter in het woord zit
    public boolean isLetterInWoord(char letter){
        String letterNaarString = "" + letter;
        int index = woord.indexOf(letterNaarString);
        return index > -1;
    }
    //zet de letter op alle plaatsen in het woord waar het voorkomt
    public String plaatsLetterInWoord(char letter){
        char[] lettersInWoord = woord.toCharArray();
        for(int c = 0; c < lettersInWoord.length; c++){
            if (lettersInWoord[c] == letter){
                String letterString = "" + letter;
                geradenWoord.replace(c, c+1, letterString);//vervang de placeholder door de letter
            }
        }
        return geradenWoord.toString();
    }
    //als alle letters geraden zijn is het spel afgelopen en heeft de speler gewonnen
    public boolean checkAlleLettersGeraden(){
        return raadWoord(geradenWoord.toString());
    }
    //functie om het woord te raden
    public boolean raadWoord(String raadWoord){
        return raadWoord.equalsIgnoreCase(woord);
    }
    //getters en setters voor woord
    public String getWoord() {
        return woord;
    }

    public void setWoord(String woord) {
        this.woord = woord;
    }
    
}
