package jp.techacademy.taiyu.taskapp

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.view.LayoutInflater
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*
import kotlin.coroutines.coroutineContext

class TaskAdapter(context: Context):BaseAdapter() {
    //コンテキストとは、アプリの情報を持っているクラス全部を一括で管理している便利クラス
    //あらゆる情報が入っているので、コンテキストからそれを取り出す作業はよく見る

    private val mLayoutInflater:LayoutInflater//レイアウトインフレーター型の定数「mLayoutInflater」を定義

    var taskList = mutableListOf<Task>()//様々なアイテムを保持するList（ここではTaskクラス）をtaskListと言うプロパティで定義する

    init {
        this.mLayoutInflater = LayoutInflater.from(context)
    }

    override fun getCount(): Int {
    //ゲットカウントメソッドでmTaskListのサイズを返す（サイズとは？）
        return taskList.size


    }

    override fun getItem(position: Int): Any {
        //ゲットアイテムメソッドでmTaskListの要素を返す

        return  taskList[position]

    }

    override fun getItemId(position: Int): Long {
        return taskList[position].id.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View =
            convertView ?: mLayoutInflater.inflate(android.R.layout.simple_list_item_2, null)

        val textView1 = view.findViewById<TextView>(android.R.id.text1)
        val textView2 = view.findViewById<TextView>(android.R.id.text2)

        textView1.text = taskList[position].title

        val categoryView = view.findViewById<TextView>(android.R.id.edit)




        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.JAPANESE)
        val date = taskList[position].date
        textView2.text = simpleDateFormat.format(date)


        return view
    }


}