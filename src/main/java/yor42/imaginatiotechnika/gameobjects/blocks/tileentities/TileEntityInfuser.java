package yor42.imaginatiotechnika.gameobjects.blocks.tileentities;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import yor42.imaginatiotechnika.Configs;
import yor42.imaginatiotechnika.power.MachineEnergyStorage;

import javax.annotation.Nullable;

public class TileEntityInfuser extends TileEntity implements ITickable {

    private ItemStackHandler stackhandler = new ItemStackHandler(4);
    private MachineEnergyStorage storage = new MachineEnergyStorage(25000);

    ItemStack WorkInProgress = ItemStack.EMPTY;
    public int energy = storage.getEnergyStored();
    private String name;
    public int progress;

    @Override
    public boolean hasCapability(Capability capability, @Nullable EnumFacing facing) {
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return true;
        return capability == CapabilityEnergy.ENERGY;
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return (T) this.stackhandler;
        if(capability == CapabilityEnergy.ENERGY) return (T) this.storage;

        return super.getCapability(capability, facing);
    }

    @Nullable
    @Override
    public ITextComponent getDisplayName() {
        return new TextComponentTranslation("container.infuser");
    }

    public int getEnergyStored(){
        return this.storage.getEnergyStored();
    }

    public int getMaxEnergyStored(){
        return this.storage.getMaxEnergyStored();
    }


    //loading machine data from savefile
    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.stackhandler.deserializeNBT(compound.getCompoundTag("Inventory"));
        this.progress = compound.getInteger("progress");
        this.name = compound.getString("name");
        this.storage.readfromNBT(compound);
        this.energy = compound.getInteger("GUIEnergy");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("progress", (short)this.progress);
        compound.setTag("Inventory", this.stackhandler.serializeNBT());
        compound.setString("name", getDisplayName().toString());
        compound.setInteger("GUIEnergy",this.energy);
        this.storage.writetoNBT(compound);
        return compound;
    }

    public int getField(int id){
        switch (id){
            case 0:
                return this.energy;
            case 1:
                return this.progress;
            default:
                return 0;
        }
    }

    public void setField(int id, int value){
        switch (id){
            case 0:
                this.energy = value;
            case 1:
                this.progress = value;
        }
    }

    @Override
    public void update() {
        ItemStack[] inputs = new ItemStack[] {stackhandler.getStackInSlot(0),stackhandler.getStackInSlot(1),stackhandler.getStackInSlot(2)};

        if(energy >= 100 && progress > 0){
            energy += Configs.MACHINES.INFUSER_RF_PERTICK;
        }
     }
}
