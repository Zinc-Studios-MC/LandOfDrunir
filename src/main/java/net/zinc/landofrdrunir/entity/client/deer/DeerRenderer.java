package net.zinc.landofrdrunir.entity.client.deer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.zinc.landofrdrunir.entity.server.custom.deer.Deer;

public class DeerRenderer extends MobRenderer<Deer, DeerModel> {
    public DeerRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new DeerModel(pContext.bakeLayer(DeerModel.LAYER_LOCATION)), 0.5f);
        this.addLayer(new DeerSaddleLayer(this, pContext.getModelSet()));
    }

    @Override
    public ResourceLocation getTextureLocation(Deer pEntity) {
        return pEntity.getVariant().location();
    }
}
