package view.consoleMenus;

import java.util.List;
import java.util.Scanner;

import dataManagement.Item;

class StandardMenu extends AbstractMenu {
	private String listName;
	private List<Item> items;
	
	/**
	 * @param items The items contained in the menu
	 * @param menuTitle The menu title that will be displayed
	 * @param sc
	 */
	public StandardMenu(List<Item> items,String menuTitle, Scanner sc, MenuGenerator mg) {
		super(sc,mg);
		this.listName = menuTitle;
		this.items = items;
	}

	public void startMenu() {

		int choice = -1;

		while (choice != 0) {

			System.out.println(listName);
			System.out.println("Type the corresponding number to remove the item from the list");
			System.out.println("0. Quit");

			for (int i = 0; i < items.size(); i++) {
				System.out.println((i+1)+" "+items.get(i));
			}

			try {
				choice = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				choice = -1;
			}
			
			if(choice > 0 && choice <= items.size()) {
				items.get(choice-1).setKnowledgeLevel(Item.UNKNOWN);
				items.remove(choice-1);
			}

		}

	}
}
