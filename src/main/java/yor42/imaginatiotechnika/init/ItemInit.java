package yor42.imaginatiotechnika.init;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.common.util.EnumHelper;
import yor42.imaginatiotechnika.ImaginatioTechnika;
import yor42.imaginatiotechnika.gameobjects.creativetabs.ImaginatioDiscs;
import yor42.imaginatiotechnika.gameobjects.creativetabs.ImaginatioResources;
import yor42.imaginatiotechnika.gameobjects.items.ItemBase;
import yor42.imaginatiotechnika.gameobjects.items.ItemRecord;
import yor42.imaginatiotechnika.gameobjects.tools.ToolAxe;
import yor42.imaginatiotechnika.gameobjects.tools.ToolHoe;
import yor42.imaginatiotechnika.gameobjects.tools.ToolPickaxe;
import yor42.imaginatiotechnika.gameobjects.tools.ToolShovel;
import yor42.imaginatiotechnika.util.handlers.SoundEventHandler;

import java.util.ArrayList;
import java.util.List;

public class ItemInit {
    public static final List<Item> ITEM_LIST = new ArrayList<>();
    public static final List<Item> TOOL_LIST = new ArrayList<>();

    public static final Item.ToolMaterial TOOL_D32 = EnumHelper.addToolMaterial("tool_d32", 3, 2043, 10.0F, 2.0F, 9);
    public static final Item.ToolMaterial TOOL_RMA7024 = EnumHelper.addToolMaterial("tool_rma7024", 3, 350, 7.0F, 2.0F, 18);

    //resource
    public static final Item pureoriginium = new ItemBase("pureoriginium", ImaginatioTechnika.ImaginatioResources);
    public static final Item d32_ingot = new ItemBase("d32_ingot", ImaginatioTechnika.ImaginatioResources);
    public static final Item d32_nugget = new ItemBase("d32_nugget", ImaginatioTechnika.ImaginatioResources);
    public static final Item rma7024_ingot = new ItemBase("rma7024_ingot", ImaginatioTechnika.ImaginatioResources);
    public static final Item rma7024_nugget = new ItemBase("rma7024_nugget", ImaginatioTechnika.ImaginatioResources);
    public static final Item wisdomcube = new ItemBase("wisdomcube", ImaginatioTechnika.ImaginatioResources);
    public static final Item manganese_ingot = new ItemBase("manganese_ingot", ImaginatioTechnika.ImaginatioResources);

    //tools
    public static final Item d32_pickaxe = new ToolPickaxe("d32_pickaxe", TOOL_D32, ImaginatioTechnika.ImaginatioTools);
    public static final Item d32_shovel = new ToolShovel("d32_shovel", TOOL_D32, ImaginatioTechnika.ImaginatioTools);
    public static final Item d32_axe = new ToolAxe("d32_axe", TOOL_D32, ImaginatioTechnika.ImaginatioTools, 8.0F, 0.8F);
    public static final Item d32_hoe = new ToolHoe("d32_hoe", TOOL_D32, ImaginatioTechnika.ImaginatioTools);

    public static final Item rma7024_pickaxe = new ToolPickaxe("rma7024_pickaxe", TOOL_RMA7024, ImaginatioTechnika.ImaginatioTools);
    public static final Item rma7024_shovel = new ToolShovel("rma7024_shovel", TOOL_RMA7024, ImaginatioTechnika.ImaginatioTools);
    public static final Item rma7024_axe = new ToolAxe("rma7024_axe", TOOL_RMA7024, ImaginatioTechnika.ImaginatioTools, 9.0F, 1.0F);
    public static final Item rma7024 = new ToolHoe("rma7024_hoe", TOOL_RMA7024, ImaginatioTechnika.ImaginatioTools);

    //Records
    public static final Item disc_revenge = new ItemRecord("disc_revenge", SoundEventHandler.JUKEBOX_REVENGE, ImaginatioTechnika.ImaginatioDiscs);
}
