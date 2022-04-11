package offlineblc.agent;

import java.util.Iterator;
import java.awt.Component;
import javax.swing.JOptionPane;
import offlineblc.agent.util.Logger;
import offlineblc.agent.util.ClassData;
import java.security.ProtectionDomain;
import offlineblc.agent.transformer.impl.FriendsOverlayClassTransformer;
import offlineblc.agent.transformer.impl.AccountManagerClassTransformer;
import offlineblc.agent.transformer.impl.EmoteManagerClassTransformer;
import offlineblc.agent.transformer.impl.DisplayClassTransformer;
import offlineblc.agent.transformer.impl.WrapperClassTransformer;
import offlineblc.agent.transformer.Transformer;
import java.util.ArrayList;
import java.lang.instrument.ClassFileTransformer;

public class AgentClassTransformer implements ClassFileTransformer {
    public static AgentClassTransformer INSTANCE;
    private final ArrayList<Transformer> transformers;
    
    public AgentClassTransformer() {
        (this.transformers = (ArrayList<Transformer>)new ArrayList()).add(new WrapperClassTransformer());
        this.transformers.add(new DisplayClassTransformer());
        this.transformers.add(new EmoteManagerClassTransformer());
        this.transformers.add(new AccountManagerClassTransformer());
        this.transformers.add(new FriendsOverlayClassTransformer());
    }
    
    public byte[] transform(final ClassLoader loader, final String className, final Class<?> classBeingRedefined, final ProtectionDomain protectionDomain, final byte[] classfileBuffer) {
        final ClassData classData = new ClassData(loader, className, classBeingRedefined, protectionDomain, classfileBuffer);
        for (final Transformer transformer : this.transformers) {
            if (transformer.getJVMTransformingClass().equals(className)) {
                Logger.info("Applying transformations on " + className);
                try {
                    return transformer.transform(classData);
                }
                catch (Throwable t) {
                    Logger.error("Failed to patch " + className);
                    t.printStackTrace();
                    JOptionPane.showMessageDialog((Component)null, ("Failed to patch " + className + "\nThe error was: " + t.getCause().getClass().getName()), "Serious error", 0);
                    Runtime.getRuntime().halt(-2);
                }
            }
        }
        return classfileBuffer;
    }
    
    static {
        AgentClassTransformer.INSTANCE = new AgentClassTransformer();
    }
}
