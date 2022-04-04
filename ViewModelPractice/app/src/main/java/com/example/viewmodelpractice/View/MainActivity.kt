package com.example.viewmodelpractice.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.viewmodelpractice.Data.Game
import com.example.viewmodelpractice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){

    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.start.setOnClickListener {
            val score = binding.score.text.toString()
            val set = binding.set.text.toString()
            val game = Game(set.toInt(), score.toInt())

            val intent = Intent(this,ScoreActivity::class.java)
            intent.putExtra("game",game)
            startActivity(intent);
        }
    }

}