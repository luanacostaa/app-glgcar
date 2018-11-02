package br.com.luanacostaa.glgcar
import android.app.Application
import java.lang.IllegalStateException

class GLGApplication: Application() {

    // chamado quando android iniciar o processo da aplicação
    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {
        // singleton
        private var appInstance: GLGApplication?  = null
        fun getInstance(): GLGApplication {
            if (appInstance == null) {
                throw IllegalStateException("Configurar application no Android Manifest")
            }
            return appInstance!!
        }
    }

    // chamado quando android terminar processo da aplicação
    override fun onTerminate() {
        super.onTerminate()
    }
}
