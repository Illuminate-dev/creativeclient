package creativeclient.ui.modgui;

import java.awt.Color;

import creativeclient.Creative;
import creativeclient.settings.BooleanSetting;
import creativeclient.settings.NumberSetting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiPageButtonList;
import net.minecraft.client.gui.GuiPageButtonList.GuiResponder;
import net.minecraft.client.gui.GuiSlider;

public class NumberButton {
	
	public int x, y, w, h;
	public NumberSetting n;
	public Minecraft mc;
	public GuiSlider slider;
	public int id;
	GuiResponder sliderResponder = new GuiPageButtonList.GuiResponder() {

		@Override
		public void func_175321_a(int p_175321_1_, boolean p_175321_2_) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTick(int id, float value) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void func_175319_a(int p_175319_1_, String p_175319_2_) {
			// TODO Auto-generated method stub
			
			
		}
		
	};
	
	GuiSlider.FormatHelper formatHelper = new GuiSlider.FormatHelper() {
		
		
		
		@Override
		public String getText(int id, String name, float value) {
			n.setValue((int) value);
			return name + ": " + (int) (value);
		}
	};
	
	public NumberButton(int id, int x, int y, int w, int h, NumberSetting n) {
		this.x = x;
		this.y = y;
		this.h = h;
		this.w = w;
		this.n = n;
		this.id = id;
		this.slider = new GuiSlider(sliderResponder, id, x, y, n.name, (int) (n.getMinimum()), (int) (n.getMaximum()), (int) (n.getValue()), formatHelper);
	}
	
	public void draw(int mouseX, int mouseY) {
		
		slider.drawButton(Minecraft.getMinecraft(), mouseX, mouseY);

	}
	
	private int getColor() {
		
		return new Color(255, 255, 255, 255).getRGB();
		

	}
	
	public void onClick(int mouseX, int mouseY, int button) {
		
		slider.mousePressed(Minecraft.getMinecraft(), mouseX, mouseY);
		
	}
	
	
	public GuiSlider getSlider() {
		return slider;
	}

}
