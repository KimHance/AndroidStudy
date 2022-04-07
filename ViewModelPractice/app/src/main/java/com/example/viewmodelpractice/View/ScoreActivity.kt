package com.example.viewmodelpractice.View

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodelpractice.Data.Game
import com.example.viewmodelpractice.ViewModel.ScoreViewModel
import com.example.viewmodelpractice.databinding.ActivityScoreBinding

class ScoreActivity :AppCompatActivity(){

    private var _binding : ActivityScoreBinding?= null
    private val binding get() = _binding!!
    lateinit var scoreViewModel: ScoreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 프로바이더를 통해 뷰모델 가져오기
        scoreViewModel = ViewModelProvider(this).get(ScoreViewModel::class.java)

        val game = intent.getSerializableExtra("game") as Game


        // 세트수 0을 초기화, 내기 점수 표시
        binding.textSet.text = scoreViewModel.getSet().toString()
        binding.textScore.text = game.score.toString()

        // 각팀 점수/세트 표시
        binding.scoreJangun.text = scoreViewModel.getJangun().toString()
        binding.scoreMungun.text = scoreViewModel.getMungun().toString()
        binding.setScoreJangun.text = scoreViewModel.getJangunSet().toString()
        binding.setScoreMungun.text = scoreViewModel.getMungunSet().toString()


        // 장군 +1
        binding.btnPlusJangun.setOnClickListener {
            scoreViewModel.jangunPlus()
        }
        // 멍군 +1
        binding.btnPlusMungun.setOnClickListener {
            scoreViewModel.mungunPlus()
        }
        // 장군 -1
        binding.btnMinusJangun.setOnClickListener {
            scoreViewModel.jangunMinus()
        }
        // 멍군 -1
        binding.btnMinusMungun.setOnClickListener {
            scoreViewModel.mungunMinus()
        }
        // 장군 세트 +1
        binding.btnJangunSet.setOnClickListener {
            scoreViewModel.jangunSetPlus()
        }
        // 멍군 세트 +1
        binding.btnMungunSet.setOnClickListener {
            scoreViewModel.mungunSetPlus()
        }

        // 롤백
        binding.btnRollback.setOnClickListener {
            scoreViewModel.rollback()
        }


        // 점수 관찰
        scoreViewModel.getJangun().observe(this, Observer {
            Log.d("Score","장군 변화 감지")
            if(it == game.score){
                Log.d("Score","장군 승리")
                scoreViewModel.scoreRollback()
                scoreViewModel.jangunSetPlus()
                binding.scoreJangun.text= it.toString()
            }else{
                binding.scoreJangun.text= it.toString()
            }
        })

        scoreViewModel.getMungun().observe(this, Observer {
            Log.d("Score","멍군 변화 감지")
            if(it == game.score){
                Log.d("Score","멍군 승리")
                scoreViewModel.scoreRollback()
                scoreViewModel.mungunSetPlus()
                binding.scoreMungun.text= it.toString()
            }else{
                binding.scoreMungun.text= it.toString()
            }
        })

        // 세트 스코어 관찰
        scoreViewModel.getJangunSet().observe(this, Observer {
            Log.d("Set","장군 변화 감지")
            if(it == game.set){
                val intent = Intent(this,VictoryActivity::class.java)
                intent.putExtra("winner","장군팀")
                startActivity(intent)
                finish()
            }
            binding.setScoreJangun.text = it.toString()
        })
        scoreViewModel.getMungunSet().observe(this, Observer {
            if(it == game.set){
                val intent = Intent(this,VictoryActivity::class.java)
                intent.putExtra("winner","멍군팀")
                startActivity(intent)
                finish()
            }
            Log.d("Set","멍군 변화 감지")
            binding.setScoreMungun.text = it.toString()
        })
        scoreViewModel.getSet().observe(this,Observer{
            Log.d("Set","세트수 변화 감지")
            binding.textSet.text = it.toString()
        })

    }


}