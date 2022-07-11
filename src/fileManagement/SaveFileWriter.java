package fileManagement;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import dataManagement.CollectionsGenerator;
import dataManagement.Item;

public class SaveFileWriter {
	private CollectionsGenerator dataToSave;
	private BufferedWriter bwriter;
	private File saveFile;
	
	public SaveFileWriter(CollectionsGenerator data, File saveFile) {
		this.dataToSave = data;
		this.saveFile = saveFile;
	}
	
	public boolean saveData() {
		
		Item[] itemsToSave = dataToSave.getContent();
		
		try {
			if(!saveFile.exists()) {
				saveFile.createNewFile();
			}
			
			bwriter = new BufferedWriter(new FileWriter(saveFile));

			for(int i = 0; i < itemsToSave.length; i++) {
				bwriter.append(itemsToSave[i].getFullString()+System.lineSeparator());
			}

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				bwriter.flush();
				bwriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return true;
	}
}
