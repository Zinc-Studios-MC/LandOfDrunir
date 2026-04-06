package net.zinc.landofrdrunir.entity.client.deer;

import net.minecraft.resources.ResourceLocation;
import net.zinc.landofrdrunir.LandOfDrunir;
import net.zinc.landofrdrunir.entity.util.MobVariant;

import java.util.List;

public class DeerVariants {
    public static final MobVariant BASIC =
            new MobVariant("cold",
                           ResourceLocation.fromNamespaceAndPath(LandOfDrunir.MOD_ID,
                            "textures/entity/deer/deer.png"));
    public static final MobVariant FLORA =
            new MobVariant("warm",
                    ResourceLocation.fromNamespaceAndPath(LandOfDrunir.MOD_ID,
                            "textures/entity/deer/flora_deer.png"));

    public static final List<MobVariant> ALL = List.of(BASIC, FLORA);
}
