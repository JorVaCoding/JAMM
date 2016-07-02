package tlk.jorva.jamm.utils;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tlk.jorva.jamm.common.tiles.RitualStone.BlockAdvancedRitualStone;
import tlk.jorva.jamm.common.tiles.RitualStone.BlockBasicRitualStone;

public class WorldUtils {
	public static void createLightning(World world, double x, double y, double z, boolean isEffect) {
		EntityLightningBolt bolt = new EntityLightningBolt(world, x, y, z, isEffect);
		world.spawnEntityInWorld(bolt);
	}

	public static <T extends Entity> List<T> getEntitiesInBox(World world, Class<? extends T> classEntity, double aX,
			double aY, double aZ, double bX, double bY, double bZ) {
		Debug.log(aX, aY, aZ, bX, bY, bZ);
		return world.getEntitiesWithinAABB(classEntity, new AxisAlignedBB(aX, aY, aZ, bX, bY, bZ));
	}

	public static boolean isRitualMultiblockComplete(EnumRitualType ritualType, World world, int x, int y, int z) {

		if (ritualType == EnumRitualType.BASIC) {
			if (!(getBlock(world, x, y, z) instanceof BlockBasicRitualStone))
				return false;
			if (!(getBlock(world, x, y, z + 1) == Blocks.IRON_BLOCK))
				return false;
			if (!(getBlock(world, x + 1, y, z) == Blocks.IRON_BLOCK))
				return false;
			if (!(getBlock(world, x - 1, y, z) == Blocks.IRON_BLOCK))
				return false;
			if (!(getBlock(world, x, y, z - 1) == Blocks.IRON_BLOCK))
				return false;
			return true;
		} else if (ritualType == EnumRitualType.FUSION) {
			if (!(getBlock(world, x, y, z) instanceof BlockBasicRitualStone))
				return false;
			if (!(getBlock(world, x, y - 1, z) == Blocks.GOLD_BLOCK))
				return false;
			if (!(getBlock(world, x, y - 1, z + 1) == Blocks.REDSTONE_BLOCK))
				return false;
			if (!(getBlock(world, x + 1, y - 1, z) == Blocks.REDSTONE_BLOCK))
				return false;
			if (!(getBlock(world, x - 1, y - 1, z) == Blocks.REDSTONE_BLOCK))
				return false;
			if (!(getBlock(world, x, y - 1, z - 1) == Blocks.REDSTONE_BLOCK))
				return false;

			if (!(getBlock(world, x + 1, y - 1, z + 1) == Blocks.IRON_BLOCK))
				return false;
			if (!(getBlock(world, x + 1, y - 1, z - 1) == Blocks.IRON_BLOCK))
				return false;
			if (!(getBlock(world, x - 1, y - 1, z + 1) == Blocks.IRON_BLOCK))
				return false;
			if (!(getBlock(world, x - 1, y - 1, z - 1) == Blocks.IRON_BLOCK))
				return false;

			if (!(getBlock(world, x + 1, y, z + 1) == Blocks.IRON_BLOCK))
				return false;
			if (!(getBlock(world, x + 1, y, z - 1) == Blocks.IRON_BLOCK))
				return false;
			if (!(getBlock(world, x - 1, y, z + 1) == Blocks.IRON_BLOCK))
				return false;
			if (!(getBlock(world, x - 1, y, z - 1) == Blocks.IRON_BLOCK))
				return false;

			if (!(getBlock(world, x + 1, y + 1, z + 1) == Blocks.GLOWSTONE))
				return false;
			if (!(getBlock(world, x + 1, y + 1, z - 1) == Blocks.GLOWSTONE))
				return false;
			if (!(getBlock(world, x - 1, y + 1, z + 1) == Blocks.GLOWSTONE))
				return false;
			if (!(getBlock(world, x - 1, y + 1, z - 1) == Blocks.GLOWSTONE))
				return false;

			return true;
		} else if (ritualType == EnumRitualType.WEAPONRY){
			if(!(getBlock(world, x,y,z) instanceof BlockAdvancedRitualStone))
				return false;
			if(!(getBlock(world,x,y-1,z) == Blocks.LAVA))
				return false;
			if(!(getBlock(world,x,y-2,z) == Blocks.EMERALD_BLOCK))
				return false;
			
			if (!(getBlock(world, x, y - 1, z + 1) == Blocks.IRON_BLOCK))
				return false;
			if (!(getBlock(world, x + 1, y - 1, z) == Blocks.IRON_BLOCK))
				return false;
			if (!(getBlock(world, x - 1, y - 1, z) == Blocks.IRON_BLOCK))
				return false;
			if (!(getBlock(world, x, y - 1, z - 1) == Blocks.IRON_BLOCK))
				return false;
			
			if (!(getBlock(world, x + 1, y - 1, z + 1) == Blocks.ANVIL))
				return false;
			if (!(getBlock(world, x + 1, y - 1, z - 1) == Blocks.ANVIL))
				return false;
			if (!(getBlock(world, x - 1, y - 1, z + 1) == Blocks.ANVIL))
				return false;
			if (!(getBlock(world, x - 1, y - 1, z - 1) == Blocks.ANVIL))
				return false;
			
			return true;
		}

		return false;
	}

	public static EnumRitualType getMultiblockRitual(World world, int x, int y, int z) {
		if (isRitualMultiblockComplete(EnumRitualType.BASIC, world, x, y, z))
			return EnumRitualType.BASIC;
		if (isRitualMultiblockComplete(EnumRitualType.FUSION, world, x, y, z))
			return EnumRitualType.FUSION;
		if (isRitualMultiblockComplete(EnumRitualType.WEAPONRY, world, x, y, z))
			return EnumRitualType.WEAPONRY;
		return null;
	}

	public static Block getBlock(World world, int x, int y, int z) {
		if (world.isAirBlock(new BlockPos(x, y, z)))
			return null;
		return world.getBlockState(new BlockPos(x, y, z)).getBlock();
	}

}
