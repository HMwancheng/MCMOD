package me.cortex.voxy.client.mixin.sodium;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = SodiumWorldRenderer.class, remap = false)
public interface AccessorSodiumWorldRenderer {
    @Accessor
    Object getRenderSectionManager();
}