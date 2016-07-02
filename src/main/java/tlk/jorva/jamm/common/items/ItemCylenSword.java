package tlk.jorva.jamm.common.items;

import net.minecraft.item.ItemShield;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import tlk.jorva.jamm.utils.ItemNBTUtils;

public class ItemCylenSword extends ItemSword {

	public ItemCylenSword() {
		super(ToolMaterial.DIAMOND);
		setRegistryName("cylenSword");
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return ItemNBTUtils.getTagCompound(stack).getBoolean("isImbued") ? "cylenSwordImbued" : "cylenSword";
	}

}
