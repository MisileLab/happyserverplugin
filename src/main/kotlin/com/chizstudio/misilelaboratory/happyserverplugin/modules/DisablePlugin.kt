package com.chizstudio.misilelaboratory.happyserverplugin.modules

import com.chizstudio.misilelaboratory.happyserverplugin.HappyServerPlugin
import io.github.monun.kommand.getValue
import io.github.monun.kommand.kommand
import kotlinx.serialization.ExperimentalSerializationApi
import org.bukkit.entity.Player

@ExperimentalSerializationApi
class DisablePlugin {
    private fun disableplugin(plugin: HappyServerPlugin, pluginstring: String): Boolean {
        val pluginmanager = plugin.server.pluginManager
        val disablepluginreal = pluginmanager.getPlugin(pluginstring)
        if (disablepluginreal != null) {
            pluginmanager.disablePlugin(disablepluginreal)
            return true
        }
        return false
    }

    private fun enableplugin(plugin: HappyServerPlugin, pluginstring: String): Boolean {
        val pluginmanager = plugin.server.pluginManager
        val enablepluginreal = pluginmanager.getPlugin(pluginstring)
        if (enablepluginreal != null) {
            pluginmanager.enablePlugin(enablepluginreal)
            return true
        }
        return false
    }

    fun setupkommand(plugin: HappyServerPlugin) {
        plugin.kommand {
            register("misile") {
                then("enableplugin") {
                    requires { isPlayer }
                    then("plugin" to string()) {
                        executes { context ->
                            val pluginname: String by context
                            val pluginsender = sender as Player
                            val disablepluginreturn = disableplugin(plugin, pluginname)
                            if (disablepluginreturn) {
                                pluginsender.sendMessage("플러그인이 성공적으로 비활성화되었습니다.")
                            }
                            else {
                                pluginsender.sendMessage("그런 플러그인이 없는 것 같습니다.")
                            }
                        }
                    }
                }
                then("disableplugin") {
                    requires { isPlayer }
                    then("plugin" to string()) {
                        executes { context ->
                            val pluginname: String by context
                            val pluginsender = sender as Player
                            val enablepluginreturn = enableplugin(plugin, pluginname)
                            if (enablepluginreturn) {
                                pluginsender.sendMessage("플러그인이 성공적으로 활성화되었습니다.")
                            }
                            else {
                                pluginsender.sendMessage("그런 플러그인이 없는 것 같습니다.")
                            }
                        }
                    }
                }
            }
        }
    }
}