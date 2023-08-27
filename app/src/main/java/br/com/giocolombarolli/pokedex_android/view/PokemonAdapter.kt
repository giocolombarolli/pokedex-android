package br.com.giocolombarolli.pokedex_android.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.giocolombarolli.pokedex_android.R
import br.com.giocolombarolli.pokedex_android.domain.Pokemon

class PokemonAdapter(
    private val items: List<Pokemon>
) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = items.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bindView(item)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(item: Pokemon) = with(itemView) {

            // TODO: Implement binding below
            val pokemonImg = findViewById<ImageView>(R.id.pokemon_img)
            val pokemonCode = findViewById<TextView>(R.id.pokemon_txt_code)
            val pokemonName = findViewById<TextView>(R.id.pokemon_txt_name)
            val pokemonType1 = findViewById<TextView>(R.id.pokemon_txt_type1)
            val pokemonType2 = findViewById<TextView>(R.id.pokemon_txt_type2)

            // TODO: Load image with Glide

            pokemonCode.text = "Nº ${item.formattedNumber}"
            pokemonName.text = item.name
            pokemonType1.text = item.types[0].name

            if (item.types.size > 1) {
                pokemonType2.visibility = View.VISIBLE
                pokemonType2.text = item.types[1].name
            } else {
                pokemonType2.visibility = View.GONE
            }

        }

    }


}