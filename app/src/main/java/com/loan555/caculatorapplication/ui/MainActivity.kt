package com.loan555.caculatorapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.loan555.caculatorapplication.R
import com.loan555.caculatorapplication.databinding.ActivityMainBinding
import com.loan555.caculatorapplication.model.ItemButton
import com.loan555.caculatorapplication.ui.adapter.ItemButtonAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: AppViewModel
    private var listButton = listOf<ItemButton>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AppViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        initData()
        initControl()
    }

    private fun initControl() {
        binding.recyclerView.layoutManager =
            GridLayoutManager(this, 4, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = ItemButtonAdapter(listButton, onItemClick)
    }

    private fun initData() {
        listButton = listButton + ItemButton("c", "C", false)
        listButton = listButton + ItemButton("%", "%", false)
        listButton = listButton + ItemButton("del", "del", false)
        listButton = listButton + ItemButton("div", "÷", false)

        listButton = listButton + ItemButton("7", "7", true)
        listButton = listButton + ItemButton("8", "8", true)
        listButton = listButton + ItemButton("9", "9", true)
        listButton = listButton + ItemButton("mul", "x", false)

        listButton = listButton + ItemButton("4", "4", true)
        listButton = listButton + ItemButton("5", "5", true)
        listButton = listButton + ItemButton("6", "6", true)
        listButton = listButton + ItemButton("sub", "-", false)

        listButton = listButton + ItemButton("1", "1", true)
        listButton = listButton + ItemButton("2", "2", true)
        listButton = listButton + ItemButton("3", "3", true)
        listButton = listButton + ItemButton("add", "+", false)

        listButton = listButton + ItemButton("00", "00", true)
        listButton = listButton + ItemButton("0", "0", true)
        listButton = listButton + ItemButton("dot", ",", false)
        listButton = listButton + ItemButton("equal", "=", false)
    }

    private val onItemClick: (Int, ItemButton) -> Unit = { pos, it ->
        Log.d("aaa", "onClick $it")
        when (it.id) {
            "equal" -> {
                viewModel.newExpression()
            }
            "del" -> {
                var newTxt = viewModel.expression.value
                if (newTxt != null && newTxt.isNotEmpty()) {
                    viewModel.expression.value = newTxt.substring(0, newTxt.length - 1)
                }
            }
            "c" -> {
                viewModel.expression.value = ""
            }
            else -> {
                viewModel.expression.value += it.text
            }
        }
        try {
            viewModel.doCount()
            binding.mess.text = ""
        } catch (e: Exception) {
            Log.e("aaa", "phep toan ko hop le")
            viewModel.mess.value = "Phép toán không hợp lệ"
        }
    }
}