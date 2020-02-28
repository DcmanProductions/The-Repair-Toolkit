package net.chaselabs.minecraft.forge.RepairToolKit.group;

import net.chaselabs.minecraft.forge.RepairToolKit.list.ItemList;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModGroup extends ItemGroup {

	public ModGroup() {
		super("repair_toolkit");
	}

	@Override
	public ItemStack createIcon() {
		return ItemList.level_one_repair_kit.getItem().getStack();
	}

}
