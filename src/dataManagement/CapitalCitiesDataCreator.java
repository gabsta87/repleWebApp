package dataManagement;

import java.util.LinkedList;
import java.util.List;

class CapitalCitiesDataCreator{

	public static List<Item> convertToItems(List<String> items) {
		List<Item> resultContent = new LinkedList<Item>();
		
		if(items!=null) {
			Item temp = null;
			for (int i = 0; i < items.size(); i++) {
				temp = createCountry(items.get(i));
				if(temp!= null){
					resultContent.add(temp);
				}
			}
		}else {
			System.out.println("Empty collection : No items to initialize");
		}
		
		return resultContent;
	}

	private static Item createCountry(String string) {
		String[] parts = string.split("\\t");
		
		if(parts.length == 2) {
			return new Country(parts[1].trim(), parts[0].trim());
		}else if(parts.length == 3) {
			return new Country(parts[0].trim(), parts[1].trim(), Integer.parseInt(parts[2].trim()));
		}else {
			return null;
		}
	}
}
