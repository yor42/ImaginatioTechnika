package yor42.imaginatiotechnika.gameobjects.blocks.fluids;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class FluidLiquid extends Fluid {

    public FluidLiquid(String name, ResourceLocation still, ResourceLocation flow) {
        super(name, still, flow);
        new FluidLiquid(name,still,flow,0);
    }

    public FluidLiquid(String name, ResourceLocation still, ResourceLocation flow, int luminosity) {
        super(name, still, flow, luminosity);
        this.setUnlocalizedName(name);
        this.setLuminosity(luminosity);
    }
}
