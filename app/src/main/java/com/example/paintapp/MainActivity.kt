package com.example.paintapp

import android.app.Dialog
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.paintapp.paintView.Companion.brushsize
import com.example.paintapp.paintView.Companion.colorlist
import com.example.paintapp.paintView.Companion.currentbrush
import com.example.paintapp.paintView.Companion.pathlist
import com.example.paintapp.paintView.Companion.sizelist

class MainActivity : AppCompatActivity() {
    companion object {
        var path = Path()
        var paintbrush = Paint()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val redbtn = findViewById<ImageButton>(R.id.redcolor)
        val bluebtn = findViewById<ImageButton>(R.id.bluecolor)
        val greenbtn = findViewById<ImageButton>(R.id.greencolor)
        val blackbtn = findViewById<ImageButton>(R.id.blackcolor)
        val yellowbtn = findViewById<ImageButton>(R.id.yellowcolor)
        val penbtn = findViewById<ImageButton>(R.id.penbtn)
        val deletebtn = findViewById<ImageButton>(R.id.btndelete)

        val eraserbtn = findViewById<ImageButton>(R.id.eraser)
        redbtn.setOnClickListener {
            Toast.makeText(this, "Red color selected", Toast.LENGTH_SHORT).show()
            paintbrush.color = Color.RED
            currentcolor(paintbrush.color)
        }
        bluebtn.setOnClickListener {
            Toast.makeText(this, "Blue color selected", Toast.LENGTH_SHORT).show()
            updateBrushSize(18f)
            paintbrush.color = Color.BLUE
            currentcolor(paintbrush.color)
        }
        greenbtn.setOnClickListener {
            Toast.makeText(this, "Green color selected", Toast.LENGTH_SHORT).show()
            updateBrushSize(18f)
            paintbrush.color = Color.GREEN
            currentcolor(paintbrush.color)
        }
        blackbtn.setOnClickListener {
            Toast.makeText(this, "Black color selected", Toast.LENGTH_SHORT).show()
            updateBrushSize(18f)
            paintbrush.color = Color.BLACK
            currentcolor(paintbrush.color)
        }
        yellowbtn.setOnClickListener{
            Toast.makeText(this, "Yellow color selected", Toast.LENGTH_SHORT).show()
            updateBrushSize(18f)
            paintbrush.color = Color.YELLOW
            currentcolor(paintbrush.color)
        }
        deletebtn.setOnClickListener {
            Toast.makeText(this, "Erase whole", Toast.LENGTH_SHORT).show()

            pathlist.clear()
            colorlist.clear()
            sizelist.clear()
            path = Path()
        }
        eraserbtn.setOnClickListener{
            paintbrush.color = Color.WHITE
            currentcolor(paintbrush.color)
            updateBrushSize(55f)

        }


        penbtn.setOnClickListener { showOptionsDialog() }


    }

    private fun currentcolor(color: Int) {
        currentbrush = color
        path = Path()
    }

    private fun showOptionsDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.pensize_options)

        val option1: Button = dialog.findViewById(R.id.option1)
        val option2: Button = dialog.findViewById(R.id.option2)
        val option3: Button = dialog.findViewById(R.id.option3)

        option1.setOnClickListener {
            updateBrushSize(18f)
            dialog.dismiss()
        }

        option2.setOnClickListener {
            updateBrushSize(28f)
            dialog.dismiss()
        }

        option3.setOnClickListener {
            updateBrushSize(38f)
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun updateBrushSize(size: Float) {
        brushsize = size
        paintbrush.strokeWidth = size
        path = Path()
    }

}
