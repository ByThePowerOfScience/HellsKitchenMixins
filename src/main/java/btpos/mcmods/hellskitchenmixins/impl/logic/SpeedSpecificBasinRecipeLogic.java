package btpos.mcmods.hellskitchenmixins.impl.logic;

import btpos.mcmods.hellskitchenmixins.ducks.ISpeedSpecificBasinRecipe;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.processing.basin.BasinBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;

public final class SpeedSpecificBasinRecipeLogic {
    
    /**
     * @return True if this recipe is ok to proceed with checks
     * @see btpos.mcmods.hellskitchenmixins.mixin.create.MBasinRecipe#checkRPMRecipes
     */
    public static boolean checkBasinRecipe(BasinBlockEntity basin, ISpeedSpecificBasinRecipe recipe) {
        if (!recipe.hellskitchen$supportsSpeedRequirement())
            return true;
        
        // check RPM of the blockentity above it
        var level = basin.getLevel();
        if (level == null)
            return true; // pass
        
        BlockEntity ent = level.getBlockEntity(basin.getBlockPos().above());
        if (!(ent instanceof KineticBlockEntity kbe)) {
            return true; // pass
        }
        
        return recipe.hellskitchen$speedInRange(kbe.getSpeed());
    }
    
    
    private SpeedSpecificBasinRecipeLogic() {}
}
