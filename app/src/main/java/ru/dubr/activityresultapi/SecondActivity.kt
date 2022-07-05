package ru.dubr.activityresultapi

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContract
import androidx.appcompat.app.AppCompatActivity
import ru.dubr.activityresultapi.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    private val resultIntent: Intent
        get() = Intent().apply {
            putExtra(EXTRA_OUTPUT_MESSAGE, binding.valueEditText.text.toString())
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            ActivitySecondBinding.inflate(layoutInflater).apply { setContentView(root) }

        binding.saveButton.setOnClickListener { onSavePressed() }
        binding.cancelButton.setOnClickListener { onBackPressed() }

        binding.valueEditText.setText(intent.getStringExtra(EXTRA_INPUT_MESSAGE))
    }

    override fun onBackPressed() {
        setResult(RESULT_CANCELED, resultIntent)
        super.onBackPressed()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun onSavePressed() {
        setResult(RESULT_OK, resultIntent)
        finish()
    }

    data class Output(
        val message: String,
        val confirmed: Boolean,
    )

    // I - входящее значение, О - исходящее
    class Contract : ActivityResultContract<String, Output>() {

        // создается интент для запуска активити и передается инпут
        override fun createIntent(context: Context, input: String?): Intent {
            val intent = Intent(context, SecondActivity::class.java)
            intent.putExtra(EXTRA_INPUT_MESSAGE, input)
            return intent
        }

        // получается результат запуска активити и из него создается Output
        override fun parseResult(resultCode: Int, intent: Intent?): Output? {
            // проверка интента на null
            if (intent == null) return null
            val message = intent.getStringExtra(EXTRA_OUTPUT_MESSAGE) ?: return null
            val confirmed = resultCode == RESULT_OK
            return Output(message, confirmed)
        }
    }

    companion object {
        private const val EXTRA_INPUT_MESSAGE = "EXTRA_MESSAGE"
        private const val EXTRA_OUTPUT_MESSAGE = "EXTRA_MESSAGE"
    }
}