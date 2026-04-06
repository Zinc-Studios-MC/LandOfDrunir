package net.zinc.landofrdrunir.entity.server.custom.deer;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.zinc.landofrdrunir.entity.client.deer.DeerVariants;
import net.zinc.landofrdrunir.entity.util.MobVariant;
import net.zinc.landofrdrunir.registry.LODEntities;
import org.jetbrains.annotations.Nullable;

public class Deer extends Animal implements Saddleable, PlayerRideableJumping {

    public static final EntityDataAccessor<Integer> VARIANT =
            SynchedEntityData.defineId(Deer.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Boolean> DATA_SADDLE_ID =
            SynchedEntityData.defineId(Deer.class, EntityDataSerializers.BOOLEAN);

    private float playerJumpPendingScale;
    private boolean isJumping;

    public Deer(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(VARIANT, 0);
        this.entityData.define(DATA_SADDLE_ID, false);
    }

    public MobVariant getVariant() {
        int i = this.entityData.get(VARIANT);
        return (i >= 0 && i < DeerVariants.ALL.size())
                ? DeerVariants.ALL.get(i)
                : DeerVariants.ALL.get(0);
    }

    public void setVariant(MobVariant variant) {
        int i = DeerVariants.ALL.indexOf(variant);
        this.entityData.set(VARIANT, Math.max(i, 0));
    }

    private void setVariantById(String id) {
        for (int i = 0; i < DeerVariants.ALL.size(); i++) {
            if (DeerVariants.ALL.get(i).id().equals(id)) {
                this.entityData.set(VARIANT, i);
                return;
            }
        }
        this.entityData.set(VARIANT, 0);
    }

    public boolean isSaddled() {
        return this.entityData.get(DATA_SADDLE_ID);
    }

    private void setSaddled(boolean saddled) {
        this.entityData.set(DATA_SADDLE_ID, saddled);
    }

    @Override
    public boolean isSaddleable() {
        return this.isAlive() && !this.isBaby();
    }

    @Override
    public void equipSaddle(@Nullable SoundSource pSource) {
        this.setSaddled(true);
        if (pSource != null) {
            this.level().playSound(null, this, SoundEvents.PIG_SADDLE, pSource, 0.5F, 1.0F);
        }
    }

    @Override
    public boolean canJump() {
        return this.isSaddled();
    }

    @Override
    public void onPlayerJump(int pJumpPower) {
        if (!this.isSaddled()) {
            return;
        }

        if (pJumpPower < 0) {
            pJumpPower = 0;
        }

        if (pJumpPower >= 90) {
            this.playerJumpPendingScale = 1.0F;
        } else {
            this.playerJumpPendingScale = 0.4F + 0.4F * (float) pJumpPower / 90.0F;
        }
        this.isJumping = true;
    }

    @Override
    public void handleStartJump(int pJumpPower) {
        if (this.isSaddled()) {
            this.playJumpSound();
        }
    }

    @Override
    public void handleStopJump() {
    }


    @Nullable
    @Override
    public LivingEntity getControllingPassenger() {
        Entity entity = this.getFirstPassenger();
        if (entity instanceof Player player && this.isSaddled()) {
            return player;
        }
        return null;
    }

    @Override
    public void travel(Vec3 pTravelVector) {
        if (this.isVehicle() && this.getControllingPassenger() instanceof Player player) {
            this.setYRot(player.getYRot());
            this.yRotO = this.getYRot();
            this.setXRot(player.getXRot() * 0.5F);
            this.setRot(this.getYRot(), this.getXRot());
            this.yBodyRot = this.getYRot();
            this.yHeadRot = this.yBodyRot;

            if (this.isControlledByLocalInstance()) {
                if (this.onGround() && this.playerJumpPendingScale > 0.0F && !this.isJumping) {
                    this.executeRidersJump(this.playerJumpPendingScale, pTravelVector);
                    this.playerJumpPendingScale = 0.0F;
                }
            }

            this.setSpeed((float) this.getAttributeValue(Attributes.MOVEMENT_SPEED));
            float forward = player.zza;
            float strafe = player.xxa * 0.5F;
            if (forward <= 0.0F) {
                forward *= 0.25F;
            }
            super.travel(new Vec3(strafe, pTravelVector.y, forward));
            return;
        }

        super.travel(pTravelVector);
    }

    private void executeRidersJump(float pPlayerJumpPendingScale, Vec3 pTravelVector) {
        double d0 = this.getAttributeValue(Attributes.JUMP_STRENGTH) * (double) pPlayerJumpPendingScale;
        Vec3 vec3 = this.getDeltaMovement();
        this.setDeltaMovement(vec3.x, d0, vec3.z);
        this.isJumping = true;
        this.hasImpulse = true;
        this.playJumpSound();

        if (pTravelVector.z > 0.0D) {
            float f = net.minecraft.util.Mth.sin(this.getYRot() * ((float) Math.PI / 180F));
            float f1 = net.minecraft.util.Mth.cos(this.getYRot() * ((float) Math.PI / 180F));
            this.setDeltaMovement(this.getDeltaMovement().add(
                    -0.4F * f * pPlayerJumpPendingScale,
                    0.0D,
                    0.4F * f1 * pPlayerJumpPendingScale
            ));
        }
    }

    private void playJumpSound() {
        this.playSound(SoundEvents.HORSE_JUMP, 0.4F, 1.0F);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.isJumping && this.onGround()) {
            this.isJumping = false;
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putString("Variant", getVariant().id());
        tag.putBoolean("Saddle", this.isSaddled());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setVariantById(tag.getString("Variant"));
        this.setSaddled(tag.getBoolean("Saddle"));
        this.applyVariantStats();
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        if (pDataTag == null || !pDataTag.contains("Variant")) {
            this.setVariant(this.pickVariant(pLevel.getLevel(), this.blockPosition()));
        }
        this.applyVariantStats();
        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return new Deer(LODEntities.DEER.get(), pLevel);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 12.0D)
                .add(Attributes.MOVEMENT_SPEED, 1.2D)
                .add(Attributes.JUMP_STRENGTH, 0.7D)
                .add(Attributes.FOLLOW_RANGE, 10.0D);
    }

    private void applyVariantStats() {
        AttributeInstance maxHealth = this.getAttribute(Attributes.MAX_HEALTH);

        if (maxHealth != null) {
            MobVariant variant = getVariant();
            if (variant.equals(DeerVariants.BASIC)) {
                maxHealth.setBaseValue(12.0D);
            } else {
                maxHealth.setBaseValue(14.0D);
            }
        }

        this.setHealth(this.getMaxHealth());
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 2.0D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.of(Items.WHEAT), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    private MobVariant pickVariant(Level level, BlockPos pos) {
        return level.random.nextBoolean() ? DeerVariants.BASIC : DeerVariants.FLORA;
    }

    @Override
    public boolean canMate(Animal pOtherAnimal) {
        return pOtherAnimal instanceof Deer;
    }

    @Override
    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);

        if (itemstack.is(Items.SADDLE) && !this.isSaddled() && !this.isBaby()) {
            if (!this.level().isClientSide) {
                this.equipSaddle(SoundSource.NEUTRAL);
                if (!pPlayer.getAbilities().instabuild) {
                    itemstack.shrink(1);
                }
            }
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }

        if (!this.isFood(itemstack) && this.isSaddled() && !this.isVehicle() && !pPlayer.isSecondaryUseActive()) {
            if (!this.level().isClientSide) {
                pPlayer.startRiding(this);
            }
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }

        InteractionResult interactionresult = super.mobInteract(pPlayer, pHand);
        if (!interactionresult.consumesAction()) {
            return InteractionResult.PASS;
        }
        return interactionresult;
    }

    @Override
    protected void dropEquipment() {
        super.dropEquipment();
        if (this.isSaddled()) {
            this.spawnAtLocation(Items.SADDLE);
        }
    }

    @Override
    public boolean isPushable() {
        return !this.isVehicle();
    }
}