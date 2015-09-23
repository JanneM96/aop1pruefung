import java.io.IOException;

/**
 * Created by Alexander on 17.09.2015.
 */

public class Gaussgeschuetz {

    public double[][] getInput() throws IOException{
        Filemanager fm = new Filemanager();
        String filename = "LGSRudolphMunkwitz/Eingabe.txt";
        String[] lines = fm.readFromFile(filename);
        double[][] splitted = Gaussgeschuetz.split(lines);
        return splitted;
    }

    public static double[][] split(String[] input){
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
            for (int j = 0; j < spalten; j++)
            {
                matrix [i][j] = Double.parseDouble(splitted[i]);
            }

        }


        //Mathe.ablauf(matrix, rechenmatrix, vector);//Ablaufplan der Mathe wird einmal aufgerufen
        return matrix;
    }
}
//Lieber Rudi, ich speichere die errechneten Daten für x1, x2, x3... in einer matrix namens speicher[],
//speicher[0] ist x1, speicher[1] ist x2, .... du machst auch die ausgabe in die datei?