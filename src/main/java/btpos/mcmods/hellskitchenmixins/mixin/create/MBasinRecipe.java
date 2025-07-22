package btpos.mcmods.hellskitchenmixins.mixin.create;

import btpos.mcmods.hellskitchenmixins.ducks.ISpeedSpecificBasinRecipe;
import btpos.mcmods.hellskitchenmixins.impl.logic.SpeedSpecificBasinRecipeLogic;
import com.google.gson.JsonObject;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.simibubi.create.content.kinetics.mixer.MixingRecipe;
import com.simibubi.create.content.processing.basin.BasinBlockEntity;
import com.simibubi.create.content.processing.basin.BasinRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder.ProcessingRecipeParams;
import com.simibubi.create.foundation.recipe.IRecipeTypeInfo;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = BasinRecipe.class, remap = false)
abstract class MBasinRecipe extends ProcessingRecipe<Container> implements ISpeedSpecificBasinRecipe {
    public MBasinRecipe(IRecipeTypeInfo typeInfo, ProcessingRecipeParams params) {
        super(typeInfo, params);
    }
    
    private int hellskitchen$minSpeed = 0;
    private int hellskitchen$maxSpeed = 0;
    
    @ModifyReturnValue(
            method="apply(Lcom/simibubi/create/content/processing/basin/BasinBlockEntity;Lnet/minecraft/world/item/crafting/Recipe;Z)Z",
            at=@At("RETURN")
    )
    private static boolean checkRPMRecipes(boolean original, @Local(argsOnly=true) BasinBlockEntity basin, @Local(argsOnly=true) Recipe<?> recipe) {
        if (recipe instanceof BasinRecipe bsr && !SpeedSpecificBasinRecipeLogic.checkBasinRecipe(basin, ((ISpeedSpecificBasinRecipe) bsr))) {
            return false;
        }
        return original;
    }
    
    @Override
    public void readAdditional(JsonObject json) {
        super.readAdditional(json);
        this.hellskitchen$minSpeed = GsonHelper.getAsInt(json, "min_speed", 0);
        this.hellskitchen$maxSpeed = GsonHelper.getAsInt(json, "max_speed", 256);
    }
    
    @Override
    public boolean hellskitchen$speedInRange(float speed) {
        return hellskitchen$minSpeed <= speed && speed <= hellskitchen$maxSpeed;
    }
}

@Mixin(value= MixingRecipe.class, remap = false)
abstract class MMixingRecipe extends MBasinRecipe {
    public MMixingRecipe(IRecipeTypeInfo typeInfo, ProcessingRecipeParams params) {
        super(typeInfo, params);
    }
    
    @Override
    public boolean hellskitchen$supportsSpeedRequirement() {
        return true;
    }
}
