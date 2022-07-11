package view.beans;

import dataManagement.Item;

class QuestionBean {
	
	private Item item;

	public QuestionBean(Item item) {
		this.item = item;
	}
	
	public String getQuestion() {
		return item.getName();
	}
	
}
