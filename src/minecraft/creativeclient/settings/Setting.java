package creativeclient.settings;

public abstract class Setting {

	public String name;
	public boolean focused;
	public abstract double getValue();
	public abstract boolean isEnabled();
	public abstract String getMode();
	
}
