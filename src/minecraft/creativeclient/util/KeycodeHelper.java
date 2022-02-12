package creativeclient.util;

public class KeycodeHelper
{
    public static final int lessThanKeycode = 224;
    public static final String lessThanName = "Foreign key";
    
    public static int extraVirtualKeysWindows(final int originalCode) {
        if (originalCode == 226) {
            return 224;
        }
        return originalCode;
    }
    
    public static String getKeyName(final int key) {
        if (key == 224) {
            return "Foreign key";
        }
        return null;
    }
}
