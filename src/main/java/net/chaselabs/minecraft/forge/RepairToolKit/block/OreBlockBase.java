package net.chaselabs.minecraft.forge.RepairToolKit.block;

import java.util.Arrays;
import java.util.List;

import net.minecraft.block.Block.Properties;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig.FillerBlockType;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class OreBlockBase {

	public static enum OreGenDimension {
		NONE,
		Overworld,
		Nether,
		BOTH;

		public List<FillerBlockType> getFillerBlock() {
			return this == Overworld ? Arrays.asList(FillerBlockType.NATURAL_STONE) : this == Nether ? Arrays.asList(FillerBlockType.NETHERRACK) : this == BOTH ? Arrays.asList(FillerBlockType.NATURAL_STONE, FillerBlockType.NETHERRACK) : null;
		}
	}

	List<BlockBase> blocks;

	/**
	 * 
	 * @param name
	 * @param properties
	 * @param dimension
	 * @param chance
	 * @param blocks_in_vein
	 * @param min_height
	 * @param max_height
	 */
	public OreBlockBase(String name, String displayName, Properties properties, OreGenDimension dimension, int chance, int blocks_in_vein, int min_height, int max_height) {
		blocks = dimension == OreGenDimension.BOTH ? Arrays.asList(new BlockBase(name + "_overworld", displayName + " Overworld", properties), new BlockBase(name + "_nether", displayName + " Nether", properties)) : dimension == OreGenDimension.Overworld ? Arrays.asList(new BlockBase(name, displayName, properties)) : dimension == OreGenDimension.Nether ? Arrays.asList(new BlockBase(name, displayName, properties)) : null;

		OreGeneration(blocks, dimension, chance, blocks_in_vein, min_height, max_height);
	}

	public List<BlockBase> getBlocks() {
		return blocks;
	}

	void OreGeneration(List<BlockBase> blocks, OreGenDimension dimension, int chance, int blocks_in_vein, int min_height, int max_height) {
		if (dimension == OreGenDimension.NONE || blocks == null)
			return;
		int index = 0;
		for (FillerBlockType filler : dimension.getFillerBlock()) {
			for (Biome biome : ForgeRegistries.BIOMES) {
				if (filler == FillerBlockType.NETHERRACK)
					biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.func_225566_b_(new OreFeatureConfig(filler, blocks.get(index).getDefaultState(), chance)).func_227228_a_(Placement.COUNT_RANGE.func_227446_a_(new CountRangeConfig(blocks_in_vein, min_height, 0, 128))));
				else
					biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.func_225566_b_(new OreFeatureConfig(filler, blocks.get(index).getDefaultState(), chance)).func_227228_a_(Placement.COUNT_RANGE.func_227446_a_(new CountRangeConfig(blocks_in_vein, min_height, 0, max_height))));
			}
			index++;
		}
	}

}
