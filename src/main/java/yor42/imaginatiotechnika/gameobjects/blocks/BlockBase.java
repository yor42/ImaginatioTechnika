package yor42.imaginatiotechnika.gameobjects.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import yor42.imaginatiotechnika.init.BlockInit;
import yor42.imaginatiotechnika.init.ItemInit;
import yor42.imaginatiotechnika.util.IHasModel;

import static yor42.imaginatiotechnika.ImaginatioTechnika.Clientproxy;

public class BlockBase extends Block implements IHasModel {

    public BlockBase(String name, Material material, CreativeTabs tab){
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(tab);

        BlockInit.BLOCK_LIST.add(this);
        ItemInit.ITEM_LIST.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    //모델 등록
    @Override
    public void registerModels() {
        Clientproxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
}
