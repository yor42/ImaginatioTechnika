package yor42.imaginatiotechnika.gameobjects.creativetabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import yor42.imaginatiotechnika.init.BlockInit;
import yor42.imaginatiotechnika.init.ItemInit;

public class ImaginatioDiscs extends CreativeTabs {
    public ImaginatioDiscs(String label) {
        super("imaginatiotechnikadiscs");
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(ItemInit.disc_revenge);
    }
}
