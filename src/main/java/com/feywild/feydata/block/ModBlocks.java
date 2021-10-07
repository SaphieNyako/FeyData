package com.feywild.feydata.block;

import com.feywild.feydata.FeyDataMod;
import com.feywild.feydata.item.ModItems;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModBlocks {

    /* BLOCK REGISTRATION */
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, FeyDataMod.MOD_ID);

    /* WOOD */
    public static final RegistryObject<Block> AUTUMN_TREE_WOOD = registerBlock("autumn_tree_wood",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.OAK_LOG)));

    public static final RegistryObject<Block> SPRING_TREE_WOOD = registerBlock("spring_tree_wood",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.OAK_LOG)));

    public static final RegistryObject<Block> SUMMER_TREE_WOOD = registerBlock("summer_tree_wood",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.OAK_LOG)));

    public static final RegistryObject<Block> WINTER_TREE_WOOD = registerBlock("winter_tree_wood",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.OAK_LOG)));

    /* STAIRS */
    public static final RegistryObject<Block> AUTUMN_TREE_STAIRS = registerBlock("autumn_tree_stairs",
            ()-> new StairsBlock(()-> AUTUMN_TREE_WOOD.get().defaultBlockState(),
                    AbstractBlock.Properties.of(Material.WOOD).harvestLevel(3).harvestTool(ToolType.AXE).strength(3)));

    public static final RegistryObject<Block> SPRING_TREE_STAIRS = registerBlock("spring_tree_stairs",
            ()-> new StairsBlock(()-> SPRING_TREE_WOOD.get().defaultBlockState(),
                    AbstractBlock.Properties.of(Material.WOOD).harvestLevel(3).harvestTool(ToolType.AXE).strength(3)));

    public static final RegistryObject<Block> SUMMER_TREE_STAIRS = registerBlock("summer_tree_stairs",
            ()-> new StairsBlock(()-> SUMMER_TREE_WOOD.get().defaultBlockState(),
                    AbstractBlock.Properties.of(Material.WOOD).harvestLevel(3).harvestTool(ToolType.AXE).strength(3)));

    public static final RegistryObject<Block> WINTER_TREE_STAIRS = registerBlock("winter_tree_stairs",
            ()-> new StairsBlock(()-> WINTER_TREE_WOOD.get().defaultBlockState(),
                    AbstractBlock.Properties.of(Material.WOOD).harvestLevel(3).harvestTool(ToolType.AXE).strength(3)));

    /* SLABS */
    public static final RegistryObject<Block> AUTUMN_TREE_SLAB = registerBlock("autumn_tree_slab",
            ()-> new SlabBlock(AbstractBlock.Properties.of(Material.WOOD).harvestLevel(3).harvestTool(ToolType.AXE).strength(3)));

    public static final RegistryObject<Block> SPRING_TREE_SLAB = registerBlock("spring_tree_slab",
            ()-> new SlabBlock(AbstractBlock.Properties.of(Material.WOOD).harvestLevel(3).harvestTool(ToolType.AXE).strength(3)));

    public static final RegistryObject<Block> SUMMER_TREE_SLAB = registerBlock("summer_tree_slab",
            ()-> new SlabBlock(AbstractBlock.Properties.of(Material.WOOD).harvestLevel(3).harvestTool(ToolType.AXE).strength(3)));

    public static final RegistryObject<Block> WINTER_TREE_SLAB = registerBlock("winter_tree_slab",
            ()-> new SlabBlock(AbstractBlock.Properties.of(Material.WOOD).harvestLevel(3).harvestTool(ToolType.AXE).strength(3)));

    /* FENCES */
    public static final RegistryObject<Block> AUTUMN_TREE_FENCE = registerBlock("autumn_tree_fence",
            () -> new FenceBlock(AbstractBlock.Properties.of(Material.WOOD).harvestLevel(3).harvestTool(ToolType.AXE).strength(3)));

    public static final RegistryObject<Block> SPRING_TREE_FENCE = registerBlock("spring_tree_fence",
            () -> new FenceBlock(AbstractBlock.Properties.of(Material.WOOD).harvestLevel(3).harvestTool(ToolType.AXE).strength(3)));

    public static final RegistryObject<Block> SUMMER_TREE_FENCE = registerBlock("summer_tree_fence",
            () -> new FenceBlock(AbstractBlock.Properties.of(Material.WOOD).harvestLevel(3).harvestTool(ToolType.AXE).strength(3)));

    public static final RegistryObject<Block> WINTER_TREE_FENCE = registerBlock("winter_tree_fence",
            () -> new FenceBlock(AbstractBlock.Properties.of(Material.WOOD).harvestLevel(3).harvestTool(ToolType.AXE).strength(3)));

    /* FENCE GATES*/
    public static final RegistryObject<Block> AUTUMN_TREE_GATE = registerBlock("autumn_tree_fence_gate",
            ()-> new FenceGateBlock(AbstractBlock.Properties.of(Material.WOOD).harvestLevel(3).harvestTool(ToolType.AXE).strength(3)));

    public static final RegistryObject<Block> SPRING_TREE_GATE = registerBlock("spring_tree_fence_gate",
            ()-> new FenceGateBlock(AbstractBlock.Properties.of(Material.WOOD).harvestLevel(3).harvestTool(ToolType.AXE).strength(3)));

    public static final RegistryObject<Block> SUMMER_TREE_GATE = registerBlock("summer_tree_fence_gate",
            ()-> new FenceGateBlock(AbstractBlock.Properties.of(Material.WOOD).harvestLevel(3).harvestTool(ToolType.AXE).strength(3)));

    public static final RegistryObject<Block> WINTER_TREE_GATE = registerBlock("winter_tree_fence_gate",
            ()-> new FenceGateBlock(AbstractBlock.Properties.of(Material.WOOD).harvestLevel(3).harvestTool(ToolType.AXE).strength(3)));


    /* REGISTER METHODES */
    private static<T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static<T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(FeyDataMod.FEYDATA_TAB)));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
