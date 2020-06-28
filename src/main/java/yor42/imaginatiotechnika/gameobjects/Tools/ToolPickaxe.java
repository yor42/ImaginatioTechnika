package yor42.imaginatiotechnika.gameobjects.Tools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemPickaxe;
import yor42.imaginatiotechnika.init.ItemInit;
import yor42.imaginatiotechnika.util.IHasModel;

import static yor42.imaginatiotechnika.ImaginatioTechnika.Clientproxy;

public class ToolPickaxe extends ItemPickaxe implements IHasModel {
    public ToolPickaxe(String name, ToolMaterial material, CreativeTabs Tab) {
        super(material);
        //레지스트리 이름
        setRegistryName(name);
        //크리에이티브 탭
        setCreativeTab(Tab);
        //유니코드 이름 번역 키
        setUnlocalizedName(name);


        //아이템 리스트에 추가
        ItemInit.ITEM_LIST.add(this);
    }


    @Override
    public void registerModels() {
        Clientproxy.registerItemRenderer(this, 0, "inventory");
    }

}
