/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raadbot;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author Ian
 */
public class GalgjeRaadBot {
    private static File woordenlijst;
    private static final String ALFABET = "abcdefghijklmnopqrstuvwxyz";
    private StringBuffer woord;
    private String geradenLetters = "";
    private int fouten;
    private List<String> alGeradenWoorden;
    private double[] letterChances = {7.49, 1.58, 1.24, 5.93, 18.91, 0.81, 3.40, 2.38, 6.50, 1.46, 2.25, 3.57, 2.21, 10.03, 6.06, 1.57, 0.01, 6.41, 3.73, 6.79, 1.99, 2.85, 1.52, 0.04, 0.034, 1.39};

    public GalgjeRaadBot() {
        woordenlijst = new File("woordenlijst.txt");
        woord = new StringBuffer();
        fouten = 0;
        alGeradenWoorden = new ArrayList<>();
    }
    
    
     //WIP
    String raadWoord(String woord){
        try(Scanner inputWoorden = new Scanner(woordenlijst)){
            String patternString = "\\b" + woord.toLowerCase().replaceAll("-", ".") + "\\b";
            Pattern pattern = Pattern.compile(patternString);
            //System.out.println("Pattern: " + pattern.pattern());
            String gevondenWoord = "";
            boolean finished = false;
            while(inputWoorden.hasNext() && !finished){
                if(inputWoorden.hasNext(pattern)){
                    String currentWoord = inputWoorden.next();
                    if(!alGeradenWoorden.contains(currentWoord)){
                        gevondenWoord = currentWoord;
                        alGeradenWoorden.add(currentWoord);
                        finished = true;
                    }
                }
                inputWoorden.nextLine();
            }
            return gevondenWoord;
        }
        catch (FileNotFoundException ex) {
            System.err.println("Kon bestand niet vinden: " + ex.getMessage());
        }
        return "";
    }
    
    public void initializeWoord(int lengte){
        for(int i = 0; i < lengte; i++){
            woord.append("-");
        }
    }
    
    public boolean handleRaden(String teRadenWoord){
        while(!isGeraden(teRadenWoord) && fouten<10){
            System.out.println(woord.toString());
            char[] lettersInWoord = woord.toString().toCharArray();
            int amountOfBlanks = 0;
            for(char c : lettersInWoord){
                if(c=='-'){
                    amountOfBlanks++;
                }
            }
            if(amountOfBlanks==1 || (amountOfBlanks==2 && woord.length()>6)){
                handleRaadWoord(teRadenWoord);
            }
            else {
                handleRaadLetter(teRadenWoord);
            }
        }
        if(fouten >= 10){
            return false;
        }
        else{
            return true;
        }
    }
    
    private void handleRaadWoord(String teRadenWoord){
        String geradenWoord = raadWoord(woord.toString());
        if(geradenWoord.isEmpty()){
            handleRaadLetter(teRadenWoord);
        }
        else{
            System.out.println("Is het woord '" + geradenWoord + "'?");
            if(geradenWoord.equalsIgnoreCase(teRadenWoord)){
                woord = new StringBuffer(geradenWoord);
            }
            else{
                fouten++;
                System.out.println("'" + geradenWoord + "' was niet het juiste woord.");
            }
        }
    }
    
    private void handleRaadLetter(String teRadenWoord){
        char randomLetter = genereerLetter();
        String letter = "" + randomLetter;
        while(geradenLetters.contains(letter)){
            randomLetter = genereerLetter();
            letter = "" + randomLetter;
        }
        geradenLetters += letter;
        if(zitLetterInWoord(randomLetter, teRadenWoord)){
            System.out.println("Letter '" + randomLetter + "' zit in het woord!");
            plaatsLetterInWoord(randomLetter, teRadenWoord);
        }
        else{
            System.out.println("Letter '" + randomLetter + "' zit niet in het woord!");
            fouten++;
        }
    }
    
    private char genereerLetter(){
        double percentage = Math.random() * 100;
        //System.out.println("Percentile: " + percentage);
        double letterChance = 0.00;
        char letter = 0;
        for (int i = 0; i < letterChances.length; i++){
            double max = letterChance + letterChances[i];
            //System.out.println("Character '" + ALFABET.charAt(i) + "' is between " + letterChance + " and " + max);
            if(percentage >= letterChance && percentage <= max){
                letter = ALFABET.charAt(i);
                break;
            }
            letterChance += letterChances[i];
        }
        return letter;
    }
    //Depecrated
    private char raadLetter(){
        int charIndex = (int)(Math.random() * 26) - 1;
        char[] letters = ALFABET.toCharArray();
        return letters[charIndex];
    }
    
    private boolean zitLetterInWoord(char randomLetter, String teRadenWoord){
        String letter = "" + randomLetter;
        return teRadenWoord.contains(letter);
    }
    
    private void plaatsLetterInWoord(char letter, String teRadenWoord){
        char[] lettersInWoord = teRadenWoord.toCharArray();
        for(int i = 0; i < teRadenWoord.length(); i++){
            if(lettersInWoord[i] == letter){
                String letterToString = "" + letter;
                woord.replace(i, i+1, letterToString);
            }
        }
    }
    
    private boolean isGeraden(String teRadenWoord){
        return woord.toString().equalsIgnoreCase(teRadenWoord);
    }
    
}
