package com.example.myapplication5252525

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication5252525.databinding.ActivityMainBinding
import com.example.room.db.DBhandler
import com.example.room.db.model.UserEntitly
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
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
        val disposable = CompositeDisposable()
        binding.btncreate.setOnClickListener {
            Thread {
                db.userDao().insertUser(
                    UserEntitly(0, "st1", "family1", "1111"),
                    UserEntitly(0, "st2", "family2", "22"),
                    UserEntitly(0, "st3", "family3", "3333"),
                    UserEntitly(0, "st4", "family4", "444"),
                )

            }.start()
        }

        binding.btnPrint.setOnClickListener {
            var text = ""
            val dispose = db.userDao().getUser
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ user ->
                    user.forEach {
                        text += "$it.name\n\n"
                    }
                    binding.txtid.text = "$text"
                }) {

                }
            disposable.add(dispose)
        }

    }
}