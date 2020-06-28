package yor42.imaginatiotechnika.gameobjects.creativetabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import yor42.imaginatiotechnika.init.ItemInit;

public class ImaginatioResources extends CreativeTabs {
    public ImaginatioResources(String label) {
        super("imaginatiotechnikaresources");
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(ItemInit.pureoriginium);
    }
}
