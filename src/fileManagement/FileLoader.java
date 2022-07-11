package fileManagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Must load UTF-8 without BOM files. The BOM will introduce a first empty character that will create an error
 * while comparing the answer for the first element of the file.
 * @author Formation
 *
 */
public class FileLoader {

	public static List<String> loadDataFromFile(File inputFile) {
		BufferedReader in = null;
		LinkedList<String> result = new LinkedList<String>();

		try {
			in = new BufferedReader(new FileReader(inputFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}

		String temp = null;
		temp = readLine(in, result, temp);
		
		// TODO remove BOM from UTF-8 files
		
		while (temp != null){
			result.add(temp);
			temp = readLine(in, result, temp);
		}
		
		closeStream(in);
		
		return result;
	}

	private static String readLine(BufferedReader in, LinkedList<String> result, String temp) {
		try {
			temp = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			closeStream(in);
		}
		return temp;
	}
	
	private static void closeStream(BufferedReader s) {
		try {
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
