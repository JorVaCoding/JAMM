package tlk.jorva.jamm;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import tlk.jorva.jamm.proxy.CommonProxy;

@Mod(modid = "jamm", version = "$VERSION", name = "J.A.M.M.")
public class JAMM {
	
	@Instance
	public static JAMM INSTANCE;
	
	@SidedProxy(serverSide="tlk.jorva.jamm.proxy.ServerProxy", clientSide="tlk.jorva.jamm.proxy.ClientProxy")
    public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		proxy.preInit(event);
	}
	
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
	}
	
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event){
	}
}
