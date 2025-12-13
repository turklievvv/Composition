package com.example.composittion.presentation

import android.app.FragmentManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import com.example.composittion.R
import com.example.composittion.databinding.FragmentChooseLevelBinding
import com.example.composittion.databinding.FragmentGameFinishedBinding
import com.example.composittion.domain.entity.GameResult

@Suppress("DEPRECATION")
class GameFinishedFragment : Fragment() {

    private lateinit var gameResult: GameResult

    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("FragmentGameFinishedBinding == null")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

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
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    retryGame()
                }
            }
        )
        bindingViews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding == null
    }

    private fun parseArgs() {
        requireArguments().getParcelable<GameResult>(GAME_RESULT_KEY)?.let {
            gameResult = it
        }
    }
    private fun bindingViews(){
        binding.buttonTryAgain.setOnClickListener {
            retryGame()
        }
        binding.finishText1.text = getString(
            R.string.finishText1,
            gameResult.gameSettings.minCountOfRightAnswers.toString()
        )
        binding.finishText2.text =
            getString(R.string.finishText2, gameResult.countOfRightAnswers.toString())
        binding.finishText3.text = getString(
            R.string.finishText3,
            gameResult.gameSettings.minPercentOfRightAnswers.toString()
        )
        binding.finishText4.text = getString(R.string.finishText4,getPercentOfRightAnswers())
        binding.finishEmoji.text = getWinnerText()
    }

    private fun getWinnerText(): String{
        return if(gameResult.winner){
            "Winner!!"
        }else{
            "Loser"
        }
    }

    private fun getPercentOfRightAnswers()=with(gameResult){
        if(countOfQuestions == 0){
            0
        }else{
            ((countOfRightAnswers/countOfQuestions.toDouble())*100).toInt()
        }
    }

    private fun retryGame() {
        requireActivity().supportFragmentManager.popBackStack(
            GameFragment.NAME,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
    }

    companion object {

        private const val GAME_RESULT_KEY = "game_result"

        fun newInstance(gameResult: GameResult): GameFinishedFragment {
            return GameFinishedFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(GAME_RESULT_KEY, gameResult)
                }
            }
        }

    }

}