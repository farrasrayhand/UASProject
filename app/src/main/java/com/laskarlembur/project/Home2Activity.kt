package com.laskarlembur.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.imageview.ShapeableImageView
import com.google.firebase.auth.FirebaseAuth

class Home2Activity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var toolbar: Toolbar
    private lateinit var nav_bottom : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home2)

        var actionBar = getSupportActionBar()
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
        }

        val person1 = findViewById(R.id.person1) as ShapeableImageView
        person1.setOnClickListener {
            Intent(this@Home2Activity, Person1Activity::class.java).also {
                startActivity(it)
            }
        }

        auth = FirebaseAuth.getInstance()

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_logout ->{
                auth.signOut()
                Intent(this@Home2Activity, LoginActivity::class.java).also {
                    startActivity(it)
                }
                return true
            }
            else -> return true
        }
    }
}