package br.com.luanacostaa.glgcar

import android.content.Context
import android.provider.CalendarContract
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import java.net.URL

object CarroService {

    //TROQUE PELO IP DE ONDE ESTÁ O WS
    val host = "https://api-glgcar.herokuapp.com"
    val TAG = "WS_GLGCARApp"

    fun getCarros (context: Context): List<Carro> {
        var carros = ArrayList<Carro>()
        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/carro"
            val json = HttpHelper.get(url)
            carros = parserJson(json)
            // salvar offline
            for (d in carros) {
                saveOffline(d)
            }
            return carros
        } else {
            val dao = DatabaseManager.getCarroDAO()
            val carros = dao.findAll()
            return carros
        }
    }

    fun getCarro (context: Context, _id: String, code: Long): Carro? {

        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/carro/${_id}"
            val json = HttpHelper.get(url)
            val carro = parserJson<Carro>(json)

            return carro
        } else {
            val dao = DatabaseManager.getCarroDAO()
            val carro = dao.getById(code)
            return carro
        }
    }

    fun save(carro: Carro): Response {
        val json = HttpHelper.post("$host/carro", carro.toJson())
        return parserJson(json)
    }

    fun saveOffline(carro: Carro) : Boolean {
        val dao = DatabaseManager.getCarroDAO()

        if (! existeCarro(carro)) {
            dao.insert(carro)
        }

        return true
    }

    fun existeCarro(carro: Carro): Boolean {
        val dao = DatabaseManager.getCarroDAO()
        return dao.getById(carro.code) != null
    }

    inline fun <reified T> parserJson(json: String): T {
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }
}