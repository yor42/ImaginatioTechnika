package yor42.imaginatiotechnika.power;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.energy.EnergyStorage;

public class MachineEnergyStorage extends EnergyStorage {
    public MachineEnergyStorage(int capacity)
    {
        super(capacity, capacity, capacity, 0);
    }

    public MachineEnergyStorage(int capacity, int maxTransfer)
    {
        super(capacity, maxTransfer, maxTransfer, 0);
    }

    public MachineEnergyStorage(int capacity, int maxReceive, int maxExtract)
    {
        super(capacity, maxReceive, maxExtract, 0);
    }

    public MachineEnergyStorage(int capacity, int maxReceive, int maxExtract, int energy)
    {
        super(capacity, maxReceive, maxExtract, energy);
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate){
        return super.receiveEnergy(maxReceive, simulate);
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate){
        return super.extractEnergy(maxExtract, simulate);
    }

    @Override
    public int getEnergyStored(){
        return super.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored(){
        return super.getMaxEnergyStored();
    }

    @Override
    public boolean canExtract(){
        return super.canExtract();
    }


    @Override
    public boolean canReceive(){
        return super.canReceive();
    }

    public void readfromNBT(NBTTagCompound compound){
        this.energy = compound.getInteger("energy");
        this.capacity = compound.getInteger("capacity");
        this.maxReceive = compound.getInteger("maxReceive");
        this.maxExtract = compound.getInteger("maxExtract");
    }

    public void writetoNBT(NBTTagCompound compound){
        compound.setInteger("energy", this.energy);
        compound.setInteger("capacity", this.capacity);
        compound.setInteger("maxReceive", this.maxReceive);
        compound.setInteger("maxExtract", this.maxExtract);
    }
}
