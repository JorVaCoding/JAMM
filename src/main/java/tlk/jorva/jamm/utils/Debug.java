package tlk.jorva.jamm.utils;

import java.io.PrintStream;

import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

public class Debug extends PrintStream {
	public static Debug INSTANCE;

	public Debug() {
		super(System.out);
		INSTANCE = this;
	}

	public static void log(Object... objects) {
		boolean isDevMode = (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
		boolean isClient = (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT);
		if (isDevMode) {
			PrintStream SysOut = System.out;
			System.setOut(INSTANCE);
			StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
			for (Object object : objects) {
				if (isClient)
					System.out.println("[CLIENT] DEBUG:\t" + stackTraceElements[2].getClassName() + ":"
							+ stackTraceElements[2].getLineNumber() + "\t" + object);
				else
					System.out.println("[SERVER] DEBUG:\t" + stackTraceElements[2].getClassName() + ":"
							+ stackTraceElements[2].getLineNumber() + "\t" + object);
			}
			INSTANCE.flush();
		}
	}
}
