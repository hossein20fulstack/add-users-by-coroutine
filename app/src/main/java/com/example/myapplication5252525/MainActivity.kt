package com.example.myapplication5252525

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.myapplication5252525.databinding.ActivityMainBinding
import com.example.room.db.DBhandler
import com.example.room.db.model.UserEntitly
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = DBhandler.getDatabase(this)
        var text = ""
        binding.btncreate.setOnClickListener {

            lifecycleScope.launch {
                text = ""
                withContext(Dispatchers.IO) {
                    val res =

//                        db.userDao().insertUser(UserEntitly(0,"St1","Family1","00000"))


//                        db.userDao().deleteallusers()

//                        db.userDao().delete(UserEntitly(2,"st1","","")
                        //////////////////
//                        db.userDao().update(
//                        UserEntitly(1, "Upddddatename", "UpdateFamily", "1111")

//                    )

                    withContext(Dispatchers.Main){

//                        Toast.makeText(this@MainActivity, res.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        binding.btnclear.setOnClickListener {
            binding.txtid.text = ""
        }
        binding.btnPrint.setOnClickListener {
            lifecycleScope.launch {
                withContext(Dispatchers.IO) {
                    val users = db.userDao().getUser
                    withContext(Dispatchers.Main) {
                        users.collect { userlist ->
                            text = ""
                            userlist.forEach {
                                text += "$it\n\n"
                            }
                            binding.txtid.text = text
                        }
                    }
                }
            }

        }


    }

}