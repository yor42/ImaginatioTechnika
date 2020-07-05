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
