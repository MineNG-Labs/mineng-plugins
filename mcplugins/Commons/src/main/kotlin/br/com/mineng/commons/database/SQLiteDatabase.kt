package br.com.mineng.commons.database

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.io.IOException
import java.sql.Connection
import java.sql.DriverManager.getConnection
import java.sql.SQLException
import java.util.logging.Level.SEVERE

class SQLiteDatabase(val nome: String = "sqlite", val plugin: JavaPlugin) {
    private lateinit var file: File

    fun setup(setupSql: Connection.() -> Unit = {}) {
        plugin.logger.info("Configurando banco de dados SQLite...")

        file = createDbFile()

        try {
            conn().use(setupSql)
        } catch (e: SQLException) {
            plugin.logger.severe("Erro ao conectar no SQLite. Encerrando plugin")
            Bukkit.getPluginManager().disablePlugin(plugin)
            throw RuntimeException(e)
        }

        plugin.logger.info("Banco de dados SQLite configurado com sucesso!")
    }

    fun conn(): Connection = getConnection("jdbc:sqlite:${file}")

    fun query(sql: String) = Query(plugin, sql, conn())

    private fun createDbFile(): File {
        val file = File(plugin.dataFolder, "$nome.sql")

        if(!file.exists()) {
            try {
                file.createNewFile()
            } catch (e: IOException) {
                handleSetupError(e)
            }
        }

        return file
    }

    private fun handleSetupError(ioException: IOException?) {
        plugin.logger.log(SEVERE,
            "Erro ao criar arquivo sqlite do plugin. Será necessário desativar o plugin.", ioException)
        throw IllegalStateException()
    }
}