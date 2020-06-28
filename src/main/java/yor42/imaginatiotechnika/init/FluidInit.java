package yor42.imaginatiotechnika.init;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import yor42.imaginatiotechnika.ImaginatioTechnika;
import yor42.imaginatiotechnika.gameobjects.blocks.fluids.FluidLiquid;

public class FluidInit {

    public static final Fluid COLLAPSE_FLUID = new FluidLiquid("collapse_fluid", new ResourceLocation(ImaginatioTechnika.MOD_ID+":"+"blocks/collapse_fluid_still"), new ResourceLocation(ImaginatioTechnika.MOD_ID+":"+"blocks/collapse_fluid_flow"), 12);

    public static void registerFluid(){
        registerFluid(COLLAPSE_FLUID);
    }

    public static void registerFluid(Fluid fluid){
        FluidRegistry.registerFluid(fluid);
        FluidRegistry.addBucketForFluid(fluid);
    }
}
