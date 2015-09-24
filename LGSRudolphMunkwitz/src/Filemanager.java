
	import java.io.*;
	import java.io.IOException;
	import java.util.ArrayList;  
	import java.util.List;  
	  
	public class Filemanager   
	{  
	    public String[] readFromFile(String filename)
	    {
			FileReader fileReader = null;
			try {
				fileReader = new FileReader(filename);
			} catch (FileNotFoundException e) {
				System.out.println("Fehler beim Lesen der Datei:" + e.getMessage());
				System.exit(1);
			}
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			List<String> lines = new ArrayList<String>();
			String line = null;

			try {
				while ((line = bufferedReader.readLine()) != null)
                {
                    lines.add(line);
                }
				bufferedReader.close();
			} catch (IOException e) {
				System.out.println("Fehler beim Lesen der Datei:" + e.getMessage());
				e.printStackTrace();
			}
			return lines.toArray(new String[lines.size()]);
	    }

		public void writeToFile(String [] sErgebnis, String filename){

			final String lineSeparator = System.getProperty("line.separator");
			try {
				FileWriter output = new FileWriter(filename, true);
				output.write(lineSeparator);
				for (int i =0; i < sErgebnis.length; i++)
				{
					output.write("x"+(i+1)+" = "+sErgebnis[i]);
					output.write(lineSeparator);
				}
				output.flush();
				output.close();
			} catch (IOException e) {
				System.out.println("Beim Schreiben der Datei ist ein Fehler aufgetreten: " + e.getMessage());
			}

		}



	}  

