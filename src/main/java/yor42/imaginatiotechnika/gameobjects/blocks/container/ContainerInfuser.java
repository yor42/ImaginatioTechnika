package yor42.imaginatiotechnika.gameobjects.blocks.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import yor42.imaginatiotechnika.gameobjects.blocks.tileentities.TileEntityInfuser;
import yor42.imaginatiotechnika.gameobjects.recipes.InfuserRecipe;

public class ContainerInfuser extends Container {

    private final TileEntityInfuser tileentity;
    private int progress, energy;



    public ContainerInfuser(InventoryPlayer playerinv, TileEntityInfuser tileentity) {
        this.tileentity = tileentity;
        IItemHandler itemHandler = tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,null);

        this.addSlotToContainer(new SlotItemHandler(itemHandler, 0, 26,20));
        this.addSlotToContainer(new SlotItemHandler(itemHandler, 1, 26,36));
        this.addSlotToContainer(new SlotItemHandler(itemHandler, 2, 26,52));
        this.addSlotToContainer(new SlotItemHandler(itemHandler, 3, 81,36));


        //adds player inventory
        for(int y = 0; y < 3; y++)
        {
            for(int x = 0; x < 9; x++)
            {
                this.addSlotToContainer(new Slot(playerinv, x + y*9 + 9, 8 + x*18, 84 + y*18));
            }
        }

        for(int x = 0; x < 9; x++)
        {
            this.addSlotToContainer(new Slot(playerinv, x, 8 + x * 18, 142));
        }

    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for(int i=0; i<this.listeners.size();i++){
            IContainerListener listener = (IContainerListener)this.listeners.get(i);

            if(this.energy != this.tileentity.getField(0)){
                listener.sendWindowProperty(this,0,this.tileentity.getField(0));
            }
            if(this.progress != this.tileentity.getField(1)){
                listener.sendWindowProperty(this,0,this.tileentity.getField(1));
            };
            this.energy = this.tileentity.getField(0);
            this.progress = this.tileentity.getField(1);
        }
    }

    @Override
    public void updateProgressBar(int id, int data) {
        this.tileentity.setField(id,data);
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return this.tileentity.isUseableByPlayer(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = (Slot)this.inventorySlots.get(index);

        if(slot != null && slot.getHasStack())
        {
            ItemStack stack1 = slot.getStack();
            stack = stack1.copy();

            if(index == 2)
            {
                if(!this.mergeItemStack(stack1, 4, 40, true)) return ItemStack.EMPTY;
                slot.onSlotChange(stack1, stack);
            }
            else if(!this.mergeItemStack(stack1, 4, 40, false))
            {
                return ItemStack.EMPTY;
            }
            if(stack1.isEmpty())
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();

            }
            if(stack1.getCount() == stack.getCount()) return ItemStack.EMPTY;
            slot.onTake(playerIn, stack1);
        }
        return stack;
    }
}
