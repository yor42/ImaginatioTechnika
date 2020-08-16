package yor42.imaginatiotechnika.init;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import yor42.imaginatiotechnika.gameobjects.blocks.BlockBase;
import yor42.imaginatiotechnika.gameobjects.blocks.BlockFluids;
import yor42.imaginatiotechnika.gameobjects.blocks.BlockInfuser;
import yor42.imaginatiotechnika.gameobjects.blocks.BlockOriginiumGenerator;

import java.util.ArrayList;
import java.util.List;

public class BlockInit {
    public static final List<Block> BLOCK_LIST = new ArrayList<>();
    public static final List<Block> MACHINE_LIST = new ArrayList<>();

    public static final Block D32_BLOCK = new BlockBase("d32_block", Material.IRON, CreativeTabs.BUILDING_BLOCKS);
    public static final Block RMA7024_ORE = new BlockBase("rma7024_ore", Material.ROCK, CreativeTabs.BUILDING_BLOCKS);

    //machines
    public static final Block ORIGINIUM_GENERATOR = new BlockOriginiumGenerator("originium_generator", false);
    public static final Block INFUSER = new BlockInfuser("infuser");

    //liquid
    public static final Block COLLAPSE_FLUID = new BlockFluids("collapse_fluid", FluidInit.COLLAPSE_FLUID, Material.WATER);
}
