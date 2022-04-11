package offlineblc.agent.transformer;

import offlineblc.agent.exceptions.PatcherException;
import offlineblc.agent.util.ClassData;

public abstract class Transformer {
    public abstract String getTransformingClass();
    
    public abstract byte[] transform(final ClassData classData) throws PatcherException;
    
    public String getJVMTransformingClass() {
        return this.getTransformingClass().replace(".", "/");
    }
}
