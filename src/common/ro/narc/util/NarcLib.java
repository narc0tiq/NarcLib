package ro.narc.util;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.world.World;

public class NarcLib {
    protected static boolean DEBUG = false;

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