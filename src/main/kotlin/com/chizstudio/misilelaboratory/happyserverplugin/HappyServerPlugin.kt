package com.chizstudio.misilelaboratory.happyserverplugin

import com.chizstudio.misilelaboratory.happyserverplugin.modules.CustomKick
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.properties.Properties
import kotlinx.serialization.properties.decodeFromMap
import kotlinx.serialization.properties.encodeToStringMap
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

@Serializable
data class Config(
    val CustomMessageKick: String,
    val CustomListKick: String
)

@ExperimentalSerializationApi
@Suppress("unused")
class HappyServerPlugin: JavaPlugin() {

    override fun onEnable() {
        logger.info("enable HappyServerPlugin")
        if (!File("./plugins/HappyServerPlugin/config.properties").exists()) {
            val file = File("./plugins/HappyServerPlugin/config.properties")
            file.createNewFile()
            val defaultconfig = Config("example", "example,example")
            val defaultconfigstring = Properties.encodeToStringMap(defaultconfig)
            for ((key, value) in defaultconfigstring) {
                file.writeText("$key=$value")
            }
        }
        kommandmanager(this)
        listenermanager(this)
    }

    fun getplugin(): HappyServerPlugin {
        return this
    }
}

@ExperimentalSerializationApi
fun kommandmanager(plugin: HappyServerPlugin) {

}

@ExperimentalSerializationApi
fun listenermanager(plugin: HappyServerPlugin) {
    plugin.server.pluginManager.registerEvents(CustomKick(), plugin)
}

@ExperimentalSerializationApi
fun getconfig(): Config {
    val file = File("./plugins/HappyServerPlugin/config.yml")
    val map = mutableMapOf<String, String>()
    for (i in file.readLines()) {
        val liststring = i.split("=")
        map[liststring[0]] = liststring[1]
    }
    return Properties.decodeFromMap(map)
}