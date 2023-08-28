package br.com.giocolombarolli.pokedex_android.api
import br.com.giocolombarolli.pokedex_android.api.model.PokemonApiResult
import br.com.giocolombarolli.pokedex_android.api.model.PokemonsApiResult
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PokemonRepository {
    private val service: PokemonService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(PokemonService::class.java)
    }

    fun listPokemons(limit: Int = 151): PokemonsApiResult? {
        val call = service.listPokemons(limit)

        return call.execute().body()

    }

    fun getPokemon(code: Int): PokemonApiResult? {
        val call = service.getPokemon(code)

        return call.execute().body()

    }
}
