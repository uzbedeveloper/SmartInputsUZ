package uz.murodxonov.uzinput

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import uz.smart.input.R
import uz.smart.input.databinding.ViewSmartUzPhoneBinding

class InputPhone @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: ViewSmartUzPhoneBinding =
        ViewSmartUzPhoneBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        setupAttributes(attrs)
        setupTextWatcher()
    }

    private fun setupAttributes(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SmartUzPhoneInput)

        val prefix = typedArray.getString(R.styleable.SmartUzPhoneInput_uz_prefix) ?: "+998"
        binding.tvPrefix.text = prefix

        typedArray.recycle()
    }

    private fun setupTextWatcher() {
        binding.etNumber.addTextChangedListener(object : TextWatcher {
            private var isUpdating = false

            override fun afterTextChanged(s: Editable?) {
                if (isUpdating) return
                isUpdating = true

                val digits = s.toString().filter { it.isDigit() }.take(9)

                val formatted = formatNumber(digits)

                binding.etNumber.setText(formatted)
                binding.etNumber.setSelection(formatted.length)

                toggleValidationUI(digits.length == 9)

                isUpdating = false
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun formatNumber(digits: String): String {
        val sb = StringBuilder()
        for (i in digits.indices) {
            sb.append(digits[i])
            if (i == 1 || i == 4 || i == 6) sb.append(" ")
        }
        return sb.toString().trim()
    }

    private fun toggleValidationUI(isValid: Boolean) {
        binding.ivValidation.animate()
            .alpha(if (isValid) 1f else 0f)
            .setDuration(200)
            .start()
    }

    fun getFullNumber(): String = "+998${binding.etNumber.text.toString().filter { it.isDigit() }}"

    fun isValid(): Boolean = binding.etNumber.text.toString().filter { it.isDigit() }.length == 9
}