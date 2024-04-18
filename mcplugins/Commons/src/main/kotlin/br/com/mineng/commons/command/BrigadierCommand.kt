package br.com.mineng.commons.command

import dev.jorel.commandapi.CommandAPICommand

abstract class BrigadierCommand {
    abstract fun command(): CommandAPICommand
}