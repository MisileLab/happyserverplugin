package com.chizstudio.misilelaboratory.happyserverplugin.modules

import com.chizstudio.misilelaboratory.happyserverplugin.getconfig
import kotlinx.serialization.ExperimentalSerializationApi
import net.kyori.adventure.text.Component
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class CustomKick: Listener {

    @ExperimentalSerializationApi
    @EventHandler
    fun customkickhandler(e: PlayerJoinEvent) {
        val config = getconfig()
        val playeruuids = config.CustomListKick.split(",")
        if (playeruuids.contains(e.player.uniqueId.toString())) {
            e.player.kick(Component.text(config.CustomMessageKick))
        }
    }
}