package yor42.imaginatiotechnika.util.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import yor42.imaginatiotechnika.gameobjects.blocks.container.ContainerInfuser;
import yor42.imaginatiotechnika.gameobjects.blocks.container.ContainerOriginiumGenenrator;
import yor42.imaginatiotechnika.gameobjects.blocks.gui.GuiInfuser;
import yor42.imaginatiotechnika.gameobjects.blocks.gui.GuiOriginiumGenerator;
import yor42.imaginatiotechnika.gameobjects.blocks.tileentities.TileEntityInfuser;
import yor42.imaginatiotechnika.gameobjects.blocks.tileentities.TileEntityOriginiumGenerator;
import yor42.imaginatiotechnika.util.Data;

import javax.annotation.Nullable;

public class GuiHandler implements IGuiHandler {
    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(ID == Data.GUI_ORIGINIUM_GENERATOR) return new ContainerOriginiumGenenrator(player.inventory,(TileEntityOriginiumGenerator)world.getTileEntity(new BlockPos(x,y,z)));
        if(ID == Data.GUI_INFUSER) return new ContainerInfuser(player.inventory,(TileEntityInfuser) world.getTileEntity(new BlockPos(x,y,z)));
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(ID == Data.GUI_ORIGINIUM_GENERATOR) return new GuiOriginiumGenerator(player.inventory,(TileEntityOriginiumGenerator)world.getTileEntity(new BlockPos(x,y,z)));
        if(ID == Data.GUI_INFUSER) return new GuiInfuser(player.inventory,(TileEntityInfuser) world.getTileEntity(new BlockPos(x,y,z)));
        return null;
    }
}
