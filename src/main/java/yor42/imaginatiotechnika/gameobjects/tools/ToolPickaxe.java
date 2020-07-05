package yor42.imaginatiotechnika.gameobjects.tools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemPickaxe;
import yor42.imaginatiotechnika.init.ItemInit;
import yor42.imaginatiotechnika.util.IHasModel;

import static yor42.imaginatiotechnika.ImaginatioTechnika.Clientproxy;

public class ToolPickaxe extends ItemPickaxe implements IHasModel {
    public ToolPickaxe(String name, ToolMaterial material, CreativeTabs Tab) {
        super(material);
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
