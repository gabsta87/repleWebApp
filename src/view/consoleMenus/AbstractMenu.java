package view.consoleMenus;

import java.util.Scanner;

abstract class AbstractMenu {
	protected Scanner sc;
	protected MenuGenerator mg;
	
	public AbstractMenu(Scanner sc, MenuGenerator mg) {
		this.sc = sc;
		this.mg = mg;
	}
	
	public abstract void startMenu();
}
