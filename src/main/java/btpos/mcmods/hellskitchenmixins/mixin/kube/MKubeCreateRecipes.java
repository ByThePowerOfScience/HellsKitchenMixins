package btpos.mcmods.hellskitchenmixins.mixin.kube;

import dev.latvian.mods.kubejs.create.ProcessingRecipeSchema.ProcessingRecipeJS;
import dev.latvian.mods.kubejs.recipe.RecipeJS;
import org.spongepowered.asm.mixin.Mixin;

import static btpos.mcmods.hellskitchenmixins.impl.kube.ProcessingRecipeKeys.MAX_SPEED;
import static btpos.mcmods.hellskitchenmixins.impl.kube.ProcessingRecipeKeys.MIN_SPEED;

/**
 * @see btpos.mcmods.hellskitchenmixins.asm.transformers.kubecreate.TProcessingRecipeSchema
 * @see btpos.mcmods.hellskitchenmixins.ducks.ISpeedSpecificBasinRecipe
 * @see btpos.mcmods.hellskitchenmixins.impl.logic.SpeedSpecificBasinRecipeLogic
 * @see btpos.mcmods.hellskitchenmixins.mixin.create.MBasinRecipe
 */
public class MKubeCreateRecipes {}

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