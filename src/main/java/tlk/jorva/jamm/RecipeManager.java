package tlk.jorva.jamm;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import tlk.jorva.jamm.proxies.CommonProxy;

public class RecipeManager {
	public static void addRecipes(){
		GameRegistry.addRecipe(new ItemStack(CommonProxy.blockBasicRitualStone, 1), "IBI", "BDB", "IBI", 'I', Blocks.IRON_BLOCK, 'B', Blocks.REDSTONE_BLOCK, 'D', Items.DIAMOND);
		GameRegistry.addRecipe(new ItemStack(CommonProxy.blockAdvancedRitualStone), "IBI", "BRB", "IBI", 'I', new ItemStack(Blocks.IRON_BLOCK), 'B', new ItemStack(Blocks.LAPIS_BLOCK), 'R', new ItemStack(CommonProxy.blockBasicRitualStone));
		
	}
}
