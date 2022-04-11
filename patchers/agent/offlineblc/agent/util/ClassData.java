package offlineblc.agent.util;

import java.security.ProtectionDomain;

public class ClassData {
    private final ClassLoader classLoader;
    private final String className;
    private final Class<?> classBeingRedefined;
    private final ProtectionDomain protectionDomain;
    private final byte[] classfileBuffer;
    
    public ClassData(final ClassLoader classLoader, final String className, final Class<?> classBeingRedefined, final ProtectionDomain protectionDomain, final byte[] classfileBuffer) {
        this.classLoader = classLoader;
        this.className = className;
        this.classBeingRedefined = classBeingRedefined;
        this.protectionDomain = protectionDomain;
        this.classfileBuffer = classfileBuffer;
    }
    
    public ClassLoader getClassLoader() {
        return this.classLoader;
    }
    
    public String getClassName() {
        return this.className;
    }
    
    public Class<?> getClassBeingRedefined() {
        return this.classBeingRedefined;
    }
    
    public ProtectionDomain getProtectionDomain() {
        return this.protectionDomain;
    }
    
    public byte[] getClassfileBuffer() {
        return this.classfileBuffer;
    }
}
