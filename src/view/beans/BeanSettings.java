package view.beans;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.ManagedBean;

import control.Controller;
import dao.ItemsDAO;
import dataManagement.Item;
import dataManagement.Settings;

@ManagedBean
public class BeanSettings implements Serializable{
	private static final long serialVersionUID = -528286399907226612L;
	private Settings settings;
	
	private List<String> previouslyKnownItems, previouslyIgnoredItems;
	private ItemsDAO dao;
	
	/*
	 * System.out.println("3. Choose another database file");
	 * System.out.println("6. See available collection files");
	 */
	public BeanSettings() {
		this.settings = Controller.getInstance().getSettings();
		this.previouslyKnownItems= new LinkedList<String>();
		this.previouslyIgnoredItems= new LinkedList<String>();
		
		for(Item i : settings.getKnownItems()) {
			previouslyKnownItems.add(i.toString());
		}
		for(Item j : settings.getIgnoredItems()) {
			previouslyIgnoredItems.add(j.toString());
		}
		
		if(previouslyIgnoredItems.size() == 0)
			System.out.println("Empty ignored list");
		
		if(previouslyKnownItems.size() == 0)
			System.out.println("Empty known list");
		
		dao = new ItemsDAO();
	}

	public void setExerciceSize(int size) {
		settings.setExerciseSize(size);
	}

	public int getExerciceSize() {
		return settings.getExerciseSize();
	}

	public Item[] getItems() {
		return settings.getContent();
	}

	@SuppressWarnings("rawtypes")
	public List getKnownItems() {
		return settings.getKnownItems();
	}
	
	@SuppressWarnings("rawtypes")
	public List getIgnoredItems() {
		return settings.getIgnoredItems();
	}

	public void setKnownItems(List<String> newKnownItems) {
		Item t;
		// Setting the new items to the right value
		for(String s: newKnownItems) {
			if(!previouslyKnownItems.contains(s)) {
				t = findItemFromText(s);
				t.setKnowledgeLevel(Item.KNOWN);
				dao.update(t);
				System.out.println("updated (Known) "+t);
			}
		}
		// Removing the old items that are absent from the new list
		for(String i: previouslyKnownItems) {
			if(!newKnownItems.contains(i)) {
				t = findItemFromText(i);
				t.setKnowledgeLevel(Item.UNKNOWN);
				dao.update(t);
				System.out.println("updated (unknown) "+t);
			}
		}
		this.previouslyKnownItems = newKnownItems;
	}

	public void setIgnoredItems(List<String> newIgnoredItems) {
		Item t;
		// Setting the new items to the right value
		for(String s: newIgnoredItems) {
			if(!previouslyIgnoredItems.contains(s)) {
				t = findItemFromText(s);
				t.setKnowledgeLevel(Item.IGNORED);
				dao.update(t);
				System.out.println("updated (ignored) "+t);
			}
		}
		// Removing the old items that are absent from the new list
		for(String i: previouslyIgnoredItems) {
			if(!newIgnoredItems.contains(i)) {
				t = findItemFromText(i);
				t.setKnowledgeLevel(Item.UNKNOWN);
				dao.update(t);
				System.out.println("updated (unknown) "+t);
			}
		}
		this.previouslyIgnoredItems = newIgnoredItems;
	}
	
	public void saveData() {
//		settings.saveData();
	}

	private Item findItemFromText(String i) {
		Item temp, result = null;
		Item[] items = settings.getContent();
		int counter = 0;
		while(result==null && counter<items.length) {
			temp = items[counter++];
			if(temp.toString().equals(i)) {
				result = temp;
			}
		}
		return result;
	}
}
