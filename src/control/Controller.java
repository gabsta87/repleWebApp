package control;

import java.util.Scanner;

import dataManagement.Settings;
import view.consoleMenus.Menu;
import view.consoleMenus.MenuGenerator;

/**
 * This class is built on the Singleton design pattern
 * @author Formation
 *
 */
public class Controller {

	private Settings settings;
	
	private static Controller instance;
	
	private Controller() {
		this.settings = new Settings();
	}
	
	public static Controller getInstance() {
		if(instance == null) {
			instance = new Controller();
		}
		return instance;
	}

	public Settings getSettings() {
		return this.settings;
	}
	
	/**
	 * Starts the console version of the program
	 */
	public void startMenu() {
		Scanner sc = new Scanner(System.in);
		MenuGenerator menuGenerator = new MenuGenerator(sc,settings);
		Menu mm = menuGenerator.getMainMenu();
		
		mm.startMenu();
	}
}
