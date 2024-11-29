package com.mindlesstoys.stickia.hexways.casting;

import at.petrak.hexcasting.api.casting.ActionRegistryEntry;
import at.petrak.hexcasting.api.casting.math.HexDir;
import at.petrak.hexcasting.api.casting.math.HexPattern;
import at.petrak.hexcasting.common.lib.hex.HexActions;
import com.mindlesstoys.stickia.hexways.Hexways;
import com.mindlesstoys.stickia.hexways.casting.spells.edit.OpMoveInput;
import com.mindlesstoys.stickia.hexways.casting.spells.edit.OpMoveOutput;
import com.mindlesstoys.stickia.hexways.casting.spells.summon.OpOneWayPortal;
import com.mindlesstoys.stickia.hexways.casting.spells.summon.OpScryPortal;
import com.mindlesstoys.stickia.hexways.casting.spells.summon.OpTwoWayPortal;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import at.petrak.hexcasting.api.casting.castables.Action;

import java.util.LinkedHashMap;
import java.util.Map;

public class PatternRegistry {
    private static final Map<ResourceLocation, ActionRegistryEntry> PATTERNS = new LinkedHashMap<>();

    //portal makers
    public static final HexPattern OP_ONE_WAY = make("awqwqwadadadaadadaqwdee",HexDir.EAST,"oponeway",new OpOneWayPortal());
    public static final HexPattern OP_TWO_WAY = make("wdeeqawqwqwadeaqadeaedaqae",HexDir.WEST,"optwoway",new OpTwoWayPortal());
    public static final HexPattern OP_SCRY_PORTAL = make("eedwwdwewewd", HexDir.NORTH_EAST,"opscryportal", new OpScryPortal());

    //portal editors
    public static final HexPattern OP_MOVE_IN_PORTAL = make("qqawwawqwqwaewaw",HexDir.NORTH_EAST,"opmoveinportal", new OpMoveInput());
    public static final HexPattern OP_MOVE_OUT_PORTAL = make("eedwwdwewewdqwdw",HexDir.NORTH_EAST,"opmoveoutportal", new OpMoveOutput());


    /*TODO: MAKE THESE PATTERNS:
     *ROTATEPORTAL, Portal
     *
     *
     *
    */
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
