package br.com.mineng.ngessentials

import br.com.mineng.ngessentials.command.FlyCommand
import org.koin.dsl.module

fun mainModule(plugin: NGEssentials) = module {
    single(createdAtStart = true) { plugin }
    single(createdAtStart = true) { plugin.config }

    single { FlyCommand() }
}