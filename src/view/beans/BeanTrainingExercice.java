package view.beans;

import java.io.Serializable;

import javax.annotation.ManagedBean;

import control.Controller;
import control.Exercice;
import control.TrainingExercice;
import dataManagement.Item;

@ManagedBean
public class BeanTrainingExercice extends BeanAbstractExercice implements Serializable {

	private static final long serialVersionUID = 6469232275331370195L;

	public BeanTrainingExercice() {
		this.exercice = new TrainingExercice(Controller.getInstance().getSettings().getItemsRandom(), Exercice.MIXED);
	}

	public void clickEasy() {
		this.exercice.changeActualItemKnowledgeLevel(Item.KNOWN);
		super.isAnswerCorrect = true;
	}

	public void clickIgnore() {
		this.exercice.changeActualItemKnowledgeLevel(Item.IGNORED);
		super.isAnswerCorrect = true;
	}

	@Override
	public void init() {
		this.exercice = new TrainingExercice(Controller.getInstance().getSettings().getItemsRandom(), Exercice.MIXED);
	}

}
