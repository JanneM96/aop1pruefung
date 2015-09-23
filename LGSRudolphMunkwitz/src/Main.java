import java.io.IOException;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {
        Mathe mathe = new Mathe();

		Filemanager fm = new Filemanager();
        String filename = "C:/Users/Alexander/workspace/aop1pruefung/test.txt";  
          
        try  
        {  
            String[] lines = fm.readFromFile(filename);
          
            for (String line : lines)   
            {  
                System.out.println(line);  
            }  
        }  
        catch(IOException e)  
        {
            System.out.println("Unable to create "+filename+": "+e.getMessage());                
        }

        Gaussgeschuetz gs = new Gaussgeschuetz();
        double [][] ausgabe = gs.getInput();
        System.out.println(Arrays.deepToString(ausgabe));

    }  
		
}
