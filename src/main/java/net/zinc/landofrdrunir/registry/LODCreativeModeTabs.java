package net.zinc.landofrdrunir.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.zinc.landofrdrunir.LandOfDrunir;

public class LODCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, LandOfDrunir.MOD_ID);

    public static RegistryObject<CreativeModeTab> ITEM_TAB = TABS.register("item_tab",
            ()-> CreativeModeTab.builder()
                    .icon(()-> new ItemStack(LODItems.ANTLERS.get()))
                    .title(Component.literal("Land Of Drunir Items"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(LODItems.ANTLERS.get());
                        pOutput.accept(LODItems.FLORA_ANTLERS.get());
                        pOutput.accept(LODItems.RAW_DEER_MEAT.get());
                        pOutput.accept(LODItems.COOKED_DEER_MEAT.get());
                        pOutput.accept(LODItems.STONELING_SPAWN_EGG.get());
                        pOutput.accept(LODItems.DEER_SPAWN_EGG.get());
                    })
                    .build());
}
