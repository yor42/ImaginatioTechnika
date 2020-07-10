package yor42.imaginatiotechnika.util.handlers;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import yor42.imaginatiotechnika.ImaginatioTechnika;

public class SoundEventHandler {
    public static SoundEvent JUKEBOX_REVENGE, JUKEBOX_FALLEN_KINGDOM, JUKEBOX_TAKE_BACK_THE_NIGHT, JUKEBOX_FIND_THE_PIECES, JUKEBOX_DRAGONHEARTED, JUKEBOX_DONT_MINE_AT_NIGHT, JUKEBOX_NEVERGONNAGIVEYOUUP;

    public static void registerSounds(){
        //meme musics
        JUKEBOX_REVENGE = registerSounds("jukebox.othermusic.revenge");
        JUKEBOX_FALLEN_KINGDOM = registerSounds("jukebox.othermusic.fallenkingdom");
        JUKEBOX_TAKE_BACK_THE_NIGHT = registerSounds("jukebox.othermusic.takebackthenight");
        JUKEBOX_FIND_THE_PIECES = registerSounds("jukebox.othermusic.findthepieces");
        JUKEBOX_DRAGONHEARTED = registerSounds("jukebox.othermusic.dragonhearted");
        JUKEBOX_DONT_MINE_AT_NIGHT = registerSounds("jukebox.othermusic.dontmineatnight");
        JUKEBOX_NEVERGONNAGIVEYOUUP = registerSounds("jukebox.othermusic.nevergonnagiveyouup");

    }

    private static SoundEvent registerSounds(String name){
        ResourceLocation filelocation = new ResourceLocation(ImaginatioTechnika.MOD_ID,name);
        SoundEvent event = new SoundEvent(filelocation);
        event.setRegistryName(name);
        ForgeRegistries.SOUND_EVENTS.register(event);
        return event;
    }

}
