package net.chaselabs.minecraft.forge.RepairToolKit.list;

import java.util.Arrays;
import java.util.List;

import net.chaselabs.minecraft.forge.RepairToolKit.block.BlockBase;
import net.chaselabs.minecraft.forge.RepairToolKit.block.OreBlockBase;
import net.chaselabs.minecraft.forge.RepairToolKit.block.OreBlockBase.OreGenDimension;
import net.minecraft.block.Block.Properties;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ToolType;

public enum BlockList {

	buxer_ore(new OreBlockBase("buxer_ore", "Buxer Ore", Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(5.f), OreGenDimension.BOTH, 8, 4, 5, 72).getBlocks()),
	pizar_gem_ore(new OreBlockBase("pizar_gem_ore", "Pizar Gem Ore", Properties.create(Material.ROCK).harvestLevel(3).harvestTool(ToolType.PICKAXE).hardnessAndResistance(25.f, 1200.0f), OreGenDimension.BOTH, 3, 3, 5, 45).getBlocks())

	;

	List<BlockBase> block;
	Properties properties;

	BlockList(BlockBase block) {
		this.block = Arrays.asList(block);
		this.properties = Properties.from(block);
	}

	BlockList(BlockBase block, Properties properties) {
		this.block = Arrays.asList(block);
		this.properties = properties;
	}

	BlockList(List<BlockBase> blocks) {
		this.block = blocks;
	}

	public List<BlockBase> getBlock() {
		return block;
	}

	public String getDisplayName(int index) {
		return block.get(index).getNameTextComponent().getString();
	}

	public ResourceLocation getResourceName(int index) {
		return block.get(index).getRegistryName();
	}

	public String getResourceLocationString(int index) {
		return block.get(index).getRegistryName().toString();
	}

	public Properties getProperties() {
		return properties;
	}

}
