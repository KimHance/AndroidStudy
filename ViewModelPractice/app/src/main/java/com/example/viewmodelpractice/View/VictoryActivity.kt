package com.example.viewmodelpractice.View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.viewmodelpractice.databinding.ActivityVictoryBinding

class VictoryActivity:AppCompatActivity() {
    private var _binding : ActivityVictoryBinding?= null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityVictoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val winner = intent.getStringExtra("winner")

        binding.winner.text="$winner 승리!"
    }
}