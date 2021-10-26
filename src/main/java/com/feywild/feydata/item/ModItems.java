package com.feywild.feydata.item;

import com.feywild.feydata.FeyDataMod;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, FeyDataMod.MOD_ID);

    public static final RegistryObject<Item> SCHEMATICS_DUNGEONS_GEAR_WEAPONS = ITEMS.register("schematics_dungeons_gear_weapons",
            ()->new Schematics(new Item.Properties().tab(FeyDataMod.FEYDATA_TAB), new TranslationTextComponent("message.feydata.schematics_dungeons_gear_weapons")));

    public static final RegistryObject<Item> SCHEMATICS_DUNGEONS_GEAR_ARTIFACTS = ITEMS.register("schematics_dungeons_gear_artifacts",
            ()->new Schematics(new Item.Properties().tab(FeyDataMod.FEYDATA_TAB), new TranslationTextComponent("message.feydata.schematics_dungeons_gear_artifacts")));

    public static final RegistryObject<Item> SCHEMATICS_DUNGEONS_GEAR_ARMOR = ITEMS.register("schematics_dungeons_gear_armor",
            ()->new Schematics(new Item.Properties().tab(FeyDataMod.FEYDATA_TAB), new TranslationTextComponent("message.feydata.schematics_dungeons_gear_armor")));

    public static final RegistryObject<Item> BOOK_OF_DRAGONS = ITEMS.register("book_of_dragons",
            ()-> new BookOfDragons(new Item.Properties().tab(FeyDataMod.FEYDATA_TAB)));

    public static final RegistryObject<Item> SHADOW_COURT_HISTORIA = ITEMS.register("shadow_court_historia",
            ()-> new ShadowCourtHistoria(new Item.Properties().tab(FeyDataMod.FEYDATA_TAB)));

    public static final RegistryObject<Item> FEY_FEATHER = ITEMS.register("fey_feather",
            () -> new FeyFeather(new Item.Properties().tab(FeyDataMod.FEYDATA_TAB)));

    public static final RegistryObject<Item> SOUL_REAPER_LANTERN = ITEMS.register("soul_reaper_lantern",
            () -> new SoulReaperLantern(new Item.Properties().tab(FeyDataMod.FEYDATA_TAB)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}
