package btpos.mcmods.hellskitchenmixins.impl.logic;

import btpos.mcmods.hellskitchenmixins.impl.objects.SpeedSpecificBasinRecipe;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.processing.basin.BasinBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;

public final class SpeedSpecificBasinRecipeLogic {
    
    /**
     * @return True if this recipe is satisfied or not applicable
     * @see btpos.mcmods.hellskitchenmixins.mixin.create.MBasinRecipe#checkRPMRecipes
     */
    public static boolean checkBasinRecipe(BasinBlockEntity basin, SpeedSpecificBasinRecipe recipe) {
        // check RPM of the blockentity above it
        var level = basin.getLevel();
        if (level == null)
            return true; // pass
        
        BlockEntity ent = level.getBlockEntity(basin.getBlockPos().above());
        if (!(ent instanceof KineticBlockEntity kbe)) {
            return true; // pass
        }
        
        return recipe.minSpeed <= kbe.getSpeed() && kbe.getSpeed() <= recipe.maxSpeed;
    }
    
    
    private SpeedSpecificBasinRecipeLogic() {}
}
