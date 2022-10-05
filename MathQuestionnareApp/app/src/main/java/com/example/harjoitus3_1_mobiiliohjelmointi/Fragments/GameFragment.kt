package com.example.harjoitus3_1_mobiiliohjelmointi.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.harjoitus3_1_mobiiliohjelmointi.R
import com.example.harjoitus3_1_mobiiliohjelmointi.TestTimer
import com.example.harjoitus3_1_mobiiliohjelmointi.databinding.FragmentGameBinding
import timber.log.Timber
import kotlin.math.floor
import kotlin.random.Random

const val KEY_QUESTION = "key_question"
const val KEY_QUESTIONCOUNT = "key_questionCount"
const val KEY_QUESTIONCORRECT = "key_questionsCorrect"
const val KEY_CORRECTANSWER = "key_correctAnswer"
const val KEY_SECONDS = "key_seconds"

class GameFragment : Fragment() {
    private lateinit var bind: FragmentGameBinding
    private  lateinit var answered: TextView
    private lateinit var question : TextView
    private lateinit var inputText: EditText
    private lateinit var updatetimer: TextView

    private var curQuestion = ""
    private var time = 0
    private var correctAnswer = 0
    private var questionsCorrect = 0
    private var questionCount = 0
    private var setEx = 0

    private var testTimer: TestTimer = TestTimer(lifecycle, this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (savedInstanceState != null){
            curQuestion = savedInstanceState.getString(KEY_QUESTION, "")
            questionCount = savedInstanceState.getInt(KEY_QUESTIONCOUNT)
            questionsCorrect = savedInstanceState.getInt(KEY_QUESTIONCORRECT)
            correctAnswer = savedInstanceState.getInt(KEY_CORRECTANSWER)
            testTimer.secondsCount = savedInstanceState.getInt(KEY_SECONDS)
        }
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_game, container, false)
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)
        bind.submitButton.setOnClickListener { checkAnswer() }
        updatetimer = bind.timerTextView

        var args =
            GameFragmentArgs.fromBundle(requireArguments())
        setEx = args.numEx
        question = bind.calculationTextView
        answered = bind.exerciseCountTextView
        inputText = bind.inputAnswerTextNumber


        question.text = curQuestion
        answered.text = "$questionCount / 10"

        if(curQuestion == "") {
            chooseEx(setEx)
            testTimer.startTimer()
        }

        return bind.root
    }


    private fun chooseEx(num: Int){
        when(num){
            1 -> additionEx()
            2 -> multiplicationEx()
            3 -> divisionEx()
        }
    }

    private fun additionEx(){
        val rndNum = List(2){Random.nextInt(1, 10)}
        correctAnswer = rndNum[0] + rndNum[1]
        curQuestion = "${rndNum[0]} + ${rndNum[1]}"
        question.text = curQuestion
    }

    private fun multiplicationEx(){
        val rndNum = List(2){Random.nextInt(1, 10)}
        correctAnswer = rndNum[0] * rndNum[1]
        curQuestion = "${rndNum[0]} * ${rndNum[1]}"
        question.text = curQuestion
    }

    private fun divisionEx(){
        val rndNum = List(2){Random.nextInt(1, 10)}
        correctAnswer = floor((rndNum[0] / rndNum[1]).toDouble()).toInt()
        curQuestion = "${rndNum[0]} : ${rndNum[1]}"
        question.text = curQuestion
    }

    private fun checkAnswer(){
        Timber.i("CORRECT = $correctAnswer")
        if(inputText.text.toString().isEmpty()){
            //error check if empty
        }
        else{
            questionCount++
            answered.text = "$questionCount / 10"
            if(correctAnswer == inputText.text.toString().toInt()){
                questionsCorrect++
            }
            else{
                //skip points when wrong
            }

            if(questionCount >= 10){
                testTimer.stopTimer()
                time = testTimer.secondsCount
                findNavController().navigate(
                    GameFragmentDirections.actionGameFragmentToEndFragment(questionsCorrect, time))
            }
            chooseEx(setEx)
        }
    }

    fun updateTimer(seconds: Int){
        updatetimer.text = "TIME: ${seconds}s"
    }

    override fun onResume() {
        super.onResume()
        testTimer.startTimer()
    }

    override fun onPause() {
        testTimer.stopTimer()
        super.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY_QUESTION, curQuestion)
        outState.putInt(KEY_QUESTIONCOUNT, questionCount)
        outState.putInt(KEY_QUESTIONCORRECT, questionsCorrect)
        outState.putInt(KEY_CORRECTANSWER, correctAnswer)
        outState.putInt(KEY_SECONDS, testTimer.secondsCount)
    }

}