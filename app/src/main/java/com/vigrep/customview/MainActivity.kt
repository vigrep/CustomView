package com.vigrep.customview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.vigrep.customview.canvas.CanvasDemoActivity
import com.vigrep.customview.databinding.ActivityMainBinding
import com.vigrep.customview.goo.GooActivity
import com.vigrep.customview.mimu.MimuActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mList: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()
        initUI()
        freshUIByData()
    }

    private fun initData() {
        mList = listOf(
            "仿MIMU控件按压效果",
            "粘性红点",
            "Canvas练习"
        )
    }

    private fun initUI() {
        binding.titleListLv.setOnItemClickListener { parent, view, position, id ->
            navigation(position)
        }
    }

    private fun freshUIByData() {
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mList)
        binding.titleListLv.adapter = adapter
    }

    private fun navigation(position: Int) {
        when(position) {
            0 -> navigationActivity(MimuActivity::class.java)
            1 -> navigationActivity(GooActivity::class.java)
            2 -> navigationActivity(CanvasDemoActivity::class.java)
        }
    }

    private fun navigationActivity(cls: Class<*>) {
        val intent = Intent(MainActivity@this, cls)
        startActivity(intent)
    }

}