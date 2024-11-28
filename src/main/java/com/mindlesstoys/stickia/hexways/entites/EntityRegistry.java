package com.mindlesstoys.stickia.hexways.entites;

import at.petrak.hexcasting.api.casting.ActionRegistryEntry;
import at.petrak.hexcasting.common.lib.hex.HexActions;
import com.mindlesstoys.stickia.hexways.Hexways;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import org.intellij.lang.annotations.Identifier;
import qouteall.imm_ptl.core.portal.Portal;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import static com.mindlesstoys.stickia.hexways.Hexways.MOD_ID;
import static qouteall.imm_ptl.core.portal.Portal.createPortalEntityType;

public class EntityRegistry {
    private static final Map<ResourceLocation, EntityType<?>> ENTITIES = new HashMap<>();

    //This place is not a place of honor, no highly esteemed deed is commemorated here
    //public static final EntityType<HexPortal> HEX_PORTAL = register("hexportal", EntityType.Builder.<HexPortal>of(
            //HexPortal::new, MobCategory.MISC).sized(0.5f,0.5f).build(Hexways.MOD_ID + ":hex_portal"));
    //public static final EntityType<HexPortal> HEXPORTAL_ENTITY_TYPE = createPortalEntityType(HexPortal::new);
//    public static final EntityType<HexPortal> HEXPORTAL_ENTITY_TYPE = Registry.register(
//            BuiltInRegistries.ENTITY_TYPE,
//            new ResourceLocation(MOD_ID,"hexportal"),
//            //EntityType.Builder.<HexPortal>of(HexPortal::new,MobCategory.MISC).sized(new EntityDimensions(1.0F, 1.0F, true)).fireImmune().trackRangeBlocks(96).trackedUpdateRate(20).forceTrackedVelocityUpdates(true).build("hexportal"));
//            FabricEntityTypeBuilder.create(MobCategory.MISC, constructor).dimensions(new EntityDimensions(1.0F, 1.0F, true)).fireImmune().trackRangeBlocks(96).trackedUpdateRate(20).forceTrackedVelocityUpdates(true).build()
//    );
    //public static final EntityType<HexPortal> HEXPORTAL_ENTITY_TYPE = createPortalEntityType(HexPortal::new);
    public static final EntityType<HexPortal> HEXPORTAL_ENTITY_TYPE = register(
            "hexportal",
            FabricEntityTypeBuilder.create(MobCategory.MISC, HexPortal::new).dimensions(new EntityDimensions(1.0F, 1.0F, true)).fireImmune().trackRangeBlocks(96).trackedUpdateRate(20).forceTrackedVelocityUpdates(true).build()
    );

    static public void init() {
        for (Map.Entry<ResourceLocation, EntityType<?>> entry : ENTITIES.entrySet()) {
            Registry.register(BuiltInRegistries.ENTITY_TYPE, entry.getKey(), entry.getValue());
        }
    }
    private static <T extends Entity> EntityType<T> register(String id, EntityType<T> type) {
        var old = ENTITIES.put(new ResourceLocation(MOD_ID, id), type);
        if (old != null) {
            throw new IllegalArgumentException("Typo? Duplicate id " + id);
        }
        return type;
    }
}
