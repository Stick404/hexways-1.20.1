package com.mindlesstoys.stickia.hexways.casting;

import at.petrak.hexcasting.api.casting.ActionRegistryEntry;
import at.petrak.hexcasting.api.casting.math.HexDir;
import at.petrak.hexcasting.api.casting.math.HexPattern;
import at.petrak.hexcasting.common.lib.hex.HexActions;
import com.mindlesstoys.stickia.hexways.Hexways;
import com.mindlesstoys.stickia.hexways.casting.patterns.OpTest;
import com.mindlesstoys.stickia.hexways.casting.spells.OpOneWayPortal;
import com.mindlesstoys.stickia.hexways.casting.spells.OpTwoWayPortal;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import at.petrak.hexcasting.api.casting.castables.Action;

import java.util.LinkedHashMap;
import java.util.Map;

public class PatternRegistry {
    private static final Map<ResourceLocation, ActionRegistryEntry> PATTERNS = new LinkedHashMap<>();
    //public static final HexPattern OPTEST = make("qaqqwa",HexDir.NORTH_EAST,"optest", new OpTest());
    public static final HexPattern OP_ONE_WAY = make("qaqqwa",HexDir.NORTH_EAST,"oponeway",new OpOneWayPortal());
    public static final HexPattern OP_TWO_WAY = make("qaqqww",HexDir.NORTH_EAST,"optwoway",new OpTwoWayPortal());

    static public void init() {
        for (Map.Entry<ResourceLocation, ActionRegistryEntry> entry : PATTERNS.entrySet()) {
            Registry.register(HexActions.REGISTRY, entry.getKey(), entry.getValue());
        }
    }
    private static HexPattern make(String signature, HexDir dir, String name, Action act ) { //gotten from ComplexHex lmao
        PATTERNS.put(
                new ResourceLocation(Hexways.MOD_ID, name),
                new ActionRegistryEntry(HexPattern.fromAngles(signature, dir), act)
        );
        return HexPattern.fromAngles(signature, dir);
    }
}
