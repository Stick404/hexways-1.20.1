package com.mindlesstoys.stickia.hexways.casting.patterns

import at.petrak.hexcasting.api.casting.ParticleSpray
import at.petrak.hexcasting.api.casting.RenderedSpell
import at.petrak.hexcasting.api.casting.castables.SpellAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.getEntity
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.misc.MediaConstants
import com.mindlesstoys.stickia.hexways.casting.mishaps.MishapPortalEntity
import net.minecraft.world.entity.Entity
import net.minecraft.world.phys.Vec3

class OpTest : SpellAction { //Very Very temp Spell. Just to check if Registry works :weedHexxy:
    override val argc = 1
    override fun execute(
        args: List<Iota>,
        env: CastingEnvironment,
    ): SpellAction.Result {
        val entity1 = args.getEntity(0,argc)

        throw MishapPortalEntity.of(entity1)
        return SpellAction.Result(
            Spell(entity1),
            (MediaConstants.DUST_UNIT),
            listOf(ParticleSpray.burst(Vec3.atCenterOf(entity1.onPos), 1.0))
        )
    }
    private data class Spell(val pos: Entity) : RenderedSpell {
        override fun cast(env: CastingEnvironment) { //Jank? Yes. Works? Hope so!
            println(":yippie:")
        }
    }
}