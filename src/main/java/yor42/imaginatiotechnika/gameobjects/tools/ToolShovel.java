package yor42.imaginatiotechnika.gameobjects.tools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSpade;
import yor42.imaginatiotechnika.init.ItemInit;
import yor42.imaginatiotechnika.util.IHasModel;

import static yor42.imaginatiotechnika.ImaginatioTechnika.Clientproxy;

public class ToolShovel extends ItemSpade implements IHasModel {
    public ToolShovel(String name, ToolMaterial material, CreativeTabs Tab) {
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
