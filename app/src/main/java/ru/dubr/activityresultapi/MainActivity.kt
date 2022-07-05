package ru.dubr.activityresultapi

import android.Manifest.permission
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.appcompat.app.AppCompatActivity
import ru.dubr.activityresultapi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val requestPermissionLauncher = registerForActivityResult(RequestPermission()) {
        Log.d(MainActivity::class.java.simpleName, "Permission result: $it")
    }

    private val editMessageLauncher = registerForActivityResult(SecondActivity.Contract()) {
        if (it.confirmed) binding.valueTextView.text = it.message
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(root) }

        binding.permissionButton.setOnClickListener { requestPermission() }
        binding.editButton.setOnClickListener { onEditPressed() }
    }

    private fun onEditPressed() {
        editMessageLauncher.launch(binding.valueTextView.text.toString())
    }

    private fun requestPermission() {
        requestPermissionLauncher.launch(permission.ACCESS_FINE_LOCATION)
    }
}