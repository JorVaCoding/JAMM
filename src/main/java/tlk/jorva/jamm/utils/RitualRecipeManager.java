package tlk.jorva.jamm.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import tlk.jorva.jamm.proxies.CommonProxy;

public class RitualRecipeManager {
	private static final HashSet<RitualRecipe> recipes = new HashSet<RitualRecipe>();

	public static void registerRecipe(RitualRecipe recipe) {
		if (!recipes.contains(recipe))
			recipes.add(recipe);
	}

	public static RitualRecipe getRecipeFromInputs(EnumRitualType ritualType, ItemStack... inputs) {
		for (RitualRecipe recipe : recipes) {
			if (recipe.getRitualType() == ritualType) {
				if (compareInputSets(Arrays.asList(inputs), recipe.getInputs())) {
					return recipe;
				}
			}
		}

		return null;
	}

	private static boolean compareInputSets(List<ItemStack> firstSet, List<ItemStack> secondSet) {
		if (firstSet.size() != secondSet.size())
			return false;

		for (ItemStack stack : firstSet) {
			boolean equal = false;
			for (ItemStack secStack : secondSet) {
				if (ItemStack.areItemStacksEqual(stack, secStack)) {
					equal = true;
					break;
				}
			}

			if (!equal)
				return false;
		}

		return true;
	}

	public static HashSet<RitualRecipe> getRecipes() {
		return recipes;
	}

	public static void addRecipes() {
		registerRecipe(new RitualRecipe(new ItemStack(Blocks.DIAMOND_BLOCK), EnumRitualType.BASIC,
				new ItemStack(Blocks.DIRT)));
		registerRecipe(new RitualRecipe(new ItemStack(Blocks.DIRT), EnumRitualType.BASIC,
				new ItemStack(Blocks.DIAMOND_BLOCK)));

		registerRecipe(new RitualRecipe(new ItemStack(CommonProxy.itemSouliniumIngot, 8), EnumRitualType.FUSION,
				new ItemStack(Blocks.SOUL_SAND, 8),
				new ItemStack(Items.ROTTEN_FLESH),
				new ItemStack(Items.GHAST_TEAR),
				new ItemStack(Items.BLAZE_ROD),
				new ItemStack(Items.SPIDER_EYE),
				new ItemStack(Items.ENDER_PEARL),
				new ItemStack(Items.BONE),
				new ItemStack(Items.GUNPOWDER),
				new ItemStack(Items.SLIME_BALL),
				new ItemStack(Items.GOLD_INGOT, 8)));
		
		registerRecipe(new RitualRecipe(new ItemStack(CommonProxy.itemCylenSword), EnumRitualType.WEAPONRY, new ItemStack(Blocks.DIAMOND_BLOCK), new ItemStack(CommonProxy.itemSouliniumIngot, 2), new ItemStack(Items.BLAZE_ROD)));
		NBTTagCompound imbuedCompound = new NBTTagCompound();
		imbuedCompound.setBoolean("isImbued", true);
		ItemStack imbuedSword = new ItemStack(CommonProxy.itemCylenSword);
		imbuedSword.setTagCompound(imbuedCompound);
		
		registerRecipe(new RitualRecipe(imbuedSword, EnumRitualType.WEAPONRY, new ItemStack(CommonProxy.itemCylenSword), new ItemStack(Items.GOLDEN_APPLE), new ItemStack(Items.BREWING_STAND), new ItemStack(Blocks.QUARTZ_BLOCK,16)));
	}

}
