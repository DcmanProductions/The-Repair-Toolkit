package net.chaselabs.minecraft.forge.RepairToolKit;

import net.chaselabs.minecraft.forge.RepairToolKit.block.BlockBase;
import net.chaselabs.minecraft.forge.RepairToolKit.item.BlockItemBase;
import net.chaselabs.minecraft.forge.RepairToolKit.list.BlockList;
import net.chaselabs.minecraft.forge.RepairToolKit.list.ItemList;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Values.MOD_ID)
public class Main {

	public Main() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(Main::commonSetup);
		MinecraftForge.EVENT_BUS.addListener(Main::serverStarting);
	}

	static void commonSetup(FMLCommonSetupEvent event) {

	}

	static void serverStarting(FMLServerStartingEvent event) {

	}

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents {

		@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> event) {
			for (ItemList value : ItemList.values())
				event.getRegistry().register(value.getItem());
			for (BlockList value : BlockList.values())
				for (BlockBase block : value.getBlock())
					event.getRegistry().register(new BlockItemBase(block));

		}

		@SubscribeEvent
		public static void registerBlocks(final RegistryEvent.Register<Block> event) {

			for (BlockList value : BlockList.values())
				for (Block block : value.getBlock())
					event.getRegistry().register(block);

		}

	}
}
