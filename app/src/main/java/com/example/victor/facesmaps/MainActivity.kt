package com.example.victor.facesmaps

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.victor.facesmaps.remote.Remote
import com.example.victor.facesmaps.remote.impl.RemoteImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val remote = RemoteImpl(Remote.create())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        remote.run {
            users(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({text.text = it.data[0].email}, {it.printStackTrace()})
        }
    }
}
