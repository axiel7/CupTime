package com.axiel7.cuptime.utils

import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Handler
import android.os.Looper

class ToneHelper {
    private val toneG = ToneGenerator(AudioManager.STREAM_ALARM, 100)

    fun beep(duration: Int) {
        toneG.startTone(ToneGenerator.TONE_CDMA_PIP, duration)
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            toneG.release()
        }, (duration + 50).toLong())
    }
}