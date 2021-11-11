package com.example.roomdefaultrepo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var db : AppDataBase
    private lateinit var contentList : List<String>
    private lateinit var memoList : List<Memo>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = AppDataBase.getInstance(this)!!

        loadData()

        findViewById<Button>(R.id.saveDataButton).setOnClickListener{
            val content : String = findViewById<EditText>(R.id.saveDateEditText).text.toString()
            CoroutineScope(Dispatchers.IO).launch{
                db.memoDao().insertData(Memo(content))
                loadData()
            }
        }

        findViewById<Button>(R.id.deleteDataButton).setOnClickListener{
            val deleteEditText : EditText = findViewById(R.id.deleteDataEditText)
            val index : Int = Integer.parseInt(deleteEditText.text.toString())
            val deleteData : Memo = memoList[index]
            CoroutineScope(Dispatchers.IO).launch{
                db.memoDao().deleteData(deleteData)
                loadData()
            }
        }
    }

    private fun loadData(){
        CoroutineScope(Dispatchers.IO).launch{

            memoList = db.memoDao().getAll()
            contentList = db.memoDao().getAllContent()
            runOnUiThread{
                findViewById<TextView>(R.id.resultDataText).text = contentList.toString()
            }
        }
    }
}