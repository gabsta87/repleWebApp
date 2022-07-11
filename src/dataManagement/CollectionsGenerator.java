package dataManagement;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import control.Controller;

class CollectionsGenerator implements Serializable{
	private static final long serialVersionUID = -1177753990160133690L;
	private final Item[] items;
	
	public CollectionsGenerator(List<Item> items) {
		this.items = new Item[items.size()];
		for(int i = 0; i < items.size(); i++) {
			this.items[i] = items.get(i);
		}
	}
	
	/**
	 * 
	 * @param itemsCount
	 * @return A specific number of random items from the collection
	 */
	public List<Item> getItemsRandom(){
		return createItemsList(Controller.getInstance().getSettings().getExerciseSize(), false);
	}

	/**
	 * 
	 * @return The full content of the collection in a random order
	 */
	public List<Item> getAllItemsRandom(){
		return createFullItemsList();
	}

	Item[] getContent() {
		return items;
	}

	public List<Item> getIgnoredItems(){
		List<Item> result = new LinkedList<Item>();
		for (Item j : items) {
			if(j.getKnowledgeLevel() == Item.IGNORED)
				result.add(j);
		}
		return result;
	}
	
	
	public List<Item> getKnownItems(){
		List<Item> result = new LinkedList<Item>();
		for (Item j : items) {
			if(j.getKnowledgeLevel() == Item.KNOWN)
				result.add(j);
		}
		return result;
	}
	
	/**
	 * Creates a list containing the number of requested items. 
	 * @param itemsCount
	 * @param isFullExercice Defines if the known items will be included
	 * @return
	 */
	private List<Item> createItemsList(int itemsCount, boolean isFullExercice) {
		List<Item> result = new LinkedList<Item>();
		Item element;

		for(int i = 0; i < itemsCount; i++) {
			do{
				element = items[(int)(Math.random()*items.length)];
			}while(result.contains(element) 
					|| element.getKnowledgeLevel() == Item.IGNORED
					|| (element.getKnowledgeLevel() == Item.KNOWN && !isFullExercice));
			result.add(element);
		}
		return result;
	}
	
	/**
	 * @return All the items in a random order
	 */
	private List<Item> createFullItemsList(){
		List<Item> result = new LinkedList<Item>();
		List<Integer> itemsToPick = new LinkedList<Integer>();
		
		for(int i = 0; i < Controller.getInstance().getSettings().getContent().length; i++) {
			if(Controller.getInstance().getSettings().getContent()[i].getKnowledgeLevel() != Item.IGNORED)
				itemsToPick.add(i);
		}

		int select = 0;
		while(itemsToPick.size() > 0) {
			select = (int) (Math.random()*itemsToPick.size());
			if(result.contains(Controller.getInstance().getSettings().getContent()[itemsToPick.get(select)]))
				System.out.println("Erreur : select="+select+", size="+itemsToPick.size());
			result.add(Controller.getInstance().getSettings().getContent()[itemsToPick.get(select)]);
			itemsToPick.remove(select);
		}
		
		return result;
	}
}
