package tlk.jorva.jamm.utils;

import java.util.List;

import com.google.common.collect.ImmutableMap;

import net.minecraft.block.Block;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
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
		}
		if (ritualType == EnumRitualType.FUSION) {
			// if(!(getBlock(world, x, y, z) instanceof BlockBasicRitualStone))
			// return false;
			// if(!(getBlock(world, x, y-1, z) == Blocks.GOLD_BLOCK)) return
			// false;
			// if(!(getBlock(world, x, y-1, z+1) == Blocks.REDSTONE_BLOCK))
			// return false;
			// if(!(getBlock(world, x+1, y-1, z) == Blocks.REDSTONE_BLOCK))
			// return false;
			// if(!(getBlock(world, x-1, y-1, z) == Blocks.REDSTONE_BLOCK))
			// return false;
			// if(!(getBlock(world, x, y-1, z-1) == Blocks.REDSTONE_BLOCK))
			// return false;
			//
			// if(!(getBlock(world, x+1, y-1, z+1) == Blocks.IRON_BLOCK)) return
			// false;
			// if(!(getBlock(world, x+1, y-1, z-1) == Blocks.IRON_BLOCK)) return
			// false;
			// if(!(getBlock(world, x-1, y-1, z+1) == Blocks.IRON_BLOCK)) return
			// false;
			// if(!(getBlock(world, x-1, y-1, z-1) == Blocks.IRON_BLOCK)) return
			// false;
			//
			// if(!(getBlock(world, x+1, y, z+1) == Blocks.IRON_BLOCK)) return
			// false;
			// if(!(getBlock(world, x+1, y, z-1) == Blocks.IRON_BLOCK)) return
			// false;
			// if(!(getBlock(world, x-1, y, z+1) == Blocks.IRON_BLOCK)) return
			// false;
			// if(!(getBlock(world, x-1, y, z-1) == Blocks.IRON_BLOCK)) return
			// false;
			//
			// if(!(getBlock(world, x+1, y+1, z+1) == Blocks.GLOWSTONE)) return
			// false;
			// if(!(getBlock(world, x+1, y+1, z-1) == Blocks.GLOWSTONE)) return
			// false;
			// if(!(getBlock(world, x-1, y+1, z+1) == Blocks.GLOWSTONE)) return
			// false;
			// if(!(getBlock(world, x-1, y+1, z-1) == Blocks.GLOWSTONE)) return
			// false;

			// if(!(setBlock(world, x, y, z) instanceof BlockBasicRitualStone))
			// return false;
			// if(!(setBlock(world, x, y-1, z, Blocks.GOLD_BLOCK))) return
			// false;
			// if(!(setBlock(world, x, y-1, z+1, Blocks.REDSTONE_BLOCK))) return
			// false;
			// if(!(setBlock(world, x+1, y-1, z, Blocks.REDSTONE_BLOCK))) return
			// false;
			// if(!(setBlock(world, x-1, y-1, z, Blocks.REDSTONE_BLOCK))) return
			// false;
			// if(!(setBlock(world, x, y-1, z-1, Blocks.REDSTONE_BLOCK))) return
			// false;
			//
			// if(!(setBlock(world, x+1, y-1, z+1, Blocks.IRON_BLOCK))) return
			// false;
			// if(!(setBlock(world, x+1, y-1, z-1, Blocks.IRON_BLOCK))) return
			// false;
			// if(!(setBlock(world, x-1, y-1, z+1, Blocks.IRON_BLOCK))) return
			// false;
			// if(!(setBlock(world, x-1, y-1, z-1, Blocks.IRON_BLOCK))) return
			// false;
			//
			// if(!(setBlock(world, x+1, y, z+1,Blocks.IRON_BLOCK))) return
			// false;
			// if(!(setBlock(world, x+1, y, z-1,Blocks.IRON_BLOCK))) return
			// false;
			// if(!(setBlock(world, x-1, y, z+1,Blocks.IRON_BLOCK))) return
			// false;
			// if(!(setBlock(world, x-1, y, z-1,Blocks.IRON_BLOCK))) return
			// false;
			//
			// if(!(setBlock(world, x+1, y+1, z+1, Blocks.GLOWSTONE))) return
			// false;
			// if(!(setBlock(world, x+1, y+1, z-1, Blocks.GLOWSTONE))) return
			// false;
			// if(!(setBlock(world, x-1, y+1, z+1, Blocks.GLOWSTONE))) return
			// false;
			// if(!(setBlock(world, x-1, y+1, z-1, Blocks.GLOWSTONE))) return
			// false;

			return true;
		}

		return false;
	}

	public static EnumRitualType getMultiblockRitual(World world, int x, int y, int z) {
		if (isRitualMultiblockComplete(EnumRitualType.BASIC, world, x, y, z))
			return EnumRitualType.BASIC;
		if (isRitualMultiblockComplete(EnumRitualType.FUSION, world, x, y, z))
			return EnumRitualType.FUSION;
		return null;
	}


	public static Block getBlock(World world, int x, int y, int z) {
		if (world.isAirBlock(new BlockPos(x, y, z)))
			return null;
		return world.getBlockState(new BlockPos(x, y, z)).getBlock();
	}

	public static boolean setBlock(World world, int x, int y, int z, Block block) {
		return world.setBlockState(new BlockPos(x, y, z), block.getDefaultState());
	}

}
