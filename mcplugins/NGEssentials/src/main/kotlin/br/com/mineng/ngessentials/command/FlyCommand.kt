package br.com.mineng.ngessentials.command

import br.com.mineng.commons.command.PlayerCommand
import br.com.mineng.commons.messages.Tag.AVISO
import br.com.mineng.commons.messages.Tag.SUCESSO
import br.com.mineng.commons.messages.tag
import net.kyori.adventure.text.Component.space
import net.kyori.adventure.text.Component.text
import org.bukkit.entity.Player

class FlyCommand : PlayerCommand("fly", "Ativa o modo fly!", aliases = listOf("f")) {
    override fun onCommand(player: Player, label: String, args: Array<out String>) {
        // TODO: Adicionar lógica de timedfly

        val isVoando = player.allowFlight

        player.allowFlight = !isVoando

        player.sendMessage(
            tag(if (isVoando) AVISO else SUCESSO)
                .append(space())
                .append(text(if(isVoando) "Fly desativado!" else "Flying ativado!")))
    }

}