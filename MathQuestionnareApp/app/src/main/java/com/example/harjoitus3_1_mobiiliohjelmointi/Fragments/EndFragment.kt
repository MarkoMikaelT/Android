package com.example.harjoitus3_1_mobiiliohjelmointi.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.harjoitus3_1_mobiiliohjelmointi.R
import com.example.harjoitus3_1_mobiiliohjelmointi.databinding.FragmentEndBinding
import androidx.navigation.fragment.findNavController as findNavController



class EndFragment : Fragment() {
    private lateinit var bind: FragmentEndBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_end, container, false)

        var args =
            EndFragmentArgs.fromBundle(requireArguments())
        bind.resultTextView.text = "${args.cCount} / 10 correct!"
        bind.timeTextView.text = "Time spent: ${args.numTime}s"

        bind.toStartbutton.setOnClickListener { findNavController().navigate(EndFragmentDirections.actionEndFragmentToStartFragment()) }
        return bind.root
    }


}