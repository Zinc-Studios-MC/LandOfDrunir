package net.zinc.landofrdrunir.registry;

import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zinc.landofrdrunir.LandOfDrunir;

public class LODItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, LandOfDrunir.MOD_ID);

    public static RegistryObject<Item> STONELING_SPAWN_EGG = ITEMS.register("stoneling_spawn_egg",
            ()-> new ForgeSpawnEggItem(LODEntities.STONELING, 0x7A7A7A, 0x4E7F3A, new Item.Properties()));
}
