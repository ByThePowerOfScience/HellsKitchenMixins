package btpos.mcmods.hellskitchenmixins.asm;

import btpos.mcmods.hellskitchenmixins.asm.transformers.kubecreate.TProcessingRecipeSchema;
import com.mojang.logging.LogUtils;
import org.objectweb.asm.tree.ClassNode;
import org.slf4j.Logger;
import org.spongepowered.asm.service.IClassBytecodeProvider;

public class Transformers {
    public static final Logger LOGGER = LogUtils.getLogger();
    
    private static IClassTransformer[] transformersBurnAfterRead = new IClassTransformer[] {
            new TProcessingRecipeSchema()
    };
    
    public static void runTransformers(IClassBytecodeProvider bytecodeProvider) {
        if (transformersBurnAfterRead == null) {
            LOGGER.error("Attempted to apply class transformers twice!!!");
            return;
        }
        LOGGER.info("Applying class transformers.");
        for (var it : transformersBurnAfterRead) {
            for (var req : it.getTargets()) {
                ClassNode node;
                try {
                    node = bytecodeProvider.getClassNode(req);
                } catch (Exception e) {
                    LOGGER.debug("Failed to find target class \"{}\" for transformer \"{}\"; skipping.", req, it.getClass().getSimpleName());
                    continue;
                }
                if (it.transform(node)) {
                    LOGGER.info("Transformer \"{}\" successfully transformed class \"{}\".", it.getClass().getSimpleName(), req);
                } else {
                    LOGGER.warn("Transformer \"{}\" failed to transform class \"{}\". Skipping.", it.getClass().getSimpleName(), req);
                }
            }
        }
        LOGGER.info("Finished applying class transformers.");
        transformersBurnAfterRead = null;
    }
}
