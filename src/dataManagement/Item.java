package dataManagement;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.OptimisticLock;

@MappedSuperclass
public abstract class Item {
	// All the possible knowledge levels for each item 
	public static final int UNKNOWN = 0;
	public static final int KNOWN = 1;
	public static final int IGNORED = 2;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@OptimisticLock( excluded = true )
	@Column(name = "knowledge_level")
	private int knowledgeLevel = 0;
	
	public Item() {
		this(Item.UNKNOWN);
	}

	public Item(int knowledgeLevel) {
		this.knowledgeLevel = knowledgeLevel;
	}

	public abstract String getName();
	
	/**
	 * @return The expected answer associated to the item's name
	 */
	public abstract String getCorrespondance();
	
	public int getKnowledgeLevel() {
		return knowledgeLevel;
	}
	
	public void setKnowledgeLevel(int newValue) {
		this.knowledgeLevel = newValue;
	}
	
	@Override
	public String toString(){
		return getName()+"\t-\t"+getCorrespondance();
	}
	
	public String getItemSavingRepresentation() {
		return getName()+"\t"+getCorrespondance()+"\t"+knowledgeLevel;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
}
