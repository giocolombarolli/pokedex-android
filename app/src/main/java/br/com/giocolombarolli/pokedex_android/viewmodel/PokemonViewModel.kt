package br.com.giocolombarolli.pokedex_android.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.giocolombarolli.pokedex_android.api.PokemonRepository
import br.com.giocolombarolli.pokedex_android.domain.Pokemon

class PokemonViewModel : ViewModel() {
    var pokemons = MutableLiveData<List<Pokemon?>>()
    val filteredPokemon = MutableLiveData<List<Pokemon?>>()
    
    init {
        Thread(Runnable {
            loadPokemons()
        }).start()
    }

    private fun loadPokemons() {
        val pokemonsApiResult = PokemonRepository.listPokemons()
        pokemonsApiResult?.results?.let {

            pokemons.postValue(it.map { pokemonResult ->
                val number = pokemonResult.url
                    .replace("https://pokeapi.co/api/v2/pokemon/", "")
                    .replace("/", "").toInt()
                val pokemonApiResult = PokemonRepository.getPokemon(number)

                pokemonApiResult?.let {
                    Pokemon(
                        pokemonApiResult.id,
                        pokemonApiResult.name,
                        pokemonApiResult.types.map { type ->
                            type.type
                        }
                    )
                }
            })
        }
    }
    fun filterPokemonByType(type: String) {
        val originalList = pokemons.value ?: emptyList()

        val filteredList = if (type.isNotBlank()) {
            originalList.filter { pokemon ->
                pokemon!!.types.any { it.name.equals(type, ignoreCase = true) }
            }
        } else {
            originalList
        }
        filteredPokemon.value = filteredList
    }
}