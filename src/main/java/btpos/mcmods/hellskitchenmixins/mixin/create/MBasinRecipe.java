package btpos.mcmods.hellskitchenmixins.mixin.create;

import btpos.mcmods.hellskitchenmixins.impl.logic.SpeedSpecificBasinRecipeLogic;
import btpos.mcmods.hellskitchenmixins.impl.objects.SpeedSpecificBasinRecipe;
import com.simibubi.create.content.processing.basin.BasinBlockEntity;
import com.simibubi.create.content.processing.basin.BasinRecipe;
import net.minecraft.world.item.crafting.Recipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = BasinRecipe.class, remap = false)
abstract class MBasinRecipe {
    @Inject(
            method="apply(Lcom/simibubi/create/content/processing/basin/BasinBlockEntity;Lnet/minecraft/world/item/crafting/Recipe;)Z",
            at=@At("HEAD"),
            cancellable = true
    )
    private static void checkRPMRecipes(BasinBlockEntity basin, Recipe<?> recipe, CallbackInfoReturnable<Boolean> cir) {
        if (recipe instanceof SpeedSpecificBasinRecipe ssr && !SpeedSpecificBasinRecipeLogic.checkBasinRecipe(basin, ssr)) {
            cir.setReturnValue(false);
        }
    }
}
