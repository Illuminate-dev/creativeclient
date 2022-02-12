package creativeclient.settings;


public class BooleanSetting extends Setting {

	private boolean enabled;

	public BooleanSetting(String name, boolean enabled) {
		this.name = name;
		this.enabled = enabled;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void toggle() {
		enabled = !enabled;
		
	}

	public boolean isOn() {
		return enabled;
	}

	@Override
	public double getValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getMode() {
		// TODO Auto-generated method stub
		return null;
	}
}
