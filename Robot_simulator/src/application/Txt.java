package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class Txt {
	public static ArrayList<Double> X_posd = new ArrayList<Double>();
	public static ArrayList<Double> Y_posd = new ArrayList<Double>();
	public static ArrayList<Double> Angd = new ArrayList<Double>(); 
	public static ArrayList<Integer> count = new ArrayList<Integer>();
	
	public static void txt(){
		
		FileChooser fc = new FileChooser();
		fc.setInitialDirectory(new File("../Robot_simulator/ghost"));
		fc.getExtensionFilters().addAll(new ExtensionFilter("txt files","*.txt"));
		File file = fc.showOpenDialog(null);
		String[] parts = readFile(file).split("\\n");

		for(int i=0; i<parts.length; i++){
			String[] part = parts[i].split(" ");
			X_posd.add(Double.parseDouble(part[2]));
			Y_posd.add(Double.parseDouble(part[3]));
			Angd.add(Double.parseDouble(part[4]));
		}
		




	
		
	}
	
	private static String readFile(File file){
        StringBuilder stringBuffer = new StringBuilder();
        BufferedReader bufferedReader = null;
         
        try {
 
            bufferedReader = new BufferedReader(new FileReader(file));
             
            String text;
            while ((text = bufferedReader.readLine()) != null) {
                stringBuffer.append(text);
                stringBuffer.append("\n");
            }
 
        } catch (FileNotFoundException ex) {
        	ex.printStackTrace();
        } catch (IOException ex) {
        	ex.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException ex) {
            	ex.printStackTrace();
            }
        } 
         
        return stringBuffer.toString();
    }


}
