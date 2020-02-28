package net.chaselabs.minecraft.forge.RepairToolKit.block;

import net.minecraft.block.Block;

public class BlockBase extends Block {

	String displayName = "";

	BlockBase(String name, Properties properties) {
		super(properties);
		setRegistryName(name);
		displayName = "";
	}

	public BlockBase(String name, String displayName, Properties properties) {
		super(properties);
		setRegistryName(name);
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}

}
