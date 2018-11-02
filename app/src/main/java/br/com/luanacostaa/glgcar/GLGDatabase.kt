package br.com.luanacostaa.glgcar

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

// anotação define a lista de entidades e a versão do banco
@Database(entities = arrayOf(Carro::class), version = 5)
abstract class GLGDatabase: RoomDatabase() {
    abstract fun carroDAO(): CarroDAO
}