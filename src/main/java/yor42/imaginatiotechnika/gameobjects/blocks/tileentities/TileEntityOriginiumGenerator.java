package yor42.imaginatiotechnika.gameobjects.blocks.tileentities;

import net.minecraft.block.BlockHopper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import yor42.imaginatiotechnika.Configs;
import yor42.imaginatiotechnika.ImaginatioTechnika;
import yor42.imaginatiotechnika.gameobjects.blocks.BlockOriginiumGenerator;
import yor42.imaginatiotechnika.gameobjects.blocks.container.ContainerOriginiumGenenrator;
import yor42.imaginatiotechnika.init.ItemInit;

import javax.annotation.Nullable;

import static yor42.imaginatiotechnika.gameobjects.blocks.BlockOriginiumGenerator.FACING;

public class TileEntityOriginiumGenerator extends TileEntityLockable implements ITickable, IEnergyStorage {

    //count of item slots
    private int maxextract = Configs.POWERGEN.OriginiumGenerator_maxextract;
    public ItemStackHandler handler = new ItemStackHandler(1);
    private EnergyStorage storage = new EnergyStorage(30000, 0 , maxextract);
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
                    if (Progress == getBurnTime(handler.getStackInSlot(0))) {
                        energy += Configs.POWERGEN.OriginiumGenerator_RFperItem;
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
        transferenergy();
    }

    private void transferenergy(){
        IBlockState state = getWorld().getBlockState(getPos());
        EnumFacing enumfacing = state.getValue(FACING).getOpposite();
        TileEntity tileEntity = world.getTileEntity(getPos().offset(enumfacing));
        if( tileEntity != null){
            if (tileEntity.hasCapability(CapabilityEnergy.ENERGY, state.getValue(FACING))){
                if(energy >= maxextract && tileEntity.getCapability(CapabilityEnergy.ENERGY, state.getValue(FACING)).canReceive()){
                   extractEnergy(maxextract, false);
                   tileEntity.getCapability(CapabilityEnergy.ENERGY, state.getValue(FACING)).receiveEnergy(maxextract, false);
                }
            }
        }
    }

    private boolean isItemFuel(ItemStack item) {
        return getBurnTime(item)>0;
    }

    private int getBurnTime(ItemStack stack) {
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

    @Override
    public String getName() {
        return hasCustomName() ? Customname : "container.grinder";
    }

    @Override
    public boolean hasCustomName() {
        return Customname != null && Customname.length() > 0;
    }

    @Nullable
    @Override
    public ITextComponent getDisplayName() {
        return new TextComponentTranslation("container.originium_generator");
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate)
    {
        return 0;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate)
    {
        if (!simulate)
            energy -= maxExtract;
        return maxExtract;
    }

    public int getEnergyStored(){
        return this.energy;
    }

    public int getMaxEnergyStored(){
        return this.storage.getMaxEnergyStored();
    }

    @Override
    public boolean canExtract()
    {
        return maxextract>0;
    }

    @Override
    public boolean canReceive()
    {
        return false;
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
    public int getFieldCount() {
        return 2;
    }

    @Override
    public void clear() {
        for (int i = 0; i < handler.getSlots(); ++i)
        {
            handler.getStackInSlot(i).setCount(0);
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

    @Override
    public int getSizeInventory() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return handler.getStackInSlot(0).isEmpty();
    }


    @Override
    public ItemStack getStackInSlot(int index) {
        return handler.getStackInSlot(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        if (handler.getStackInSlot(index) != null)
        {
            ItemStack itemstack;

            if (handler.getStackInSlot(index).getMaxStackSize() <= count)
            {
                itemstack = handler.getStackInSlot(index);
                handler.getStackInSlot(index).setCount(0);
                return itemstack;
            }
            else
            {
                itemstack = handler.getStackInSlot(index).splitStack(count);

                if (handler.getStackInSlot(index).getCount() == 0)
                {
                    handler.getStackInSlot(index).setCount(0);
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        handler.setStackInSlot(index, stack);

        if (stack != null && stack.getCount() > getInventoryStackLimit())
        {
            stack.setCount(getInventoryStackLimit());
        }
        markDirty();
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    public boolean isUsableByPlayer(EntityPlayer player)
    {
        return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return stack.getItem() == ItemInit.pureoriginium;
    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
        return new ContainerOriginiumGenenrator(playerInventory, this);
    }

    @Override
    public String getGuiID() {
        return ImaginatioTechnika.MOD_ID+ ":originiumgenerator";
    }
}
