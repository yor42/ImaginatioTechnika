package yor42.imaginatiotechnika.gameobjects.blocks.tileentities;

import net.minecraft.block.BlockFurnace;
import net.minecraft.block.state.IBlockState;
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
import net.minecraftforge.fml.common.event.FMLEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import yor42.imaginatiotechnika.Configs;
import yor42.imaginatiotechnika.gameobjects.blocks.BlockOriginiumGenerator;
import yor42.imaginatiotechnika.init.ItemInit;
import yor42.imaginatiotechnika.power.MachineEnergyStorage;

import javax.annotation.Nullable;

import static yor42.imaginatiotechnika.gameobjects.blocks.BlockOriginiumGenerator.FACING;

public class TileEntityOriginiumGenerator extends TileEntity implements ITickable {


    //count of item slots
    public ItemStackHandler handler = new ItemStackHandler(1);

    private MachineEnergyStorage storage = new MachineEnergyStorage(30000);
    public int energy = storage.getEnergyStored();
    private String name;
    public int Burntime;

    public boolean isActive()
    {
        return this.Burntime > 0;
    }

    @Override
    public void update() {
        boolean flag = this.isActive();
        boolean flag1 = false;

        if (!this.world.isRemote) {
            if (!handler.getStackInSlot(0).isEmpty() && isItemFuel(handler.getStackInSlot(0))) {
                if (this.energy < getMaxEnergyStored()) {
                    Burntime++;
                    flag1 = true;
                    if (Burntime == 100) {
                        energy += getFuelValue(handler.getStackInSlot(0));
                        handler.getStackInSlot(0).shrink(1);
                        Burntime = 0;
                    }
                }
            } else {
                Burntime = 0;
            }
        }

        if(this.energy > getMaxEnergyStored()){
            this.energy = getMaxEnergyStored();
        }

        if (flag != this.isActive())
        {
            flag1 = true;
            BlockOriginiumGenerator.setState(this.isActive(), this.world, this.pos);
        }
        if (flag1)
        {
            this.markDirty();
        }
    }

    private boolean isItemFuel(ItemStack item) {
        return getFuelValue(item)>0;
    }

    private int getFuelValue(ItemStack stack) {
        if(stack.getItem() == ItemInit.pureoriginium){
            return Configs.POWERGEN.ORIGINIUM_BURNTIME;
        }
        else return 0;
    }

    @Override
    public <T> T getCapability(Capability <T> capability, EnumFacing facing){
        IBlockState state = getWorld().getBlockState(getPos());
        if(facing == state.getValue(FACING).getOpposite()) {
            if (capability == CapabilityEnergy.ENERGY) {
                return (T) this.storage;
            }
        }
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return (T)this.handler;
        return super.getCapability(capability, facing);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        if(capability == CapabilityEnergy.ENERGY)
            return true;
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return true;
        return super.hasCapability(capability, facing);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound){
        super.writeToNBT(compound);
        compound.setTag("Inventory", this.handler.serializeNBT());
        compound.setInteger("Burntime", Burntime);
        compound.setInteger("GUIEnergy", this.energy);
        compound.setString("Name", getDisplayName().toString());
        this.storage.writetoNBT(compound);
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.handler.deserializeNBT(compound.getCompoundTag("Inventory"));
        this.Burntime = compound.getInteger("Burntime");
        this.energy = compound.getInteger("GUIEnergy");
        this.name =  compound.getString("Name");
        this.storage.readfromNBT(compound);
    }

    @Nullable
    @Override
    public ITextComponent getDisplayName() {
        return new TextComponentTranslation("container.originium_generator");
    }

    public int getEnergyStored(){
        return this.energy;
    }

    public int getMaxEnergyStored(){
        return this.storage.getMaxEnergyStored();
    }

    public int getField(int id){
        switch (id){
            case 0:
                return this.energy;
            case 1:
                return this.Burntime;
            default:
                return 0;
        }
    }

    public void setField(int id, int value){
        switch (id){
            case 0:
                this.energy = value;
            case 1:
                this.Burntime = value;
        }
    }

    public boolean isUseableByPlayer(EntityPlayer player){
        return this.world.getTileEntity(this.pos) == this && player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
    }

}
