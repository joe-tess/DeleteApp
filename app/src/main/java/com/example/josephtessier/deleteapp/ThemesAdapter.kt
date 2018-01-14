package com.example.josephtessier.deleteapp

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class ThemeAdapter (private val list: Array<String>, private val listID: Array<Int>, private val context: Context): RecyclerView.Adapter<ThemeAdapter.ViewHolder>(){

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindItem(list[position], listID[position])
    }




    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindItem(themeName: String, id: Int){
            var checkBox: CheckBox? = itemView.findViewById(R.id.checkboxTheme)
            if(checkBox == null) checkBox = itemView.findViewById(id)
            checkBox?.id = id
            checkBox?.text = themeName
//            Log.d("ADATPER >> ", checkBox?.text.toString() +" / id="+ checkBox?.id)
           /* itemView.setOnClickListener {
                Toast.makeText(context, theme, Toast.LENGTH_SHORT).show()
//                userListThemes!!.add(theme)
            }*/
        }


    }
}