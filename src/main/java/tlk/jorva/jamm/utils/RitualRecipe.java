package tlk.jorva.jamm.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.item.ItemStack;

public class RitualRecipe {
	private ArrayList<ItemStack> inputs;
	private EnumRitualType ritualType;
	private ItemStack output;

	public RitualRecipe(ItemStack output, EnumRitualType rType, ItemStack... inputArgs) {
		this.output = output;
		ritualType = rType;
		inputs = new ArrayList<ItemStack>(Arrays.asList(inputArgs));
	}

	public ItemStack getOutput() {
		return output.copy();
	}

	public EnumRitualType getRitualType() {
		return ritualType;
	}

	public List<ItemStack> getInputs() {
		return inputs;
	}

}