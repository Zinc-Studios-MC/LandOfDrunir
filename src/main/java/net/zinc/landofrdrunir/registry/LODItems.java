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
            ()-> new ForgeSpawnEggItem(LODEntities.STONELING, 0x7A7A7A, 0x5F9A4A, new Item.Properties()));
    public static RegistryObject<Item> DEER_SPAWN_EGG = ITEMS.register("deer_spawn_egg",
            ()-> new ForgeSpawnEggItem(LODEntities.DEER, 0x6B4423, 0xD8C4A8, new Item.Properties()));

    public static RegistryObject<Item> ANTLERS = ITEMS.register("deer_antlers", ()-> new Item(new Item.Properties()));
    public static RegistryObject<Item> FLORA_ANTLERS = ITEMS.register("flora_deer_antlers", ()-> new Item(new Item.Properties()));
    public static RegistryObject<Item> RAW_DEER_MEAT = ITEMS.register("raw_deer_meat", ()-> new Item(new Item.Properties()));
    public static RegistryObject<Item> COOKED_DEER_MEAT = ITEMS.register("cooked_dear_meat", ()-> new Item(new Item.Properties()));
}
