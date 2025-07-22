package btpos.mcmods.hellskitchenmixins.kube.schemas;

import dev.latvian.mods.kubejs.create.ProcessingRecipeSchema.ProcessingRecipeJS;
import dev.latvian.mods.kubejs.recipe.RecipeJS;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent.IntRange;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;

import static dev.latvian.mods.kubejs.create.ProcessingRecipeSchema.*;
import static dev.latvian.mods.kubejs.create.ProcessingRecipeSchema.HEAT_REQUIREMENT;

public final class ProcessingRecipeSchema {
    public static final RecipeSchema PROCESSING_UNWRAPPED = new RecipeSchema(SpeedRecipeJS.class, SpeedRecipeJS::new, RESULTS, INGREDIENTS_UNWRAPPED, PROCESSING_TIME, HEAT_REQUIREMENT, MIN_SPEED, MAX_SPEED);
    
}

