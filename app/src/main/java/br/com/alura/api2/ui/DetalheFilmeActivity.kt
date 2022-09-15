package br.com.alura.api2.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import br.com.alura.api2.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop

const val FILME_BACKDROP = "extra_filme_backdrop"
const val FILME_POSTER = "extra_filme_poster"
const val FILME_TITULO = "extra_filme_titulo"
const val FILME_RATING = "extra_filme_rating"
const val FILME_DATA = "extra_filme_data"
const val FILME_OVERVIEW = "extra_filme_overview"

class DetalheFilmeActivity : AppCompatActivity() {

    private lateinit var backdrop: ImageView
    private lateinit var poster: ImageView
    private lateinit var titulo: TextView
    private lateinit var rating: RatingBar
    private lateinit var data: TextView
    private lateinit var overview: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_filme)

        backdrop = findViewById(R.id.filme_backdrop)
        poster = findViewById(R.id.filme_poster)
        titulo = findViewById(R.id.filme_titulo)
        rating = findViewById(R.id.filme_rating)
        data = findViewById(R.id.filme_data)
        overview = findViewById(R.id.filme_overview)

        val extras = intent.extras
        if (extras != null) {
            popularDetalhes(extras)
        } else {
            finish()
        }
    }

    private fun popularDetalhes(extras: Bundle) {
        extras.getString(FILME_BACKDROP)?.let { backdropPath ->
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w1280$backdropPath")
                .transform(CenterCrop())
                .into(backdrop)
        }

        extras.getString(FILME_POSTER)?.let { posterPath ->
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w342$posterPath")
                .transform(CenterCrop())
                .into(poster)
        }

        titulo.text = extras.getString(FILME_TITULO, "")
        rating.rating = extras.getFloat(FILME_RATING, 0f) / 2
        data.text = extras.getString(FILME_DATA, "")
        overview.text = extras.getString(FILME_OVERVIEW, "")
    }
}