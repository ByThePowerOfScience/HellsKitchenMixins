package btpos.mcmods.hellskitchenmixins.api.kube;

import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent.IntRange;
import org.spongepowered.asm.mixin.Unique;

public final class ProcessingRecipeKeys {
    public static final RecipeKey<Integer> MIN_SPEED;
    public static final RecipeKey<Integer> MAX_SPEED;
    static {
        IntRange intRange = NumberComponent.intRange(0, 256);
        MIN_SPEED = intRange.key("min_speed");
        MAX_SPEED = intRange.key("max_speed");
    }
}
