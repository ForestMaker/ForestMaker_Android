package com.example.forestmaker.ui.Sign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.forestmaker.MainActivity
import com.example.forestmaker.R
import com.example.forestmaker.server.RequestToServer
import com.example.forestmaker.server.data.SignInResponse
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.activity_sign_in.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        act_signin_btn_signin.setOnClickListener {

            val signInJsonData = JSONObject()
            signInJsonData.put("id", act_signin_edit_id.text.toString())
            signInJsonData.put("pw", act_signin_edit_password.text.toString())


            val body = JsonParser.parseString(signInJsonData.toString()) as JsonObject

            RequestToServer.service.requestSignIn(body).enqueue(object : Callback<SignInResponse> {
                override fun onResponse(
                    call: Call<SignInResponse>,
                    response: Response<SignInResponse>
                ) {
                    if (response.isSuccessful) {
                        Log.d("success", response.body().toString())
                        val intent = Intent(this@SignInActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }

                override fun onFailure(call: Call<SignInResponse>, t: Throwable) {
                    Log.e("fail", t.message.toString())
                }

            })

        }

        act_signin_btn_go_signup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            // 종료할 건가?
            // finish()
        }
    }
}