package com.feywild.feydata;

import com.feywild.feydata.quest.task.AnimalPetTask;
import com.feywild.feywild.FeywildMod;
import com.feywild.feywild.network.OpenLibraryScreenSerializer;
import com.feywild.feywild.quest.Quest;
import com.feywild.feywild.quest.player.QuestData;
import com.feywild.feywild.util.LibraryBooks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.network.PacketDistributor;

public class EventListener {

    @SubscribeEvent
    public void entityInteract(PlayerInteractEvent.EntityInteract event) {
        if (!event.getWorld().isClientSide && event.getPlayer() instanceof ServerPlayerEntity) {
            if(event.getTarget() instanceof LivingEntity && event.getPlayer().getItemInHand(event.getHand()).isEmpty()) {
                QuestData.get((ServerPlayerEntity) event.getPlayer()).checkComplete(AnimalPetTask.INSTANCE, event.getTarget().getType().getRegistryName());
                event.getPlayer().swing(event.getPlayer().getUsedItemHand(), true);
                }
            }
        }
}
