package com.example.domasna1

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etSearch = findViewById<TextInputEditText>(R.id.etSearchQuery)
        val etTag = findViewById<TextInputEditText>(R.id.etTag)
        val btnSave = findViewById<MaterialButton>(R.id.btnSave)
        val btnClear = findViewById<MaterialButton>(R.id.btnClear)
        val container = findViewById<LinearLayout>(R.id.tagsContainer)

        btnSave.setOnClickListener {
            val tagText = etTag.text.toString()
            if (tagText.isNotEmpty()) {
                // Додавање на нов ред со модерен дизајн
                addNewTagRow(container, tagText)

                etTag.text?.clear()
                etSearch.text?.clear()
            } else {
                Toast.makeText(this, "Please enter a tag!", Toast.LENGTH_SHORT).show()
            }
        }

        btnClear.setOnClickListener {
            container.removeAllViews()
        }
    }

    private fun addNewTagRow(container: LinearLayout, tagName: String) {
        val row = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            val lp = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            lp.setMargins(0, 0, 0, 12)
            layoutParams = lp
        }

        // Tonal Button изглед (заоблен како пилула)
        val tagBtn = MaterialButton(this).apply {
            layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
            text = tagName
            setBackgroundColor(resources.getColor(R.color.m3_primary_container, null))
            setTextColor(resources.getColor(R.color.m3_primary, null))
            cornerRadius = 28 // Material 3 "Pill" форма
            isAllCaps = false
        }

        // Outlined Button изглед
        val editBtn = MaterialButton(this, null, com.google.android.material.R.attr.materialButtonOutlinedStyle).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            text = "Edit"
            isAllCaps = false
            (layoutParams as LinearLayout.LayoutParams).marginStart = 8
        }

        row.addView(tagBtn)
        row.addView(editBtn)
        container.addView(row)
    }
}