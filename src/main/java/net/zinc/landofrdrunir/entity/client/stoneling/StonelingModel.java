package net.zinc.landofrdrunir.entity.client.stoneling;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.zinc.landofrdrunir.LandOfDrunir;
import net.zinc.landofrdrunir.entity.server.custom.stoneling.StonelingEntity;

public class StonelingModel extends HierarchicalModel<StonelingEntity> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(LandOfDrunir.MOD_ID, "stoneling"), "main");
	private final ModelPart body;
	private final ModelPart group;
	private final ModelPart legs;
	private final ModelPart left_leg_front;
	private final ModelPart left_leg_back;
	private final ModelPart right_leg_front;
	private final ModelPart right_leg_back;

	public StonelingModel(ModelPart root) {
		this.body = root.getChild("body");
		this.group = this.body.getChild("group");
		this.legs = this.body.getChild("legs");
		this.left_leg_front = this.legs.getChild("left_leg_front");
		this.left_leg_back = this.legs.getChild("left_leg_back");
		this.right_leg_front = this.legs.getChild("right_leg_front");
		this.right_leg_back = this.legs.getChild("right_leg_back");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -9.0F, -5.0F, 16.0F, 6.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(0, 25).addBox(-8.0F, -7.0F, -7.0F, 9.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(24, 23).addBox(-8.0F, -7.0F, 5.0F, 9.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(9, 3).addBox(6.0F, -4.999F, -5.5F, 3.0F, 2.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition group = body.addOrReplaceChild("group", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, -9.0F, 0.0F, 13.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.0F, -6.0F, 0.0F));

		PartDefinition legs = body.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(5.0F, 0.0F, 6.0F));

		PartDefinition left_leg_front = legs.addOrReplaceChild("left_leg_front", CubeListBuilder.create().texOffs(0, 31).addBox(-1.5F, 0.0F, -1.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, -5.0F, -1.0F));

		PartDefinition left_leg_back = legs.addOrReplaceChild("left_leg_back", CubeListBuilder.create().texOffs(31, 29).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-10.5F, -5.0F, 0.5F));

		PartDefinition right_leg_front = legs.addOrReplaceChild("right_leg_front", CubeListBuilder.create().texOffs(19, 29).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, -5.0F, -11.0F));

		PartDefinition right_leg_back = legs.addOrReplaceChild("right_leg_back", CubeListBuilder.create().texOffs(9, 36).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-10.5F, -5.0F, -12.5F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}
	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return this.body;
	}

	@Override
	public void setupAnim(StonelingEntity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {

	}
}