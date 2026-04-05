package net.zinc.landofrdrunir.entity.client.stoneling;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.zinc.landofrdrunir.LandOfDrunir;
import net.zinc.landofrdrunir.entity.server.custom.stoneling.StonelingEntity;

public class StonelingRenderer extends MobRenderer<StonelingEntity, StonelingModel> {
    public StonelingRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new StonelingModel(pContext.bakeLayer(StonelingModel.LAYER_LOCATION)), 0.4f);
    }

    @Override
    public ResourceLocation getTextureLocation(StonelingEntity pEntity) {
        return ResourceLocation.fromNamespaceAndPath(LandOfDrunir.MOD_ID, "textures/entity/stoneling/stoneling.png");
    }
}
