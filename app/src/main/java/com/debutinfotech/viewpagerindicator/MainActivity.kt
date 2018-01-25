package com.debutinfotech.viewpagerindicator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    fun init(){
        val adapter=SimplePagerAdapter(supportFragmentManager,getItemsList())
        pager.adapter=adapter
        indicator.setPager(pager)
    }


    fun getItemsList():ArrayList<Item>{
        val list=ArrayList<Item>()
        val colors=resources.getStringArray(R.array.list_colors)
        for (listItem in colors.indices){
            list.add(Item("${listItem}",colors[listItem]))
        }
        return list
    }
}
