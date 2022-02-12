package creativeclient.mod.impl.autogg;

import java.util.Arrays;

import creativeclient.Creative;
import creativeclient.event.EventTarget;
import creativeclient.mod.Category;
import creativeclient.mod.Mod;
import net.minecraft.client.Minecraft;
import net.minecraft.util.IChatComponent;

public class AutoGG extends Mod {
	
	private long lastTrigger;

	public AutoGG() {
		super("Auto GG", "Automatically says gg", Category.MISC);
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public void onChat(IChatComponent message) {
		
        if (this.isEnabled()) {
        	
            if (Minecraft.getMinecraft().getCurrentServerData() != null && Minecraft.getMinecraft().getCurrentServerData().serverIP != null) {
                if (System.currentTimeMillis() > lastTrigger + 1000L && Arrays.asList(getHypixelTrigger().split("\n")).stream().anyMatch(trigger -> message.getUnformattedText().contains(trigger))) {
                    Minecraft.getMinecraft().thePlayer.sendChatMessage("/achat gg");
                    Minecraft.getMinecraft().ingameGUI.getChatGUI().addToSentMessages("/achat gg");
                    lastTrigger = System.currentTimeMillis();
                }
            }
        }
    }
	
	public static String getHypixelTrigger()
    {
        return "1st Killer - \n" +
                "1st Place - \n" +
                "Winner: \n" +
                " - Damage Dealt - \n" +
                "Winning Team -\n" +
                "1st - \n" +
                "Winners: \n" +
                "Winner: \n" +
                "Winning Team: \n" +
                " won the game!\n" +
                "Top Seeker: \n" +
                "1st Place: \n" +
                "Last team standing!\n" +
                "Winner #1 (\n" +
                "Top Survivors\n" +
                "Winners - \n" +
                "Sumo Duel - \n";
    }

}
