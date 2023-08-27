package br.com.giocolombarolli.pokedex_android.api

import br.com.giocolombarolli.pokedex_android.api.model.PokemonApiResult
import br.com.giocolombarolli.pokedex_android.api.model.PokemonsApiResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonService {
    @GET("pokemon")
    fun listPokemons(@Query("limit") limit: Int): Call<PokemonsApiResult>

    @GET("pokemon/{code}")
    fun getPokemon(code: Int): Call<PokemonApiResult>
}