package com.axiel7.cuptime.nescafe

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.HapticFeedbackConstants
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.core.animation.addListener
import androidx.core.animation.doOnCancel
import androidx.core.animation.doOnEnd
import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.axiel7.cuptime.BaseFragment
import com.axiel7.cuptime.R
import com.axiel7.cuptime.databinding.FragmentNescafeBinding
import com.axiel7.cuptime.utils.ToneHelper

class NescafeFragment : BaseFragment<FragmentNescafeBinding>() {

    private lateinit var viewModel: NescafeViewModel
    private var valueAnimator: ValueAnimator? = null
    private val isTimerRunning get() = valueAnimator != null && valueAnimator?.isRunning == true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[NescafeViewModel::class.java]
        _binding = FragmentNescafeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.lines.observe(viewLifecycleOwner) {
            if (!isTimerRunning) {
                binding.timeText.text = viewModel.linesToTime().toString()
            }
        }

        initClickListeners()
        binding.line1.performClick()
    }

    private fun initTimer() {
        val startTime = (viewModel.linesToTime() * 1000L) + 1000 // Add 1s delay
        valueAnimator = ValueAnimator.ofInt(viewModel.linesToTime(), 0).apply {
            duration = startTime
            interpolator = LinearInterpolator()
            addUpdateListener {
                binding.timeText.text = valueAnimator?.animatedValue.toString()
            }
            doOnEnd {
                onEndTimer()
            }
            start()
        }
    }

    private fun onEndTimer() {
        ToneHelper().beep(1500)
        switchButton(true)
        binding.timeText.text = getString(R.string.finished)
    }

    private fun onCancelTimer() {
        switchButton(true)
        binding.timeText.text = viewModel.linesToTime().toString()
    }

    private fun switchButton(play: Boolean) {
        if (play) {
            binding.startButton.text = getString(R.string.start)
            binding.startButton.icon = ContextCompat.getDrawable(safeContext, R.drawable.ic_play)
        }
        else {
            binding.startButton.text = getString(R.string.pause)
            binding.startButton.icon = ContextCompat.getDrawable(safeContext, R.drawable.ic_pause)
        }
    }

    private fun initClickListeners() {
        binding.startButton.setOnClickListener {
            it.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
            if (binding.startButton.text == getString(R.string.start)) {
                switchButton(false)
                if (valueAnimator?.isPaused == true) {
                    valueAnimator?.resume()
                }
                else {
                    initTimer()
                }
            }
            else {
                switchButton(true)
                valueAnimator?.pause()
                binding.timeText.text = getString(R.string.paused)
                binding.resetButton.isVisible = true
            }
        }

        binding.resetButton.setOnClickListener {
            it.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
            valueAnimator?.removeAllListeners()
            valueAnimator?.cancel()
            onCancelTimer()
            valueAnimator?.doOnEnd { onEndTimer() }
            it.isInvisible = true
        }

        binding.line1.setOnClickListener {
            if (!isTimerRunning) {
                it.performHapticFeedback(HapticFeedbackConstants.CLOCK_TICK)
                viewModel.setLines(1)
                binding.line1.setImageResource(R.drawable.ic_line_1_fill)
                binding.line2.setImageResource(R.drawable.ic_line_2)
                binding.line3.setImageResource(R.drawable.ic_line_3)
                binding.line4.setImageResource(R.drawable.ic_line_4)
                binding.line5.setImageResource(R.drawable.ic_line_5)
                binding.line6.setImageResource(R.drawable.ic_line_6)
                binding.line7.setImageResource(R.drawable.ic_line_7)
            }
        }
        binding.line2.setOnClickListener {
            if (!isTimerRunning) {
                it.performHapticFeedback(HapticFeedbackConstants.CLOCK_TICK)
                viewModel.setLines(2)
                binding.line1.setImageResource(R.drawable.ic_line_1_fill)
                binding.line2.setImageResource(R.drawable.ic_line_2_fill)
                binding.line3.setImageResource(R.drawable.ic_line_3)
                binding.line4.setImageResource(R.drawable.ic_line_4)
                binding.line5.setImageResource(R.drawable.ic_line_5)
                binding.line6.setImageResource(R.drawable.ic_line_6)
                binding.line7.setImageResource(R.drawable.ic_line_7)
            }
        }
        binding.line3.setOnClickListener {
            if (!isTimerRunning) {
                it.performHapticFeedback(HapticFeedbackConstants.CLOCK_TICK)
                viewModel.setLines(3)
                binding.line1.setImageResource(R.drawable.ic_line_1_fill)
                binding.line2.setImageResource(R.drawable.ic_line_2_fill)
                binding.line3.setImageResource(R.drawable.ic_line_3_fill)
                binding.line4.setImageResource(R.drawable.ic_line_4)
                binding.line5.setImageResource(R.drawable.ic_line_5)
                binding.line6.setImageResource(R.drawable.ic_line_6)
                binding.line7.setImageResource(R.drawable.ic_line_7)
            }
        }
        binding.line4.setOnClickListener {
            if (!isTimerRunning) {
                it.performHapticFeedback(HapticFeedbackConstants.CLOCK_TICK)
                viewModel.setLines(4)
                binding.line1.setImageResource(R.drawable.ic_line_1_fill)
                binding.line2.setImageResource(R.drawable.ic_line_2_fill)
                binding.line3.setImageResource(R.drawable.ic_line_3_fill)
                binding.line4.setImageResource(R.drawable.ic_line_4_fill)
                binding.line5.setImageResource(R.drawable.ic_line_5)
                binding.line6.setImageResource(R.drawable.ic_line_6)
                binding.line7.setImageResource(R.drawable.ic_line_7)
            }
        }
        binding.line5.setOnClickListener {
            if (!isTimerRunning) {
                it.performHapticFeedback(HapticFeedbackConstants.CLOCK_TICK)
                viewModel.setLines(5)
                binding.line1.setImageResource(R.drawable.ic_line_1_fill)
                binding.line2.setImageResource(R.drawable.ic_line_2_fill)
                binding.line3.setImageResource(R.drawable.ic_line_3_fill)
                binding.line4.setImageResource(R.drawable.ic_line_4_fill)
                binding.line5.setImageResource(R.drawable.ic_line_5_fill)
                binding.line6.setImageResource(R.drawable.ic_line_6)
                binding.line7.setImageResource(R.drawable.ic_line_7)
            }
        }
        binding.line6.setOnClickListener {
            if (!isTimerRunning) {
                it.performHapticFeedback(HapticFeedbackConstants.CLOCK_TICK)
                viewModel.setLines(6)
                binding.line1.setImageResource(R.drawable.ic_line_1_fill)
                binding.line2.setImageResource(R.drawable.ic_line_2_fill)
                binding.line3.setImageResource(R.drawable.ic_line_3_fill)
                binding.line4.setImageResource(R.drawable.ic_line_4_fill)
                binding.line5.setImageResource(R.drawable.ic_line_5_fill)
                binding.line6.setImageResource(R.drawable.ic_line_6_fill)
                binding.line7.setImageResource(R.drawable.ic_line_7)
            }
        }
        binding.line7.setOnClickListener {
            if (!isTimerRunning) {
                it.performHapticFeedback(HapticFeedbackConstants.CLOCK_TICK)
                viewModel.setLines(7)
                binding.line1.setImageResource(R.drawable.ic_line_1_fill)
                binding.line2.setImageResource(R.drawable.ic_line_2_fill)
                binding.line3.setImageResource(R.drawable.ic_line_3_fill)
                binding.line4.setImageResource(R.drawable.ic_line_4_fill)
                binding.line5.setImageResource(R.drawable.ic_line_5_fill)
                binding.line6.setImageResource(R.drawable.ic_line_6_fill)
                binding.line7.setImageResource(R.drawable.ic_line_7_fill)
            }
        }
    }
}