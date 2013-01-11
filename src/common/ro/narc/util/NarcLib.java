package ro.narc.util;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.world.World;

public class NarcLib {
    public static final int versionTag = 1;
    protected static boolean DEBUG = false;

    public static void requireMinVersion(int minVersion) {
        if(versionTag < minVersion) {
            throw new RuntimeException("Something you have installed demands NarcLib version "  + minVersion + ", but the active version is " + versionTag + "! Ask Narc to help you out here, and tell him what versions of his mods you have.");
        }
    }

    public static boolean isDebugging() {
        return DEBUG;
    }

    public static void enableDebug() {
        DEBUG = true;
    }

    public static Side getSide() {
        return FMLCommonHandler.instance().getEffectiveSide();
    }

    @SideOnly(Side.CLIENT)
    public static World getClientWorld() {
        return cpw.mods.fml.client.FMLClientHandler.instance().getClient().theWorld;
    }
}
