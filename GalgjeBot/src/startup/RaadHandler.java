/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startup;

import java.util.Scanner;
import woordbot.GalgjeWoordBot;

/**
 * Deze klasse houdt de status bij van het spel als de speler een woord moet raden.
 * Het ontvangt input van de speler, en stuurt de input door naar GalgjeWoordBot.
 * Op basis van de output van GalgjeWoordBot worden de acties van de speler bijgehouden/beoordeeld
 * @author Ian
 * 
 */
public class RaadHandler {
    private GalgjeWoordBot woordBot;
    private boolean finished = false;
    private String geradenLetters;
    private int fouten;
    private Scanner input = Galgje.input;
    

    public RaadHandler() {
        woordBot = new GalgjeWoordBot();
        woordBot.generateWoordOmTeRaden();
    }
    //wordt aangeroepen tijdens de start van het spel
    public boolean handleRaden(){
        this.geradenLetters = "";
        this.fouten = 0;
        while(!this.finished){
            System.out.println("Geraden letters: " + this.geradenLetters);
            System.out.println("Aantal fouten: " + this.fouten + "/10");
            if(this.fouten >= 10){
                System.out.println("Maximaal aantal fouten gemaakt. Je hebt verloren!");
                System.out.println("Het goede woord was: " + woordBot.getWoord());
                this.finished = true;
            }
            else {
                processGuess();
            }
        }
        return true;//return als het spel afgelopen is
    }
    //wat wil de speler raden?
    public void processGuess(){
        System.out.println("Wil je een letter raden, of het woord raden?");
        System.out.println("1: Letter raden.");
        System.out.println("2: Woord raden.");
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                processGuessLetter();
                break;
            case 2:
                processGuessWoord();
                break;
            default:
                System.out.println("Geen bijbehorende optie gevonden. Vul opnieuw in.");
                processGuess();
                break;
        }
    }
    //code voor het raden van een letter
    public void processGuessLetter(){
        System.out.println("Voer letter in");
        String letter = input.next();
        letter = letter.trim();
        if(letter.length() == 1){//het mag maar 1 letter zijn
            if (!geradenLetters.contains(letter)){//als de letter nog niet geraden is
                geradenLetters += letter;//voeg de letter bij de geraden letters
                if(woordBot.isLetterInWoord(letter.charAt(0))){
                    System.out.println("Letter zit in het woord: " + woordBot.plaatsLetterInWoord(letter.charAt(0)));
                    if(woordBot.checkAlleLettersGeraden()){
                        System.out.println("Alle letters zijn geraden! Jij wint!");
                        this.finished = true;
                    }
                }
                else{
                    System.out.println("Deze letter zit niet in het woord!");
                    System.out.println(woordBot.geradenWoord.toString());
                    this.fouten++;
                }
            }
            else{
                System.out.println("Deze letter is al een keer geraden");
                System.out.println(woordBot.geradenWoord.toString());
                processGuessLetter();
            }
        }
        else{
            System.out.println("Maar 1 letter tegelijkertijd!");
            processGuessLetter();
        }
    }
    //methode voor het raden van een woord
    public void processGuessWoord(){
        System.out.println("Wat denk je dat het woord is?");
        String gokWoord = input.next();
        if(woordBot.raadWoord(gokWoord)){
            System.out.println("Het was inderdaad '" + gokWoord + "'! Jij wint!");
            this.finished = true;
        }
        else{
            System.out.println("Sorry, dat was niet het woord.");
            System.out.println(woordBot.geradenWoord.toString());
            this.fouten++;
        }
    }
}
