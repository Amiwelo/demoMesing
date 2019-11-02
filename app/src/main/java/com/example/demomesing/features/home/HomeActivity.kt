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
        viewModel = ViewModelProviders.of(this, HomeViewModelFactory(
            Injection.getHome(),
            ShPreference(getSharedPreferences(
                ShPreference.PREFERENCE_NAME,
                Context.MODE_PRIVATE
            ),this)
        )).get(HomeViewModel::class.java)
        viewModel.launchMain(2,1,1)

    }
    private fun paintMain(item: List<Main>){
        val list = ArrayList<ScreenSlidePageFragment>()
        for(it in item){
            list.add(ScreenSlidePageFragment.newInstance(it.nomMain,
                selectColor(it.idMain.toInt())))
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
    private fun selectColor(int: Int): Int{
        var color = 0
        when(int){
            1 -> color = R.color.red_active
            2 -> color = R.color.orange_inactive
            3 -> color = R.color.purple_active
            4 -> color = R.color.red_inactive
            5 -> color = R.color.green_active
            6 -> color = R.color.blue_inactive
        }
        return color
    }
}
