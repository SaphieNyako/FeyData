package com.feywild.feydata;

import com.feywild.feydata.quest.task.BiomeTask;
import com.feywild.feydata.quest.task.StructureTask;
import com.feywild.feywild.quest.player.QuestData;
import com.feywild.feywild.quest.task.ItemTask;
import com.feywild.feywild.world.structure.ModStructures;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EventListener {

    @SubscribeEvent
    public void playerTick(TickEvent.PlayerTickEvent event) {
        // Only check one / second
        if (event.player.tickCount % 20 == 0 && !event.player.level.isClientSide && event.player instanceof ServerPlayerEntity) {
            ServerPlayerEntity player = (ServerPlayerEntity) event.player;
            QuestData quests = QuestData.get(player);
            //Quest Check for Biome
            player.getLevel().getBiomeName(player.blockPosition()).ifPresent(biome -> quests.checkComplete(BiomeTask.INSTANCE, biome.location()));
            //Quest Check for Structure
           quests.checkComplete(StructureTask.INSTANCE, player.getLevel().structureFeatureManager().getStructureAt(player.blockPosition(), true, ModStructures.library).getFeature().getRegistryName());
        }
    }



}
