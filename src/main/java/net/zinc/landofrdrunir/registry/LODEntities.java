package net.zinc.landofrdrunir.registry;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zinc.landofrdrunir.LandOfDrunir;
import net.zinc.landofrdrunir.entity.server.custom.stoneling.StonelingEntity;

public class LODEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, LandOfDrunir.MOD_ID);
    public static RegistryObject<EntityType<StonelingEntity>> STONELING = ENTITIES.register("stoneling",
            ()-> EntityType.Builder.of(StonelingEntity::new, MobCategory.AMBIENT).sized(1.25f, 0.75f).build("stoneling"));
}
