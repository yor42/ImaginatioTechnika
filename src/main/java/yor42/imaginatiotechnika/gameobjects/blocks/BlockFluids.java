package yor42.imaginatiotechnika.gameobjects.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import yor42.imaginatiotechnika.init.BlockInit;
import yor42.imaginatiotechnika.init.ItemInit;

public class BlockFluids extends BlockFluidClassic {




    public BlockFluids(String name, Fluid fluid, Material material){
        super(fluid, material);
        setUnlocalizedName(name);
        setRegistryName(name);

        BlockInit.BLOCK_LIST.add(this);
        ItemInit.ITEM_LIST.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state){
        return EnumBlockRenderType.MODEL;
    }
}
