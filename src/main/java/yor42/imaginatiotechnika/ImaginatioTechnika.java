package yor42.imaginatiotechnika;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import yor42.imaginatiotechnika.init.BlockInit;
import yor42.imaginatiotechnika.init.FluidInit;
import yor42.imaginatiotechnika.init.ItemInit;
import yor42.imaginatiotechnika.proxy.ClientProxy;
import yor42.imaginatiotechnika.util.IHasModel;
import yor42.imaginatiotechnika.util.handlers.GuiHandler;
import yor42.imaginatiotechnika.util.handlers.RenderHandler;
import yor42.imaginatiotechnika.util.handlers.TileEntityHandler;
import yor42.imaginatiotechnika.world.gen.oreworldgen;

@Mod(
        modid = ImaginatioTechnika.MOD_ID,
        name = ImaginatioTechnika.MOD_NAME,
        version = ImaginatioTechnika.VERSION
)
public class ImaginatioTechnika {

    public static final String MOD_ID = "imgtechnika";
    public static final String MOD_NAME = "Imaginatio Technika";
    public static final String VERSION = "1.0-SNAPSHOT";

    @SidedProxy(clientSide = "yor42.imaginatiotechnika.proxy.ClientProxy")
    public static ClientProxy Clientproxy;

    /** This is the instance of your mod as created by Forge. It will never be null. */
    @Mod.Instance(MOD_ID)
    public static ImaginatioTechnika INSTANCE;

    static {FluidRegistry.enableUniversalBucket();}

    private void registerotherthings(){
        GameRegistry.registerWorldGenerator(new oreworldgen(), 0);
    }

    /**
     * This is the first initialization event. Register tile entities here.
     * The registry events below will have fired prior to entry to this method.
     */
    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        //DO NOT PUT ANY REGISTER ABOVE THIS. IT WILL CRASH THE GAME.
        FluidInit.registerFluid();

        registerotherthings();
        RenderHandler.registerCustomMeshesandStates();
    }

    /**
     * This is the second initialization event. Register custom recipes
     */
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(ImaginatioTechnika.INSTANCE, new GuiHandler());
    }

    /**
     * This is the final initialization event. Register actions from other mods here
     */
    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {

    }

    /**
     * Forge will automatically look up and bind blocks to the fields in this class
     * based on their registry name.
     */
    @GameRegistry.ObjectHolder(MOD_ID)
    public static class Blocks {
      /*
          public static final MySpecialBlock mySpecialBlock = null; // placeholder for special block below
      */
    }

    /**
     * Forge will automatically look up and bind items to the fields in this class
     * based on their registry name.
     */
    @GameRegistry.ObjectHolder(MOD_ID)
    public static class Items {
      /*
          public static final ItemBlock mySpecialBlock = null; // itemblock for the block above
          public static final MySpecialItem mySpecialItem = null; // placeholder for special item below
      */
    }

    /**
     * This is a special class that listens to registry events, to allow creation of mod blocks and items at the proper time.
     */
    @Mod.EventBusSubscriber
    public static class ObjectRegistryHandler {
       /** Listen for the register event for creating custom items */
       @SubscribeEvent
       public static void addItems(RegistryEvent.Register<Item> event) {
           event.getRegistry().registerAll(ItemInit.ITEM_LIST.toArray(new Item[0]));

       }
       /** Listen for the register event for creating custom blocks */
       @SubscribeEvent
       public static void addBlocks(RegistryEvent.Register<Block> event) {
           event.getRegistry().registerAll(BlockInit.BLOCK_LIST.toArray(new Block[0]));
           TileEntityHandler.registerTileentity();
       }

       @SubscribeEvent
        public static void addModels(ModelRegistryEvent event){
           for(Item item: ItemInit.ITEM_LIST){
               if (item instanceof IHasModel){
                   ((IHasModel)item).registerModels();
               }
           }

           for(Block block: BlockInit.BLOCK_LIST){
               if(block instanceof IHasModel){
                   ((IHasModel)block).registerModels();
               }
           }
       }
    }

    /* EXAMPLE ITEM AND BLOCK - you probably want these in separate files
    public static class MySpecialItem extends Item {

    }

    public static class MySpecialBlock extends Block {

    }
    */
}
