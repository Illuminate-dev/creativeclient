package net.minecraft.launchwrapper;

import java.io.File;
import java.util.List;
import net.minecraft.launchwrapper.LaunchClassLoader;

public interface ITweaker {
   

   void injectIntoClassLoader(LaunchClassLoader var1);

   String getLaunchTarget();

   String[] getLaunchArguments();

   void acceptOptions(List<String> args, File gameDir, File assetsDir, String profile);
   
   
}
