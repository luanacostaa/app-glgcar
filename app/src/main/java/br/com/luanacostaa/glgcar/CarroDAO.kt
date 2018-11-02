package br.com.luanacostaa.glgcar

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface CarroDAO {
    @Query("SELECT * FROM carro where code = :code")
    fun getById(code: Long) : Carro?

    @Query("SELECT * FROM carro")
    fun findAll(): List<Carro>

    @Insert
    fun insert(carro: Carro)

    @Delete
    fun delete(carro: Carro)

}