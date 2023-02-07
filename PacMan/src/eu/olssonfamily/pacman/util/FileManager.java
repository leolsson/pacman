package eu.olssonfamily.pacman.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class FileManager {

	public File file;

	public FileManager(String fileName) {

		file = new File(fileName);

		if (!file.exists()) {
			createFile(fileName);
		}

	}

	private void createFile(String fileName) {
		String currentDir = System.getProperty("user.dir");
		try {
			new File(currentDir + "/" + fileName).createNewFile();
			write("0");
		} catch (IOException e) {
			e.printStackTrace();
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
	public String read() {
		String data = "";

		try {
			Scanner sc = new Scanner(file, StandardCharsets.UTF_8.name());

			while (sc.hasNextLine()) {
				data += sc.nextLine();
			}
			sc.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		} finally {

			return data;
		}

	}

}
