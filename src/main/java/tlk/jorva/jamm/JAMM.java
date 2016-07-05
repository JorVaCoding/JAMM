package tlk.jorva.jamm;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import tlk.jorva.jamm.proxies.ClientRegistry;
import tlk.jorva.jamm.proxies.CommonRegistry;

@Mod(modid = "jamm", version = "$VERSION", name = "J.A.M.M.")
public class JAMM {
	
	@Instance
	public static JAMM INSTANCE;
	
    public static CommonRegistry registry;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		
		registry = (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) ? new ClientRegistry() : new CommonRegistry();
		
		registry.preInit(event);
	}
	
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		registry.init(event);
	}
	
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event){
		registry.postInit(event);
	}
}
