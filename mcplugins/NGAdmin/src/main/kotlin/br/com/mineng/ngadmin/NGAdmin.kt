package br.com.mineng.ngadmin

import br.com.mineng.commons.NGPlugin
import br.com.mineng.commons.ext.getAll
import com.google.inject.Guice

class NGAdmin : NGPlugin() {
    override fun onEnable() {
        createConfig()

        val injector = Guice.createInjector(DIModule(this))

        registerCommands(brigadierCommands = injector.getAll())
        registerListeners(injector.getAll())
    }
}