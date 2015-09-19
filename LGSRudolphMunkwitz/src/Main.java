import java.io.IOException;

public class Main {

	public static void main(String[] args) {
        Mathe mathe = new Mathe();

       /*//#finger weg <3
        //unittest für mathe mit x-ergebnissen 1, -2 und 3
        double[][] matrix;
        matrix = new double[][]{{1, 2, 3, 6},{0, 1, 4, 10},{0, 0, 1, 3}};

        Mathe.ablauf(matrix);
        *///#finger weg <3

		Filemanager fm = new Filemanager();
        String filename = "C:/Users/Alexander/workspace/aop1pruefung/test.txt";  
          
        try  
        {  
            String[] lines = fm.readLines(filename);  
          
            for (String line : lines)   
            {  
                System.out.println(line);  
            }  
        }  
        catch(IOException e)  
        {
            System.out.println("Unable to create "+filename+": "+e.getMessage());                
        }

    }  
		
}
