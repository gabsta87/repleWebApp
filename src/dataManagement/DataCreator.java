package dataManagement;

abstract class DataCreator {
	protected Settings settings;
	
	public DataCreator(Settings settings) {
		this.settings = settings;
	}
	
	public abstract CollectionsGenerator createCollectionGenerator();
}
