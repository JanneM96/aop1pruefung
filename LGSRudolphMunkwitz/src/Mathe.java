/**
 * Created by Janne on 18.09.2015.
 */
import javax.print.DocFlavor;
import java.io.*;
import java.util.Arrays;
public class Mathe {

    /*
    * Diese Funktion erfüllt die Funktion einer Art kleiner Main/Ablaufplan,
    * damit die Matrix nur einmal übergeben werden muss, und der Programmablauf
    * ungestört und in der richtigen Reihenfolge bleibt
    */
    public static void ablauf(double[][] matrix){
        Mathe.fehlerabfangen(matrix);
        Mathe.dreiecksform(matrix);
    }

    /*
    * Diese Funktion fängt die formalen Fehler ab
    */
    public static void fehlerabfangen(double[][] matrix){
        int m, n;
        m = matrix.length; //Anzahl Zeilen
        n = matrix[0].length-1; //Anzahl Spalten ohne Ergebnisspalte
        //ist das LGS quadratisch?
        if(!(m == n)){
            Mathe.abbruch("LGS ist nicht quadratisch, Fehler");
        }
        //ist das LGS eindeutig lösbar?
        int a = 0;//Laufvariable Zeilen durchzählen
        int b = 0;//Laufvariable Spalten durchzählen
        //ist jedes elem einer zeile=0? + wenn letztes elem =0 => Trapezform .. != 0 unlösbar
        for(a=0; a<=m-1; a++){
            for(b=0; b<=n-1; b++){//Dadurch ist Ergebnis in letzter Spalte noch nicht betrachtet
                if(matrix[a][b] != 0){
                    break;
                }
                //wir betrachten jetzt das letzte Element, wenn alle Elemente einer Zeile =0 sind
                if(b == n-1){
                    if(matrix[a][n] == 0){
                        //Wenn das Ergebnis 0 ist, befindet sich die Matrix in einer Trapezform
                        Mathe.abbruch("Trapezform, mehrdeutig lösbar, Fehler");
                    }else{
                        Mathe.abbruch("Unlösbar, Fehler");
                    }
                }
            }
        }
    }

    /*
    * Diese Funktion erleichtert die Ausgabe von Fehlermeldungen
    */
    public static void abbruch(String msg)
    {
        System.out.println(msg);
        System.exit(0);
    }

    /*
    * Diese Funktion sorgt dafür, dass die Matrix in Dreiecksform umgewandelt wird
    * Sie ruft dazu weitere Funktionen auf:
    *   Mathe.testAufDreiecksform(matrix)
    *   Mathe.dreiecksformErstellen(matrix)
    *   Mathe.dreiecksformAufloesen(matrix)
    */
    public static void dreiecksform(double[][] matrix){
        if(Mathe.testAufDreiecksform(matrix) == false){
            Mathe.dreiecksformErstellen(matrix);
        }
        double[] loesung = Mathe.dreiecksformAufloesen(matrix);
        System.out.println(Arrays.toString(loesung)); //vorläufige Ausgabe der Testwerte
    }

    /*
    * Diese Funktion löst eine Matrix, die sich in Dreiecksform befindet
    * Sie wird auch in Mathe.dreiecksform aufgerufen
    */
    private static double[] dreiecksformAufloesen(double[][] matrix){
        int a,b;
        double[] speicher = new double[matrix.length];
        for(a = matrix.length-1; a>=0; a--){//a ist der Index im speicher ABER für xa (beim Lösen) unbedingt a+1 benutzen
            double summe = matrix[a][matrix.length];//summe = Index der Ergebnisspalte
            for (b = a; b < matrix.length - 1; b++){
                summe = summe - (matrix[a][b+1] * speicher[b+1]);
            }
            speicher[a] = (summe / matrix[a][a]);
            summe = 0;
        }
        return speicher;
    }

    /*
    * Diese Funktion bildet aus einer beliebigen quadratischen Matrix die Dreiecksform
    */
    private static void dreiecksformErstellen(double[][] matrix){//#TODO Test
        int a, b, c, d;
        a = b = c = d = 1;
        if(matrix[a][b] == 0){//erstes Diagonalelement =0?
            for(c = 1; c < matrix.length; c++){//c sorgt dafür, dass wir nicht nur Zeilen nacheinander vertauschen, sondern a mit jeder nachfolgenden
                for (int k = 0; k < matrix.length; k++) {//einfacher Metrizenzeilentausch
                    double tmp = matrix[a][k];
                    matrix[a][k] = matrix[a+c][k];
                    matrix[a+c][k] = tmp;
                }
            }
            if(c == matrix.length){
                Mathe.abbruch("Keine Dreiecksform erzeugbar, Fehler");
            }
        }else{
            //#TODO
        }
    }

    /*
    * Diese Funktion prüft die Matrix auf Dreiecksform
    * return false : Matrix ist nicht in Dreiecksform
    * return true : Matrix ist in Dreiecksform
    */
    private static boolean testAufDreiecksform(double[][] matrix) {//FALSCH #TODO
        int a,b,c;
        for(a=0; a < matrix.length; a++){
            //prüfen der Diagonalelemente auf =! 0
            if(matrix[a][a] != 0){
                //a = letztes Diagonalelement:
                if(a == matrix.length - 1){
                    for (b = 1; b < matrix.length; b++) {//Start bei 1, weil 0te Zeile keine 0 braucht
                        //testen der unteren Dreiecksmatrix ohne Diagonalelemente auf 0
                        for(c = b-1; c >= 0; c--){
                            if(matrix[b][c] != 0){
                                return false;
                            }
                        }
                    }
                    return true;
                }
            }else{
                return false;//Sobald mindestens ein Diagonalelement 0 ist
            }
        }
        return false;//Sobald mindestens ein Diagonalelement 0 ist
    }
}