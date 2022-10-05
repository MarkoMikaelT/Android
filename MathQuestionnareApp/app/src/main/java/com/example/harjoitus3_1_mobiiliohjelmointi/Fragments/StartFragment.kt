package com.example.harjoitus3_1_mobiiliohjelmointi.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.harjoitus3_1_mobiiliohjelmointi.R
import com.example.harjoitus3_1_mobiiliohjelmointi.databinding.FragmentStartBinding
import androidx.navigation.fragment.findNavController as findNavController


class StartFragment : Fragment() {
    private lateinit var bind: FragmentStartBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bind = DataBindingUtil.inflate(
            inflater, R.layout.fragment_start, container, false
        )
        bind.playButton.setOnClickListener {changeExercise()}
        return bind.root
    }

    private fun changeExercise(){
        var numExercise: Int = 0
        val id: Int = bind.exerciseRadioGroup.checkedRadioButtonId
        when(id){
            bind.addRadioButton.id ->{
                numExercise = 1 //addition exercise
            }
            bind.multRadioButton.id ->{
                numExercise = 2 //multiplication exercise
            }
            bind.divRadioButton.id ->{
                numExercise = 3 //division exercise

            }
            else ->{
                Toast.makeText(context, "Select exercise!", Toast.LENGTH_SHORT).show()
            }
        }
        if(id != -1){
            findNavController().navigate(StartFragmentDirections.actionStartFragmentToGameFragment(
                "numEx",
                numExercise))
        }

    }

}