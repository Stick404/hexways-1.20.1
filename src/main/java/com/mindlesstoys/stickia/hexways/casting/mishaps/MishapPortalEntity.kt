package com.mindlesstoys.stickia.hexways.casting.mishaps

import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.mishaps.Mishap
import at.petrak.hexcasting.api.pigment.FrozenPigment
import at.petrak.hexcasting.api.utils.aqua
import at.petrak.hexcasting.api.utils.asTranslatedComponent
import com.mindlesstoys.stickia.hexways.entites.HexPortal
import net.minecraft.ChatFormatting
import net.minecraft.network.chat.Component
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.item.DyeColor
import qouteall.imm_ptl.core.portal.Portal

class MishapPortalEntity(val got: Entity,val wanted: EntityType<Portal>) : Mishap() {
    override fun accentColor(ctx: CastingEnvironment, errorCtx: Context): FrozenPigment = dyeColor(DyeColor.LIGHT_BLUE)

    override fun errorMessage(ctx: CastingEnvironment, errorCtx: Context) = error(
        "bad_entity",
        wanted,
        got.displayName.plainCopy().aqua
    )

    override fun execute(env: CastingEnvironment, errorCtx: Context, stack: MutableList<Iota>) {
    }

    companion object {
        @JvmStatic
        fun of(wantedEntity: Entity): MishapPortalEntity {
            val typePortal = HexPortal.entityType
            return MishapPortalEntity(wantedEntity,typePortal)
        }
    }
}