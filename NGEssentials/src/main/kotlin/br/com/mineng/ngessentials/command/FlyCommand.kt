package br.com.mineng.ngessentials.command

import br.com.mineng.ngessentials.messages.Tag
import br.com.mineng.ngessentials.messages.tag
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.Component.space
import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.JoinConfiguration
import org.bukkit.entity.Player

class FlyCommand : PlayerCommand("fly", "Ativa o modo fly!", aliases = listOf("f")) {
    override fun onCommand(player: Player, label: String, args: Array<out String>) {
        // TODO: Adicionar lógica de timedfly

        val isVoando = player.allowFlight

        player.allowFlight = !isVoando

        player.sendMessage(
            tag(if (isVoando) Tag.AVISO else Tag.SUCESSO)
                .append(space())
                .append(text(if(isVoando) "Fly desativado!" else "Flying ativado!")))
    }

}