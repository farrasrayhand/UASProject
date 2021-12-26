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

class HomeActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var toolbar: Toolbar
    private lateinit var nav_bottom : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        var actionBar = getSupportActionBar()
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
        }

        val person1 = findViewById(R.id.person1) as ShapeableImageView
        person1.setOnClickListener {
            Intent(this@HomeActivity, Person1Activity::class.java).also {
                startActivity(it)
            }
        }

        val person2 = findViewById(R.id.person2) as ShapeableImageView
        person2.setOnClickListener {
            Intent(this@HomeActivity, Person2Activity::class.java).also {
                startActivity(it)
            }
        }

        val person3 = findViewById(R.id.person3) as ShapeableImageView
        person3.setOnClickListener {
            Intent(this@HomeActivity, Person3Activity::class.java).also {
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
        // Handle action bar item clicks here.
        val id = item.getItemId()

        if (id == R.id.menu_home) {
            Intent(this@HomeActivity, HomeActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
            return true
        }

        if (id == R.id.menu_tentang) {
            Intent(this@HomeActivity, AboutActivity::class.java).also {
                startActivity(it)
            }
            return true
        }
        if (id == R.id.menu_logout) {
            auth.signOut()
            Intent(this@HomeActivity, LoginActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}