package view.beans;

import control.Exercice;

class ExerciceBean {
	
	private String text;
	private Exercice exercice;

	public ExerciceBean(Exercice ex) {
		this.exercice = ex;
	}
	 
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    
    public String getQuestion() {
    	return exercice.getNextQuestion();
    }
    
    public String getExpectedAnswer() {
    	return exercice.getExpectedAnswer();
    }
	
    public boolean tryAnswer(String a) {
    	return exercice.tryAnswer(a);
    }
    
    public int getRemainingItems() {
    	return exercice.getSize();
    }
}
