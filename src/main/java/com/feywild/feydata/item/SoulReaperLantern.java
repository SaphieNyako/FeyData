package com.feywild.feydata.item;

import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class SoulReaperLantern extends Item {
    public SoulReaperLantern(Properties prop) {
        super(prop);
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> use( @Nonnull World pWorld,@Nonnull PlayerEntity pPlayerEntity,@Nonnull Hand pHand) {
        pWorld.getEntities(null, pPlayerEntity.getBoundingBox().inflate(24)).forEach(entity -> {
            if(entity instanceof ZombieEntity || entity instanceof SkeletonEntity) {
                ((MonsterEntity) entity).addEffect(new EffectInstance(Effects.GLOWING, 20 * 60 , 1)); // 1 min glow
            }
        });
        pPlayerEntity.getCooldowns().addCooldown(this, 20 * 5 * 60); // 5 mins cooldown
        return ActionResult.success(pPlayerEntity.getItemInHand(pHand));
    }
}
