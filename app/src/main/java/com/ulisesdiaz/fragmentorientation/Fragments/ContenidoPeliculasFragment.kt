package com.ulisesdiaz.fragmentorientation.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.ulisesdiaz.fragmentorientation.R


class ContenidoPeliculasFragment : Fragment() {

    var vista: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        vista = inflater.inflate(R.layout.fragment_contenido_peliculas, container, false)

        cambiarFoto()

        return  vista
    }

    fun nuevaInstancia(index: Int): ContenidoPeliculasFragment {
        val f = ContenidoPeliculasFragment()
        val args = Bundle()
        args.putInt("INDEX", index)
        f.arguments = args

        return f
    }

    fun obtenerIndex(): Int{
        val index = arguments?.getInt("INDEX", 0)!!
        return index
    }

    fun cambiarFoto(){
        val foto = vista?.findViewById<ImageView>(R.id.imgFoto)
        foto?.setImageResource(ListaPeliculasFragment.peliculas?.get(obtenerIndex())?.imagen!!)
    }

}