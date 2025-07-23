package btpos.mcmods.hellskitchenmixins.mixin.kube;

import dev.latvian.mods.kubejs.create.ProcessingRecipeSchema.ProcessingRecipeJS;
import dev.latvian.mods.kubejs.recipe.RecipeJS;
import org.spongepowered.asm.mixin.Mixin;

import static btpos.mcmods.hellskitchenmixins.impl.kube.ProcessingRecipeKeys.MAX_SPEED;
import static btpos.mcmods.hellskitchenmixins.impl.kube.ProcessingRecipeKeys.MIN_SPEED;


/**
 * @see btpos.mcmods.hellskitchenmixins.ducks.ISpeedSpecificBasinRecipe
 * @see btpos.mcmods.hellskitchenmixins.impl.logic.SpeedSpecificBasinRecipeLogic
 * @see btpos.mcmods.hellskitchenmixins.mixin.create.MBasinRecipe
 */

@SuppressWarnings({"AddedMixinMembersNamePattern", "MissingUnique", "unused"})
@Mixin(value = ProcessingRecipeJS.class, remap = false)
abstract class MProcessingRecipeJS extends RecipeJS {
    public RecipeJS minSpeed(float speed) {
        return setValue(MIN_SPEED, speed);
    }
    public RecipeJS maxSpeed(float speed) {
        return setValue(MAX_SPEED, speed);
    }
}