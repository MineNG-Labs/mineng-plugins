package br.com.mineng.ngessentials

import br.com.mineng.commons.NGPlugin
import br.com.mineng.commons.ext.getAll
import com.google.inject.Guice

class NGEssentials : NGPlugin() {
    override fun onEnable() {
        createConfig()

        val injector = Guice.createInjector(DIModule(this))

        registerCommands(bukkitCommands = injector.getAll())
        registerListeners(injector.getAll())
    }
}