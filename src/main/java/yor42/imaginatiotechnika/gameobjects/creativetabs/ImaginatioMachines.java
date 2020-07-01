package yor42.imaginatiotechnika.gameobjects.creativetabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import yor42.imaginatiotechnika.init.BlockInit;

public class ImaginatioMachines extends CreativeTabs {
    public ImaginatioMachines(String label) {
        super("imaginatiotechnikamachines");
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(BlockInit.ORIGINIUM_GENERATOR);
    }
}
