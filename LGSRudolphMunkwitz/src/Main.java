import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    //Übernimmt Dateipfad, lässt Daten auslesen aus Datei, übergibt diese zur Verarbeitung an Mathe
    //und Lässt daten wieder speichern
    public static void berechnung(String filename){

        Controller control = new Controller();
        double[][] input = control.getInput(filename);
        //System.out.println(Arrays.deepToString(input)); //Testausgabe für gesplittetes Array
        double[] loesung = Mathe.ablauf(input);
        System.out.println("Ergebnisse:");
        for(int i = 0; i < loesung.length; i++) {
            System.out.println("x" + (i + 1) + " = " + loesung[i]);
        }

        control.doOutput(loesung, filename);
    }

	public static void main(String[] args) {

        boolean erfolg = false;
        int eingabe2 = 0;

        System.out.println("Dateipfad für Eingabe auswaehlen:");
        System.out.println("1.Standarddateipfad (\"LGSRudolphMunkwitz/Eingabe.txt\")");
        System.out.println("2.Anderer Dateipfad \n");


        do {

            do {
                int eingabe1 = 0;

                System.out.println("Auswahl: ");
                Scanner auswahl = new Scanner(System.in);

                try {
                    eingabe1 = auswahl.nextInt();
                    erfolg = true;
                    eingabe2 = eingabe1;
                } catch (InputMismatchException e) {
                    System.out.println("Eingabefehler: Bitte eine ganze Zahl eingeben\n");
                }

            }while(erfolg == false);

            if (eingabe2 < 1 || eingabe2 > 2){
                System.out.println("Eingabefehler: Ungueltige Auswahl\n");
            }

        }while (eingabe2 < 1 || eingabe2 > 2);

        switch (eingabe2){

            case 1:
                String filename = new String("LGSRudolphMunkwitz/Eingabe.txt");
                berechnung(filename);
                return;

            case 2:
                System.out.println("Bitte Dateipfad eingeben: ");
                Scanner eingabe = new Scanner(System.in);
                filename = eingabe.nextLine();
                berechnung(filename);
                return;

            default:
                System.out.println("unerwarteter Fehler");
        }



        //Test der Lesefunktion
        /*
		Filemanager fm = new Filemanager();
        String filename = "C:/Users/Alexander/workspace/aop1pruefung/test.txt";  
          
        try  
        {  
            String[] lines = fm.readFromFile(filename);
          
            for (String line : lines)   
            {  
                System.out.println(line);  
            }  
        }  
        catch(IOException e)  
        {
            System.out.println("Unable to create "+filename+": "+e.getMessage());                
        }

        Controller gs = new Controller();
        double [][] ausgabe = gs.getInput();
        System.out.println(Arrays.deepToString(ausgabe));
        */
    }

}
