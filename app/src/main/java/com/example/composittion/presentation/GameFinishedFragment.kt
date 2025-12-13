package com.example.composittion.presentation

import android.app.FragmentManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.composittion.R
import com.example.composittion.databinding.FragmentChooseLevelBinding
import com.example.composittion.databinding.FragmentGameFinishedBinding
import com.example.composittion.domain.entity.GameResult

@Suppress("DEPRECATION")
class GameFinishedFragment : Fragment() {

    private val args by navArgs<GameFinishedFragmentArgs>()

    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("FragmentGameFinishedBinding == null")


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingViews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding == null
    }

    private fun bindingViews() {
        binding.buttonTryAgain.setOnClickListener {
            retryGame()
        }
        binding.finishText1.text = getString(
            R.string.finishText1,
            args.gameResult.gameSettings.minCountOfRightAnswers.toString()
        )
        binding.finishText2.text =
            getString(R.string.finishText2, args.gameResult.countOfRightAnswers.toString())
        binding.finishText3.text = getString(
            R.string.finishText3,
            args.gameResult.gameSettings.minPercentOfRightAnswers.toString()
        )
        binding.finishText4.text =
            getString(R.string.finishText4, getPercentOfRightAnswers().toString())
        binding.finishEmoji.text = getWinnerText()
    }

    private fun getWinnerText(): String {
        return if (args.gameResult.winner) {
            "Winner!!"
        } else {
            "Loser"
        }
    }

    private fun getPercentOfRightAnswers() = with(args.gameResult) {
        if (countOfQuestions == 0) {
            0
        } else {
            ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
        }
    }

    private fun retryGame() {
        findNavController().popBackStack()
    }

}