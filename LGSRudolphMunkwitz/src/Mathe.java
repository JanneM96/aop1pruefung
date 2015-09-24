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
            Mathe.dreiecksformErstellen(matrix);
        }
        double[] loesung = Mathe.dreiecksformAufloesen(matrix);
        //System.out.println(Arrays.toString(loesung)); //vorläufige Ausgabe der Testwerte
        return (loesung);
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
    private static void dreiecksformErstellen(double[][] matrix){
        double [][] rechenmatrix = Mathe.matrizenErstellen1(matrix);
        double[] vector = Mathe.matrizenErstellen2(matrix);
        Mathe.zeilenUmformen(rechenmatrix, vector);
        Mathe.matrizenUmwandeln(matrix, rechenmatrix, vector);
    }

    /*
    * Diese Funktion formt die Hilfsmatrizen um
    */
    private static void zeilenUmformen(double[][] matrix, double[] vector){
        /*// Spalte, welche eine Zahl ungleich null besitzt
        int merkeSpalte = -1;
        // Alle Zeilen a durchgehen
        for (int a = 0; a < rechenmatrix.length; a++) {
            merkeSpalte = -1;
            //Alle Spalten b nach Wert ungleich 0 durchsuchen, c=Zeilenzähler
            for (int b = 0; b < rechenmatrix[a].length; b++) {
                for (int c = a; c < rechenmatrix.length; c++) {
                    if (rechenmatrix[c][b] != 0) {
                        merkeSpalte = b;
                        break;
                    }
                }
                // Abbruch wenn Zahl ungleich 0 gefunden
                if (merkeSpalte != 0) {
                    break;
                }
            }
            // rechenmatrix[a][merkeSpalte] Zahlen
            if (rechenmatrix[a][merkeSpalte] == 0) {
                for (int c = a + 1; c < rechenmatrix.length; c++) {
                    if (rechenmatrix[c][merkeSpalte] != 0) {
                        // Vertauschen von Zeilen, so dass rechenmatrix[a][merkeSpalte] != 0
                        zeilenVertauschen(a, c, rechenmatrix, vector);
                        break;
                    }
                }
            }
            //rechenmatrix[a][merkeSpalte] darf nicht 0 sein
            if (rechenmatrix[a][merkeSpalte] != 0) {
                // Division der Zeile mit rechenmatrix[a][merkeSpalte]
                dividieren(a, rechenmatrix[a][merkeSpalte], rechenmatrix, vector);
            }
            // Sind alle Zahlen unter rechenmatrix[a][merkeSpalte] =0?
            for (int c = a + 1; c < rechenmatrix.length; c++) {
                // Subtraktion um unter der Zahl nur 0en zu erreichen
                zeilenSubtraktion(rechenmatrix[c][merkeSpalte], a, c, rechenmatrix, vector);
            }
        }
        */
        int a;
        int b;
        for(b=0; b<matrix.length; b++){
            for(a= b+1; b<matrix.length; b++){
                zeilenSubtraktion(matrix[b][b], a, b, matrix, vector);
            }
        }
    }

    /*
    *Diese Methode subtrahiert zwei Zeilen
     */
    private static void zeilenSubtraktion(double multFaktor, int zeilenVar, int zeile, double[][] matrix, double[] vector){
        for (int column = 0; column < matrix[zeile].length; column++) {
            matrix[zeile][column] = matrix[zeile][column] - multFaktor * matrix[zeilenVar][column];
        }
        vector[zeile] = vector[zeile] - multFaktor * vector[zeilenVar];
    }

    /*
    * Diese Methode vertauscht zwei Zeilen
    */
    public static double [][] matrizenUmwandeln(double[][] matrix, double[][] rechenmatrix, double[] vector) {
        int a, b, c;
        for(a=0; a<matrix.length; a++){//rechenmatrix wird in matrix eingefügt
            for(b=0; b<matrix.length; b++){
                matrix[a][b]=rechenmatrix[a][b];
            }
        }
        for(c=0; c<matrix.length; c++) {//vector wird in ergebnisspalte matrix eingefügt
            matrix[c][matrix.length]=vector[c];
        }
        return matrix;
    }

    /*
     * Diese Methode vertauscht zwei Zeilen
     */
    private static void zeilenVertauschen(int tauschZeile1, int tauschZeile2, double[][] rechenmatrix, double[] vector) {
        double[] hilfsZeile;
        double hilfsVariable;

        hilfsZeile = rechenmatrix[tauschZeile1];
        hilfsVariable = vector[tauschZeile1];

        rechenmatrix[tauschZeile1] = rechenmatrix[tauschZeile2];
        vector[tauschZeile1] = vector[tauschZeile2];

        rechenmatrix[tauschZeile2] = hilfsZeile;
        vector[tauschZeile2] = hilfsVariable;
    }

    /*
     * Diese Methode dividiert eine Zeile durch den divisor != 0 geteilt
     */
    private static void dividieren(int c, double divisor, double[][] rechenmatrix, double[] vector) {
        for (int a = 0; a < rechenmatrix[c].length; a++) {
            rechenmatrix[c][a] = rechenmatrix[c][a] / divisor;
        }
        vector[c] = vector[c] / divisor;
    }

    /*
    * Diese Funktion erstellt die erste Hilfsmatrize zur Berechnung der Dreiecksform
    */
    private static double [][] matrizenErstellen1(double[][] matrix){
        double[][] rechenmatrix = new double[matrix.length][matrix.length];
        int a, b;
        for(a=0; a<matrix.length; a++){
            for(b=0; b<matrix.length; b++){
                rechenmatrix[a][b] = matrix[a][b];
            }
        }
        return rechenmatrix;
    }

    /*
   * Diese Funktion erstellt die zweite Hilfsmatrize zur Berechnung der Dreiecksform
   */
    private static double [] matrizenErstellen2(double[][] matrix){
        double[] vector = new double[matrix.length];
        int a;
        for(a=0; a<matrix.length; a++){
            vector[a] = matrix[a][matrix.length];
        }
        return vector;
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