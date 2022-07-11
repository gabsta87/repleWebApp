package dataManagement.fileManagement;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import dataManagement.Item;

public class SaveFileWriter {
	private Item[] itemsToSave;
	private File saveFile;
	
	public SaveFileWriter(Item[] data, File saveFile) {
		this.itemsToSave = data;
		this.saveFile = saveFile;
	}
	
	public int saveData() {
		OutputStreamWriter writer = null;
		int itemsSaved = 0;
		try {
			if(!saveFile.exists()) {
				saveFile.createNewFile();
			}
			
			writer = new OutputStreamWriter(new FileOutputStream(saveFile), StandardCharsets.UTF_8);

			for(int i = 0; i < itemsToSave.length; i++) {
				writer.write(itemsToSave[i].getItemSavingRepresentation()+System.lineSeparator());
				itemsSaved++;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.flush();

				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return itemsSaved;
	}
}
