package com.rdvdev2.disablecustomworldsadvice.mixin;

import net.minecraft.server.integrated.IntegratedServerLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(IntegratedServerLoader.class)
public abstract class MixinIntegratedServerLoader {

    // Set canShowBackupPrompt = false
    @ModifyVariable(
            method = "start(Lnet/minecraft/client/gui/screen/Screen;Ljava/lang/String;ZZ)V",
            at = @At("HEAD"),
            argsOnly = true,
            index = 4
    )
    private boolean removeAdviceOnLoad(boolean original) {
        return false;
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
