package ru.dubr.activityresultapi

import android.Manifest.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.*
import ru.dubr.activityresultapi.databinding.ActivityEditTextBinding
import ru.dubr.activityresultapi.databinding.ActivityMainBinding
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val requestPermissionLauncher = registerForActivityResult(RequestPermission()) {
        Log.d(MainActivity::class.java.simpleName, "Permission result: $it")
    }

    //private val editMessageLauncher = registerForActivityResult()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(root) }

        binding.permissionButton.setOnClickListener { requestPermission() }
    }

    private fun requestPermission() {
        requestPermissionLauncher.launch(permission.ACCESS_FINE_LOCATION)
    }
}