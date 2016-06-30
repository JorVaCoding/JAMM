package tlk.jorva.jamm.common.tiles.RitualStone;

import java.util.List;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import tlk.jorva.jamm.utils.Debug;
import tlk.jorva.jamm.utils.EnumRitualType;
import tlk.jorva.jamm.utils.RitualRecipe;
import tlk.jorva.jamm.utils.RitualRecipeManager;
import tlk.jorva.jamm.utils.WorldUtils;

public class TileRitualStone extends TileEntity implements ITickable {

	int cooldown;

	public TileRitualStone() {
		cooldown = 0;
	}

	@Override
	public void update() {
		if (cooldown > 0)
			cooldown -= 1;
	}

	public void onActivated(EntityPlayer player) {
		int x, y, z;

		x = pos.getX();
		y = pos.getY();
		z = pos.getZ();

		EnumRitualType ritualType = WorldUtils.getMultiblockRitual(worldObj, x, y, z);
		if (ritualType != null) {
			// List<EntityItem> nearbyItems =
			// this.getWorld().getEntitiesWithinAABB(EntityItem.class, new
			// AxisAlignedBB(
			// pos.getX() - 1, pos.getY(), pos.getZ() - 1, pos.getX() + 1,
			// pos.getY() + 1, pos.getZ() + 1));
			List<EntityItem> nearbyItems = this.getWorld().getEntitiesWithinAABB(EntityItem.class,
					new AxisAlignedBB(pos.getX() - 1, pos.getY() + 1, pos.getZ() - 1, pos.getX() + 1,
							pos.getY() + 2 + 1, pos.getZ() + 1));

			ItemStack[] stacks = new ItemStack[nearbyItems.size()];
			for (int i = 0; i < nearbyItems.size(); i++) {
				stacks[i] = nearbyItems.get(i).getEntityItem();
			}

			RitualRecipe recipe = RitualRecipeManager.getRecipeFromInputs(ritualType, stacks);
			if (recipe != null) {
				if (cooldown == 0) {
					if (!worldObj.isRemote) {
						EntityItem outputEntity = new EntityItem(worldObj, x + .5, y + 1, z + .5, recipe.getOutput());
						worldObj.spawnEntityInWorld(outputEntity);
						for (EntityItem i : nearbyItems)
							i.setDead();
					} else {
						WorldUtils.createLightning(worldObj, x, y, z, true);
					}
					cooldown = 20;
				}
			}
		}
	}
}
