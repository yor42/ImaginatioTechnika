package yor42.imaginatiotechnika.init;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;
import yor42.imaginatiotechnika.gameobjects.items.ItemBase;
import yor42.imaginatiotechnika.gameobjects.tools.ToolAxe;
import yor42.imaginatiotechnika.gameobjects.tools.ToolPickaxe;
import yor42.imaginatiotechnika.gameobjects.tools.ToolShovel;

import java.util.ArrayList;
import java.util.List;

public class ItemInit {
    //아이템 목록 어레이 선언
    public static final List<Item> ITEM_LIST = new ArrayList<Item>();
    public static final List<Item> TOOL_LIST = new ArrayList<Item>();

    public static final Item.ToolMaterial TOOL_D32 = EnumHelper.addToolMaterial("tool_d32", 3, 2043, 10.0F, 2.0F, 9);
    public static final Item.ToolMaterial TOOL_RMA7024 = EnumHelper.addToolMaterial("tool_rma7024", 3, 350, 7.0F, 2.0F, 18);

    //기초 자원
    public static final Item pureoriginium = new ItemBase("pureoriginium", CreativeTabs.MISC);
    public static final Item d32_ingot = new ItemBase("D32_INGOT", CreativeTabs.MISC);
    public static final Item d32_nugget = new ItemBase("D32_nugget", CreativeTabs.MISC);
    public static final Item rma7024_ingot = new ItemBase("rma7024_ingot", CreativeTabs.MISC);
    public static final Item rma7024_nugget = new ItemBase("rma7024_nugget", CreativeTabs.MISC);
    public static final Item wisdomcube = new ItemBase("WISDOMCUBE", CreativeTabs.MISC);

    //도구
    public static final Item d32_pickaxe = new ToolPickaxe("D32_PICKAXE", TOOL_D32, CreativeTabs.TOOLS);
    public static final Item d32_shovel = new ToolShovel("D32_SHOVEL", TOOL_D32, CreativeTabs.TOOLS);
    public static final Item d32_axe = new ToolAxe("D32_AXE", TOOL_D32, CreativeTabs.TOOLS, 8.0F, 0.8F);
    public static final Item d32_hoe = new ToolAxe("D32_HOE", TOOL_D32, CreativeTabs.TOOLS, 8.0F, 0.8F);

    public static final Item rma7024_pickaxe = new ToolPickaxe("RMA7024_PICKAXE", TOOL_RMA7024, CreativeTabs.TOOLS);
    public static final Item rma7024_shovel = new ToolShovel("RMA7024_SHOVEL", TOOL_RMA7024, CreativeTabs.TOOLS);
    public static final Item rma7024_axe = new ToolAxe("RMA7024_AXE", TOOL_RMA7024, CreativeTabs.TOOLS, 9.0F, 1.0F);
    public static final Item rma7024 = new ToolAxe("RMA7024_HOE", TOOL_RMA7024, CreativeTabs.TOOLS, 9.0F, 1.0F);
}
