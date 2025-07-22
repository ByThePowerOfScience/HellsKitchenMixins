package btpos.mcmods.hellskitchenmixins.impl.objects;

import com.simibubi.create.content.processing.basin.BasinRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder.ProcessingRecipeParams;
import com.simibubi.create.foundation.recipe.IRecipeTypeInfo;

public class SpeedSpecificBasinRecipe extends BasinRecipe {
    public final int minSpeed;
    public final int maxSpeed;
    
    protected SpeedSpecificBasinRecipe(IRecipeTypeInfo type, ProcessingRecipeParams params, int minSpeed, int maxSpeed) {
        super(type, params);
        this.minSpeed = minSpeed;
        this.maxSpeed = maxSpeed;
    }
    
}
