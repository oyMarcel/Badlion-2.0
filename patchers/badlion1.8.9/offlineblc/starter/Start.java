package offlineblc.starter;

import java.util.Arrays;
import net.minecraft.launchwrapper.Launch;
import java.io.File;
import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager;

public class Start {
    public static void main(final String[] args) {
        System.out.println("Badlion Client 2.13.3 brought back by oyMarcel");
        System.out.println("Based off Badlion Client Offline by skidunion");
        System.out.println("");
        System.out.println("");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            System.err.println("Failed to set system LaF");
        }
        if (!checkIfAgentPresent()) {
            JOptionPane.showMessageDialog((Component)null, "You are not using the Java agent, so this won't launch!\nPlease use the -javaagent:Agent.jar argument and try again", "Offline BLC", 0);
            System.exit(-1);
        }
        final boolean contains = System.getProperty("java.class.path").contains("idea_rt.jar");
        final Object runtime = new File("");
        if (contains) {
            Launch.main((String[])Start.<String>concat(new String[] { "--version", "BLClient", "--accessToken", "0", "--assetsDir", "assets", "--assetIndex", "1.8", "--userProperties", "{}", "--gameDir", ((File)runtime).getAbsolutePath(), "--gameVersion", "1.8.9", "--username", "pidor", "--uuid", "00000000-0000-0000-0000-000000000000", "--tweakClass", "optifine.OptiFineTweaker", "--tweakClass", "net.badlion.client.tweaker.BadlionTweaker" }, args));
        }
        else {
            Launch.main(args);
        }
    }
    
    private static boolean checkIfAgentPresent() {
        try {
            Class.forName("offlineblc.agent.AgentMain");
            return true;
        }
        catch (Throwable t) {
            return false;
        }
    }
    
    private static <T> T[] concat(final T[] first, final T[] second) {
        final T[] result = (T[])Arrays.copyOf((Object[])first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }
}
