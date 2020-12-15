package com.ulisesdiaz.fragmentorientation

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ulisesdiaz.fragmentorientation.Fragments.ContenidoPeliculasFragment

class DetallePeliculaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_pelicula)

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            finish()
            return
        }

        if (savedInstanceState == null){
            val fDetalles = ContenidoPeliculasFragment()
            fDetalles.arguments = intent.extras
            supportFragmentManager.beginTransaction().add(R.id.container, fDetalles).commit()
        }

        /*val index = intent.getIntExtra("INDEX", 0)
        val foto = findViewById<ImageView>(R.id.imgFoto)

        foto.setImageResource(ListaPeliculasFragment.peliculas?.get(index)?.imagen!!)*/
    }
}