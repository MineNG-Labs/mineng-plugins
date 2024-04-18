package br.com.mineng.ngadmin.commands

import br.com.mineng.commons.command.BrigadierCommand
import com.google.inject.Inject
import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.executors.CommandExecutor
import net.kyori.adventure.text.Component.*
import net.kyori.adventure.text.JoinConfiguration.spaces
import net.kyori.adventure.text.format.NamedTextColor.*
import net.kyori.adventure.text.format.TextDecoration.BOLD
import org.bukkit.entity.Player
import oshi.SystemInfo
import java.lang.Runtime.getRuntime

class ServerInfoCommand @Inject constructor(
    private val oshi: SystemInfo
) : BrigadierCommand() {
    override fun command() = CommandAPICommand("serverinfo")
        .withAliases("svinfo")
        .withPermission("mineng.admin")
        .withFullDescription("Mostra informações sobre o servidor no " +
                "presente momento, bem como detalhes da máquina")
        .executes(CommandExecutor { sender, _ ->
            sender.sendMessage(
                text("Informações do servidor:", GOLD, BOLD)
                    .append(newline().decoration(BOLD, false))
                    .apply { if(sender is Player) append(stat("Ping", sender.ping)) }
                    .append(stat("CPU (Qtd)", getRuntime().availableProcessors()))
                    .append(stat("RAM Total", getRuntime().maxMemory()))
                    .append(stat("Processador", oshi.hardware.processor
                        .processorIdentifier.name))

            )
        })!!

    private fun stat(nome: String, valor: Any) = join(
        spaces(),
        text("$nome:", YELLOW),
        text(valor.toString(), GREEN)
    ).append(newline())
}