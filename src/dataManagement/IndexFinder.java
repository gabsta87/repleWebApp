package dataManagement;

import java.util.HashMap;
import java.util.List;

class IndexFinder<E> {
	private HashMap<E,Integer> reverseTable;

	private IndexFinder(E[] content) {
		reverseTable = new HashMap<E, Integer>();
		int i = 0;
		for(E element: content) {
			reverseTable.put(element, i++);
		}
	}
	
	private IndexFinder(List<E> content) {
		reverseTable = new HashMap<E, Integer>();
		int i = 0;
		for(E element: content) {
			reverseTable.put(element, i++);
		}
	}
	
	public void add(E newItem) {
		reverseTable.put(newItem,reverseTable.size());
	}
	
	public boolean contains(E item) {
		return reverseTable.containsKey(item);
	}
	
	public int getIndex(E element) {
		return reverseTable.get(element);
	}
	
}
