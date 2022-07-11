package view.consoleMenus;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Menu extends AbstractMenu {

	private String title;
	private List<Executable> options;

	public Menu(Scanner sc, String title, MenuGenerator mg) {
		super(sc,mg);
		this.title = title;
		this.options = new LinkedList<Executable>();
	}
	
	public void addOption(Executable item) {
		options.add(item);
	}

	@Override
	public void startMenu() {
		int choice = -1;

		while (choice != 0) {

			System.out.println(title);
			System.out.println("0. Quit");
			for (int i = 1; i < options.size()+1; i++) {
				System.out.println(i + ". " + options.get(i-1));
			}

			try {
				choice = Integer.parseInt(sc.nextLine());
			} catch (InputMismatchException e) {
				choice = -1;
			} catch (NumberFormatException e) {
				choice = -1;
			}
			
			if(choice >0 && choice < options.size()+1)
				options.get(choice-1).execute();
		}
	}
}
