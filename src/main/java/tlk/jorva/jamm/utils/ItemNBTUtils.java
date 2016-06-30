package tlk.jorva.jamm.utils;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemNBTUtils {
	
	public static boolean hasTagCompound(ItemStack stack){
		return stack.getTagCompound() != null ? true : false;
	}
	
	public static NBTTagCompound getTagCompound(ItemStack stack){
		return hasTagCompound(stack) ? stack.getTagCompound() : new NBTTagCompound();
	}
	
}
