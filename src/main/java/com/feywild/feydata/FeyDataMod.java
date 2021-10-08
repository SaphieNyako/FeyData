package com.feywild.feydata;

import com.feywild.feydata.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(FeyDataMod.MOD_ID)
public class FeyDataMod {
    public static final String MOD_ID = "feydata";
    public static final ItemGroup FEYDATA_TAB = new ItemGroup("feydataTab") {

        @Nonnull
        @Override
        public ItemStack makeIcon() {

            return new ItemStack(ModItems.SCHEMATICS_DUNGEONS_GEAR_ARMOR.get());
        }
    };


    private static final Logger LOGGER = LogManager.getLogger();

    public FeyDataMod() {
        // Register the setup method for modloading
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(eventBus);

        eventBus.addListener(this::setup);
        // Register the enqueueIMC method for modloading
        eventBus.addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        eventBus.addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        eventBus.addListener(this::doClientStuff);

        FeyDataMod.init();

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }

    /* LOOTTABLES */

    public static void init() {
        MinecraftForge.EVENT_BUS.addListener(FeyDataMod::lootTableLoad);
    }

    private static void lootTableLoad(LootTableLoadEvent event) {
        if (event.getName().equals(LootTables.ABANDONED_MINESHAFT) || event.getName().equals(LootTables.BURIED_TREASURE) || event.getName().equals(LootTables.STRONGHOLD_LIBRARY)) {
            @Nullable
            LootPool pool = event.getTable().getPool("main");
            //noinspection ConstantConditions
            if (pool != null) {
                addEntry(pool, ItemLootEntry.lootTableItem(ModItems.SCHEMATICS_DUNGEONS_GEAR_ARMOR.get()).setWeight(5).build());
                addEntry(pool, ItemLootEntry.lootTableItem(ModItems.SCHEMATICS_DUNGEONS_GEAR_ARTIFACTS.get()).setWeight(5).build());
                addEntry(pool, ItemLootEntry.lootTableItem(ModItems.SCHEMATICS_DUNGEONS_GEAR_WEAPONS.get()).setWeight(5).build());
            }
        }
    }

    private static void addEntry(LootPool pool, LootEntry entry) {
        try {
            //noinspection unchecked
            List<LootEntry> lootEntries = (List<LootEntry>) ObfuscationReflectionHelper.findField(LootPool.class, "field_186453_a").get(pool);
            if (lootEntries.stream().noneMatch(e -> e == entry)) {
                lootEntries.add(entry);
            }
        } catch (ReflectiveOperationException e) {
            //
        }
    }
}
