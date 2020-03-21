package com.example.hipo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import java.io.IOException
import java.io.InputStream

var membersArray = ArrayList<String>()

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Read from json file and display on listView:
        readJson()
        val listView = findViewById<ListView>(R.id.main_listView)
        listView.adapter = MyCustomAdapter(this)

        //When user clicks 'Add New Member' button,
        //second activity starts:
        val addButton = findViewById<Button>(R.id.addButton)
        addButton.setOnClickListener {
            val intent = Intent(this, AddingActivity::class.java)
            startActivity(intent)
        }
    }

    private fun readJson(){

        membersArray.clear()
        val json : String?
        try {
            val inputStream: InputStream = assets.open("hipo.json")
            json = inputStream.bufferedReader().use { it.readText() }

            val jsonArray = JSONArray(json)
            for(i in 0 until jsonArray.length())
            {
                //For each member in json file
                //take this member's name information and keep it in the membersArray
                val jsonObject = jsonArray.getJSONObject(i)
                membersArray.add(jsonObject.getString("name"))
            }
        }
        catch(e : IOException){}
    }

    private inner class MyCustomAdapter(context : Context): BaseAdapter(){

        private val mContext : Context = context


        //responsible for showing how many rows we have in list
        override fun getCount(): Int {
            return membersArray.size
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getItem(position: Int): Any {
            return "TEST STRING"
        }

        //responsible for rendering out each row
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val layoutInflater = LayoutInflater.from(mContext)
            val rowMain = layoutInflater.inflate(R.layout.row_main,null, false)
            val nameTextView = rowMain.findViewById<TextView>(R.id.row_textView)
            nameTextView.text = membersArray[position]
            return rowMain
            //rowMain is the model layout of each cell in the listView
        }
    }
}

