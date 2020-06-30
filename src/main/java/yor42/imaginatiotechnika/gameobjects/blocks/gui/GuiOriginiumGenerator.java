package yor42.imaginatiotechnika.gameobjects.blocks.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import yor42.imaginatiotechnika.ImaginatioTechnika;
import yor42.imaginatiotechnika.gameobjects.blocks.container.ContainerOriginiumGenenrator;
import yor42.imaginatiotechnika.gameobjects.blocks.tileentities.TileEntityOriginiumGenerator;

import java.util.Objects;

public class GuiOriginiumGenerator extends GuiContainer {

    private static final ResourceLocation TEXTURE = new ResourceLocation(ImaginatioTechnika.MOD_ID+":"+"textures/gui/originium_generator.png");
    private final InventoryPlayer player;
    private final TileEntityOriginiumGenerator tileentity;

    public GuiOriginiumGenerator(InventoryPlayer player, TileEntityOriginiumGenerator tileentity) {
        super(new ContainerOriginiumGenenrator(player, tileentity));
        this.player = player;
        this.tileentity = tileentity;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String tileName = Objects.requireNonNull(this.tileentity.getDisplayName()).getUnformattedText();
        this.fontRenderer.drawString(tileName, (this.xSize/2 - this.fontRenderer.getStringWidth(tileName))/2,6, 16777215);
        this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(),7,this.ySize-94,16777215);
        this.fontRenderer.drawString(Integer.toString(this.tileentity.getEnergyStored()),115,72,16777215);
    }

    private int getBurnTimeScaled(int pixels){
        int i = this.tileentity.Burntime;
        return i!= 0? i*pixels/25:0;
    }

    private int getStoredPowerScaled(int pixels){
        int currentpower = this.tileentity.getEnergyStored();
        int maxpower = this.tileentity.getMaxEnergyStored();

        return currentpower != 0 && maxpower != 0? currentpower*pixels/maxpower:0;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

        GlStateManager.color(1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(TEXTURE);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0,0,this.xSize,this.ySize);

        int b = this.getBurnTimeScaled(24);
        this.drawTexturedModalRect(this.guiLeft+113,this.guiTop+32,176,14,b+1,16);

        int p = this.getStoredPowerScaled(75);
        this.drawTexturedModalRect(this.guiLeft+152, this.guiTop+7, 176, 32, 16, 76-p);


    }
}
