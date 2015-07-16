package app.gametrick.data;

public class CardSchemeItemData {
	private int id;
	private String name;
	private boolean isChecked;
	
	
	public CardSchemeItemData() {
		
	}
	
	public CardSchemeItemData(int id, String name, boolean isChecked) {
		this.id = id;
		this.name = name;
		this.isChecked = isChecked;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean getIsCheck() {
		return this.isChecked;
	}
	
	public void setIsCheck(boolean isChecked) {
		this.isChecked = isChecked;
	}
}

