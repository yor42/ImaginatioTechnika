package yor42.imaginatiotechnika.gameobjects.Creativetabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import yor42.imaginatiotechnika.init.ItemInit;

public class ImaginatioTools extends CreativeTabs {
    public ImaginatioTools(String label) {
        super("ImaginatioTools");
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(ItemInit.d32_pickaxe);
    }
}
