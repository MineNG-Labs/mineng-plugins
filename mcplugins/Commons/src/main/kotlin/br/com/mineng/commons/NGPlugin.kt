package br.com.mineng.commons

import br.com.mineng.commons.command.BrigadierCommand
import org.bukkit.command.Command
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.io.IOException

open class NGPlugin : JavaPlugin() {
    private lateinit var configFile: File
    lateinit var config: YamlConfiguration
        private set

    fun log() = slF4JLogger

    open fun createConfig() {
        configFile = File(dataFolder, "config.yml")
        config = YamlConfiguration.loadConfiguration(configFile)
    }

    override fun saveConfig() {
        try {
            config.save(configFile);
        } catch (e: IOException) {
            log().error("Erro ao salvar a config", e);
        }
    }

    open fun registerCommands(
        bukkitCommands: List<Command> = emptyList(),
        brigadierCommands: List<BrigadierCommand> = emptyList()) {

        bukkitCommands.forEach {
            this.server.commandMap.register(it.name, it)
        }

        brigadierCommands.forEach {
            it.command().register()
        }
    }

    open fun registerListeners(listeners: List<Listener>) {
        listeners.forEach {
            this.server.pluginManager.registerEvents(it, this)
        }
    }


}