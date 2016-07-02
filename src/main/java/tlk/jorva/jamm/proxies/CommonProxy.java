package tlk.jorva.jamm.proxies;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import tlk.jorva.jamm.RecipeManager;
import tlk.jorva.jamm.common.items.ItemCylenSword;
import tlk.jorva.jamm.common.items.ItemSouliniumIngot;
import tlk.jorva.jamm.common.tiles.RitualStone.BlockAdvancedRitualStone;
import tlk.jorva.jamm.common.tiles.RitualStone.BlockBasicRitualStone;
import tlk.jorva.jamm.common.tiles.RitualStone.TileRitualStone;
import tlk.jorva.jamm.utils.Debug;
import tlk.jorva.jamm.utils.RitualRecipeManager;

public class CommonProxy {

	public static Block blockAdvancedRitualStone, blockBasicRitualStone;
	public static Item itemCylenSword, itemSouliniumIngot;

	public void preInit(FMLPreInitializationEvent e) {
		setupItems();
		setupTiles();
		setupBlocks();
		RitualRecipeManager.addRecipes();

	}

	public void init(FMLInitializationEvent e) {
		RecipeManager.addRecipes();
	}

	public void postInit(FMLPostInitializationEvent e) {
	}

	public void setupItems() {

	}

	public void setupBlocks() {

		blockBasicRitualStone = new BlockBasicRitualStone().setRegistryName("basicRitualStone")
				.setUnlocalizedName("basicRitualStone");
		blockAdvancedRitualStone = new BlockAdvancedRitualStone().setRegistryName("advancedRitualStone")
				.setUnlocalizedName("advancedRitualStone");

		GameRegistry.registerWithItem(blockBasicRitualStone);
		GameRegistry.registerWithItem(blockAdvancedRitualStone);

		// Items
		itemCylenSword = new ItemCylenSword();
		itemSouliniumIngot = new ItemSouliniumIngot();

		GameRegistry.register(itemCylenSword);
		GameRegistry.register(itemSouliniumIngot);

	}

	public void setupTiles() {
		GameRegistry.registerTileEntity(TileRitualStone.class, "ritualstone");
	}

}
