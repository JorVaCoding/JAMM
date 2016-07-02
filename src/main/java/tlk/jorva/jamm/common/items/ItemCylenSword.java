package tlk.jorva.jamm.common.items;

import java.util.List;

import com.mojang.realmsclient.gui.ChatFormatting;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import tlk.jorva.jamm.utils.ItemNBTUtils;
import tlk.jorva.jamm.utils.WorldUtils;

public class ItemCylenSword extends ItemSword {
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		if(ItemNBTUtils.getTagCompound(stack).getBoolean("isImbued")){
			tooltip.add(ChatFormatting.LIGHT_PURPLE + "Imbued!");
			tooltip.add(ChatFormatting.RED + "WARNING: " + ChatFormatting.YELLOW + "Not safe for creepers (or warlordjones).");
		}
		super.addInformation(stack, playerIn, tooltip, advanced);
	}

	public ItemCylenSword() {
		super(ToolMaterial.DIAMOND);
		setRegistryName("cylenSword");
		setUnlocalizedName("cylenSword");
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return ItemNBTUtils.getTagCompound(stack).getBoolean("isImbued") ? getUnlocalizedName() + "Imbued"
				: getUnlocalizedName();
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {

		if (entity instanceof EntityLivingBase) {
			if (ItemNBTUtils.getTagCompound(stack).getBoolean("isImbued")) {
				EntityLivingBase target = (EntityLivingBase) entity;
				World world = target.worldObj;
				double x = target.posX;
				double y = target.posY;
				double z = target.posZ;
				
				WorldUtils.createLightning(world, x, y, z, false);
				target.addPotionEffect(new PotionEffect(Potion.getPotionById(20), 5*20, 2));
				target.addPotionEffect(new PotionEffect(Potion.getPotionById(24), 5*20, 2));
				target.addPotionEffect(new PotionEffect(Potion.getPotionById(11), 5*20, 2));
				
				if(target.getName().equalsIgnoreCase("warlordjones")){
					target.attackEntityFrom(new DamageSource("cylent"), 1000);
					return false;
				}

			}
		}

		return super.onLeftClickEntity(stack, player, entity);
	}
}
