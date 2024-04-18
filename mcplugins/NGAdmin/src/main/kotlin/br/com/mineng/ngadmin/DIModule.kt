package br.com.mineng.ngadmin

import br.com.mineng.commons.command.BrigadierCommand
import br.com.mineng.ngadmin.commands.ServerInfoCommand
import dev.misfitlabs.kotlinguice4.KotlinModule
import dev.misfitlabs.kotlinguice4.multibindings.KotlinMultibinder.Companion.newSetBinder
import org.bukkit.configuration.file.YamlConfiguration
import org.slf4j.Logger
import oshi.SystemInfo


class DIModule (private val plugin: NGAdmin) : KotlinModule() {
    override fun configure() {
        bind<NGAdmin>().toInstance(plugin)
        bind<YamlConfiguration>().toInstance(plugin.config)
        bind<Logger>().toInstance(plugin.log())

        bind<SystemInfo>().toInstance(SystemInfo())

        newSetBinder<BrigadierCommand>(binder()).apply {
            addBinding().to<ServerInfoCommand>()
        }
    }
}