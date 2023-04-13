package config

import mu.KotlinLogging
import java.io.File
import java.nio.file.Files
import java.util.*

object ConfigApp {
    lateinit var APP_NAME: String
    lateinit var APP_VERSION : String
    lateinit var APP_AUTHOR: String
    lateinit var APP_DATA: String
    private val LOCAL_PATH: String = System.getProperty("user.dir")+File.separator

    private val logger = KotlinLogging.logger {}

    init{
        loadProperties()
        initStorage()
    }

    private fun initStorage() {
        logger.debug { "Se inicia en caso de que no exista, la carpeta del storage." }
        val file = File(APP_DATA)
        if(!file.exists()){
            Files.createDirectory(file.toPath())
        }
    }

    private fun loadProperties(){
        logger.debug { "Se cargan las propiedades salidas de la carpeta resources." }
        val properties = Properties()
        properties.load(ConfigApp::class.java.getResourceAsStream("/config.properties"))
        APP_NAME = properties.getProperty("APP_NAME") ?: "ITV-Ficheros-Kotlin"
        APP_VERSION = properties.getProperty("APP_VERSION") ?: "1.0"
        APP_AUTHOR = properties.getProperty("APP_AUTHOR") ?: "IvánRC"
        APP_DATA = properties.getProperty("APP_DATA") ?: "data"
        APP_DATA = LOCAL_PATH+APP_DATA
    }
}