package com.example.calculatorapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import com.example.calculatorapp.databinding.ActivityMainBinding
import org.w3c.dom.Text

class MainActivity2 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main2)

        var historyText = intent.getStringArrayListExtra("historyText")

        findViewById<Button>(R.id.Back).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putStringArrayListExtra("historyText", historyText)
            startActivity(intent)
        }

        val arrayAdapter: ArrayAdapter<*>
        var listView = findViewById<ListView>(R.id.ListView)
        arrayAdapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, mutableListOf(historyText))
        listView.adapter = arrayAdapter
    }
}