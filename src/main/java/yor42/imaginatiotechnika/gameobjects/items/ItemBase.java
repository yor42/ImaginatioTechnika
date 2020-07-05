package yor42.imaginatiotechnika.gameobjects.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import yor42.imaginatiotechnika.init.ItemInit;
import yor42.imaginatiotechnika.util.IHasModel;

import static yor42.imaginatiotechnika.ImaginatioTechnika.Clientproxy;

public class ItemBase extends Item implements IHasModel {


    public ItemBase(String name, CreativeTabs Tab)
    {
        setRegistryName(name);
        setCreativeTab(Tab);
        setUnlocalizedName(name);

        ItemInit.ITEM_LIST.add(this);
    }

    @Override
    public void registerModels() {
        Clientproxy.registerItemRenderer(this, 0, "inventory");
    }
}
