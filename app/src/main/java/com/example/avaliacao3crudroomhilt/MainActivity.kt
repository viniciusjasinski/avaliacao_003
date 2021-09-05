package com.example.avaliacao3crudroomhilt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.avaliacao3crudroomhilt.databinding.MainActivityBinding
import com.example.avaliacao3crudroomhilt.view.DoctorFragment
import com.example.avaliacao3crudroomhilt.view.PatientFragment
import com.example.avaliacao3crudroomhilt.view.ScheduleFragment
import com.example.avaliacao3crudroomhilt.view.SpecialtyFragment
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MainActivityBinding.inflate(layoutInflater)

        setContentView(binding.root)

        bottomNav()
        changeFrag(PatientFragment.newInstance(),"tag_patient")

    }

    private fun changeFrag(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment, tag)
            .commitNow()
    }

    private fun bottomNav() {
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.page_patients -> {
                    changeFrag(PatientFragment.newInstance(), "tag_patient")
                }
                R.id.page_specialty -> {
                    changeFrag(SpecialtyFragment.newInstance(), "tag_specialty")
                }
                R.id.page_doctor -> {
                    changeFrag(DoctorFragment.newInstance(), "tag_doctor")
                }
                R.id.page_schedule -> {
                    changeFrag(ScheduleFragment.newInstance(), "tag_schedule")
                }
            }
            true
        }
    }

}