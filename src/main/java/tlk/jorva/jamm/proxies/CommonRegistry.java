package tlk.jorva.jamm.proxies;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import tlk.jorva.jamm.RecipeManager;
import tlk.jorva.jamm.common.items.ItemCylenSword;
import tlk.jorva.jamm.common.items.ItemSouliniumIngot;
import tlk.jorva.jamm.common.items.wands.ItemBuildingWand;
import tlk.jorva.jamm.common.tiles.RitualStone.BlockAdvancedRitualStone;
import tlk.jorva.jamm.common.tiles.RitualStone.BlockBasicRitualStone;
import tlk.jorva.jamm.common.tiles.RitualStone.TileRitualStone;
import tlk.jorva.jamm.utils.Debug;
import tlk.jorva.jamm.utils.RitualRecipeManager;

public class CommonRegistry {

	public static Block blockAdvancedRitualStone, blockBasicRitualStone;
	public static Item itemCylenSword, itemSouliniumIngot, itemBuildingWand;

	public void preInit(FMLPreInitializationEvent e) {
		
		new Debug();
		
		setupItems();
		setupTiles();
		setupBlocks();
		RitualRecipeManager.addRecipes();

	}

	public void init(FMLInitializationEvent e) {
		addRecipes();
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
		itemBuildingWand = new ItemBuildingWand();

		GameRegistry.register(itemCylenSword);
		GameRegistry.register(itemSouliniumIngot);
		GameRegistry.register(itemBuildingWand);

	}

	public void setupTiles() {
		GameRegistry.registerTileEntity(TileRitualStone.class, "ritualstone");
	}
	
	public void addRecipes(){
		CraftingManager.getInstance().addRecipe(new ItemStack(blockBasicRitualStone, 1), "IBI", "BDB", "IBI", 'I', Blocks.IRON_BLOCK, 'B', Blocks.REDSTONE_BLOCK, 'D', Items.DIAMOND);
		CraftingManager.getInstance().addRecipe(new ItemStack(blockAdvancedRitualStone), "IBI", "BRB", "IBI", 'I', new ItemStack(Blocks.IRON_BLOCK), 'B', new ItemStack(Blocks.LAPIS_BLOCK), 'R', new ItemStack(blockBasicRitualStone));
	}
}
