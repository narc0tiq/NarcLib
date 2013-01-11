package ro.narc.util;

import net.minecraft.client.gui.Gui;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.LiquidStack;

public class GUITank extends Gui {
    public ILiquidTank tank;

    public String parentGUITexture;

    public int displayHeight;
    public int gradationX;
    public int gradationY;

    public GUITank(String parentGUITexture, int displayHeight, int gradationX, int gradationY) {
        this.parentGUITexture = parentGUITexture;
        this.displayHeight = displayHeight;
        this.gradationX = gradationX;
        this.gradationY = gradationY;
    }

    public int getScaledAmount() {
        if(tank == null) {
            return 0;
        }
        LiquidStack liquid = tank.getLiquid();
        if(liquid == null) {
            return 0;
        }

        float scaledAmount = (float)(liquid.amount * displayHeight) / (float)tank.getCapacity();
        return MathHelper.ceiling_float_int(scaledAmount);
    }

    public void draw(int posX, int posY) {
        if((tank != null) && (tank.getLiquid() != null)) {
            ItemStack liquid = tank.getLiquid().asItemStack();
            ForgeHooksClient.bindTexture(liquid.getItem().getTextureFile(), 0);
            int iconIndex = liquid.getIconIndex();
            int scaledValue = getScaledAmount();

            int imgY = iconIndex / 16;
            int imgX = iconIndex - imgY * 16;

            for(int i = scaledValue; i > 0; i -= 16) {
                drawTexturedModalRect(posX, posY + displayHeight - i,
                                      imgX * 16, imgY * 16,
                                      16, Math.min(16, i));
            }
        }

        ForgeHooksClient.bindTexture(parentGUITexture, 0);
        drawTexturedModalRect(posX, posY, gradationX, gradationY, 16, displayHeight);
    }
}
