package tlk.jorva.jamm.common.items;

import java.util.List;

import com.mojang.realmsclient.gui.ChatFormatting;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tlk.jorva.jamm.utils.EntityUtils;
import tlk.jorva.jamm.utils.ItemNBTUtils;
import tlk.jorva.jamm.utils.WorldUtils;

public class ItemCylenSword extends ItemSword {

	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		if (ItemNBTUtils.getTagCompound(stack).getBoolean("isImbued")) {
			tooltip.add(ChatFormatting.LIGHT_PURPLE + "Imbued!");
			tooltip.add(ChatFormatting.RED + "WARNING: " + ChatFormatting.YELLOW
					+ "Not safe for creepers (or warlordjones).");
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
				target.addPotionEffect(new PotionEffect(Potion.getPotionById(20), 5 * 20, 2));
				target.addPotionEffect(new PotionEffect(Potion.getPotionById(24), 5 * 20, 2));
				target.addPotionEffect(new PotionEffect(Potion.getPotionById(11), 5 * 20, 2));

				if (target.getName().equalsIgnoreCase("warlordjones") && target instanceof EntityPlayer) {
					if (player.isSneaking()) {
						target.attackEntityFrom(new DamageSource("cylen").setDamageAllowedInCreativeMode(), 1000);
						return false;
					}
				}

			}
		}

		return super.onLeftClickEntity(stack, player, entity);
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
		NBTTagCompound compound = ItemNBTUtils.getTagCompound(stack);
		int cooldown = 0;
		if (compound.hasKey("cooldown"))
			cooldown = compound.getInteger("cooldown");
		if (cooldown > 0)
			cooldown--;
		compound.setInteger("cooldown", cooldown);
		stack.setTagCompound(compound);
		super.onUpdate(stack, world, entity, slot, selected);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand) {
		if (!world.isRemote) {
			NBTTagCompound compound = ItemNBTUtils.getTagCompound(stack);
			int cooldown = 0;
			if (compound.hasKey("cooldown"))
				cooldown = compound.getInteger("cooldown");
			if (cooldown == 0) {
				EntityUtils.WitherSkullUtils.SummonSkull(player, true);
				cooldown = (int)(20 * 2.5);
//				cooldown = 1;
			}
			compound.setInteger("cooldown", cooldown);
			stack.setTagCompound(compound);
		}
		return super.onItemRightClick(stack, world, player, hand);
	}
	
	@Override
	public boolean hasEffect(ItemStack stack) {
		return (ItemNBTUtils.getTagCompound(stack).getBoolean("isImbued"));
	}
	

}
