package com.mindlesstoys.stickia.hexways

import com.mindlesstoys.stickia.hexways.casting.PatternRegistry
import com.mindlesstoys.stickia.hexways.entites.EntityRegistry
import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory

object Hexways : ModInitializer {
    private val logger = LoggerFactory.getLogger("hexways")
	const val MOD_ID = "hexways"

	override fun onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		logger.info("Hello Fabric world!")
		PatternRegistry.init()
		EntityRegistry.init()
	}
}