package offlineblc.agent.transformer.impl;

import javassist.CtClass;
import java.io.IOException;
import javassist.CannotCompileException;
import javassist.NotFoundException;
import offlineblc.agent.exceptions.PatcherException;
import javassist.ClassPool;
import offlineblc.agent.util.ClassData;
import offlineblc.agent.transformer.Transformer;

public class EmoteManagerClassTransformer extends Transformer {
    private final String className = "net.badlion.clientcommon.emotes.EmoteManager";
    
    @Override
    public String getTransformingClass() {
        return "net.badlion.clientcommon.emotes.EmoteManager";
    }
    
    @Override
    public byte[] transform(final ClassData classData) throws PatcherException {
        try {
            final CtClass emoteManager = ClassPool.getDefault().get("net.badlion.clientcommon.emotes.EmoteManager");
            emoteManager.getDeclaredMethod("isUserOwns").setBody("{ return true; }");
            return emoteManager.toBytecode();
        }
        catch (NotFoundException | CannotCompileException | IOException ex3) {
            final Exception ex2;
            final Exception ex = ex2;
            throw new PatcherException((Throwable)ex);
        }
    }
}
