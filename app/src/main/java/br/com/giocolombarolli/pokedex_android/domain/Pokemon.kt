package br.com.giocolombarolli.pokedex_android.domain


data class Pokemon(

    val number: Int,
    val name: String,
    val types: List<PokemonType>
) {
    val formattedName = name.replaceFirstChar { it.uppercase() }
    val formattedNumber = number.toString().padStart(3, '0')
    val imageUrl = "https://assets.pokemon.com/assets/cms2/img/pokedex/full/$formattedNumber.png"
}

