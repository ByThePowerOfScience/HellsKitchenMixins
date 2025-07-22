package btpos.mcmods.hellskitchenmixins.asm;

import dev.latvian.mods.kubejs.recipe.RecipeJS;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;

import java.util.Arrays;
import java.util.function.Supplier;

import static btpos.mcmods.hellskitchenmixins.api.kube.ProcessingRecipeKeys.MAX_SPEED;
import static btpos.mcmods.hellskitchenmixins.api.kube.ProcessingRecipeKeys.MIN_SPEED;

public final class ASMHooks {
    @SuppressWarnings("unused")
    public static RecipeSchema addKeys(Class<? extends RecipeJS> recipeType, Supplier<? extends RecipeJS> factory, RecipeKey<?>... keys) {
        keys = Arrays.copyOf(keys, keys.length + 2);
        keys[keys.length - 2] = MIN_SPEED;
        keys[keys.length - 1] = MAX_SPEED;
        return new RecipeSchema(recipeType, factory, keys);
    }
}
