package com.ulisesdiaz.fragmentorientation.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.ulisesdiaz.fragmentorientation.DetallePeliculaActivity
import com.ulisesdiaz.fragmentorientation.Models.Pelicula
import com.ulisesdiaz.fragmentorientation.R


class ListaPeliculasFragment : Fragment() {

    companion object{
        var peliculas: ArrayList<Pelicula>? = null
    }

    var nombrePeliculas: ArrayList<String>? = null
    var listaPeliculas: ListView? = null

    var doblePanel = false

    var posicionActual = 0

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configurarListview()

        val frameDetalles = activity!!.findViewById<FrameLayout>(R.id.detalles)
        doblePanel = frameDetalles != null && frameDetalles.visibility == View.VISIBLE

        if (savedInstanceState != null){
            posicionActual = savedInstanceState.getInt("INDEX", 0)
        }
        if (doblePanel){
            listaPeliculas?.choiceMode = ListView.CHOICE_MODE_SINGLE
            mostrarDetalles(posicionActual)
        }
    }

    private fun configurarListview(){
        peliculas = ArrayList()
        peliculas?.add(Pelicula("Avengers end Game", R.drawable.poster1))
        peliculas?.add(Pelicula("Iron man 3", R.drawable.poster2))
        peliculas?.add(Pelicula("Jocker", R.drawable.poster3))
        peliculas?.add(Pelicula("Animales nocturnos", R.drawable.poster4))

        nombrePeliculas = obtenerNombrePeliculas(peliculas!!)

        val adaptador = ArrayAdapter(activity!!, android.R.layout.simple_list_item_activated_1,
            nombrePeliculas!!
        )

        listaPeliculas = activity!!.findViewById(R.id.lista)
        listaPeliculas?.adapter = adaptador

        listaPeliculas?.setOnItemClickListener { adapterView,  view, i, l ->
            mostrarDetalles(i)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val vista = inflater.inflate(R.layout.fragment_lista_peliculas, container, false)


        return vista

    }

    fun mostrarDetalles(index: Int){
        posicionActual = index

        if (doblePanel){
            listaPeliculas?.setItemChecked(index, true)

            var fDetalles = activity!!.supportFragmentManager.findFragmentById(R.id.detalles)  as? ContenidoPeliculasFragment
            if (fDetalles == null || fDetalles.obtenerIndex() != index){
                fDetalles = ContenidoPeliculasFragment().nuevaInstancia(index)

                val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()

                fragmentTransaction.replace(R.id.detalles, fDetalles)
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                fragmentTransaction.commit()
            }
        }else{
            val intent = Intent(activity, DetallePeliculaActivity::class.java)
            intent.putExtra("INDEX", index)
            startActivity(intent)
        }
    }

    fun obtenerNombrePeliculas(peliculas: ArrayList<Pelicula>): ArrayList<String>{
        val nombres = ArrayList<String>()

        for (pelicula in peliculas){
            nombres.add(pelicula.nombre)
        }

        return nombres
    }


}