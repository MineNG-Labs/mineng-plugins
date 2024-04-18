package br.com.mineng.commons.ext

import com.google.inject.Injector
import dev.misfitlabs.kotlinguice4.findBindingsByType

inline fun <reified T> Injector.getAll() = this.findBindingsByType<T>()
    .map { it.provider.get() }