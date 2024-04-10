package br.com.mineng.ngessentials.messages

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration.BOLD

enum class Tag(val tonalidadePrincipal: TextColor, val tonalidadeSecundaria: TextColor) {
    SUCESSO(NamedTextColor.GREEN, NamedTextColor.DARK_GREEN),
    AVISO(NamedTextColor.YELLOW, TextColor.fromCSSHexString("#ffc107")!!),
    ERRO(TextColor.fromCSSHexString("#ff6600")!!, TextColor.fromCSSHexString("#ff3300")!!),
}

fun tag(tag: Tag): Component {
    return text("[", tag.tonalidadePrincipal)
        .append(text("!", tag.tonalidadeSecundaria, BOLD))
        .append(text("]", tag.tonalidadePrincipal))
}