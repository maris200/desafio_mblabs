package br.com.mblabs.desafio.view.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.persistence.room.Room
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import br.com.mblabs.desafio.R
import br.com.mblabs.desafio.data.local.room.database.AppDatabase
import br.com.mblabs.desafio.data.remote.model.entity.item
import br.com.mblabs.desafio.databinding.ActivityMainBinding
import br.com.mblabs.desafio.util.Constants
import br.com.mblabs.desafio.view.details.DetailsActivity
import br.com.mblabs.desafio.view.main.adapter.MainAdapter
import br.com.mblabs.desafio.viewmodel.MainViewModel
import com.ethanhua.skeleton.Skeleton


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MainAdapter
    private lateinit var rvItem: RecyclerView
    private lateinit var appDatabase: AppDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        bindView()
    }

    private fun bindView() {

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        setupRecyclerView()
        createDatabaseRoom()
        setObservers()
        LoadRecyclerView()
    }

    private fun createDatabaseRoom() {

        appDatabase = Room
                .databaseBuilder(applicationContext, AppDatabase::class.java, "production")
                .allowMainThreadQueries()
                .build()

    }

    private fun setupRecyclerView() {

        rvItem = binding.rvItem
        rvItem.setItemViewCacheSize(30)
        rvItem.setHasFixedSize(true)
        rvItem.itemAnimator = DefaultItemAnimator()
        rvItem.layoutManager = LinearLayoutManager(this)

    }

    private fun setObservers() {

        viewModel.loadData()
        viewModel.item.observe(this, Observer { item ->
            saveDataRoom(item)

        })

        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            if (!TextUtils.isEmpty(errorMessage)) {
                Snackbar.make(binding.root, errorMessage!!, Snackbar.LENGTH_LONG).show()
            }
        })

    }

    private fun saveDataRoom(item: item?) {

        appDatabase.ItensDao().add(item!!.itemList)

    }

    private fun LoadRecyclerView() {

        adapter = MainAdapter(appDatabase.ItensDao().all(), this@MainActivity)
        rvItem.adapter = adapter

        val skeletonScreen = Skeleton
                .bind(binding.rvItem)
                .adapter(adapter)
                .shimmer(true)
                .angle(10)
                .frozen(false)
                .duration(800)
                .count(10)
                .load(R.layout.list_main_load)
                .show()

        binding.rvItem.postDelayed({ skeletonScreen.hide() }, Constants.Skeleton.TIME_MILLIS)

    }

    fun openItemDetails(url: String?, currentPrice: Double?, oldPrice: Double?, name: String?, id: Int?) {

        val intent = Intent(this@MainActivity, DetailsActivity::class.java)

        intent.putExtra("url", url)
        intent.putExtra("currentPrice", currentPrice)
        intent.putExtra("oldPrice", oldPrice)
        intent.putExtra("name", name)
        intent.putExtra("id", id)

        startActivity(intent)


    }


    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }

}
