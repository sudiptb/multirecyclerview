package com.example.multirecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.multirecyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            val userParentAdapter = UserParentAdapter{ currentList, adp ->
                rvList.apply {
                    /*val list = currentList.toMutableList()
                    list.forEachIndexed { index, user ->
                        if (list[index].productList.isEmpty()){
                            list.removeAt(index)
                        }
                    }
                    adp.submitList(list)
                    adapter = adp*/
                    adp.submitList(currentList)
                    adapter = adp
                }
            }
            userParentAdapter.submitList(getList())
            rvList.run {
                setHasFixedSize(true)
                adapter = userParentAdapter
            }
        }

    }
}