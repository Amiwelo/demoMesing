package com.example.demomesing.features.home

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.example.demomesing.R
import com.example.demomesing.data.session.ShPreference
import com.example.demomesing.di.Injection
import com.example.demomesing.model.Collection
import com.example.demomesing.model.Main
import com.gauravk.bubblenavigation.BubbleNavigationLinearView
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener

class HomeActivity : AppCompatActivity() {

    private lateinit var viewModel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initApp()
        viewModel.responseBody.observe(this, response )

    }

    private fun initApp(){
        val shPreference = ShPreference(getSharedPreferences(ShPreference.PREFERENCE_NAME, Context.MODE_PRIVATE),this)
        viewModel = ViewModelProviders.of(this, HomeViewModelFactory(
            Injection.getHome(),
            ShPreference(getSharedPreferences(
                ShPreference.PREFERENCE_NAME,
                Context.MODE_PRIVATE
            ),this)
        )).get(HomeViewModel::class.java)
        viewModel.launchMain(2,shPreference.user!!.id_rol,shPreference.user!!.id_per)

    }
    private fun paintMain(item: List<Main>){
        val list = ArrayList<ScreenSlidePageFragment>()
        for(it in item){
            list.add(ScreenSlidePageFragment.newInstance(it))
        }

        val screenSlidePagerAdapter = ScreenSlidePagerAdapter(list, supportFragmentManager)

        val bubble=findViewById<BubbleNavigationLinearView>(R.id.bottom_navigation_view_linear)
        bubble.setTypeface(Typeface.createFromAsset(assets, "rubik.ttf"))
        val viewPager = findViewById<ViewPager>(R.id.view_pager)

        viewPager.adapter = screenSlidePagerAdapter
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                bubble.setCurrentActiveItem(position)
            }

        })
        bubble.setNavigationChangeListener { _, position -> viewPager.setCurrentItem(position, true) }
    }
    private val response = Observer<Collection>{
        paintMain(it.collection)
    }
}
