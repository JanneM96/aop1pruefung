import java.io.IOException;

/**
 * Created by Alexander on 17.09.2015.
 */
public class Gaussgeschuetz {

    public String[] getInput() throws IOException{
        Filemanager fm = new Filemanager();
        String filename = "C:/Users/Alexander/workspace/aop1pruefung/test.txt";
        String[] lines = fm.readLines(filename);
        return lines;
    }

    public double[][] split(String[] input){
        double [][] matrix;
        matrix = null;

        Mathe.ablauf(matrix);//Ablaufplan der Mathe wird einmal aufgerufen
        return matrix;
    }
}
//Lieber Rudi, ich speichere die errechneten Daten für x1, x2, x3... in einer matrix namens speicher[],
//speicher[0] ist x1, speicher[1] ist x2, .... du machst auch die ausgabe in die datei?