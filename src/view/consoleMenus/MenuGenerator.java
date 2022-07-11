package view.consoleMenus;

import java.util.Scanner;

import control.Controller;
import control.Exam;
import control.Exercice;
import control.TrainingExercice;
import dataManagement.Settings;

public class MenuGenerator {
	private Scanner sc;
	private Settings settings;
	
	public MenuGenerator(Scanner sc,Settings settings) {
		this.sc = sc;
		this.settings = settings;
	}

	public Menu getMainMenu() {
		Menu result = new Menu(sc, "Main menu",this);
		
		result.addOption(new Executable("Start normal exercice") {
			
			@Override
			public void execute() {
				getNormalExerciceMenu().startMenu();
				Controller.getInstance().getSettings().saveData();
			}
		});
		
		result.addOption(new Executable("Start full exercice") {
			
			@Override
			public void execute() {
				getFullExerciceMenu().startMenu();
				Controller.getInstance().getSettings().saveData();
			}
		});
		
		result.addOption(new Executable("Settings") {
			
			@Override
			public void execute() {
				getSettingsMenu().startMenu();
			}
		});
		
		return result;
	}

	Menu getSettingsMenu() {

		/*
		 * System.out.println("1. Return to normal exercises");
		 * System.out.println("1. Reverse exercises");
		 * System.out.println("3. Choose another database file");
		 * System.out.println("6. See available collection files");
		 */
		
		Menu result = new Menu(sc, "Settings",this);

		result.addOption(new Executable("Set exercice size (actual size is "+settings.getExerciseSize()+")") {
			
			@Override
			public void execute() {
				int size = -1;
				while(size <= 0 || size > settings.getContent().length) {
					try {
						size = Integer.parseInt(sc.nextLine());
					}catch(NumberFormatException e) {
						size = -1;
					}
				}
				settings.setExerciseSize(size);
				this.setName("Set exercice size (actual size is "+settings.getExerciseSize()+")");
			}
		});
		
		result.addOption(new Executable("See known items") {
			
			@Override
			public void execute() {
				getKnownItemsMenu().startMenu();
				Controller.getInstance().getSettings().saveData();
			}
		});
		
		result.addOption(new Executable("See ignored items") {
			
			@Override
			public void execute() {
				getIgnoredItemsMenu().startMenu();
				Controller.getInstance().getSettings().saveData();
			}
		});

		return result;
	}

	StandardMenu getIgnoredItemsMenu() {
		return new StandardMenu(settings.getIgnoredItems(), "Ignored Items", sc, this);
	}
	
	StandardMenu getKnownItemsMenu() {
		return new StandardMenu(settings.getKnownItems(), "Known Items", sc, this);	
	}
	
	ExerciceMenu getFullExerciceMenu() {
		Exercice ex = new Exam(settings);
		return new ExerciceMenu(ex, sc, this);
	}
	
	ExerciceMenu getNormalExerciceMenu() {
		Exercice ex = new TrainingExercice(settings.getItemsRandom(), Exercice.MIXED);
		return new ExerciceMenu(ex, sc, this);
	}
	
}
