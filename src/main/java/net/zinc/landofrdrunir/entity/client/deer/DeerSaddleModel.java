package net.zinc.landofrdrunir.entity.client.deer;// Made with Blockbench 5.1.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.zinc.landofrdrunir.LandOfDrunir;
import net.zinc.landofrdrunir.entity.server.custom.deer.Deer;


public class DeerSaddleModel extends HierarchicalModel<Deer> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(LandOfDrunir.MOD_ID, "deer_saddle"), "main");
	private final ModelPart bridle;
	private final ModelPart reins;
	private final ModelPart mirror;
	private final ModelPart saddle;

	public DeerSaddleModel(ModelPart root) {
		this.saddle = root.getChild("saddle");
		this.bridle = saddle.getChild("bridle");
		this.reins = this.bridle.getChild("reins");
		this.mirror = this.bridle.getChild("mirror");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition saddle = partdefinition.addOrReplaceChild("saddle", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 9.0F, 9.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 9.0F, 0.0F));

		PartDefinition bridle = saddle.addOrReplaceChild("bridle", CubeListBuilder.create().texOffs(34, 0).addBox(-2.5F, -9.0F, -8.0F, 5.0F, 5.0F, 8.0F, new CubeDeformation(0.1F))
		.texOffs(0, 37).addBox(-2.0F, -7.0F, -11.0F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.1F))
		.texOffs(34, 13).addBox(2.1F, -6.0F, -10.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 7.0F, -7.0F));

		PartDefinition reins = bridle.addOrReplaceChild("reins", CubeListBuilder.create().texOffs(0, 18).addBox(3.1F, -1.5F, 0.0F, 0.0F, 3.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(32, 18).addBox(-3.1F, -1.5F, 0.0F, 0.0F, 3.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.5F, -9.0F));

		PartDefinition mirror = bridle.addOrReplaceChild("mirror", CubeListBuilder.create().texOffs(34, 15).addBox(-3.1F, -41.0F, -27.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 35.0F, 17.0F));


		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		saddle.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return this.saddle;
	}

	@Override
	public void setupAnim(Deer pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {

	}
}