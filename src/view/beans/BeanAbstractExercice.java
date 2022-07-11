package view.beans;

import control.Exercice;

public abstract class BeanAbstractExercice {
	protected Exercice exercice;
	protected String actualTry = "";
	protected boolean isAnswerCorrect = true;
	private String lastExpectedAnswer = "";
	
	public BeanAbstractExercice() {}

	public String getQuestion() {
		if(this.exercice == null)
			init();
		this.lastExpectedAnswer = exercice.getExpectedAnswer();
		return exercice.getNextQuestion();
	}

	public String getLastExpectedAnswer() {
		return lastExpectedAnswer;
	}

	public void clickSubmit() {
		isAnswerCorrect = exercice.tryAnswer(actualTry);
		setActualTry("");
	}

	public boolean isAnswerWrong() {
		return !isAnswerCorrect;
	}

	public int getRemainingItems() {
		return exercice.getSize();
	}

	public void setActualTry(String actualTry) {
		this.actualTry = actualTry;
	}

	public String getActualTry() {
		return actualTry;
	}
	
	public boolean isExerciceFinished() {
		if(exercice == null)
			return false;
		return exercice.getSize() == 0;
	}
	
	public int getErrors() {
		return exercice.getErrorsCount();
	}
	
	public abstract void init();
}
