package com.example.demomesing.features.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.demomesing.R
import com.example.demomesing.data.session.ShPreference
import com.example.demomesing.model.Main
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

import kotlinx.android.synthetic.main.fragment_screen_slide_page.*
import kotlinx.android.synthetic.main.fragment_screen_slide_page.screen_slide_title
import kotlinx.android.synthetic.main.fragment_screen_slide_profile.*

private const val ARG_TITLE = "arg_title"
private const val ARG_BG_COLOR = "arg_bg_color"
private const val ARG_ID_MAIN = "arg_id_main"

/**
 * A simple [Fragment] subclass.
 * Use the [ScreenSlidePageFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ScreenSlidePageFragment : Fragment() {
    private var title: String? = "Default title."
    private var bgColorResId: Int = R.color.blue_inactive
    private lateinit var inflatedView: View
    private var idMain: String? = "IDMAIN"
    //private lateinit var shPreference : ShPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            title = it.getString(ARG_TITLE)
            bgColorResId = it.getInt(ARG_BG_COLOR)
            idMain = it.getString(ARG_ID_MAIN)
            Log.i("IDMAIN", idMain.toString())
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        inflatedView = inflater.inflate(R.layout.fragment_screen_slide_profile, container, false)
        return inflatedView
    }
    private fun selectFragment(position: Int):Int{
        var int = 0
        when(position){
            4-> R.layout.fragment_screen_slide_profile
            else -> {
                R.layout.fragment_screen_slide_page
            }
        }
        return int
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val shPreference = ShPreference(
            context!!.getSharedPreferences(
                ShPreference.PREFERENCE_NAME,
                Context.MODE_PRIVATE
            ), context!!.applicationContext
        )
        val user = shPreference.user

        if (shPreference.user != null) {
            Picasso.get().load(user!!.img).placeholder(R.color.colorAccent)
                .into(screen_slide_img_user)
            screen_slide_tv_name_user.text = user.nom_usu
            screen_slide_tv_user.text = user.log_usu
        }
        inflatedView.setBackgroundColor(ContextCompat.getColor(context!!, bgColorResId))
        screen_slide_title.text = title
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param main object parameter
         * //@param bgColorId Background Color
         * @return A new instance of fragment ScreenSlidePageFragment.
         */
        @JvmStatic
        fun newInstance(main: Main) =
            ScreenSlidePageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_TITLE, main.nomMain)
                    putInt(ARG_BG_COLOR, selectColor(main.idMain!!.toInt()))
                    putString(ARG_ID_MAIN, main.idMain)
                }
            }

        private fun selectColor(int: Int): Int {
            var color = 0
            when (int) {
                1 -> color = R.color.red_active
                2 -> color = R.color.orange_inactive
                3 -> color = R.color.purple_active
                4 -> color = R.color.red_inactive
                5 -> color = R.color.green_active
                6 -> color = R.color.blue_inactive
                7 -> color = R.color.green_active
                8 -> color = R.color.blue_inactive
                13 -> color = R.color.green_active
                20 -> color = R.color.blue_inactive
                21 -> color = R.color.blue_inactive
                22 -> color = R.color.green_active
                23 -> color = R.color.blue_inactive
            }
            return color
        }
    }
}
