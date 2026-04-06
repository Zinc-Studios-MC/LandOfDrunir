package net.zinc.landofrdrunir.entity.client.deer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.zinc.landofrdrunir.entity.server.custom.deer.Deer;

public class DeerSaddleLayer extends RenderLayer<Deer, DeerModel> {

    private static final ResourceLocation SADDLE_LOCATION = ResourceLocation.fromNamespaceAndPath("landofdrunir", "textures/entity/deer/deer_saddle.png");
    private final DeerSaddleModel model;

    public DeerSaddleLayer(RenderLayerParent<Deer, DeerModel> pRenderer, EntityModelSet set) {
        super(pRenderer);
        this.model = new DeerSaddleModel(set.bakeLayer(DeerSaddleModel.LAYER_LOCATION));
    }

    @Override
    public void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, Deer pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTick, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        if(pLivingEntity.isSaddled()){
            this.getParentModel().copyPropertiesTo(this.model);
            this.model.prepareMobModel(pLivingEntity, pLimbSwing, pLimbSwingAmount, pPartialTick);
            this.model.setupAnim(pLivingEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
            VertexConsumer vertexconsumer = pBuffer.getBuffer(RenderType.entityCutoutNoCull(SADDLE_LOCATION));
            this.model.renderToBuffer(pPoseStack, vertexconsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

        }
    }
}
