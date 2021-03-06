package yor42.imaginatiotechnika;

import net.minecraftforge.common.config.Config;
import scala.tools.nsc.interpreter.Power;

@Config(modid = ImaginatioTechnika.MOD_ID)
public class Configs {

    public static modules MODULES = new modules();
    public static Worldgen WORLDGEN = new Worldgen();
    public static PowerGen POWERGEN = new PowerGen();
    public static Machines MACHINES = new Machines();

    public static class modules{

        @Config.Comment("Should Minecraft add tools from this mod?")
        public boolean enable_tools = true;

    }

    public static class Worldgen{

        @Config.Comment("Maximum Block count per vein of RMA70-24 ore, Default = 4")
        public int RMA7024_MAXNUMBER = 5;

        @Config.Comment("Spawn Chance of RMA70-24 ore, Default = 4 for now")
        public int RMA7024_Chance = 10;

        @Config.Comment("Min spawn height of RMA70-24 ore, Default = 5")
        public int RMA7024_Minheight = 1;

        @Config.Comment("Max spawn height of RMA70-24 ore, Default = 15")
        public int RMA7024_Maxheight = 15;

    }

    public static class PowerGen{

        @Config.Comment("Total Burning time of Originium Generator")
        public int ORIGINIUM_BURNTIME = 1500;
        @Config.Comment("Originium Generator's output per tick")
        public int OriginiumGenerator_RFperItem = 500;

        @Config.Comment("Max Extract from Originium Generator")
        public int OriginiumGenerator_maxextract = 100;

    }

    public static class Machines{

        @Config.Comment("Power useage per tick of Infuser")
        public int INFUSER_RF_PERTICK = 100;
    }

}
