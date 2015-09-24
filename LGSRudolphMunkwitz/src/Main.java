import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {

        System.out.println("Dateipfad für Eingabe auswählen:");
        System.out.println("1.Standarddateipfad (\"LGSRudolphMunkwitz/Eingabe.txt\")");
        System.out.println("2.Anderer Pfad \n");

        boolean erfolg = false;
        int eingabe2 = 1;
        /*do {
            do {
                System.out.println("Auswahl: ");
                Scanner auswahl = new Scanner(System.in);
                int eingabe1 = 0;
                try {
                    eingabe1 = auswahl.nextInt();
                    erfolg = true;
                    eingabe2 = eingabe1;
                } catch (InputMismatchException e) {
                    System.out.println("Eingabefehler: Bitte eine ganze Zahl eingeben\n");
                }
            }while(erfolg == false);
            if (eingabe2 < 1 || eingabe2 > 2){
                System.out.println("Eingabefehler: Ungültige Auswahl\n");
            }
        }while (eingabe2 < 1 || eingabe2 > 2);
        */
        switch (eingabe2){
            case 1:
                Controller control = new Controller();

                double [][] input = control.getInput("LGSRudolphMunkwitz/Eingabe.txt");
                System.out.println(Arrays.deepToString(input));

                double[] loesung = Mathe.ablauf(input);
                System.out.println(Arrays.toString(loesung));

                return;
            case 2:

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
