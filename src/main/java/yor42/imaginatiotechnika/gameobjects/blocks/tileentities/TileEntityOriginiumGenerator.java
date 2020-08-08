package yor42.imaginatiotechnika.gameobjects.blocks.tileentities;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import yor42.imaginatiotechnika.Configs;
import yor42.imaginatiotechnika.gameobjects.blocks.BlockOriginiumGenerator;
import yor42.imaginatiotechnika.init.ItemInit;

import javax.annotation.Nullable;

import static yor42.imaginatiotechnika.gameobjects.blocks.BlockOriginiumGenerator.FACING;

public class TileEntityOriginiumGenerator extends TileEntity implements ITickable {

    //count of item slots
    public ItemStackHandler handler = new ItemStackHandler(1);
    private EnergyStorage storage = new EnergyStorage(30000, 0 , 100);
    public int energy = storage.getEnergyStored();
    private String Customname;
    public int Progress;

    public boolean isActive()
    {
        return this.Progress > 0;
    }

    @Override
    public void update() {
        boolean flag = this.isActive();
        boolean flag1 = false;
        if (!this.world.isRemote) {
            if (!handler.getStackInSlot(0).isEmpty() && isItemFuel(handler.getStackInSlot(0))) {
                if (this.energy < getMaxEnergyStored()) {
                    Progress++;
                    flag1 = true;
                    if (Progress == 100) {
                        energy += getFuelValue(handler.getStackInSlot(0));
                        handler.getStackInSlot(0).shrink(1);
                        Progress = 0;
                    }
                }
                else{
                    Progress = 0;
                }
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
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound NBT = new NBTTagCompound();
        NBT.setTag("Inventory", this.handler.serializeNBT());
        NBT.setInteger("Burntime", Progress);
        NBT.setInteger("GUIEnergy", this.energy);
        return new SPacketUpdateTileEntity(getPos(), 1, NBT);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt){
        NBTTagCompound tag = pkt.getNbtCompound();
        this.handler.deserializeNBT(tag.getCompoundTag("Inventory"));
        this.Progress = tag.getInteger("Burntime");
        this.energy = tag.getInteger("GUIEnergy");
    }

    @Override
    public <T> T getCapability(Capability <T> capability, EnumFacing facing){
        IBlockState state = getWorld().getBlockState(getPos());
            if (capability == CapabilityEnergy.ENERGY) {
                if(facing == state.getValue(FACING).getOpposite()) {
                    return (T) this.storage;
                }
            }
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return (T)this.handler;
        return super.getCapability(capability, facing);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {

        IBlockState state = getWorld().getBlockState(getPos());
        if (capability == CapabilityEnergy.ENERGY) {
            return facing == state.getValue(FACING).getOpposite();
        }

        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return true;
        return super.hasCapability(capability, facing);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound){
        super.writeToNBT(compound);
        compound.setTag("Inventory", this.handler.serializeNBT());
        compound.setInteger("Progress", Progress);
        compound.setInteger("GUIEnergy", this.energy);
        compound.setString("Customname", getDisplayName().toString());
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.handler.deserializeNBT(compound.getCompoundTag("Inventory"));
        this.Progress = compound.getInteger("Progress");
        this.energy = compound.getInteger("GUIEnergy");
        this.Customname =  compound.getString("Customname");
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
                return this.Progress;
            default:
                return 0;
        }
    }

    public void setField(int id, int value){
        switch (id){
            case 0:
                this.energy = value;
            case 1:
                this.Progress = value;
        }
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        return super.getUpdateTag();
    }

    @Override
    public void handleUpdateTag(NBTTagCompound tag) {
        super.handleUpdateTag(tag);
    }

    public boolean isUsableByPlayer(EntityPlayer player)
    {
        return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
    }

}
