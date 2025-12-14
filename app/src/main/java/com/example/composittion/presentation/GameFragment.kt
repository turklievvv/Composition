package com.example.composittion.presentation

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.AndroidException
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.composittion.R
import com.example.composittion.databinding.FragmentGameBinding
import com.example.composittion.databinding.FragmentGameFinishedBinding
import com.example.composittion.domain.entity.GameResult
import com.example.composittion.domain.entity.GameSettings
import com.example.composittion.domain.entity.Level
class GameFragment : Fragment() {

    private val args by navArgs<GameFragmentArgs>()
    private val gameViewModelFactory by lazy {
        GameViewModelFactory(requireActivity().application, args.level)
    }
    private val viewModel: GameViewModel by lazy {
        ViewModelProvider(
            this, gameViewModelFactory
        )[GameViewModel::class.java]
    }

    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding == null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        observeViewModels()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding == null
    }

    private fun observeViewModels() {
        viewModel.gameResult.observe(viewLifecycleOwner) {
            launchGameFinishedFragment(it)
        }
    }

    private fun launchGameFinishedFragment(gameResult: GameResult) {
        findNavController().navigate(GameFragmentDirections.actionGameFragmentToGameFinishedFragment(gameResult))
    }



}