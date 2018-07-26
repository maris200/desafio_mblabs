package br.com.mblabs.desafio.view.details

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.mblabs.desafio.R

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val url = intent.getStringExtra("url")
        val currentPrice = intent.getStringExtra("currentPrice")
        val oldPrice = intent.getStringExtra("oldPrice")
        val name = intent.getStringExtra("name")
        val id = intent.getStringExtra("id")

    }
}
