package tlk.jorva.jamm.common.tiles.RitualStone;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockBasicRitualStone extends BlockContainer{
	public BlockBasicRitualStone() {
		super(Material.PISTON);
	}
	

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileRitualStone();
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		
		TileRitualStone te = (TileRitualStone)worldIn.getTileEntity(pos);
		te.onActivated(playerIn);
		
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, heldItem, side, hitX, hitY, hitZ);
	}
	
}
