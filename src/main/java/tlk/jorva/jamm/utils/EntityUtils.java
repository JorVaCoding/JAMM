package tlk.jorva.jamm.utils;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityUtils {
	public static class WitherSkullUtils{
		public static void SummonSkull(EntityPlayer player, EntityLivingBase target, boolean glow) {
			SummonSkull(player, target.posX, target.posZ, target.posY, glow);
		}

		public static void SummonSkull(EntityPlayer player, double x, double y, double z, boolean glow) {
			World world = player.worldObj;
			double d0 = player.posX;
			double d1 = player.posY;
			double d2 = player.posZ;
			double d3 = x - d0;
			double d4 = y - d1;
			double d5 = z - d2;
			EntityWitherSkull entitywitherskull = new EntityWitherSkull(world, player, d3, d4, d5);
			entitywitherskull.posY = d1;
			entitywitherskull.posX = d0;
			entitywitherskull.posZ = d2;
			
			entitywitherskull.setGlowing(true);
			
			world.spawnEntityInWorld(entitywitherskull);
		}

		public static void SummonSkull(EntityPlayer player, boolean glow) {
			BlockPos rtr = player.rayTrace(200, 1.0F).getBlockPos();
			if (rtr != null)
				SummonSkull(player, rtr.getX(), rtr.getY(), rtr.getZ(), glow);
		}	
	}
}
