package dataManagement.fileManagement;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * 
 * @author Formation
 *
 */
public class FileLoader {

	public static List<String> loadDataFromFile(File inputFile) {
		/* Check if must load UTF-8 files without BOM only.
		 * The BOM could introduce a first empty character that will create an error
		 * while comparing the answer for the first element of the file.
		 */
		
		List<String> result = null;

		try {
			Path p = Paths.get(inputFile.getAbsolutePath());
			result = Files.readAllLines(p,StandardCharsets.UTF_8);
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		return result;
	}
}
