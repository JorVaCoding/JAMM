package tlk.jorva.jamm.utils;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tlk.jorva.jamm.common.tiles.RitualStone.BlockBasicRitualStone;

public class WorldUtils {
	public static void createLightning(World world, double x, double y, double z, boolean isEffect){
		EntityLightningBolt bolt = new EntityLightningBolt(world, x, y, z, isEffect);
		world.spawnEntityInWorld(bolt);
	}
	
    public static <T extends Entity> List<T> getEntitiesInBox(World world, Class <? extends T > classEntity, double aX, double aY, double aZ, double bX, double bY, double bZ){
        Debug.log(aX, aY, aZ, bX, bY, bZ);
    	return world.getEntitiesWithinAABB(classEntity, new AxisAlignedBB(aX, aY, aZ, bX, bY, bZ));
    }
	
    public static boolean isRitualMultiblockComplete(EnumRitualType ritualType, World world, int x, int y, int z){
    	
    	if(ritualType == EnumRitualType.BASIC){
    		if(!(getBlock(world, x,y,z) instanceof BlockBasicRitualStone)) return false;
    		if(!(getBlock(world, x, y, z+1) == Blocks.IRON_BLOCK)) return false;
    		if(!(getBlock(world, x+1, y, z) == Blocks.IRON_BLOCK)) return false;
    		if(!(getBlock(world, x-1, y, z) == Blocks.IRON_BLOCK)) return false;
    		if(!(getBlock(world, x, y, z-1) == Blocks.IRON_BLOCK)) return false;
    		return true;
    	}
    	
    	return false;
    }
    
    public static EnumRitualType getMultiblockRitual(World world, int x, int y, int z){
    	for(EnumRitualType ritualType : EnumRitualType.getTypes()){
    		if(isRitualMultiblockComplete(ritualType, world, x, y, z))
    			return ritualType;
    	}
    	
    	return null;
    }
    
    public static Block getBlock(World world, int x, int y, int z){
    	if(world.isAirBlock(new BlockPos(x,y,z))) return null;
    	return world.getBlockState(new BlockPos(x,y,z)).getBlock();
    }
	
}
