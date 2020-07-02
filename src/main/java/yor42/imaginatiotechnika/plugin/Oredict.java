package yor42.imaginatiotechnika.plugin;

import net.minecraftforge.oredict.OreDictionary;
import yor42.imaginatiotechnika.init.BlockInit;
import yor42.imaginatiotechnika.init.ItemInit;

public class Oredict {
    public static void registerOreDict(){
        OreDictionary.registerOre("gemOriginium", ItemInit.pureoriginium);

        OreDictionary.registerOre("oreRMA70-24", BlockInit.RMA7024_ORE);
        OreDictionary.registerOre("ingotRMA70-24", ItemInit.rma7024_ingot);
        OreDictionary.registerOre("nuggetRMA70-24", ItemInit.rma7024_nugget);

        OreDictionary.registerOre("ingotD32", ItemInit.d32_hoe);
        OreDictionary.registerOre("nuggetD32", ItemInit.d32_nugget);

    }
}
