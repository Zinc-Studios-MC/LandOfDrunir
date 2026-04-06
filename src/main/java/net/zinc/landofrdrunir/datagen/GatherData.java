package net.zinc.landofrdrunir.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.zinc.landofrdrunir.LandOfDrunir;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = LandOfDrunir.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GatherData {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        generator.addProvider(event.includeClient(), new LODItemModelProvider(packOutput, existingFileHelper));
    }
}
