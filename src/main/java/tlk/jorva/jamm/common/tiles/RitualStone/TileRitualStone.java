package tlk.jorva.jamm.common.tiles.RitualStone;

import java.util.List;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import tlk.jorva.jamm.utils.EnumRitualType;
import tlk.jorva.jamm.utils.RitualRecipe;
import tlk.jorva.jamm.utils.RitualRecipeManager;
import tlk.jorva.jamm.utils.WorldUtils;

public class TileRitualStone extends TileEntity {
	int x, y, z;
	World world;

	public TileRitualStone() {
		world = worldObj;
		x = pos.getX();
		y = pos.getY();
		z = pos.getZ();
	}

	public void onActivated(EntityPlayer player) {
		EnumRitualType ritualType = WorldUtils.getMultiblockRitual(world, x, y, z);

		if (ritualType != null) {
			List<EntityItem> nearbyItems = WorldUtils.getEntitiesInBox(world, EntityItem.class, x - 1, y, z - 1, x + 1, y, z + 1);
			ItemStack[] stacks = new ItemStack[nearbyItems.size()];
			for (int i = 0; i < nearbyItems.size(); i++){
				stacks[i] = nearbyItems.get(i).getEntityItem();
			}
			
			RitualRecipe recipe = RitualRecipeManager.getRecipeFromInputs(ritualType, stacks);
			if(recipe != null){
				WorldUtils.createLightning(world, x, y, z, true);
				EntityItem outputEntity = new EntityItem(world,x+.5,y+1,z+.5, recipe.getOutput());
			}
		}
	}
}
