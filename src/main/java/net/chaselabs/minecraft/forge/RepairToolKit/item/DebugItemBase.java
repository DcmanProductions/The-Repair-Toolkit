package net.chaselabs.minecraft.forge.RepairToolKit.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class DebugItemBase extends ItemBase {

	public DebugItemBase(String name, String displayName, Properties builder) {
		super(name, displayName, builder);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		for (ItemStack item : playerIn.inventory.mainInventory) {
			if (item.isDamageable()) {
				item.setDamage(item.getMaxDamage() - 1);
			}
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

}
