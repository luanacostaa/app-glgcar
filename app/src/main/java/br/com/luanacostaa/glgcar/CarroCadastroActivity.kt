package br.com.luanacostaa.glgcar

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_cadastro_carro.*
import kotlinx.android.synthetic.main.login.*

class CarroCadastroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_carro)
        setTitle("Novo Carro")

        val numero = intent.getIntExtra("numero",0)

        salvarCarro.setOnClickListener {
            val carro = Carro()
            carro.name = nameCarro.text.toString()
            carro.description = descriptionCarro.text.toString()
            carro.price = priceCarro.text.toString().toInt()
            carro.imageUrl = imageUrl.text.toString()

            taskAtualizar(carro)
        }
    }

    private fun taskAtualizar(carro: Carro) {
        // Thread para salvar a discilpina
        Thread {
            if (AndroidUtils.isInternetDisponivel(GLGApplication.getInstance().applicationContext)) {
                CarroService.save(carro)
            } else {
                CarroService.saveOffline(carro)
            }
            runOnUiThread {
                // após cadastrar, voltar para activity anterior
                finish()
            }
        }.start()
    }
}
