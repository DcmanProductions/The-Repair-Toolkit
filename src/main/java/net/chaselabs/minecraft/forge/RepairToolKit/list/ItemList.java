package net.chaselabs.minecraft.forge.RepairToolKit.list;

import net.chaselabs.minecraft.forge.RepairToolKit.item.DebugItemBase;
import net.chaselabs.minecraft.forge.RepairToolKit.item.ItemBase;
import net.chaselabs.minecraft.forge.RepairToolKit.item.RepairToolItemBase;
import net.minecraft.item.Item.Properties;
import net.minecraft.util.ResourceLocation;

public enum ItemList {
	level_one_repair_kit(new RepairToolItemBase("repair_kit", new Properties(), 1)),
	level_two_repair_kit(new RepairToolItemBase("repair_kit", new Properties(), 2)),
	level_three_repair_kit(new RepairToolItemBase("repair_kit", new Properties(), 3)),
	level_four_repair_kit(new RepairToolItemBase("repair_kit", new Properties(), 4)),
	buxer_ingot(new ItemBase("buxer_ingot", "Buxer Ingot", new Properties())),
	pizar_gem(new ItemBase("pizar_gem", "Pizar Gem", new Properties())),

	test(new DebugItemBase("debug", "Debug Stick", new Properties()))

	;

	ItemBase item;
	Properties properties;

	ItemList(ItemBase item) {
		this.item = item;
		this.properties = item.GetBuilder();
	}

	ItemList(ItemBase item, Properties properties) {
		this.item = item;
		this.properties = properties;
	}

	public ItemBase getItem() {
		return item;
	}

	public Properties getProperties() {
		return properties;
	}

	public String getItemDisplayName() {
		return item.getName().getString();
	}

	public ResourceLocation getItemResourceLocation() {
		return item.getRegistryName();
	}

	public String getItemResourceString() {
		return item.getRegistryName().toString();
	}
}
