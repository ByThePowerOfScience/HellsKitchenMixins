package btpos.mcmods.hellskitchenmixins.impl.kube;

import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent.FloatRange;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent.IntRange;

public final class ProcessingRecipeKeys {
    public static final RecipeKey<Float> MIN_SPEED;
    public static final RecipeKey<Float> MAX_SPEED;
    static {
        FloatRange intRange = NumberComponent.floatRange(30f, 256f);
        MIN_SPEED = intRange.key("minSpeed").optional(0f);
        MAX_SPEED = intRange.key("maxSpeed").optional(256f);
    }
}
