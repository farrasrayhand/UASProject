package com.laskarlembur.project

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class Person1Activity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var logoutbutton : Button
    private lateinit var toolbar: Toolbar
    private lateinit var nav_bottom : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person1)

        val twitter = findViewById(R.id.twitter) as ImageView
        twitter.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/elonmusk"))
            startActivity(i)
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
        // Handle action bar item clicks here.
        val id = item.getItemId()

        if (id == R.id.menu_home) {
            Intent(this@Person1Activity, HomeActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
            return true
        }

        if (id == R.id.menu_tentang) {
            Intent(this@Person1Activity, AboutActivity::class.java).also {
                startActivity(it)
            }
            return true
        }
        if (id == R.id.menu_logout) {
            auth.signOut()
            Intent(this@Person1Activity, LoginActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}