package btpos.mcmods.hellskitchenmixins.asm.transformers.kubecreate;

import btpos.mcmods.hellskitchenmixins.asm.ASMHooks;
import btpos.mcmods.hellskitchenmixins.asm.IClassTransformer;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.ListIterator;

public class TProcessingRecipeSchema implements IClassTransformer {
    @Override
    public String[] getTargets() {
        return new String[] { "dev.latvian.mods.kubejs.create.ProcessingRecipeSchema" };
    }
    
    @Override
    public boolean transform(ClassNode node) {
        MethodNode mth = node.methods.stream().filter(it -> it.name.contains("clinit")).findFirst().orElse(null);
        if (mth == null)
            return false;
        return transformMethod(mth);
    }
    
    private boolean transformMethod(MethodNode node) {
        ListIterator<AbstractInsnNode> iter = node.instructions.iterator();
        while (iter.hasNext()) {
            var current = iter.next();
            if (!(current instanceof FieldInsnNode field && field.name.equals("PROCESSING_UNWRAPPED")))
                continue;
            
            var instantiationNode = iter.previous();
            instantiationNode = iter.previous();
            if (!(instantiationNode instanceof MethodInsnNode ctor && ctor.name.equals("<init>") && ctor.owner.equals("dev/latvian/mods/kubejs/recipe/schema/RecipeSchema"))) {
                LOGGER.error("Unable to find RecipeSchema constructor invocation. Found this node:");
                LOGGER.error(instantiationNode.toString());
                return false;
            }
            
            iter.remove();
            iter.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "btpos/mcmods/hellskitchenmixins/asm/ASMHooks", "addKeys", "addKeys(Ljava/lang/Class;Ljava/util/function/Supplier;[Ldev/latvian/mods/kubejs/recipe/RecipeKey;)Ldev/latvian/mods/kubejs/recipe/schema/RecipeSchema;"));
            return true; // Good return
        }
        LOGGER.error("Couldn't find PROCESSING_UNWRAPPED!");
        return false;
    }
}
