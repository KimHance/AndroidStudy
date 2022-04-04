package com.example.viewmodelpractice.View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.viewmodelpractice.Data.Game
import com.example.viewmodelpractice.databinding.ActivityScoreBinding

class ScoreActivity :AppCompatActivity() {

    private var _binding : ActivityScoreBinding?= null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val game = intent.getSerializableExtra("game") as Game
        // databinding
        binding.game = game
    }
}