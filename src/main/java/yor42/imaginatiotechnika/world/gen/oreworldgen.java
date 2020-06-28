package yor42.imaginatiotechnika.world.gen;

import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import scala.collection.parallel.ParIterableLike;
import yor42.imaginatiotechnika.Configs;
import yor42.imaginatiotechnika.init.BlockInit;

import java.util.Random;

public class oreworldgen implements IWorldGenerator {

    private WorldGenerator RMA7024;

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        switch (world.provider.getDimension()){
            case 0:
                runGenerator(RMA7024, world, random, chunkX, chunkZ, Configs.WORLDGEN.RMA7024_Chance, Configs.WORLDGEN.RMA7024_Minheight, Configs.WORLDGEN.RMA7024_Maxheight);
                break;
        }
    }

    public oreworldgen(){
        RMA7024 = new WorldGenMinable(BlockInit.RMA7024_ORE.getDefaultState(), Configs.WORLDGEN.RMA7024_MAXNUMBER);
    }

    private void runGenerator(WorldGenerator gen, World world, Random rand, int ChunkX, int ChunkZ, int chance, int MinHeight, int MaxHeight){
        if(MinHeight > MaxHeight || MinHeight < 0 || MaxHeight > 256){
            throw new IllegalArgumentException("Ore generated Out of bound! I preeeeety sure you messed up bit of config there...\ncheck if Minheight is greater than Max or Min is lower than 0 or Max is greater than 256.\n and no idk how to support Cubic chunks yet...");
        }

        int heightDifference = MaxHeight - MinHeight + 1;

        for(int i = 0; i <chance; i++){
            int x = ChunkX*16 + rand.nextInt(16);
            int y = MinHeight + rand.nextInt(heightDifference);
            int z = ChunkZ*16 + rand.nextInt(16);

            gen.generate(world, rand, new BlockPos(x,y,z));
        }
    }

}
