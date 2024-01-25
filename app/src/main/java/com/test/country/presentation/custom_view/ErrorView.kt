package com.test.country.presentation.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.test.country.databinding.ErrorViewBinding

class ErrorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding: ErrorViewBinding =
        ErrorViewBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        binding.btnRetry.setOnClickListener {
            retryListener?.onRetry()
        }
    }

    fun interface OnRetryListener {
        fun onRetry()
    }

    private var retryListener: OnRetryListener? = null

    fun setOnRetryListener(listener: OnRetryListener) {
        retryListener = listener
    }

    fun setErrorMessage(message: String) {
        binding.tvErrorMessage.text = message
    }

    fun setErrorIcon(resId: Int) {
        binding.imgError.setImageResource(resId)
    }
}
