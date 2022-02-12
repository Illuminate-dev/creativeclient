package creativeclient;

import java.util.UUID;

import org.lwjgl.opengl.Display;

import creativeclient.commands.CommandManager;
import creativeclient.event.EventManager;
import creativeclient.event.EventTarget;
import creativeclient.event.impl.ClientTickEvent;
import creativeclient.event.impl.KeyEvent;
import creativeclient.event.impl.RenderLivingEvent;
import creativeclient.event.impl.RenderPlayerEvent;
import creativeclient.hud.HudConfigScreen;
import creativeclient.hud.mod.HudManager;
import creativeclient.mod.ModManager;
import creativeclient.mod.impl.QuickPlay.ModQuickPlay;
import creativeclient.settings.SettingsManager;
import creativeclient.ui.SplashProgress;
import creativeclient.ui.capegui.CapeManager;
import creativeclient.util.config.Config;
import net.hypixel.api.HypixelAPI;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.util.ChatComponentText;


public class Creative {
	
	public String NAME = "Creative Client", VERSION = "1.1.0", AUTHOR = "creativities", NAMEVER = NAME + " " + VERSION;
	public static Creative INSTANCE = new Creative();
	public Minecraft mc = Minecraft.getMinecraft();
	
	public EventManager eventManager;
	public SettingsManager settingsManager;
	public ModManager modManager;
	public HudManager hudManager;
	public CapeManager capeManager;
	public Config config;
	public static CommandManager commandManager;
	private ModQuickPlay modQuickPlay;
	public HypixelAPI API;
	public String currentlydragging;
	
	public void startup() {
		config = new Config();
		
		API = new HypixelAPI(UUID.fromString("6a56924f-7671-47df-9b3f-d3cf489f62cc"));
		commandManager = new CommandManager();
		eventManager = new EventManager();
		settingsManager = new SettingsManager();
		modManager = new ModManager();
		hudManager = new HudManager();
		capeManager = new CapeManager();
		SplashProgress.setProgress(7, "Loading Mod Config");
		config.loadModConfig();
		
		
		
		Display.setTitle(NAMEVER + " by " + AUTHOR);
		
		System.out.println("Starting " + NAMEVER +" by " + AUTHOR);
		
		SplashProgress.setProgress(8, "Finishing Mods");
		eventManager.register(this);

	}
	
	

	public void shutdown() {
		System.out.println("Shutting Down " + NAMEVER);
		
		config.saveModConfig();
		
		
		eventManager.unregister(this);

	}
	
	@EventTarget
	public void onRenderLiving(RenderLivingEvent e) {
		modManager.animationsMod.onRenderLiving(e);
	}
	
	@EventTarget
	public void onRenderPlayer(RenderPlayerEvent e) {
		modManager.animationsMod.onRenderPlayer(e);
	}
	
	@EventTarget
	public void onTick(ClientTickEvent event) {
		if(mc.gameSettings.TEST_MOD.isPressed()) {
			modManager.testMod.toggle();
		}
		if(mc.gameSettings.TOGGLE_SPRINT.isPressed()) {
			modManager.toggleSprint.toggle();
		}
		if(mc.gameSettings.HUD_CONFIG.isPressed()) {
			mc.displayGuiScreen(new HudConfigScreen());
			
		}
		if(mc.gameSettings.QUICKPLAY_MOD.isPressed()) {
			mc.displayGuiScreen(new ModQuickPlay(modQuickPlay));
			
		}
		
		
	}
	
	@EventTarget
	public void onKey(KeyEvent key) {
		modManager.perspective.keyboardEvent(key);
	}
	
	public static void addChatMessage(String message) {
		Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(message));
	
	}
	
	public static boolean onRecieveChatMessage(S02PacketChat packet) {
		return true;
	}
	
	public static boolean onSendChatMessage(String s) {
		if(s.startsWith(".")) {
			commandManager.callCommand(s.substring(1));
			return false;
		}
		
		
		
		return true;
	}
	
	
}
