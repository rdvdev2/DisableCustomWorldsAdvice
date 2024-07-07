package com.rdvdev2.disablecustomworldsadvice.mixin;

import com.mojang.serialization.Lifecycle;
import net.minecraft.server.integrated.IntegratedServerLoader;
import net.minecraft.world.SaveProperties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(IntegratedServerLoader.class)
public abstract class MixinIntegratedServerLoader {

    // Make SaveProperties.getLifecycle() always return Lifecycle.stable()
    @Redirect(
            method = "checkBackupAndStart",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/SaveProperties;getLifecycle()Lcom/mojang/serialization/Lifecycle;"
            )
    )
    private Lifecycle removeAdviceOnLoad(SaveProperties saveProperties) {
        return Lifecycle.stable();
    }

    // Set bypassWarnings = true
    @ModifyVariable(
            method = "tryLoad",
            at = @At("HEAD"),
            argsOnly = true,
            index = 4
    )
    private static boolean removeAdviceOnCreation(boolean original) {
        return true;
    }
}
