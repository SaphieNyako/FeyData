package com.feywild.feydata.quest.task;

import com.feywild.feywild.quest.task.RegistryTaskType;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.registries.ForgeRegistries;

public class BiomeTask extends RegistryTaskType<Biome, ResourceLocation> {

    public static final BiomeTask INSTANCE = new BiomeTask();

    private BiomeTask() {
        super("biome", ForgeRegistries.BIOMES);
    }

    @Override
    public Class<ResourceLocation> testType() {
        return ResourceLocation.class;
    }

    @Override
    public boolean checkCompleted(ServerPlayerEntity player, Biome element, ResourceLocation match) {
        return element.getRegistryName() != null && element.getRegistryName().equals(match);
    }

    @Override
    public boolean repeatable() {
        return false;
    }
}
