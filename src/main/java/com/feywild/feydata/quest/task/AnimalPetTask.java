package com.feywild.feydata.quest.task;

import com.feywild.feywild.quest.task.RegistryTaskType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

public class AnimalPetTask extends RegistryTaskType<EntityType<?>, ResourceLocation> {

    public static final AnimalPetTask INSTANCE = new AnimalPetTask();

    protected AnimalPetTask() {
        super( "type", ForgeRegistries.ENTITIES);
    }


    @Override
    public Class<ResourceLocation> testType() {
        return ResourceLocation.class;
    }

    @Override
    public boolean checkCompleted(ServerPlayerEntity serverPlayerEntity, EntityType<?> entityType, ResourceLocation resourceLocation) {
        return entityType.getRegistryName() == resourceLocation;
    }

    @Override
    public boolean repeatable() {
        return false;
    }
}
