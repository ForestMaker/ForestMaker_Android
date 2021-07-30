package com.forest.forestmaker.ui.Sign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.forest.forestmaker.R
import com.forest.forestmaker.server.RequestToServer
import com.forest.forestmaker.server.data.SignUpResponse
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        setButton()

    }

    private fun setButton() {
        act_signup_btn_back.setOnClickListener {
            finish()
        }

        act_signup_btn_signup.setOnClickListener {

            if (act_signup_edit_password1.text.toString() == act_signup_edit_password2.text.toString()) {
                val signUpJsonData = JSONObject()
                signUpJsonData.put("id", act_signup_edit_id.text.toString())
                signUpJsonData.put("pw", act_signup_edit_password1.text.toString())
                signUpJsonData.put("phone", act_signup_edit_phone.text.toString())
                signUpJsonData.put("nickname", act_signup_edit_nickname.text.toString())

                val body = JsonParser.parseString(signUpJsonData.toString()) as JsonObject

                checkSignUp(body)
            } else {
                Toast.makeText(this, "비밀번호를 다시 확인해주세요. ", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkSignUp(body: JsonObject) {
        RequestToServer.service.requestSignUp(body).enqueue(object: Callback<SignUpResponse>{
            override fun onResponse(
                call: Call<SignUpResponse>,
                response: Response<SignUpResponse>
            ) {
                if (response.isSuccessful) {
                    Log.d("success signUp", response.body().toString())

                    val intent = Intent(this@SignUpActivity, SignInActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }

            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                Log.e("fail signUp", t.message.toString())
            }

        })
    }
}