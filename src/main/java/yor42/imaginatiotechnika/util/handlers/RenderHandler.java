package yor42.imaginatiotechnika.util.handlers;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import yor42.imaginatiotechnika.ImaginatioTechnika;
import yor42.imaginatiotechnika.init.BlockInit;

public class RenderHandler {

    public static void registerCustomMeshesandStates(){
        ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(BlockInit.COLLAPSE_FLUID), new ItemMeshDefinition() {
            @Override
            public ModelResourceLocation getModelLocation(ItemStack stack) {
                return new ModelResourceLocation(ImaginatioTechnika.MOD_ID+":"+"collapse_fluid", "fluid");
            }
        });

        ModelLoader.setCustomStateMapper(BlockInit.COLLAPSE_FLUID, new StateMapperBase() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                return new ModelResourceLocation(ImaginatioTechnika.MOD_ID+":"+"collapse_fluid", "fluid");
            }
        });
    }
}
