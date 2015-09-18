/**
 * Created by Janne on 18.09.2015.
 */
import javax.print.DocFlavor;
import java.io.*;
public class Mathe {


    /*
    * Diese Funktion fängt die formale Fehler ab
    * @param matrix[][]
    * @return null
    * error abfangen
    */
    public static void fehlerabfangen(double[][] matrix) {
        int m, n;
        m = matrix.length; //anzahl Zeilen
        n = matrix[0].length - 1; //anzahl Spalten ohne ergebnis


        //ist das LGS quadratisch?
        if (!(m == n)) {
            Mathe.abbruch("LGS ist nicht quadratisch, Fehler");
        }

        //ist das LGS eindeutig lösbar
        //laufvariablen
        int a = 0;//zeilen durchzählen
        int b = 0;//spalten durchzählen

        //ist jedes elem der zeile=0? ja? letztes elem =0 => Trapezform .. != 0 unlösbar
        for (a = 0; a <= m - 1; a++) {

            for (b = 0; b <= n - 1; b++) {//ende ohne das ergebniss n+1 betrachtet zu haben
                if (matrix[a][b] != 0) {
                    break;
                }

                //wenn wir beim letzten element sind
                //also alle € einer zeiler null sind dann betrachtet wir das letzte element
                if ( b == n-1) {
                    if (matrix[a][n] == 0 ) {
                        //ergebnis 0 => zeile weg => trapezform =>mehrdeutig => error
                        Mathe.abbruch("Trapezform, mehrdeutig lösbar, Fehler");

                    }
                    else {
                       Mathe.abbruch("Unlösbar, Fehler");
                    }
                }


            }

        }
    }

    public static void abbruch(String msg)
    {
        System.out.println(msg);
        System.exit(0);
    }
}