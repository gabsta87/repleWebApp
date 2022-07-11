package control;

import dataManagement.Item;

public abstract class Exercice {
	public final static int NORMAL = 0;
	public final static int REVERSED = 1;
	public final static int MIXED = 2;
	public final static int FULL = 3;

	protected Item actualItem;
	protected String actualQuestion = "";
	protected String expectedAnswer = "";
	protected int errors = 0;
	protected int exerciceType;
	
	public int getErrorsCount() {
		return errors;
	}

	public String getExpectedAnswer() {
//		if(expectedAnswer.equals(""))
//			actualQuestion = getNextQuestion();
		return expectedAnswer;
	}

	public abstract boolean tryAnswer(String a);
	
	public abstract String getNextQuestion();
	
	public abstract int getSize();

	public abstract void changeActualItemKnowledgeLevel(int knowledgeLevel);
}
