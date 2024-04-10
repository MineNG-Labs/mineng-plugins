package br.com.mineng.ngessentials.command

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

abstract class PlayerCommand(
    private val name: String,
    description: String = "",
    usageMessage: String = "/$name",
    aliases: List<String> = emptyList(),
) : Command(name, description, usageMessage, aliases) {
    override fun execute(sender: CommandSender, label: String, args: Array<out String>?): Boolean {
        if(sender !is Player) {
            sender.sendMessage("Comando acessível apenas por players!")
            return false
        }

        onCommand(sender, label, args ?: emptyArray())
        return true
    }

    abstract fun onCommand(player: Player, label: String, args: Array<out String>)
}