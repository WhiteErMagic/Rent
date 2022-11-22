package com.works.renta.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.works.renta.R
import com.works.renta.TheApp
import com.works.renta.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity() , Navigator {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun goToNext(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(binding.fragmentContainer.id, fragment)
            .commit()
    }

    override fun goToBack() {
        supportFragmentManager.popBackStack()
    }
}