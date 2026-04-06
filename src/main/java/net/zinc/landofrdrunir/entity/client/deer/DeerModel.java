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

public class DeerModel extends HierarchicalModel<Deer> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(LandOfDrunir.MOD_ID, "deer"), "main");
	private final ModelPart deer;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart front_left_leg;
	private final ModelPart front_right_leg;
	private final ModelPart rear_left_leg;
	private final ModelPart rear_right_leg;

	public DeerModel(ModelPart root) {
		this.deer = root.getChild("deer");
		this.body = this.deer.getChild("body");
		this.head = this.body.getChild("head");
		this.front_left_leg = this.body.getChild("front_left_leg");
		this.front_right_leg = this.body.getChild("front_right_leg");
		this.rear_left_leg = this.body.getChild("rear_left_leg");
		this.rear_right_leg = this.body.getChild("rear_right_leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition deer = partdefinition.addOrReplaceChild("deer", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, -1.0F));

		PartDefinition body = deer.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -9.0F, 8.0F, 8.0F, 18.0F, new CubeDeformation(0.0F))
		.texOffs(35, 8).addBox(-1.5F, -5.0F, 8.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -15.0F, 1.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(48, 5).addBox(-2.0F, -4.0F, -3.0F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 27).addBox(-2.5F, -9.0F, -8.0F, 5.0F, 5.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(1, 10).addBox(-2.0F, -7.0F, -11.0F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -7.0F));

		PartDefinition antlers_right_r1 = head.addOrReplaceChild("antlers right_r1", CubeListBuilder.create().texOffs(43, 29).addBox(-1.5F, -8.0F, 0.0F, 7.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -9.0F, -4.0F, -0.0873F, -0.9163F, -0.6545F));

		PartDefinition antlers_left_r1 = head.addOrReplaceChild("antlers left_r1", CubeListBuilder.create().texOffs(28, 29).addBox(-5.5F, -8.0F, 0.0F, 7.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, -9.0F, -4.0F, -0.0873F, 0.9163F, 0.6545F));

		PartDefinition ear_right_r1 = head.addOrReplaceChild("ear right_r1", CubeListBuilder.create().texOffs(5, 5).addBox(-1.0F, -1.0F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -9.0F, -1.75F, 0.0F, 0.0F, -0.7854F));

		PartDefinition ear_left_r1 = head.addOrReplaceChild("ear left_r1", CubeListBuilder.create().texOffs(5, 5).addBox(-1.0F, -1.0F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, -9.0F, -1.75F, 0.0F, 0.0F, 0.7854F));

		PartDefinition front_left_leg = body.addOrReplaceChild("front_left_leg", CubeListBuilder.create().texOffs(0, 41).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 11.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 4.0F, -7.0F));

		PartDefinition front_right_leg = body.addOrReplaceChild("front_right_leg", CubeListBuilder.create().texOffs(0, 41).mirror().addBox(-1.5F, 0.0F, -1.5F, 3.0F, 11.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.0F, 4.0F, -7.0F));

		PartDefinition rear_left_leg = body.addOrReplaceChild("rear_left_leg", CubeListBuilder.create().texOffs(12, 41).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 11.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 4.0F, 7.0F));

		PartDefinition rear_right_leg = body.addOrReplaceChild("rear_right_leg", CubeListBuilder.create().texOffs(12, 41).mirror().addBox(-1.5F, 0.0F, -1.5F, 3.0F, 11.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.0F, 4.0F, 7.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		deer.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return this.deer;
	}

	@Override
	public void setupAnim(Deer pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {

	}
}