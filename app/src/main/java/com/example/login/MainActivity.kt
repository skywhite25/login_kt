package com.example.login

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val TAG: String = "Register"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_next.setOnClickListener{
            // 회원가입 화면으로 이동
            Log.d(TAG, "회원가입 버튼 클릭")

            val intent = Intent(this, InputActivity::Class.java)
            startActivity(intent)
        }

        button_next2.setOnClickListener{
            // 로그인 화면으로 이동
            Log.d(TAG, "로그인 버튼 클릭")

            val intent = Intent(this, LoginActivity::Class.java)
            startActivity(intent)
        }

        CallButton.setOnClickListener{
            var intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel : 010.0000.0000")
            if (intent.resolveActivity(packageManager) != null){
                startActivity(intent)
            }
        }
    }
}