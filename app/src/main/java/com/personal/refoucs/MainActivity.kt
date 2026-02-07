package com.personal.refoucs

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toggle = findViewById<CompoundButton>(R.id.toggle)
        val accessibilityBtn = findViewById<Button>(R.id.accessibilityBtn)

        toggle.isChecked = RefocusDataStore.isBlockingEnabled(this)

        toggle.setOnCheckedChangeListener { _, enabled ->
            lifecycleScope.launch {
                RefocusDataStore.setBlockingEnabled(this@MainActivity, enabled)
            }
        }

        accessibilityBtn.setOnClickListener {
            startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
        }
    }
}
