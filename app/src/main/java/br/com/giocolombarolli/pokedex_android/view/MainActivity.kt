package br.com.giocolombarolli.pokedex_android.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.giocolombarolli.pokedex_android.R
import br.com.giocolombarolli.pokedex_android.domain.Pokemon
import br.com.giocolombarolli.pokedex_android.viewmodel.PokemonViewModel
import br.com.giocolombarolli.pokedex_android.viewmodel.PokemonViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: EditText
    private lateinit var loader: ProgressBar
  
    private val viewModel by lazy {
        ViewModelProvider(this, PokemonViewModelFactory())[PokemonViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        searchView = findViewById(R.id.svPokemons)
        recyclerView = findViewById(R.id.rvPokemons)
        loader = findViewById(R.id.loader)

        viewModel.pokemons.observe(this, Observer {
            loader.visibility = View.GONE
            loadRecyclerView(it)
        })

        searchView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val searchText = s.toString()
                viewModel.filterPokemonByType(searchText)
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

        viewModel.filteredPokemon.observe(this, Observer { filteredList ->
            loadRecyclerView(filteredList)
        })
    }
    
    private fun loadRecyclerView(pokemons: List<Pokemon?>) {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = PokemonAdapter(pokemons)
    }
}