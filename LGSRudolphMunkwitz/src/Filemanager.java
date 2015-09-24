
	import java.io.*;
	import java.io.IOException;
	import java.util.ArrayList;  
	import java.util.List;  
	  
	public class Filemanager   
	{  
	    public String[] readFromFile(String filename) throws IOException
	    {
			FileReader fileReader = new FileReader(filename);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			List<String> lines = new ArrayList<String>();
			String line = null;

			while ((line = bufferedReader.readLine()) != null)
			{
                lines.add(line);
            }

			bufferedReader.close();
			return lines.toArray(new String[lines.size()]);
	    }

		public void writeToFile(String [] sErgebnis){

			try {
				FileWriter output = new FileWriter("LGSRudolphMunkwitz/Ausgabe.txt");
				int j =1;
				for (int i =0; i < sErgebnis.length; i++)
				{
					output.write("x"+j+" = "+sErgebnis[i]+"\n");
					j++;
				}
				output.flush();
				output.close();
			} catch (IOException e) {
				System.out.println("Beim Schreiben der Datei ist ein Fehler aufgetreten: " + e.getMessage());
			}

		}



	}  

