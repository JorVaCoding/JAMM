package tlk.jorva.jamm.common.items;

import java.util.List;

import com.mojang.realmsclient.gui.ChatFormatting;

import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;

public class ItemDebug extends Item{
	public ItemDebug(){
		setRegistryName("itemDebug");
		setUnlocalizedName("itemDebug");
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		tooltip.add(ChatFormatting.AQUA + "Not your normal debug item...");
		if(playerIn.getName() == "JorVa_" || playerIn.capabilities.isCreativeMode){
			
		}
		super.addInformation(stack, playerIn, tooltip, advanced);
	}
}
