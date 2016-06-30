package tlk.jorva.jamm.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class RitualRecipeManager {
	private static final HashSet<RitualRecipe> recipes = new HashSet<RitualRecipe>();

	public static void registerRecipe(RitualRecipe recipe) {
		if (!recipes.contains(recipe))
			recipes.add(recipe);
	}

	public static RitualRecipe getRecipeFromInputs(EnumRitualType ritualType, ItemStack... inputs) {
		for (RitualRecipe recipe : recipes) {
			Debug.log(ritualType, recipe.getRitualType());
			if (recipe.getRitualType() == ritualType && compareInputSets(Arrays.asList(inputs), recipe.getInputs())) {
				Debug.log(ritualType, recipe.getRitualType());
				return recipe;
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
	
	public static HashSet<RitualRecipe> getRecipes(){
		return recipes;
	}
	
	public static void addRecipes(){
		registerRecipe(new RitualRecipe(new ItemStack(Blocks.DIAMOND_BLOCK), EnumRitualType.BASIC, new ItemStack(Blocks.DIRT)));
		registerRecipe(new RitualRecipe(new ItemStack(Blocks.DIRT), EnumRitualType.BASIC, new ItemStack(Blocks.DIAMOND_BLOCK)));
	}
	
}


