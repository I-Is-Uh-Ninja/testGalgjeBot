package startup;

import java.util.Scanner;
import raadbot.GalgjeRaadBot;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Main klasse waarin gekozen kan worden tussen de twee speelopties: een woord raden, of de bot een woord laten raden
 * @author Ian
 */
public class Galgje {
    public static Scanner input = new Scanner(System.in);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Galgje gestart!");
        choosePlayingOption();
    }
    //kies een spel-optie
    private static void choosePlayingOption(){
        System.out.println("Wil je het woord raden, of wil je een woord laten raden?");
        System.out.println("1: Woord raden.");
        System.out.println("2: Woord laten raden.");
        System.out.println("3: Stoppen.");
        System.out.println("Vul het getal in van de gewenste optie");
        int mode = input.nextInt();
        switch(mode){
            case 1:
                startPlayerGuessing();
                break;
            case 2:
                startBotGuessing();
                break;
            case 3:
                System.out.println("Tot ziens!");
                break;
            default:
                System.out.println("Geen bijbehorende optie gevonden. Vul opnieuw in.");
                choosePlayingOption();
        }
    }
    //start spel-optie 1
    private static void startPlayerGuessing(){
        RaadHandler raadHandler = new RaadHandler();
        if(raadHandler.handleRaden()){//deze wordt gereturned als true wanneer het spel afgelopen is
            choosePlayingOption(); //kies opnieuw de spel-optie
        }
    }
    //start spel-optie 2
    private static void startBotGuessing(){
        System.out.println("Welk woord wil je laten raden?");
        String teRadenWoord = input.next();
        if(teRadenWoord.length() > 2 && teRadenWoord.length() <= 8){
            GalgjeRaadBot galgjeRaadBot = new GalgjeRaadBot();
            galgjeRaadBot.initializeWoord(teRadenWoord.length());
            if(galgjeRaadBot.handleRaden(teRadenWoord)){
                System.out.println("De bot heeft het woord geraden!");
            }
            else{
                System.out.println("De bot heeft het woord niet kunnen raden. Het juiste woord was '" + teRadenWoord + "'.");
            }
            choosePlayingOption();
        }
        else{
            System.out.println("Woord moet minstens 3 en maximaal 8 letters lang zijn!");
            startBotGuessing();
        }
    }
    
}
