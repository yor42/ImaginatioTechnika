package yor42.imaginatiotechnika.gameobjects.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import yor42.imaginatiotechnika.ImaginatioTechnika;
import yor42.imaginatiotechnika.init.BlockInit;
import yor42.imaginatiotechnika.init.ItemInit;
import yor42.imaginatiotechnika.util.IHasModel;

import javax.annotation.Nullable;

import java.util.Objects;

import static yor42.imaginatiotechnika.ImaginatioTechnika.Clientproxy;

public class BlockMachinebase extends BlockContainer implements ITileEntityProvider, IHasModel {


    public BlockMachinebase(String name) {
        super(Material.IRON);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(ImaginatioTechnika.ImaginatioMachines);

        BlockInit.MACHINE_LIST.add(this);
        ItemInit.ITEM_LIST.add(new ItemBlock(this).setRegistryName(Objects.requireNonNull(this.getRegistryName())));

        setSoundType(SoundType.METAL);
        setHardness(15.0F);
        setHarvestLevel("pickaxe", 2);
        setResistance(20.0F);
        setTickRandomly(false);
        useNeighborBrightness = false;
    }

    @Override
    public void registerModels() {
        Clientproxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return null;
    }
}
