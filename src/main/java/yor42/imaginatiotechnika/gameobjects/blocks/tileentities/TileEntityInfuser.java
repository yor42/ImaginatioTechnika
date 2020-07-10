package yor42.imaginatiotechnika.gameobjects.blocks.tileentities;

import net.minecraft.entity.player.EntityPlayer;
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
import yor42.imaginatiotechnika.gameobjects.blocks.BlockInfuser;
import yor42.imaginatiotechnika.gameobjects.recipes.InfuserRecipe;
import yor42.imaginatiotechnika.power.MachineEnergyStorage;


import javax.annotation.Nullable;

public class TileEntityInfuser extends TileEntity implements ITickable {

    public ItemStackHandler stackhandler = new ItemStackHandler(4);
    public MachineEnergyStorage storage = new MachineEnergyStorage(10000, 300 , 0);

    ItemStack OutputItem = ItemStack.EMPTY;
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

    @Override
    public void update() {

        //debugging code.
        if(world.isBlockPowered(pos)) energy += 100;

        ItemStack[] inputs = new ItemStack[] {stackhandler.getStackInSlot(0),stackhandler.getStackInSlot(1),stackhandler.getStackInSlot(2)};
        if(energy >= Configs.MACHINES.INFUSER_RF_PERTICK ) {
            if (progress > 0) {
                energy -= Configs.MACHINES.INFUSER_RF_PERTICK;
                progress++;
                BlockInfuser.setState(true, world, pos);

                if (progress == 800) {
                    if (stackhandler.getStackInSlot(3).getCount() > 0) {
                        stackhandler.getStackInSlot(3).grow(1);
                    }
                    else {
                        stackhandler.insertItem(3, OutputItem, false);
                    }
                    OutputItem = ItemStack.EMPTY;
                    progress = 0;
                }
            }
            else {
                if (!inputs[0].isEmpty() && !inputs[1].isEmpty() && !inputs[2].isEmpty()) {
                    ItemStack output = InfuserRecipe.getInstance().getInfusingResult(inputs[0], inputs[1], inputs[2]);
                    if (!output.isEmpty()) {
                        OutputItem = output;
                        progress++;
                        inputs[0].shrink(1);
                        inputs[1].shrink(1);
                        inputs[2].shrink(1);
                        stackhandler.setStackInSlot(0, inputs[0]);
                        stackhandler.setStackInSlot(1, inputs[1]);
                        stackhandler.setStackInSlot(2, inputs[2]);

                        energy -= Configs.MACHINES.INFUSER_RF_PERTICK;
                    }
                }
            }
        }
        else {
            BlockInfuser.setState(false, world, pos);
        }

    }

    public boolean isUseableByPlayer(EntityPlayer player){
        return this.world.getTileEntity(this.pos) == this && player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
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
        this.storage.readFromNBT(compound);
        this.energy = compound.getInteger("GUIEnergy");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("progress", (short)this.progress);
        compound.setTag("Inventory", this.stackhandler.serializeNBT());
        compound.setString("name", getDisplayName().toString());
        compound.setInteger("GUIEnergy",this.energy);
        this.storage.writeToNBT(compound);
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
}
