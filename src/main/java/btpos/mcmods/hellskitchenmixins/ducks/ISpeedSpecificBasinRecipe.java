package btpos.mcmods.hellskitchenmixins.ducks;

public interface ISpeedSpecificBasinRecipe {
    boolean hellskitchen$speedInRange(float speed);
    
    /**
     * If this recipe requires a block above it that moves at a certain speed.  E.g. the mixer or the compactor
     */
    default boolean hellskitchen$supportsSpeedRequirement() {
        return false;
    }
}
