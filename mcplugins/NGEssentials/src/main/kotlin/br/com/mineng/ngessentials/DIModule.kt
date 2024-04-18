package br.com.mineng.ngessentials

import br.com.mineng.ngessentials.command.FlyCommand
import dev.misfitlabs.kotlinguice4.KotlinModule
import dev.misfitlabs.kotlinguice4.multibindings.KotlinMultibinder.Companion.newSetBinder
import org.bukkit.command.Command
import org.bukkit.configuration.file.YamlConfiguration
import org.slf4j.Logger

class DIModule(private val plugin: NGEssentials) : KotlinModule() {
    override fun configure() {
        bind<NGEssentials>().toInstance(plugin)
        bind<YamlConfiguration>().toInstance(plugin.config)
        bind<Logger>().toInstance(plugin.log())

        newSetBinder<Command>(binder()).apply {
            addBinding().to<FlyCommand>()
        }
    }
}