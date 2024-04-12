package br.com.mineng.commons.database

import org.bukkit.plugin.java.JavaPlugin
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class Query internal constructor(val plugin: JavaPlugin, val sql: String, val connection: Connection) {
    private var bindingLogic: PreparedStatement.() -> Unit = {}
    private var finished = false

    fun params(bindingLogic: PreparedStatement.() -> Unit): Query {
        this.bindingLogic = bindingLogic
        return this
    }

    suspend fun thenQueryData(): ResultSet = suspendCoroutine { continuation ->
        finishQuery(continuation) {
            continuation.resume(executeQuery())
        }
    }

    suspend fun thenRunUpdate() = suspendCoroutine { continuation ->
        finishQuery(continuation) {
            continuation.resume(executeUpdate())
        }
    }

    private fun finishQuery(continuation: Continuation<*>, logic: PreparedStatement.()->Unit) {
        validateQueryFinished(continuation)

        try {
            connection.use {
                it.prepareStatement(this.sql).use { ps ->
                    bindingLogic.invoke(ps)

                    logic.invoke(ps)
                }
            }
        } catch (e: SQLException) {
            plugin.logger.warning("Erro ao executar query SQL: $e")
            continuation.resumeWithException(e)
        }
    }


    private fun validateQueryFinished(continuation: Continuation<*>) {
        if(this.finished) {
            return continuation.resumeWithException(IllegalStateException("A execução da query ja foi encerrada"))
        }

        finished = true
    }
}