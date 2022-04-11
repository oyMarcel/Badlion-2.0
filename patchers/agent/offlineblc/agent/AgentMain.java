package offlineblc.agent;

import java.lang.instrument.ClassFileTransformer;
import offlineblc.agent.util.Logger;
import java.lang.instrument.Instrumentation;

public class AgentMain {
    public static void premain(final String agentOps, final Instrumentation inst) {
        Logger.info("Agent loaded");
        inst.addTransformer((ClassFileTransformer)AgentClassTransformer.INSTANCE);
    }
}
