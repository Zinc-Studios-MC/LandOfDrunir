package net.zinc.landofrdrunir.entity.server.custom.stoneling;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.Tags;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;

public class StonelingWalkToOresGoal extends Goal {
    private final PathfinderMob mob;
    private final double speed;
    private final double playerRange;
    private final int oreRange;

    private Player nearbyPlayer;
    private BlockPos targetOrePos;
    private BlockPos targetStandPos;

    private long nextScanTick = 0L;
    private static final int SCAN_COOLDOWN_TICKS = 10;

    public StonelingWalkToOresGoal(PathfinderMob mob, double speed, double playerRange, int oreRange) {
        this.mob = mob;
        this.speed = speed;
        this.playerRange = playerRange;
        this.oreRange = oreRange;
        this.setFlags(EnumSet.of(Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        Level level = mob.level();

        if (level.getGameTime() < nextScanTick) {
            return false;
        }

        nearbyPlayer = findNearbyPlayer();
        if (nearbyPlayer == null) {
            return false;
        }

        nextScanTick = level.getGameTime() + SCAN_COOLDOWN_TICKS;

        targetOrePos = findNearestReachableOre();
        return targetOrePos != null;
    }

    @Override
    public boolean canContinueToUse() {
        if (nearbyPlayer == null || targetOrePos == null || targetStandPos == null) {
            return false;
        }

        if (!nearbyPlayer.isAlive()) {
            return false;
        }

        if (mob.distanceToSqr(nearbyPlayer) > playerRange * playerRange) {
            return false;
        }

        Level level = mob.level();

        if (!level.getBlockState(targetOrePos).is(Tags.Blocks.ORES)) {
            return false;
        }

        if (!level.isEmptyBlock(targetStandPos)) {
            return false;
        }

        double distSqr = mob.distanceToSqr(
                targetStandPos.getX() + 0.5D,
                targetStandPos.getY(),
                targetStandPos.getZ() + 0.5D
        );

        return distSqr > 2.25D;
    }

    @Override
    public void start() {
        moveToTarget();
    }

    @Override
    public void tick() {
        if (targetStandPos == null) {
            return;
        }

        if (mob.getNavigation().isDone()) {
            moveToTarget();
        }
    }

    @Override
    public void stop() {
        nearbyPlayer = null;
        targetOrePos = null;
        targetStandPos = null;
        mob.getNavigation().stop();
    }

    private void moveToTarget() {
        if (targetStandPos == null) {
            return;
        }

        mob.getNavigation().moveTo(
                targetStandPos.getX() + 0.5D,
                targetStandPos.getY(),
                targetStandPos.getZ() + 0.5D,
                speed
        );
    }

    private Player findNearbyPlayer() {
        List<Player> players = mob.level().getEntitiesOfClass(
                Player.class,
                mob.getBoundingBox().inflate(playerRange),
                Player::isAlive
        );

        return players.stream()
                .min(Comparator.comparingDouble(p -> p.distanceToSqr(mob)))
                .orElse(null);
    }

    private BlockPos findNearestReachableOre() {
        Level level = mob.level();
        BlockPos origin = mob.blockPosition();

        BlockPos bestOre = null;
        BlockPos bestStand = null;
        double bestDist = Double.MAX_VALUE;

        for (int dx = -oreRange; dx <= oreRange; dx++) {
            for (int dy = -oreRange; dy <= oreRange; dy++) {
                for (int dz = -oreRange; dz <= oreRange; dz++) {
                    BlockPos orePos = origin.offset(dx, dy, dz);

                    if (!level.getBlockState(orePos).is(Tags.Blocks.ORES)) {
                        continue;
                    }

                    BlockPos standPos = orePos.above();

                    if (!level.isEmptyBlock(standPos)) {
                        continue;
                    }

                    if (!level.isEmptyBlock(standPos.above())) {
                        continue;
                    }

                    if (mob.getNavigation().createPath(standPos, 0) == null) {
                        continue;
                    }

                    double dist = origin.distSqr(orePos);
                    if (dist < bestDist) {
                        bestDist = dist;
                        bestOre = orePos.immutable();
                        bestStand = standPos.immutable();
                    }
                }
            }
        }

        targetStandPos = bestStand;
        return bestOre;
    }
}