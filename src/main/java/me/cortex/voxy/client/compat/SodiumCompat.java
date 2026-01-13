package me.cortex.voxy.client.compat;

import me.cortex.voxy.common.Logger;

public class SodiumCompat {
    private static SodiumVersion version = SodiumVersion.UNKNOWN;

    public static SodiumVersion getVersion() {
        if (version != SodiumVersion.UNKNOWN) {
            return version;
        }

        try {
            Class<?> sodiumWorldRendererClass = Class.forName("net.caffeinemc.mods.sodium.client.render.SodiumWorldRenderer");
            Class<?> renderSectionManagerClass = Class.forName("net.caffeinemc.mods.sodium.client.render.chunk.RenderSectionManager");
            
            version = SodiumVersion.V0_8_2;
            Logger.info("Detected Sodium 0.8.2+ (API compatible)");
        } catch (ClassNotFoundException e) {
            try {
                Class<?> sodiumWorldRendererClass = Class.forName("net.caffeinemc.mods.sodium.client.render.SodiumWorldRenderer");
                version = SodiumVersion.V0_7_3;
                Logger.info("Detected Sodium 0.7.3 (API compatible)");
            } catch (ClassNotFoundException e2) {
                version = SodiumVersion.UNKNOWN;
                Logger.warn("Could not detect Sodium version");
            }
        }

        return version;
    }

    public static boolean isSodiumAvailable() {
        return getVersion() != SodiumVersion.UNKNOWN;
    }

    public static boolean isSodium082OrHigher() {
        return getVersion() == SodiumVersion.V0_8_2;
    }

    public static boolean isSodium073() {
        return getVersion() == SodiumVersion.V0_7_3;
    }

    public enum SodiumVersion {
        UNKNOWN,
        V0_7_3,
        V0_8_2
    }
}