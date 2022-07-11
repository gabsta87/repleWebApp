package dataManagement;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity (name = "countries")
class Country extends Item implements Serializable{
	private static final long serialVersionUID = -1244054282366419544L;
	
	@Column(name = "country")
	private String countryName;
	@Column(name = "capital")
	private String capitalName;
	
	public Country() {
		super(Item.UNKNOWN);
	}
	
	public Country(String countryName, String capitalName){
		super(Item.UNKNOWN);
		this.countryName = countryName;
		this.capitalName = capitalName;
	}
	
	public Country(String countryName, String capitalName, int knowledgeLevel) {
		super(knowledgeLevel);
		this.countryName = countryName;
		this.capitalName = capitalName;
	}
	
	public String getName(){
		return countryName;
	}
	
	public String getCorrespondance() {
		return capitalName;
	}
	
	@Override
	public boolean equals(Object i) {
		if( i == null || i.getClass() != this.getClass()) {
			return false;
		}
		Country c = (Country) i;
		if(c.countryName.equals(countryName) && c.capitalName.equals(capitalName))
			return true;
		
		return false;
	}
}
