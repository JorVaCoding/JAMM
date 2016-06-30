package tlk.jorva.jamm.utils;

import java.io.PrintStream;

import net.minecraft.launchwrapper.Launch;

public class Debug extends PrintStream {
	public static Debug INSTANCE;

	public Debug() {
		super(System.out);
		INSTANCE = this;
	}

	public static void log(Object... objects) {
		boolean isDevMode = (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
		if (isDevMode) {
			PrintStream SysOut = System.out;
			System.setOut(INSTANCE);
			StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
			for (Object object : objects) {
				System.out.println("DEBUG:\t" + stackTraceElements[2].getClassName() + ":"
						+ stackTraceElements[2].getLineNumber() + "\t" + object);
			}
			INSTANCE.flush();
		}
	}
}
