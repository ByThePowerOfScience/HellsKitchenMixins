package btpos.mcmods.hellskitchenmixins.mixin.kube;

import dev.latvian.mods.kubejs.create.ProcessingRecipeSchema;
import dev.latvian.mods.kubejs.create.ProcessingRecipeSchema.ProcessingRecipeJS;
import dev.latvian.mods.kubejs.recipe.RecipeJS;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Slice;

import java.util.Arrays;

import static btpos.mcmods.hellskitchenmixins.api.kube.ProcessingRecipeKeys.MAX_SPEED;
import static btpos.mcmods.hellskitchenmixins.api.kube.ProcessingRecipeKeys.MIN_SPEED;

public class MKubeCreateRecipes {}

/**
 * Adds the MIN_SPEED and MAX_SPEED properties to PROCESSING_UNWRAPPED
 */
@Mixin(value = ProcessingRecipeSchema.class, remap = false)
abstract class MProcessingRecipeSchema {
    @ModifyArg(
            method="<clinit>",
            at=@At(
                    target="dev/latvian/mods/kubejs/recipe/schema/RecipeSchema.<init> (Ljava/lang/Class;Ljava/util/function/Supplier;[Ldev/latvian/mods/kubejs/recipe/RecipeKey;)V",
                    value="INVOKE"
            ),
            slice=@Slice(
                    from=@At(
                            target="dev/latvian/mods/kubejs/create/ProcessingRecipeSchema.PROCESSING_WITH_TIME : Ldev/latvian/mods/kubejs/recipe/schema/RecipeSchema;",
                            value="FIELD",
                            opcode=Opcodes.PUTSTATIC
                    ),
                    to = @At(
                            target = "dev/latvian/mods/kubejs/create/ProcessingRecipeSchema.PROCESSING_UNWRAPPED : Ldev/latvian/mods/kubejs/recipe/schema/RecipeSchema;",
                            value="FIELD",
                            opcode= Opcodes.PUTSTATIC
                    )
            )
    )
    private static RecipeKey<?>[] addNewRecipeKeys(RecipeKey<?>[] keys) {
        var newArr = Arrays.copyOf(keys, keys.length + 2);
        newArr[newArr.length - 2] = MIN_SPEED;
        newArr[newArr.length - 1] = MAX_SPEED;
        return newArr;
    }
}

/**
 * Adds the minSpeed and maxSpeed methods to the Kube class
 */
@SuppressWarnings({"AddedMixinMembersNamePattern", "MissingUnique", "unused"})
@Mixin(value = ProcessingRecipeJS.class, remap = false)
abstract class MProcessingRecipeJS extends RecipeJS {
    public RecipeJS minSpeed(int speed) {
        return setValue(MIN_SPEED, speed);
    }
    public RecipeJS maxSpeed(int speed) {
        return setValue(MAX_SPEED, speed);
    }
}