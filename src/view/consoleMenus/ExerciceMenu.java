package view.consoleMenus;

import java.util.Scanner;

import control.Exercice;
import dataManagement.Item;

class ExerciceMenu extends AbstractMenu {
	private Exercice ex;

	public ExerciceMenu(Exercice ex, Scanner sc, MenuGenerator mg) {
		super(sc, mg);
		this.ex = ex;
	}

	public void startMenu() {
		System.out.println("Starting exercice");
		System.out.println("Type \"k\" to set the item as Known, \"i\" to ignore it or \"q\" to quit");

		String userAnswer;

		String i = ex.getNextQuestion();

		while (i != null) {

			System.out.println("Still " + (ex.getSize()) + " to go!");
			System.out.println(i);

			userAnswer = sc.nextLine();

			switch (userAnswer) {
			case "q":
				i = null;
				break;
			case "i":
				ex.changeActualItemKnowledgeLevel(Item.IGNORED);
				i = ex.getNextQuestion();
				break;
			case "k":
				ex.changeActualItemKnowledgeLevel(Item.KNOWN);
				i = ex.getNextQuestion();
				break;
			default:
				if (!ex.tryAnswer(userAnswer))
					System.out.println("Wrong, the answer was " + ex.getExpectedAnswer());

				i = ex.getNextQuestion();
				break;
			}
		}
		if (ex.getErrorsCount() == 0)
			System.out.print("Congratulations ! ");
		System.out.println("You made " + ex.getErrorsCount() + " mistakes");
	}

}
