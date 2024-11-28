package com.mindlesstoys.stickia.hexways.entites;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import qouteall.imm_ptl.core.portal.Portal;
import qouteall.imm_ptl.core.render.PortalEntityRenderer;

public class HexPortalRenderer extends PortalEntityRenderer {
    public HexPortalRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(Portal portal) {
        return null;
    }
}
