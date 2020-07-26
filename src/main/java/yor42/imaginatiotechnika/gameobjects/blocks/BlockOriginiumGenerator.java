package yor42.imaginatiotechnika.gameobjects.blocks;

import com.google.common.base.Optional;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import yor42.imaginatiotechnika.ImaginatioTechnika;
import yor42.imaginatiotechnika.gameobjects.blocks.tileentities.TileEntityOriginiumGenerator;
import net.minecraft.block.BlockFurnace;
import yor42.imaginatiotechnika.init.BlockInit;
import yor42.imaginatiotechnika.util.Data;

import java.util.Collection;
import java.util.Random;

public class BlockOriginiumGenerator extends BlockBase {
    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    public static final PropertyBool ACTIVE = PropertyBool.create("active");

    public BlockOriginiumGenerator(String name) {
        super(name, Material.IRON, ImaginatioTechnika.ImaginatioMachines);
        setSoundType(SoundType.METAL);
        setHardness(15.0F);
        setResistance(25.0F);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING,  EnumFacing.NORTH).withProperty(ACTIVE, false));
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(BlockInit.ORIGINIUM_GENERATOR);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntityOriginiumGenerator) {
                playerIn.openGui(ImaginatioTechnika.INSTANCE, Data.GUI_ORIGINIUM_GENERATOR, worldIn, pos.getX(), pos.getY(), pos.getZ());
            }

        }
        return true;
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        {
            if (!worldIn.isRemote)
            {
                IBlockState north = worldIn.getBlockState(pos.north());
                IBlockState south = worldIn.getBlockState(pos.south());
                IBlockState west = worldIn.getBlockState(pos.west());
                IBlockState east = worldIn.getBlockState(pos.east());
                EnumFacing face = state.getValue(FACING);

                if (face == EnumFacing.NORTH && north.isFullBlock() && !south.isFullBlock()) face = EnumFacing.SOUTH;
                else if (face == EnumFacing.SOUTH && south.isFullBlock() && !north.isFullBlock()) face = EnumFacing.NORTH;
                else if (face == EnumFacing.WEST && west.isFullBlock() && !east.isFullBlock()) face = EnumFacing.EAST;
                else if (face == EnumFacing.EAST && east.isFullBlock() && !west.isFullBlock()) face = EnumFacing.WEST;
                worldIn.setBlockState(pos, state.withProperty(FACING, face), 2);

            }
        }
    }

    public static void setState(boolean active, World worldin, BlockPos pos){
        IBlockState state = worldin.getBlockState(pos);
        TileEntity tileentity = worldin.getTileEntity(pos);

        if(active) worldin.setBlockState(pos,BlockInit.ORIGINIUM_GENERATOR.getDefaultState().withProperty(FACING, state.getValue(FACING)).withProperty(ACTIVE, true), 3);
        else worldin.setBlockState(pos,BlockInit.ORIGINIUM_GENERATOR.getDefaultState().withProperty(FACING, state.getValue(FACING)).withProperty(ACTIVE, false), 3);

        if(tileentity != null) {
            tileentity.validate();
            worldin.setTileEntity(pos, tileentity);
        }

    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state){
        return new TileEntityOriginiumGenerator();
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        worldIn.setBlockState(pos,this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite()),2);
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state){
        TileEntityOriginiumGenerator tileentity = (TileEntityOriginiumGenerator)worldIn.getTileEntity(pos);
        worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), tileentity.handler.getStackInSlot(0)));
        super.breakBlock(worldIn, pos, state);
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public IBlockState withRotation(IBlockState state, Rotation rot) {
        return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
        return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{ACTIVE, FACING});
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        EnumFacing facing = EnumFacing.getFront(meta);
        if(facing.getAxis() == EnumFacing.Axis.Y) facing = EnumFacing.NORTH;
        return this.getDefaultState().withProperty(FACING, facing);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return ((EnumFacing)state.getValue(FACING)).getIndex();
    }
}
