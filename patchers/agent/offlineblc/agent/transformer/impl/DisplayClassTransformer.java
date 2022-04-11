package offlineblc.agent.transformer.impl;

import javassist.CtClass;
import java.io.IOException;
import javassist.CannotCompileException;
import javassist.NotFoundException;
import offlineblc.agent.exceptions.PatcherException;
import javassist.ClassPool;
import offlineblc.agent.util.ClassData;
import offlineblc.agent.transformer.Transformer;

public class DisplayClassTransformer extends Transformer {
    @Override
    public String getTransformingClass() {
        return "org.lwjgl.opengl.Display";
    }
    
    @Override
    public byte[] transform(final ClassData classData) throws PatcherException {
        try {
            final Object display = ClassPool.getDefault().get(this.getTransformingClass());
            ((CtClass)display).getDeclaredMethod("setTitle").setBody("{ title = \"Badlion Minecraft Client v2.13.3-367e102-PRODUCTION \";\nif (isCreated()) display_impl.setTitle(title);\n}");
            return ((CtClass)display).toBytecode();
        }
        catch (NotFoundException | CannotCompileException | IOException ex) {
            final Object o;
            final Object display = o;
            throw new PatcherException((Throwable)display);
        }
    }
}
