package creativeclient.ui.capegui;

import java.util.ArrayList;

import creativeclient.cosmetics.cape.CapeBase;
import creativeclient.cosmetics.cape.impl.*;

public class CapeManager {
	
	public ArrayList<CapeBase> capes;
	
	public CapeSword capeSword;
	public CapeFox capeFox;
	
	public CapeManager() {
		capes = new ArrayList<>();
		
		capes.add(capeSword = new CapeSword());
		capes.add(capeFox = new CapeFox());
		
	}

}
