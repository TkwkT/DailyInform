package com.example.dailyinform

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.dailyinform.databinding.ActivityDetailBinding
import com.example.dailyinform.viewmodel.WebViewDetailViewModel

class DetailActivity : BaseActivity() {

    private lateinit var viewModel: WebViewDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val url = intent.getStringExtra("detail_url")
        val title = intent.getStringExtra("detail_title")
        val binding = DataBindingUtil.setContentView<ActivityDetailBinding>(this,R.layout.activity_detail)
        if (!url.isNullOrEmpty() && !title.isNullOrEmpty()){
            init(url, title,binding)
        }
        canSlideFinish(true)
    }

    private fun init(url: String, title: String,binding:ActivityDetailBinding) {
        viewModel = ViewModelProviders.of(this).get(WebViewDetailViewModel::class.java)
        binding.viewmodel = viewModel
        val webView = binding.webviewDetail
        viewModel.setWebView(webView,url)
    }
}
