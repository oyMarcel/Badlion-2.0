package offlineblc.agent.transformer.impl;

import javassist.CtClass;
import java.io.IOException;
import javassist.CannotCompileException;
import javassist.NotFoundException;
import offlineblc.agent.exceptions.PatcherException;
import javassist.ClassPool;
import offlineblc.agent.util.ClassData;
import offlineblc.agent.transformer.Transformer;

public class AccountManagerClassTransformer extends Transformer {
    @Override
    public String getTransformingClass() {
        return "net.badlion.client.manager.AccountManager";
    }
    
    @Override
    public byte[] transform(final ClassData classData) throws PatcherException {
        try {
            final CtClass accountManager = ClassPool.getDefault().get(this.getTransformingClass());
            accountManager.getDeclaredMethod("loadSkin").setBody("{ return; }");
            return accountManager.toBytecode();
        }
        catch (NotFoundException | CannotCompileException | IOException ex3) {
            final Exception ex2;
            final Exception ex = ex2;
            throw new PatcherException((Throwable)ex);
        }
    }
}
