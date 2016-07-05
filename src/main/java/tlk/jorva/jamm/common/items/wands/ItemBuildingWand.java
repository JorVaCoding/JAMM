package tlk.jorva.jamm.common.items.wands;

import java.util.List;

import org.lwjgl.Sys;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import scala.collection.concurrent.Debug;

public class ItemBuildingWand extends Item {
	public ItemBuildingWand() {
		setRegistryName("buildingWand");
		setUnlocalizedName("buildingWand");
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		System.out.println(advanced);
		super.addInformation(stack, playerIn, tooltip, advanced);
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
			Block block = world.getBlockState(pos).getBlock();
			// player.inventory.dropAllItems();
			int currentBlocks = 0;

			if (!player.capabilities.isCreativeMode) {
				for (ItemStack invStack : player.inventory.mainInventory) {
					if (invStack != null) {
						if (invStack.getItem() instanceof ItemBlock) {
							if (((ItemBlock) invStack.getItem()).getBlock() == block) {
								currentBlocks += invStack.stackSize;
							}
						}
					}
				}
			} else
				currentBlocks = Integer.MAX_VALUE;
			int placeable = Math.min(currentBlocks, 81);
			if (facing == EnumFacing.UP) {
				int radius = 3;
				if (world.isAirBlock(pos.add(0, 1, 0)) && placeable > 0) {
					world.setBlockState(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ()), block.getDefaultState());
					placeable--;
				}
				for (int x = pos.getX() - radius + 1; x < pos.getX() + radius; x++) {
					int y = pos.getY() + 1;
					for (int z = pos.getZ() - radius + 1; z < pos.getZ() + radius; z++) {
						BlockPos bPos = new BlockPos(x, y, z);
						if (world.isAirBlock(bPos) ) {
							if(placeable > 0){
								world.setBlockState(bPos, block.getDefaultState());
								placeable--;
							}
						}
						System.out.println(placeable);
					}
				}
			}
			
			for (ItemStack invStack : player.inventory.mainInventory) {
				if (invStack != null) {
					if (invStack.getItem() instanceof ItemBlock) {
						if (((ItemBlock) invStack.getItem()).getBlock() == block) {
							int r = Math.min(placeable, invStack.stackSize);
							if(r == invStack.stackSize)
								invStack = null;
							else
								invStack.stackSize -= r;
						}
					}
				}
			}
			
		return EnumActionResult.FAIL;
	}
}
