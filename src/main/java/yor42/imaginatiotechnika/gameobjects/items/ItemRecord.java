package yor42.imaginatiotechnika.gameobjects.items;

import net.minecraft.util.SoundEvent;
import yor42.imaginatiotechnika.init.ItemInit;
import yor42.imaginatiotechnika.util.IHasModel;

import static yor42.imaginatiotechnika.ImaginatioTechnika.Clientproxy;

public class ItemRecord extends net.minecraft.item.ItemRecord implements IHasModel {
    protected ItemRecord(String name,String description, SoundEvent soundIn) {
        super(description, soundIn);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        ItemInit.ITEM_LIST.add(this);
    }

    @Override
    public void registerModels() {
        Clientproxy.registerItemRenderer(this, 0, "inventory");
    }
}
