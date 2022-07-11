package dataManagement;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import dao.ItemsDAO;
import dataManagement.fileManagement.FileLoader;
import dataManagement.fileManagement.SaveFileWriter;

public class Settings implements Serializable{
	private static final long serialVersionUID = 8330783901337204985L;

	private final String applicationName = "Reple";

	private int exerciseSize = 10;
	
	private String appFolderPath = System.getenv("HOMEPATH")+"\\."+(applicationName.toLowerCase())+"\\";
	private File savedDataFile;
	private File inputFile;
	private File defaultRawDataFile;
	private File appFolder;
	
	private CollectionsGenerator cg;
	
	public Settings() {
		prepareSavingFiles();
		ItemsDAO dao = new ItemsDAO();
		
		List<Item> itemsFromDB = dao.getAll();
		List<Item> itemsFromFile = CapitalCitiesDataCreator.convertToItems(FileLoader.loadDataFromFile(inputFile));
		
		List<Item> itemsToSave = merge(itemsFromDB,itemsFromFile);
		
		this.cg = new CollectionsGenerator(itemsToSave);
		
//		if(itemsFromFile.size() < itemsToSave.size())
			saveDataOnDisk();
		
		if(itemsFromDB.size() < itemsToSave.size())
			saveDataInDatabase();
		
		// Manage error : No data available
//		settingsFile = new File("settings");
	}

	private List<Item> merge(List<Item> first, List<Item> second) {
		List<Item> result = first;
		for(Item i : second) {
			if(!result.contains(i)) {
				result.add(i);
			}
		}
		return result;
	}

	private void prepareSavingFiles() {
		appFolder = new File(appFolderPath);
		
		if(!appFolder.exists()) {
			appFolder.mkdirs();
		}
		
		defaultRawDataFile = new File(appFolderPath+"capitals_list.txt");
		savedDataFile = new File(appFolderPath+"saved_"+defaultRawDataFile.getName());
		
		if(savedDataFile.exists() && savedDataFile.length() > 0) {
			inputFile = savedDataFile;
		}else{
			inputFile = defaultRawDataFile;
		}
	}
	
	
	public int getExerciseSize() {
		return exerciseSize;
	}

	public void setExerciseSize(int exerciseSize) {
		this.exerciseSize = exerciseSize;
	}

	
	public String[] getAvailableDataFileNames() {
		return appFolder.list(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				return name.contains("list");
			}
		});
	}
	
	private File getSaveFile() {
		if(!savedDataFile.exists())
			try {
				savedDataFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		return savedDataFile;
	}

	/**
	 * Save data in a file on the current machine
	 * @return The number of elements saved
	 */
	private int saveDataOnDisk() {
		// Saving into a file
		SaveFileWriter sfwData = new SaveFileWriter(cg.getContent(),getSaveFile());
		return sfwData.saveData();
	}

	/**
	 * @return The number of elements saved
	 */
	private int saveDataInDatabase() {
		// Saving into the database
		ItemsDAO idao = new ItemsDAO();
		
		for(Item i : cg.getContent()) {
			idao.saveOrUpdate(i);
		}
		return cg.getContent().length;
	}
	
	public void saveData() {
		saveDataOnDisk();
		saveDataInDatabase();
	}
	
	public List<Item> getItemsRandom(){
		return cg.getItemsRandom();
	}
	
	public List<Item> getAllItemsRandom(){
		return cg.getAllItemsRandom();
	}
	
	public Item[] getContent() {
		return cg.getContent();
	}
	
	public List<Item> getIgnoredItems(){
		return cg.getIgnoredItems();
	}
	
	public List<Item> getKnownItems(){
		return cg.getKnownItems();
	}

}
