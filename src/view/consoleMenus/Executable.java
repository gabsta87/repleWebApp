package view.consoleMenus;

abstract class Executable {
	private String name;
	
	public Executable(String name) {
		this.name = name;
	}
	
	void setName(String newName) {
		this.name = newName;
	}
	
	@Override
	public String toString() {
		return name;
	}

	public abstract void execute();
	
}
