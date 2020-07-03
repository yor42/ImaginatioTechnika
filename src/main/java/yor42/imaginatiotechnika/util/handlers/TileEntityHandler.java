package yor42.imaginatiotechnika.util.handlers;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import yor42.imaginatiotechnika.ImaginatioTechnika;
import yor42.imaginatiotechnika.gameobjects.blocks.tileentities.TileEntityInfuser;
import yor42.imaginatiotechnika.gameobjects.blocks.tileentities.TileEntityOriginiumGenerator;

public class TileEntityHandler {

    public static void registerTileentity(){
        GameRegistry.registerTileEntity(TileEntityOriginiumGenerator.class, new ResourceLocation(ImaginatioTechnika.MOD_ID+":"+"originium_generator"));
        GameRegistry.registerTileEntity(TileEntityInfuser.class, new ResourceLocation(ImaginatioTechnika.MOD_ID+":"+"infuser"));
    }

}
