package tlk.jorva.jamm.common.tiles.RitualStone;

import java.util.List;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import tlk.jorva.jamm.utils.Debug;
import tlk.jorva.jamm.utils.EnumRitualType;
import tlk.jorva.jamm.utils.RitualRecipe;
import tlk.jorva.jamm.utils.RitualRecipeManager;
import tlk.jorva.jamm.utils.WorldUtils;

public class TileRitualStone extends TileEntity {

	public void onActivated(EntityPlayer player) {
		int x, y, z;

		x = pos.getX();
		y = pos.getY();
		z = pos.getZ();

		EnumRitualType ritualType = WorldUtils.getMultiblockRitual(worldObj, x, y, z);

		if (ritualType != null) {
			List<EntityItem> nearbyItems = this.getWorld().getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(pos.getX() - 1, pos.getY(), pos.getZ() - 1, pos.getX() + 1, pos.getY() + 1, pos.getZ() + 1));
			ItemStack[] stacks = new ItemStack[nearbyItems.size()];
			for (int i = 0; i < nearbyItems.size(); i++) {
				stacks[i] = nearbyItems.get(i).getEntityItem();
			}
			for (ItemStack itemStack : stacks) {
				Debug.log(itemStack.getDisplayName());
			}

			RitualRecipe recipe = RitualRecipeManager.getRecipeFromInputs(ritualType, stacks);
			if (recipe != null) {
				Debug.log("Recipe is not null!");
				WorldUtils.createLightning(worldObj, x, y, z, true);
				EntityItem outputEntity = new EntityItem(worldObj, x + .5, y + 1, z + .5, recipe.getOutput());
			}
		}
	}
}
