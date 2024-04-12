package br.com.mineng.ngessentials

import br.com.mineng.ngessentials.command.FlyCommand
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import org.koin.core.context.startKoin

class NGEssentials : JavaPlugin() {
    override fun onEnable() {
        Bukkit.getCommandMap()
            .register("fly", FlyCommand())

        startKoin {
            modules(mainModule(this@NGEssentials))
        }
    }
}