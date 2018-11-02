package br.com.luanacostaa.glgcar

import android.arch.persistence.room.Room

object DatabaseManager {

    // singleton
    private var dbInstance: GLGDatabase
    init {
        val appContext = GLGApplication.getInstance().applicationContext
        dbInstance = Room.databaseBuilder(
                appContext, // contexto global
                GLGDatabase::class.java, // Referência da classe do banco
                "glg.sqlite" // nome do arquivo do banco
        ).build()
    }

    fun getCarroDAO(): CarroDAO {
        return dbInstance.carroDAO()
    }
}