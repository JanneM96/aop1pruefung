import java.io.*;
import java.util.Arrays;
public class Mathe {
    /*
    * Diese Funktion erfüllt die Funktion einer Art kleiner Main/Ablaufplan,
    * damit die Matrix nur einmal übergeben werden muss, und der Programmablauf
    * ungestört und in der richtigen Reihenfolge bleibt
    */
    public static double[] ablauf(double[][] matrix){
        double [][] rechenmatrix = new double[matrix.length][matrix.length];
        //rechenmatrix = null;
        double [] vector = new double[matrix.length];
        //vector = null;
        Mathe.fehlerabfangen(matrix);
        double[] loesung = Mathe.dreiecksform(matrix);
        return(loesung);
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
    public static double[] dreiecksform(double[][] matrix){
        if(!Mathe.testAufDreiecksform(matrix)){//= Mathe.testAufDreiecksform(matrix) == false
            Mathe.zeilenUmformen(matrix);
        }
        double[] loesung = Mathe.dreiecksformAufloesen(matrix);
        //System.out.println(Arrays.toString(loesung)); //vorläufige Ausgabe der Testwerte
        return (loesung);
    }

    /*
    Diese Funktion prüft die Diagonale, vertauscht Zeilen und ruft dann die Division auf.
     */
    private static double[][] zeilenUmformen(double[][] matrix) {
        int a, b, c;
        double[] hilfe=new double[matrix.length];//Hilfearray zum zeilentausch
        //Diagonale auf 0en überprüfen, so tauschen, dass keine 0en mehr darin vorkommen
        for(a=0; a<matrix.length; a++){//Laufvariable um diagonale zu überprüfen
            for(b=1; b<matrix.length-1; b++){//Laufvariable zum zeilentausch, um tausch mit jeder Zeile zu ermöglichen
                if(matrix[a][a]==0){
                    for(c=0; c<matrix.length; c++){//Laufvariable, um jede spalte umzuschreiben
                        hilfe[c]=matrix[a][c];//Zeilentausch
                        matrix[a][c]=matrix[a+b][c];
                        matrix[a+b][c]=hilfe[c];
                    }
                }
            }
            if(matrix[a][a]==0){
                Mathe.abbruch("Fehler, Umformung nicht möglich!");
            }
        }
        //erneutes durchgehen durch die nun umgeformte matrix, nun werden alle elemente unter der diagonalen zu 0 gemacht
        for(a=0; a<matrix.length; a++){
            Mathe.zeilenDividieren(matrix, a, a);
        }
        return matrix;
    }

    /*
    Diese Funktion dividiert zwei komplette Zeilen miteinander, um unter der diagonalen bei jedem element 0 zu erreichen
     */
    private static double[][] zeilenDividieren(double[][] matrix, int zeile1, int spalte){
        int zeile2, g, s;
        double faktor;
        for(zeile2 = zeile1 + 1; zeile2 < matrix.length; zeile2++){//g zählt zeilen mit, die nacheinander durchgegangen werden müssen
            faktor = -( matrix[zeile2][spalte] / matrix[zeile1][spalte] );
            for(s=spalte; s<=matrix.length; s++){
                matrix[zeile2][s] = (matrix[zeile1][s] * faktor) + matrix[zeile2][s];
            }
        }
        return matrix;
    }

    /*
    * Diese Funktion löst eine Matrix, die sich in Dreiecksform befindet
    * Sie wird auch in Mathe.dreiecksform aufgerufen
    */
    private static double[] dreiecksformAufloesen(double[][] matrix) {
        int a, b;
        double[] speicher = new double[matrix.length];
        for (a = matrix.length - 1; a >= 0; a--) {//a ist der Index im speicher ABER für xa (beim Lösen) unbedingt a+1 benutzen
            double summe = matrix[a][matrix.length];//summe = Index der Ergebnisspalte
            for (b = a; b < matrix.length - 1; b++) {
                summe = summe - (matrix[a][b + 1] * speicher[b + 1]);
            }
            speicher[a] = (summe / matrix[a][a]);
            summe = 0;
        }
        return speicher;
    }

    /*
    * Diese Funktion prüft die Matrix auf Dreiecksform
    * return false : Matrix ist nicht in Dreiecksform
    * return true : Matrix ist in Dreiecksform
    */
    private static boolean testAufDreiecksform(double[][] matrix) {
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