package btpos.mcmods.hellskitchenmixins.asm;

import btpos.mcmods.hellskitchenmixins.asm.util.ASMFileWriter;
import com.mojang.logging.LogUtils;
import net.minecraft.FileUtil;
import org.apache.commons.io.FileUtils;
import org.objectweb.asm.tree.ClassNode;
import org.slf4j.Logger;
import org.spongepowered.asm.service.IClassBytecodeProvider;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Transformers {
    public static final Logger LOGGER = LogUtils.getLogger();
    
    
    private static IClassTransformer[] transformersBurnAfterRead = new IClassTransformer[] {
    };
    
    public static void runTransformers(IClassBytecodeProvider bytecodeProvider) {
        if (transformersBurnAfterRead == null) {
            LOGGER.error("Attempted to apply class transformers twice!!!");
            return;
        }
        LOGGER.info("Applying class transformers.");
        for (IClassTransformer it : transformersBurnAfterRead) {
            for (String req : it.getTargets()) {
                ClassNode node;
                try {
                    node = bytecodeProvider.getClassNode(req);
                } catch (Exception e) {
                    LOGGER.debug("Failed to find target class \"{}\" for transformer \"{}\"; skipping.", req, it.getClass().getSimpleName());
                    continue;
                }
                if (it.transform(node)) {
                    LOGGER.info("Transformer \"{}\" successfully transformed class \"{}\".", it.getClass().getSimpleName(), req);
                    ASMFileWriter.printClass(node);
                } else {
                    throw new RuntimeException("Transformer \"" + it.getClass().getName() + "\" failed to transform class \"" + req + "\".");
                }
            }
            
        }
        LOGGER.info("Finished applying class transformers.");
        transformersBurnAfterRead = null;
    }
}
