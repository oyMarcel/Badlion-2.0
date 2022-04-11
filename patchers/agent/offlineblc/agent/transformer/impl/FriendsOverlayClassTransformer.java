package offlineblc.agent.transformer.impl;

import javassist.CtClass;
import java.io.IOException;
import javassist.CannotCompileException;
import javassist.NotFoundException;
import offlineblc.agent.exceptions.PatcherException;
import javassist.ClassPool;
import offlineblc.agent.util.ClassData;
import offlineblc.agent.transformer.Transformer;

public class FriendsOverlayClassTransformer extends Transformer {
    @Override
    public String getTransformingClass() {
        return "net.badlion.clientcommon.gui.overlay.friends.FriendsOverlay";
    }
    
    @Override
    public byte[] transform(final ClassData classData) throws PatcherException {
        try {
            final CtClass display = ClassPool.getDefault().get(this.getTransformingClass());
            display.getDeclaredMethod("onRender").setBody("{ return; }");
            return display.toBytecode();
        }
        catch (NotFoundException | CannotCompileException | IOException ex3) {
            final Exception ex2;
            final Exception ex = ex2;
            throw new PatcherException((Throwable)ex);
        }
    }
}
