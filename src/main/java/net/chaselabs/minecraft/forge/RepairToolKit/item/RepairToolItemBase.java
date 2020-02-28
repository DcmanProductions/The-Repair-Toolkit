package net.chaselabs.minecraft.forge.RepairToolKit.item;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class RepairToolItemBase extends ItemBase {

	int level;
	int interact_current_clock = 0, interact_wanted_clock = 20;
	int inventory_current_clock = 0, inventory_wanted_clock;
	boolean isEnabled = false;
	boolean interact = true;

	public RepairToolItemBase(String name, Properties builder, int level) {
		super(name + "_" + level, builder.maxStackSize(1));
		this.level = level;
		inventory_wanted_clock = 20 * (5 - level);
	}

	@Override
	public ITextComponent getDisplayName(ItemStack stack) {
		return new StringTextComponent(TextFormatting.AQUA + "Level " + level + TextFormatting.GOLD + " Repair Toolkit: " + (isEnabled ? TextFormatting.GREEN + "ACTIVE" : TextFormatting.RED + "DISABLED"));
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		if (level <= 2)
			tooltip.add(new StringTextComponent("Can Repair " + level + " items at a time"));
		else {
			tooltip.add(new StringTextComponent("Will Repair all items at once"));
			if (level == 4) {
				tooltip.add(new StringTextComponent("Adds Speed"));
				tooltip.add(new StringTextComponent("Adds Haste"));
				tooltip.add(new StringTextComponent("Adds Luck"));
				tooltip.add(new StringTextComponent("Adds Strength"));
			}

		}
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entityIn, int itemSlot, boolean isSelected) {
		super.inventoryTick(stack, world, entityIn, itemSlot, isSelected);
//		((PlayerEntity) entityIn).sendMessage(new StringTextComponent("isEnabled: " + isEnabled + "  is Remote: " + world.isRemote + "  item level " + level));

		if (!interact) {
			interact_current_clock++;
			if (interact_current_clock >= interact_wanted_clock) {
				interact_current_clock = 0;
				interact = true;
			}
		}

		if (!world.isRemote && entityIn instanceof PlayerEntity && isEnabled) {
			PlayerEntity player = (PlayerEntity) entityIn;
			sendMessage(player, ("Entity isn't Remote and Entity is a Player and The Item is Active"));
			inventory_current_clock++;
			sendMessage(player, ("Current clock: " + inventory_current_clock + "  |  Wanted Clock: " + inventory_wanted_clock));
			if (inventory_current_clock >= inventory_wanted_clock) {
				sendMessage(player, ("Ready with level " + level));
				Heal(player, world);
				if (level == 4)
					Effect(player, world);
				inventory_current_clock = 0;
			}
		}
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		super.hasEffect(stack);
		return isEnabled;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand handIn) {
		if (interact) {
			isEnabled = !isEnabled;
			if (world.isRemote) {
				getDisplayName(getStack());
				hasEffect(getStack());
			}
			interact = false;
			return new ActionResult<ItemStack>(ActionResultType.SUCCESS, getStack());
		}
		return new ActionResult<ItemStack>(ActionResultType.FAIL, getStack());
	}

	void Effect(PlayerEntity player, World world) {
		sendMessage(player, ("Applying Potion Effect to Player"));
		player.addPotionEffect(new EffectInstance(new MiningGod(), 40, 1, false, false));
	}

	void Heal(PlayerEntity player, World world) {
		int index = 0;
		sendMessage(player, ("Starting Healing"));
		for (ItemStack item : player.inventory.offHandInventory) {
			sendMessage(player, ("Examining \"" + item.getDisplayName() + "\""));
			if (item.isDamaged()) {
				sendMessage(player, (item.getDisplayName() + " is Damaged"));
				if (index < level || level >= 3) {
					index++;
					sendMessage(player, ("Healing " + item.getDisplayName()));
					item.setDamage(item.getDamage() - level);
				}
			} else
				sendMessage(player, (item.getDisplayName() + " is not Damaged"));
		}
		index = 0;
		for (ItemStack item : player.inventory.armorInventory) {
			sendMessage(player, ("Examining \"" + item.getDisplayName() + "\""));
			if (item.isDamaged()) {
				sendMessage(player, (item.getDisplayName() + " is Damaged"));
				if (index < level || level >= 3) {
					index++;
					sendMessage(player, ("Healing " + item.getDisplayName()));
					item.setDamage(item.getDamage() - level);
				}
			} else
				sendMessage(player, (item.getDisplayName() + " is not Damaged"));
		}
		index = 0;
		for (ItemStack item : player.inventory.mainInventory) {
			sendMessage(player, ("Examining \"" + item.getDisplayName() + "\""));
			if (item.isDamaged()) {
				sendMessage(player, (item.getDisplayName() + " is Damaged"));
				if (index < level || level >= 3) {
					index++;
					sendMessage(player, ("Healing " + item.getDisplayName()));
					item.setDamage(item.getDamage() - level);
				}
			} else
				sendMessage(player, (item.getDisplayName() + " is not Damaged"));
		}
	}

	void sendMessage(PlayerEntity player, Object message) {
//		Logger log = org.apache.logging.log4j.LogManager.getLogger(Values.MOD_ID);
//
//		log.error(message + "");
//		player.sendMessage(new StringTextComponent(message + ""));
	}

	class MiningGod extends Effect {

		protected MiningGod() {
			super(EffectType.BENEFICIAL, 13107455);
			addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "91AEAA56-376B-4498-935B-2F7F68070635", (double) 0.2F, AttributeModifier.Operation.MULTIPLY_TOTAL);
			addAttributesModifier(SharedMonsterAttributes.ATTACK_SPEED, "AF8B6E3F-3328-4C0A-AA36-5BA2BB9DBEF3", (double) 2.1F, AttributeModifier.Operation.MULTIPLY_TOTAL);
			addAttributesModifier(SharedMonsterAttributes.LUCK, "03C3C89D-7037-4B42-869F-B146BCB64D2E", 1.0D, AttributeModifier.Operation.ADDITION);
			addAttributesModifier(SharedMonsterAttributes.ATTACK_DAMAGE, "648D7064-6A60-4F59-8ABE-C2C23A6DD7A9", 0.0D, AttributeModifier.Operation.ADDITION);
		}

		@Override
		public ITextComponent getDisplayName() {
			return new StringTextComponent(TextFormatting.GOLD + "Mining Friend");
		}

		@Override
		public boolean shouldRender(EffectInstance effect) {
			return false;
		}

	}

}
