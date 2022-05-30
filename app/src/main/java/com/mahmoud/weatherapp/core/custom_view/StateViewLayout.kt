package com.mahmoud.weatherapp.core.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.mahmoud.weatherapp.core.utils.Action
import com.mahmoud.weatherapp.databinding.LayoutStateViewBinding

class StateViewLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private var binding: LayoutStateViewBinding =
        LayoutStateViewBinding.inflate(LayoutInflater.from(context), null, false)

    init {
        addView(binding.root)
    }

    fun showLoading(loading: Boolean) {
        binding.root.isVisible = loading
        binding.cvLoading.isVisible = loading
        binding.clError.isVisible = false
    }

    fun showError(error: String?, retry: Action? = null) {
        binding.clError.isVisible = error != null
        binding.root.isVisible = error != null
        binding.cvLoading.isVisible = false
        binding.tvErrorMessage.text = error
        binding.tvRetry.setOnClickListener {
            retry?.invoke()
        }
    }

    fun setStateViewBackgroundColor(backgroundColor: Int) =
        binding.root.setBackgroundColor(
            ContextCompat.getColor(
                context,
                backgroundColor
            )
        )
}
