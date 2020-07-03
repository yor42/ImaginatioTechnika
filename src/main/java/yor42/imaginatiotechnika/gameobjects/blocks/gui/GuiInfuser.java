package yor42.imaginatiotechnika.gameobjects.blocks.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import yor42.imaginatiotechnika.ImaginatioTechnika;
import yor42.imaginatiotechnika.gameobjects.blocks.container.ContainerInfuser;
import yor42.imaginatiotechnika.gameobjects.blocks.tileentities.TileEntityInfuser;

public class GuiInfuser extends GuiContainer {

    private static final ResourceLocation TEXTURE = new ResourceLocation(ImaginatioTechnika.MOD_ID+":"+"textures/gui/infuser.png");
    private final InventoryPlayer player;
    private final TileEntityInfuser tileentity;

    public GuiInfuser(InventoryPlayer player, TileEntityInfuser tileentity) {
        super(new ContainerInfuser(player, tileentity));
        this.player = player;
        this.tileentity = tileentity;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String tilename = this.tileentity.getDisplayName().getUnformattedComponentText();
        this.fontRenderer.drawString(tilename, (this.xSize / 2 - this.fontRenderer.getStringWidth(tilename) / 2) + 3, 0, 16777215);
        this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 122, this.ySize - 94, 16777215);
        this.fontRenderer.drawString(this.tileentity.getEnergyStored() +"/"+this.tileentity.getMaxEnergyStored()+" FE",90,72,16777215);
    }

    private int getStoredPowerScaled(int pixels){
        int currentpower = this.tileentity.getEnergyStored();
        int maxpower = this.tileentity.getMaxEnergyStored();

        return currentpower != 0 && maxpower != 0? currentpower*pixels/maxpower:0;
    }

    private int getProgressScaled(int pixels){
        int i = this.tileentity.progress;
        return i != 0 ? i * pixels / 800 : 0;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0f,1.0f,1.0f);
        this.mc.getTextureManager().bindTexture(TEXTURE);
        this.drawTexturedModalRect(this.guiLeft,this.guiTop,0,0,this.xSize,this.ySize);

        int l = this.getProgressScaled(24);
        this.drawTexturedModalRect(this.guiLeft + 63, this.guiTop + 36, 176, 14, l + 1, 16);

        int p = this.getStoredPowerScaled(75);
        this.drawTexturedModalRect(this.guiLeft+152, this.guiTop+7, 176, 32, 16, 76-p);



    }
}
