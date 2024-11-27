package com.mindlesstoys.stickia.hexways.entites;

import at.petrak.hexcasting.api.casting.ActionRegistryEntry;
import at.petrak.hexcasting.common.lib.hex.HexActions;
import com.mindlesstoys.stickia.hexways.Hexways;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class EntityRegistry {
    private static final Map<ResourceLocation, EntityType<?>> ENTITIES = new HashMap<>();

    public static final EntityType<HexPortal> HEX_PORTAL = register("hexportal", EntityType.Builder.<HexPortal>of(
            HexPortal::new, MobCategory.MISC).sized(0.5f,0.5f).build(Hexways.MOD_ID + ":hex_portal"));

    static public void init() {
        for (Map.Entry<ResourceLocation, EntityType<?>> entry : ENTITIES.entrySet()) {
            Registry.register(BuiltInRegistries.ENTITY_TYPE, entry.getKey(), entry.getValue());
        }
    }
    private static <T extends Entity> EntityType<T> register(String id, EntityType<T> type) {
        var old = ENTITIES.put(new ResourceLocation(Hexways.MOD_ID, id), type);
        if (old != null) {
            throw new IllegalArgumentException("Typo? Duplicate id " + id);
        }
        return type;
    }
}
