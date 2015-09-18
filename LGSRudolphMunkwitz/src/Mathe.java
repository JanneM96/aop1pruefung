/**
 * Created by Janne on 18.09.2015.
 */
import javax.print.DocFlavor;
import java.io.*;
import java.util.Arrays;
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


    public static void dreiecksform(double[][] matrix) {

        if ( Mathe.testAufDreiecksform(matrix) == false ) {
            System.out.print("1");
            Mathe.dreiecksformErstellen(matrix);
        }
            double[] loesung = Mathe.dreiecksformAufloesen(matrix);
            System.out.println(Arrays.toString(loesung));
    }


    private static double[] dreiecksformAufloesen(double[][] matrix) { //FALSCH

        int a,b;
        double[] speicher = new double[matrix.length];
        for(a=matrix.length-1; a>0; a--){//index speicher ABER für xa unbedingt a+1 benutzen
            double summe = matrix[a][matrix.length];
            for(b=matrix.length-1; b<a; b++){
                summe = summe - ( matrix[a][b] * speicher[b+1]); // -= ist wie sum =sum -
            }
            speicher[a] =(summe/matrix[a][a]);
            summe = 0;
        }
        return speicher;
    }

    private static void dreiecksformErstellen(double[][] matrix) {
        System.out.print("2");
    }

    private static boolean testAufDreiecksform(double[][] matrix) {

        int a,b,c;
        for (a=0;a < matrix.length ; a++) {
            if (matrix[a][a] != 0) {
                if (a == matrix.length -1) {
                    System.out.println("win");
                    //wenn alle diagonalelemente ungleich null sind
                    //dann prüfen wir das alles links davon 0 sind
                    for (b = 1; b < matrix.length; b++) {//0te / 1.te zeile egal, weil diagonalelem ganz links
                        for ( c = b-1; c >= 0; c--) {
                            if (matrix[b][c] != 0)
                            {   System.out.println("ausgabe b:" + b + "und c: " + c);
                                return false;
                            }
                        }
                    }
                    System.out.println("win");
                    return true; //dreiecksform existiert schon
                }
            }else {
                return false;//ein diaelem ist null
            }


        }
        //eins der diaelem ist eine null
        return false;
    }

}