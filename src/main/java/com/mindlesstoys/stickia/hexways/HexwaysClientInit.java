package com.mindlesstoys.stickia.hexways;

import com.mindlesstoys.stickia.hexways.entites.HexPortalRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

import static com.mindlesstoys.stickia.hexways.entites.EntityRegistry.HEXPORTAL_ENTITY_TYPE;

@Environment(EnvType.CLIENT)
public class HexwaysClientInit implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(HEXPORTAL_ENTITY_TYPE, (HexPortalRenderer::new));
    }
}
