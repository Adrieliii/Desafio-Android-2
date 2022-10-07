package br.com.alura.api2.ui

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import br.com.alura.api2.R
import br.com.alura.api2.ui.data.database.AppDatabase
import br.com.alura.api2.ui.data.model.FilmeEntidade
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop

const val FILME_BACKDROP = "extra_filme_backdrop"
const val FILME_POSTER = "extra_filme_poster"
const val FILME_TITULO = "extra_filme_titulo"
const val FILME_RATING = "extra_filme_rating"
const val FILME_DATA = "extra_filme_data"
const val FILME_OVERVIEW = "extra_filme_overview"
const val FILME_ID = "extra_filme_id"

class DetalheFilmeActivity : AppCompatActivity() {

    private lateinit var backdrop: ImageView
    private lateinit var poster: ImageView
    private lateinit var titulo: TextView
    private lateinit var rating: RatingBar
    private lateinit var data: TextView
    private lateinit var overview: TextView
    private lateinit var adicionaFavoritos: Button

    private var filmeId = 0L
    private var filmeBackdrop = ""
    private var filmePoster = ""
    private var filmeTitulo = ""
    private var filmeRating = 0f
    private var filmeData = ""
    private var filmeOverview = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_filme)

        backdrop = findViewById(R.id.filme_backdrop)
        poster = findViewById(R.id.filme_poster)
        titulo = findViewById(R.id.filme_titulo)
        rating = findViewById(R.id.filme_rating)
        data = findViewById(R.id.filme_data)
        overview = findViewById(R.id.filme_overview)
        adicionaFavoritos = findViewById(R.id.adiciona_favoritos)

        val extras = intent.extras
        if (extras != null) {
            popularDetalhes(extras)
        } else {
            finish()
        }
    }

    private val db: AppDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "meusfilmes.db"
        ).allowMainThreadQueries().build()
    }

    private fun getFilme(id: Long): FilmeEntidade? {
        return db.filmeDAO().findById(id)
    }

    private fun popularDetalhes(extras: Bundle) {
        filmeId = extras.getLong(FILME_ID)
        filmeBackdrop = extras.getString(FILME_BACKDROP, "")
        filmePoster = extras.getString(FILME_POSTER, "")
        filmeTitulo = extras.getString(FILME_TITULO, "")
        filmeRating = extras.getFloat(FILME_RATING, 0f)
        filmeData = extras.getString(FILME_DATA, "")
        filmeOverview = extras.getString(FILME_OVERVIEW, "")

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w1280$filmeBackdrop")
            .transform(CenterCrop())
            .into(backdrop)

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w342$filmePoster")
            .transform(CenterCrop())
            .into(poster)

        titulo.text = filmeTitulo
        rating.rating = filmeRating / 2
        data.text = filmeData
        overview.text = filmeOverview

        val filme = getFilme(filmeId)
        if (filme == null) {
            adicionaFavoritos.text = getString(R.string.adiciona_favoritos)
        } else {
            adicionaFavoritos.text = getString(R.string.remove_favoritos)
        }
    }

    override fun onStart() {
        super.onStart()
        adicionaFavoritos.setOnClickListener {
            if (getFilme(filmeId) == null) {
                val entity = FilmeEntidade(
                    filmeId,
                    filmeTitulo,
                    filmeOverview,
                    filmePoster,
                    filmeBackdrop,
                    filmeRating,
                    filmeData
                )
                db.filmeDAO().insert(entity)
                adicionaFavoritos.text = getString(R.string.remove_favoritos)
            } else {
                db.filmeDAO().delete(filmeId)
                adicionaFavoritos.text = getString(R.string.adiciona_favoritos)
            }
        }
    }
}