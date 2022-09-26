package com.myhome.android.factorialtest

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.myhome.android.factorialtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        observeViewModel()
        binding.buttonCalculate.setOnClickListener {
            viewModel.calculate(binding.EditTextNumber.text.toString())
        }
    }

    private fun observeViewModel() {
        viewModel.error.observe(this) {
            if (it) {
                Toast.makeText(this, "enter number", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.progress.observe(this) {
            if (it) {
                binding.progressBarLoading.visibility = View.VISIBLE
                binding.buttonCalculate.isEnabled = false
            } else {
                binding.progressBarLoading.visibility = View.GONE
                binding.buttonCalculate.isEnabled = true
            }
        }
        viewModel.factorial.observe(this) {
            binding.textViewFactorial.text = it
        }
    }
}