package com.example.demomesing.features.home

import android.content.ClipData
import android.content.Context
import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import com.example.demomesing.R
import com.example.demomesing.data.session.ShPreference
import com.example.demomesing.di.Injection
import com.example.demomesing.model.Collection
import com.example.demomesing.model.Main
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.nav_header_menu.*
import java.lang.reflect.Array

class HomeActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var viewModel: HomeViewModel
    private lateinit var shPreference: ShPreference
    private lateinit var navView: NavigationView
    private lateinit var navController: NavController
    private lateinit var menu: Menu
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        navController  = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        menu = navView.menu
        initApp()
    }
    private fun paintMain(item: List<Main>){

    }

    private fun initApp(){
        viewModel = ViewModelProviders.of(this, HomeViewModelFactory(Injection.getHome(),
            ShPreference(getSharedPreferences(
                ShPreference.PREFERENCE_NAME,
                Context.MODE_PRIVATE
            ),this))).get(HomeViewModel::class.java)
        viewModel.launchMain(1,1,1)
        viewModel.responseBody.observe(this, response )
    }

    private val response = Observer<Collection>{
        val menu = it.collection
        paintMain(menu)
    }

    private fun initProfile(){
        shPreference = ShPreference(getSharedPreferences(ShPreference.PREFERENCE_NAME, Context.MODE_PRIVATE), this)
        Log.i("USER", ""+shPreference.user!!.email)
        if(shPreference.user == null){
            Log.i("Info", "sin datos")
        } else {
            val user = shPreference.user
            tv_email_slide.text = user?.email
            tv_name_user_slide.text = user?.nameUser
            Picasso.get().load(user?.avatar).placeholder(R.color.colorPrimary).into(img_profile_slide)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        initProfile()
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
