package control;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import dataManagement.Item;
import dataManagement.Settings;

/**
 * This specific exercice will ask the question for each item in normal and
 * reversed mode. It also defines when an item is known if the answer is correct
 * the first time twice in both normal and reversed mode
 * 
 * @author Formation
 *
 */
public class Exam extends Exercice implements Serializable{
	private static final long serialVersionUID = -4782032749038121228L;
	private List<Item> unknownItems;
	private TrainingExercice normalExercice;
	private TrainingExercice reversedExercice;
	private Item[] fullList;

	public Exam(Settings settings) {
		this.exerciceType = Exercice.FULL;
		this.unknownItems = new LinkedList<Item>();
		this.normalExercice = new TrainingExercice(settings.getAllItemsRandom(), TrainingExercice.NORMAL);
		this.reversedExercice = new TrainingExercice(settings.getAllItemsRandom(), TrainingExercice.REVERSED);
		this.fullList = settings.getContent();
	}

	@Override
	public String getNextQuestion() {

		String question = null;
		if(normalExercice.getSize() > 0) {
			question = normalExercice.getNextQuestion();
			expectedAnswer = normalExercice.getExpectedAnswer();
			actualItem = normalExercice.actualItem;
		}else if(reversedExercice.getSize() > 0){
			question = reversedExercice.getNextQuestion();
			expectedAnswer = reversedExercice.getExpectedAnswer();
			actualItem = reversedExercice.actualItem;
		}else {
			finalizeItemsKnowledge();
		}
		
		return question;
	}

	public boolean tryAnswer(String answer) {
		
		boolean isAnswerCorrect = false;
		
		if(normalExercice.getSize() > 0) {
			
			isAnswerCorrect = normalExercice.tryAnswer(answer);
			if (!isAnswerCorrect && !unknownItems.contains(normalExercice.actualItem)) {
				unknownItems.add(normalExercice.actualItem);
				errors++;
			}
			
		}else if(reversedExercice.getSize() > 0) {
			
			isAnswerCorrect = reversedExercice.tryAnswer(answer);
			if (!isAnswerCorrect && !unknownItems.contains(reversedExercice.actualItem)) {
				unknownItems.add(reversedExercice.actualItem);
				errors++;
			}
			
		}else {
			System.out.println("Event that should not happen : no more answers available in the Exam");
		}
		
		return isAnswerCorrect;
	}
	
	private void finalizeItemsKnowledge() {
		for (Item i : fullList) {
			if(!unknownItems.contains(i) && (i.getKnowledgeLevel() != Item.IGNORED)) {
				i.setKnowledgeLevel(Item.KNOWN);
			}
		}
	}

	@Override
	public int getSize() {
		return normalExercice.getSize()+reversedExercice.getSize();
	}

	@Override
	public void changeActualItemKnowledgeLevel(int knowledgeLevel) {
		actualItem.setKnowledgeLevel(knowledgeLevel);
		normalExercice.removeItem(actualItem);
		reversedExercice.removeItem(actualItem);
	}
}
