package br.com.luanacostaa.glgcar

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.squareup.picasso.Picasso

class CarroActivity : DebugActivity() {

    private val context: Context get() = this
    var carro: Carro? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carro)

        // recuperar onjeto de Carro da Intent
        if (intent.getSerializableExtra("carro") is Carro)
            carro = intent.getSerializableExtra("carro") as Carro

        // configurar título com nome da Carro e botão de voltar da Toobar
        // colocar toolbar
        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // alterar título da ActionBar
        supportActionBar?.title = carro?.name

        // up navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // atualizar dados do carro
        var texto = findViewById<TextView>(R.id.nomeCarro)
        texto.text = carro?.name
        var imagem = findViewById<ImageView>(R.id.imagemCarro)
        Picasso.with(this).load(carro?.imageUrl).fit().into(imagem,
                object: com.squareup.picasso.Callback{
                    override fun onSuccess() {}

                    override fun onError() { }
                })
    }

    // método sobrescrito para inflar o menu na Actionbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // infla o menu com os botões da ActionBar
        menuInflater.inflate(R.menu.menu_main_carro, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        // id do item clicado
        val id = item?.itemId
        // verificar qual item foi clicado
        // remover a carro no WS
        if  (id == R.id.action_remover) {
            // alerta para confirmar a remeção
            // só remove se houver confirmação positiva
            AlertDialog.Builder(this)
                    .setTitle(R.string.app_name)
                    .setMessage("Deseja excluir a carro")
                    .setPositiveButton("Sim") {
                        dialog, which ->
                            dialog.dismiss()
                            taskExcluir()
                    }.setNegativeButton("Não") {
                        dialog, which -> dialog.dismiss()
                    }.create().show()
        }
        // botão up navigation
        else if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun taskExcluir() {
        if (this.carro != null && this.carro is Carro) {
            // Thread para remover a carro
            Thread {
                //                CarroService.delete(this.carro as Carro)
                runOnUiThread {
                    // após remover, voltar para activity anterior
                    finish()
                }
            }.start()
        }
    }

}
