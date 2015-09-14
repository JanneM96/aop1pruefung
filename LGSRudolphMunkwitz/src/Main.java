import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			//test
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
