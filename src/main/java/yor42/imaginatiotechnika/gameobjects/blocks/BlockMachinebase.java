package yor42.imaginatiotechnika.gameobjects.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockMachinebase extends Block implements ITileEntityProvider {


    public BlockMachinebase(Material blockMaterialIn, MapColor blockMapColorIn) {
        super(blockMaterialIn, blockMapColorIn);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return null;
    }
}
