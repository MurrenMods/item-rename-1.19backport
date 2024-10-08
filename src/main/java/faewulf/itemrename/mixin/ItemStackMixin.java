package faewulf.itemrename.mixin;

import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public class ItemStackMixin {
    @Inject(method = "hasGlint", at = @At("HEAD"), cancellable = true)
    private void hasGlint(CallbackInfoReturnable<Boolean> cir) {
        ItemStack itemStack = (ItemStack) (Object) this;
        if (itemStack.hasNbt()) {
            if(itemStack.getNbt().contains("ForceGlint"))
            {
                cir.setReturnValue(itemStack.getNbt().getBoolean("ForceGlint"));
            }
        }
    }
}
