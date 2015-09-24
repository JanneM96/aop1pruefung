import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Alexander on 17.09.2015.
 */

public class Controller {

    public double[][] getInput(String filename)
    {
        Filemanager fm = new Filemanager();
        String[] lines = null;
        lines = fm.readFromFile(filename);
        System.out.println("Eingegebenes LGS:");
        for (String line : lines)
        {
            System.out.println(line);
        }
        double[][] splitted = Controller.split(lines);
        return splitted;
    }

    private static double[][] split(String[] input)
    {
        int linesLength = input.length;
        int zeile = 0;
        double [][] matrix = new double[linesLength][linesLength + 1];
        double [][] rechenmatrix;
        rechenmatrix = null;
        double [] vector;
        vector = null;

        for (int i = 0; i < linesLength; i++)
        {
            String [] splitted = input[i].split(";");
            int spalten = splitted.length;
            try {
                for (int j = 0; j < spalten; j++)
                {
                    matrix [i][j] = Double.parseDouble(splitted[j]);
                }
            }catch (NumberFormatException e){
                System.out.println("Fehler beim Lesen der Datei: "+ e.getMessage());
                System.exit(1);
            }

        }
       // Mathe.ablauf(matrix, rechenmatrix, vector);//Ablaufplan der Mathe wird einmal aufgerufen
        return matrix;
    }

    public void doOutput(double[] dErgebnis, String filename)
    {
        String [] umgewandelt = new String[dErgebnis.length];
        for (int i = 0; i < dErgebnis.length; i++)
        {
            umgewandelt[i] = String.valueOf(dErgebnis[i]);
        }
        Filemanager fm = new Filemanager();
        fm.writeToFile(umgewandelt, filename);
    }

}
//Lieber Rudi, ich speichere die errechneten Daten für x1, x2, x3... in einer matrix namens speicher[],
//speicher[0] ist x1, speicher[1] ist x2, .... du machst auch die ausgabe in die datei?