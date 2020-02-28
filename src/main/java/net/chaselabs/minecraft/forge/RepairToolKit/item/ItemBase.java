package net.chaselabs.minecraft.forge.RepairToolKit.item;

import net.chaselabs.minecraft.forge.RepairToolKit.Values;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class ItemBase extends Item {

	String displayName = "";
	Properties builder;

	ItemBase(String name, Properties builder) {
		super(builder.group(Values.group));
		this.builder = builder;
		setRegistryName(name);
	}

	public ItemBase(String name, String displayName, Properties builder) {
		super(builder.group(Values.group));
		this.builder = builder;
		this.displayName = displayName;
		setRegistryName(name);
	}

	@Override
	public ITextComponent getDisplayName(ItemStack stack) {
		if (displayName != "")
			return new StringTextComponent(displayName);
		return super.getDisplayName(stack);

	}

	public Item getItem() {
		return this.asItem();
	}

	public ItemStack getStack() {
		return new ItemStack(this);
	}

	public Properties GetBuilder() {
		return builder;
	}

}
