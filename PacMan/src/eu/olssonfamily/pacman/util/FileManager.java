package eu.olssonfamily.pacman.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class FileManager {
	
	public File file;
	
	public FileManager(String path) {
		
		file = new File(path);
		
		if (!file.exists()) {
			String currentDir = System.getProperty("user.dir");
			try {
				new File(currentDir + "/" + path).createNewFile();
				write("0");
			} 	catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	public void write(String data) {
		try {
			FileWriter fw = new FileWriter(file);
			fw.write(data);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	@SuppressWarnings("finally")
	public String read () {
		String data = "";
		
		try {
			Scanner myReader = new Scanner(file, StandardCharsets.UTF_8.name());
			
		    while (myReader.hasNextLine()) {
		    	data += myReader.nextLine();
		    }
		    myReader.close();
	      
	    } catch (FileNotFoundException e) {
	    	
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	      
	    } finally {
	    	
	    	return data;
	    }
		
	}
	
}
