package com.mindlesstoys.stickia.hexways.casting

import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.eval.CastingEnvironmentComponent
import at.petrak.hexcasting.api.casting.eval.CastingEnvironmentComponent.IsVecInRange
import com.mindlesstoys.stickia.hexways.entites.HexPortal
import net.minecraft.world.phys.AABB
import net.minecraft.world.phys.Vec3
import kotlin.random.Random

class Key(val id: Int) : CastingEnvironmentComponent.Key<PortalAmbit>

class PortalAmbit(private val env: CastingEnvironment) : IsVecInRange {
    //before we start the code. HOLY FUCKING SHIT LETS GOOO
    //Thank you Hex Devs for being awesome
    private val id = Keygen.randid()
    private val key = Key(id)
    override fun getKey(): CastingEnvironmentComponent.Key<*> = key
    override fun onIsVecInRange(pos: Vec3?, inAmbit: Boolean): Boolean {
        var inAmbitMaybe = inAmbit
        if (!inAmbitMaybe) {
            if (env.castingEntity != null) {
                val eyePos = env.castingEntity!!.eyePosition
                val aabb = AABB(eyePos.add(32.0,32.0,32.0),eyePos.add(-32.0,-32.0,-32.0))
                val portals = env.world.getEntities(HexPortal.entityType,aabb) {true}
                for (e: HexPortal in portals) { //semi-redundant, but still smart to have
                    val ambitLeft = (32 - e.eyePosition.distanceTo(eyePos))/2
                    if (ambitLeft > 0) {//Add if Portal is in 32 blocks
                        inAmbitMaybe = true
                    }
                }
            }
        }
        return inAmbitMaybe
    }
    private object Keygen { //code from HexSky
        val rand = Random(2819038190)
        fun randid() = rand.nextInt()
    }
}