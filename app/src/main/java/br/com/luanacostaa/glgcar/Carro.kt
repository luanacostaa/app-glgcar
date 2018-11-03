package br.com.luanacostaa.glgcar

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.Serializable

@Entity(tableName = "carro")
class Carro : Serializable {

    @PrimaryKey
    var code:Long = 0
    var name = ""
    var description = ""
    var imageUrl = ""
    var price = 0

    override fun toString(): String {
        return "Carro(name='$name')"
    }

    fun toJson(): String {
        return GsonBuilder().create().toJson(this)
    }
}