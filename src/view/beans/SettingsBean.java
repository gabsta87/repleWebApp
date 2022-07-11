package view.beans;

import dataManagement.Settings;

class SettingsBean {

	private Settings settings;
	
	/*
	 * System.out.println("Settings");
			System.out.println("0. Back to main menu");
			if(settings.isReversed()) {
				System.out.println("1. Return to normal exercises");
			}else {
				System.out.println("1. Reverse exercises");
			}
			System.out.println("2. Set exercice length (actual length : "+settings.getExerciseSize()+")");
			System.out.println("3. Choose another database file");
			System.out.println("4. See ignored items");
			System.out.println("5. See known items");
			System.out.println("6. See available collection files");
	 */

	public SettingsBean(Settings settings) {
		this.settings = settings;
	}
	
}
