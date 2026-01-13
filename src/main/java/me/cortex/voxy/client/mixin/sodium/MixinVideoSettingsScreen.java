package me.cortex.voxy.client.mixin.sodium;

import me.cortex.voxy.client.config.IConfigPageSetter;
import me.cortex.voxy.common.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.Method;

@Mixin(value = VideoSettingsScreen.class, remap = false)
public abstract class MixinVideoSettingsScreen implements IConfigPageSetter {
    @Unique
    private Object voxyJumpPage;

    public void voxy$setPageJump(Object page) {
        this.voxyJumpPage = page;
    }

    @Inject(method = "rebuild", at = @At("TAIL"))
    private void voxy$jumpPages(CallbackInfo ci) {
        if (this.voxyJumpPage != null) {
            try {
                Method jumpToPageMethod = this.getClass().getMethod("jumpToPage", Object.class);
                jumpToPageMethod.invoke(this, this.voxyJumpPage);
                
                Method onSectionFocusedMethod = this.getClass().getMethod("onSectionFocused", Object.class);
                onSectionFocusedMethod.invoke(this, this.voxyJumpPage);
            } catch (Exception e) {
                Logger.warn("Failed to jump to Voxy config page: " + e.getMessage());
            }
        }
    }
}