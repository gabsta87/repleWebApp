package dataManagement;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name="words")
class VocabularyWord extends Item implements Serializable{
	private static final long serialVersionUID = -2041325745681121048L;
	
	@Column
	private String lang1;
	@Column
	private String lang2;
	
	public VocabularyWord() {
		super(Item.UNKNOWN);
	}
	
	public VocabularyWord(int knowledgeLeve) {
		super(knowledgeLeve);
	}

	@Override
	public String getName() {
		return lang1;
	}

	@Override
	public String getCorrespondance() {
		return lang2;
	}


	@Override
	public boolean equals(Object i) {
		if( i == null || i.getClass() != this.getClass()) {
			return false;
		}
		VocabularyWord c = (VocabularyWord) i;
		if(c.lang1.equals(lang1) && c.lang2.equals(lang2))
			return true;
		
		return false;
	}

}
