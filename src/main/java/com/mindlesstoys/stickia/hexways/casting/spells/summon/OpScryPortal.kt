package com.mindlesstoys.stickia.hexways.casting.spells.summon

import at.petrak.hexcasting.api.casting.*
import at.petrak.hexcasting.api.casting.castables.SpellAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.misc.MediaConstants
import com.mindlesstoys.stickia.hexways.PortalHexUtils
import com.mindlesstoys.stickia.hexways.PortalHexUtils.Companion.PortalVecRotate
import com.mindlesstoys.stickia.hexways.entites.EntityRegistry.HEXPORTAL_ENTITY_TYPE
import com.mindlesstoys.stickia.hexways.entites.HexPortal
import net.minecraft.world.phys.Vec3
import org.joml.Vector3f
import qouteall.imm_ptl.core.api.PortalAPI
import qouteall.imm_ptl.core.portal.Portal

class OpScryPortal : SpellAction {
    override val argc = 3
    override fun execute(
        args: List<Iota>,
        env: CastingEnvironment,
    ): SpellAction.Result {
        val prtPos: Vec3 = args.getVec3(0,argc)
        val prtPosOut: Vec3 = args.getVec3(1,argc)
        val prtRot: Vec3 = args.getVec3(2,argc)

        val cost = (prtPos.distanceTo(prtPosOut)*MediaConstants.DUST_UNIT/2).toLong()

        val prtPos3f = Vector3f(prtPos.x.toFloat(), prtPos.y.toFloat(), prtPos.z.toFloat())

        env.assertVecInRange(prtPos)
        env.assertVecInRange(prtPosOut)

        return SpellAction.Result(
            Spell(prtPos3f,prtPosOut,prtRot),
            cost,
            listOf(ParticleSpray.burst(env.mishapSprayPos(), 1.0), ParticleSpray.burst(prtPos, 1.0))
        )

    }

    data class Spell(val prtPos: Vector3f, val prtPosOut: Vec3, val prtRot: Vec3) : RenderedSpell {
        override fun cast(env: CastingEnvironment) {
            val portalIn: Portal? = HEXPORTAL_ENTITY_TYPE.create(env.world)

            portalIn!!.originPos = Vec3(prtPos)
            portalIn.setDestinationDimension(env.world.dimension())
            portalIn.setDestination(prtPosOut)
            portalIn.setOrientationAndSize(
                PortalVecRotate(prtRot)[0],
                PortalVecRotate(prtRot)[1],
                1.0,
                1.0
            )
            PortalHexUtils.MakePortalNGon(portalIn,6 ,0.0)

            val portalInOp = PortalAPI.createFlippedPortal(portalIn)
            portalIn as HexPortal
            portalInOp as HexPortal

            portalIn.ambitTraversable = false
            portalInOp.ambitTraversable = false
            portalIn.teleportable = false
            portalInOp.teleportable = false
            portalIn.isInteractable = false
            portalInOp.isInteractable = false

            portalIn.originWorld.addFreshEntity(portalIn)
            portalIn.originWorld.addFreshEntity(portalInOp)
        }
    }
}