package net.chaselabs.minecraft.forge.RepairToolKit.item;

import net.chaselabs.minecraft.forge.RepairToolKit.Values;
import net.chaselabs.minecraft.forge.RepairToolKit.block.BlockBase;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class BlockItemBase extends BlockItem {

	String displayName;

	public BlockItemBase(BlockBase blockIn) {
		super(blockIn, new Item.Properties().group(Values.group));
		setRegistryName(blockIn.getRegistryName());
		displayName = blockIn.getDisplayName();
	}

	@Override
	public ITextComponent getDisplayName(ItemStack stack) {
		if (displayName != "")
			return new StringTextComponent(displayName);
		return super.getDisplayName(stack);
	}

}
