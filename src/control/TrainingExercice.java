package control;

import java.util.List;

import dao.ItemsDAO;
import dataManagement.Item;

public class TrainingExercice extends Exercice {

	private int counter = 0;
	private List<Item> questions;
	private ItemsDAO dao;
	
	/**
	 * @param items The items that will be used in the exercice
	 * @param exerciceType Defines if the exercise questions are mixed, reversed or normal
	 */
	public TrainingExercice(List<Item> items, int exerciceType) {
		this.questions = items;
		this.exerciceType = exerciceType;
		this.dao = new ItemsDAO();
	}

	public String getNextQuestion() {
		actualQuestion = null;
		if (!questions.isEmpty()) {
			counter = counter % questions.size();
			actualItem = questions.get(counter);

			switch (exerciceType) {
			case NORMAL:
				actualQuestion = actualItem.getName();
				expectedAnswer = actualItem.getCorrespondance();
				break;
			case REVERSED:
				actualQuestion = actualItem.getCorrespondance();
				expectedAnswer = actualItem.getName();
				break;
			case MIXED:
				if (Math.random() > 0.5) {
					actualQuestion = actualItem.getName();
					expectedAnswer = actualItem.getCorrespondance();
				} else {
					actualQuestion = actualItem.getCorrespondance();
					expectedAnswer = actualItem.getName();
				}
				break;
			default:
				break;
			}
		}

		return actualQuestion;
	}

	public boolean tryAnswer(String answer) {
		if (answer.equals(expectedAnswer)) {
			questions.remove(actualItem);
			return true;
		} else if (expectedAnswer.contains("(") && answer.equals((expectedAnswer.split("\\(")[0]).trim())) {
			// Dans le cas ou la réponse contient une précision entre parenthèse,
			// on considère la réponse juste, même sans précision
			questions.remove(actualItem);
			return true;
		} else {
			counter++;
			actualItem.setKnowledgeLevel(Item.UNKNOWN);
			super.errors++;
			return false;
		}
	}
	
	public int getSize() {
		return questions.size();
	}

	public void changeActualItemKnowledgeLevel(int knowledgeLevel) {
		actualItem.setKnowledgeLevel(knowledgeLevel);
		dao.saveOrUpdate(actualItem);
//		questions.remove(actualItem);
		this.tryAnswer(expectedAnswer);
	}
	
	protected void removeItem(Item i) {
		questions.remove(i);
	}
	
}
