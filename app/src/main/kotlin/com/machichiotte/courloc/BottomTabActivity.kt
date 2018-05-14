package com.machichiotte.courloc

import android.app.Fragment
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_bottom_tab.*

class BottomTabActivity : AppCompatActivity() {
    private val fragmentProfil = FragmentProfil()
    private val fragmentList = FragmentList()
    private val fragmentMemo = FragmentMemo()
    private val fragmentGroupe = FragmentGroupe()
    private val fragmentMaison = FragmentMaison()
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_memo -> {
                moveFragment(fragmentMemo, "Memo")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_home -> {
                moveFragment(fragmentMaison, "Maison")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_group -> {
                moveFragment(fragmentGroupe, "Groupe")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_list -> {
                moveFragment(fragmentList, "List")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_profil -> {
                moveFragment(fragmentProfil, "Profil")
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    fun moveFragment(fragment: Fragment, fragmentname: String) {
        val fragmentTransaction = fragmentManager
                .beginTransaction()
        fragmentTransaction.replace(R.id.content_frame,
                fragment)
        fragmentTransaction.commitAllowingStateLoss()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_tab)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.menu.getItem(0).setChecked(true) }
}
