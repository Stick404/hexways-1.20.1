package com.mindlesstoys.stickia.hexways.entites;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import qouteall.imm_ptl.core.portal.Portal;

public class HexPortal extends Portal {
    public static final EntityType<HexPortal> entityType = createPortalEntityType(HexPortal::new);
    public HexPortal(EntityType<?> entityType, Level world) {
        super(entityType, world);
    }
}
