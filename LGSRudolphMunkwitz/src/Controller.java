import java.io.FileNotFoundException;
import java.io.IOException;

public class Controller {

    //Lässt Dateimanager File einlesen und lässt String array in Methode split auseinandernehmen
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

    //Zerlegt String Array in zahlen und speichert diese in form einer matrix
    private static double[][] split(String[] input)
    {
        int linesLength = input.length;
        double [][] matrix = new double[linesLength][linesLength + 1];

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
        return matrix;
    }

    //Wandelt Ergebnis Array(double) in String um und lässt den Filemanager die Datei damit beschreiben
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
