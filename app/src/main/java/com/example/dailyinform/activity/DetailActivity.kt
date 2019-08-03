package com.example.dailyinform.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.dailyinform.R
import com.example.dailyinform.bean.CollectionDetailBean
import com.example.dailyinform.databinding.ActivityDetailBinding
import com.example.dailyinform.utils.InjectorUtils
import com.example.dailyinform.viewmodel.DetailActivityViewModel

class DetailActivity : BaseActivity() {
    private var url: String? = ""
    private var title: String? = ""
    private var cover: String? = ""
    private var collectionDetailBean:CollectionDetailBean? = null
    private lateinit var viewModel: DetailActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        url = intent.getStringExtra("detail_url")
        title = intent.getStringExtra("detail_title")
        cover = intent.getStringExtra("detail_cover")
        canSlideFinish(true)
        val binding = DataBindingUtil.setContentView<ActivityDetailBinding>(this, R.layout.activity_detail)

        if (!url.isNullOrEmpty() && !title.isNullOrEmpty()) {
            collectionDetailBean = CollectionDetailBean(cover,title!!,url!!)
            init(binding)
        }
    }

    private fun init(binding: ActivityDetailBinding) {
        val factory = InjectorUtils.provideDetailActivityViewModelFactory(this)
        viewModel = ViewModelProviders.of(this, factory).get(DetailActivityViewModel::class.java)

        binding.viewmodel = viewModel
        val webView = binding.webviewDetail
        if (collectionDetailBean != null){
            viewModel.setWebView(webView, collectionDetailBean!!.url)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflate = menuInflater
        menuInflate.inflate(R.menu.detail_activity_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.collection -> {
                if (collectionDetailBean != null)
                    Log.d("aaa","$collectionDetailBean")
                    viewModel.insertCollectionDetail(collectionDetailBean!!)
            }
            R.id.open_on_other -> openOnOther()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun openOnOther() {
        if (url.isNullOrEmpty()) {
            return
        }
        val intent = Intent()
        intent.action = ("android.intent.action.VIEW")
        val contentUrl = Uri.parse(url)
        intent.data = contentUrl
        startActivity(intent)
    }


}
