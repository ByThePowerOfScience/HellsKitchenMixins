package btpos.mcmods.hellskitchenmixins.asm;

import com.mojang.logging.LogUtils;
import org.objectweb.asm.tree.ClassNode;
import org.slf4j.Logger;

public interface IClassTransformer {
    Logger LOGGER = LogUtils.getLogger();
    
    String[] getTargets();
    
    boolean transform(ClassNode node);
}
