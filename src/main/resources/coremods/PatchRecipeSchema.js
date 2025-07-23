// noinspection ES6ConvertVarToLetConst,JSUnresolvedReference

function initializeCoreMod() {
    return {
        "PatchRecipeSchema": {
            "target": {
                "type": "METHOD",
                "class": "dev.latvian.mods.kubejs.create.ProcessingRecipeSchema",
                "methodName": "<clinit>",
                "methodDesc": "()V"
            },
            "transformer": transformMethod
        }
    }
}

function transform(classNode) {
    var methods = classNode.methods
    for (var el of methods) {
        if (el.name.contains("clinit")) {
            transformMethod(el)
            return;
        }
    }
}

FIELDTYPE = Java.type("org.objectweb.asm.tree.AbstractInsnNode").FIELD_INSN
MethodInsnNode = Java.type("org.objectweb.asm.tree.MethodInsnNode")
Opcodes = Java.type("org.objectweb.asm.Opcodes")

function transformMethod(methodNode) {
    iter = methodNode.instructions.iterator()
    while (iter.hasNext()) {
        current = iter.next()
        if (!(current.getType() === FIELDTYPE && current.name.equals("PROCESSING_UNWRAPPED"))) {
            continue;
        }
        
        iter.previous();
        iter.previous();
        iter.remove();
        iter.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "btpos/mcmods/hellskitchenmixins/asm/ASMHooks", "addKeys", "(Ljava/lang/Class;Ljava/util/function/Supplier;[Ldev/latvian/mods/kubejs/recipe/RecipeKey;)Ldev/latvian/mods/kubejs/recipe/schema/RecipeSchema;"))
        return;
    }
}