package offlineblc.agent.transformer.impl;

import java.util.stream.Collectors;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Stream;
import com.google.gson.JsonParser;
import javassist.CtClass;
import offlineblc.agent.exceptions.PatcherException;
import javassist.ClassPool;
import offlineblc.agent.util.ClassData;
import com.google.gson.Gson;
import offlineblc.agent.transformer.Transformer;

public class WrapperClassTransformer extends Transformer {
    private final String menuJson;
    private final Gson gson;
    
    public WrapperClassTransformer() {
        this.gson = new Gson();
        this.menuJson = this.getJSONFromResources("/resources/menu.json");
    }
    
    @Override
    public String getTransformingClass() {
        return "net.badlion.client.Wrapper";
    }
    
    @Override
    public byte[] transform(final ClassData classData) throws PatcherException {
        try {
            final CtClass wrapper = ClassPool.getDefault().get(this.getTransformingClass());
            wrapper.getDeclaredMethod("load").setBody("{ return true; }");
            wrapper.getDeclaredMethod("wasAntiLauncherSet").setBody("{ return true; }");
            wrapper.getDeclaredMethod("isServerBACEnabled").setModifiers(1);
            wrapper.getDeclaredMethod("isServerBACEnabled").setBody("{ return 1337; }");
            wrapper.getDeclaredMethod("checkVerify").setBody("{ return true; }");
            wrapper.getDeclaredMethod("updateConnectionStatus").setModifiers(1);
            wrapper.getDeclaredMethod("updateConnectionStatus").setBody("{ return; }");
            wrapper.getDeclaredMethod("getAvailableProfiles").setModifiers(1);
            wrapper.getDeclaredMethod("getAvailableProfiles").setBody("{ return \"Fail\"; }");
            wrapper.getDeclaredMethod("getMenu").setModifiers(1);
            wrapper.getDeclaredMethod("getMenu").setBody("{ return \"" + this.menuJson + "\"; }");
            return wrapper.toBytecode();
        }
        catch (Throwable ex) {
            if (!ex.getMessage().contains("frozen")) {
                throw new PatcherException(ex);
            }
            return classData.getClassfileBuffer();
        }
    }
    
    private String getJSONFromResources(final String resourcePath) {
        return this.gson.toJson(new JsonParser().parse((String)((Stream)new BufferedReader((Reader)new InputStreamReader(this.getClass().getResourceAsStream(resourcePath))).lines().parallel()).collect(Collectors.joining("\n")))).replace("\"", "\\\"");
    }
}
