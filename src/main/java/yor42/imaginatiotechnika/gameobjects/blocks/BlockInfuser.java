package yor42.imaginatiotechnika.gameobjects.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import yor42.imaginatiotechnika.ImaginatioTechnika;
import yor42.imaginatiotechnika.init.BlockInit;
import yor42.imaginatiotechnika.util.Data;

import java.util.Random;

public class BlockInfuser extends BlockBase {

    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    public static final PropertyBool ACTIVE = PropertyBool.create("active");

    public BlockInfuser(String name) {
        super(name, Material.IRON, ImaginatioTechnika.ImaginatioMachines);
        setSoundType(SoundType.METAL);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING,  EnumFacing.NORTH).withProperty(ACTIVE, false));
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(BlockInit.INFUSER);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(!worldIn.isRemote){
            playerIn.openGui(ImaginatioTechnika.INSTANCE, Data.GUI_ORIGINIUM_GENERATOR, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }

        return true;
    }
}
