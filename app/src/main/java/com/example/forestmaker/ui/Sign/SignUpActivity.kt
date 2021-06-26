package com.example.forestmaker.ui.Sign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.forestmaker.R
import com.example.forestmaker.server.RequestToServer
import com.example.forestmaker.server.data.SignUpResponse
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {

    var id = false
    var pw1 = false
    var pw2 = false
    var nickname = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        act_signup_btn_back.setOnClickListener {
            finish()
        }

        act_signup_btn_signup.setOnClickListener {

            id = !act_signup_edit_id.text.isNullOrBlank()
            pw1 = !act_signup_edit_password1.text.isNullOrBlank()
            pw2 = !act_signup_edit_password2.text.isNullOrBlank()
            nickname = !act_signup_edit_nickname.text.isNullOrBlank()

            if (id) {
                if (pw1) {
                    if (pw2) {
                        if (nickname) {
                            if (act_signup_edit_password1.text.toString() == act_signup_edit_password2.text.toString()){
                                val signUpJsonData = JSONObject()
                                signUpJsonData.put("id", act_signup_edit_id.text.toString())
                                signUpJsonData.put("pw", act_signup_edit_password1.text.toString())
                                signUpJsonData.put("nickname", act_signup_edit_nickname.text.toString())

                                val body = JsonParser.parseString(signUpJsonData.toString()) as JsonObject

//                    checkSignUp(body)

                                // 통신 빼고 뷰 테스트
                                val intent = Intent(this@SignUpActivity, SignInActivity::class.java)
                                startActivity(intent)
                                finish()

                            } else {
                                Toast.makeText(this, "아이디 및 비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }

            Toast.makeText(this, "모든 항목을 입력해주세요", Toast.LENGTH_SHORT).show()

        }
    }

    private fun checkSignUp(body: JsonObject) {
        RequestToServer.service.requestSignUp(body).enqueue(object: Callback<SignUpResponse>{
            override fun onResponse(
                call: Call<SignUpResponse>,
                response: Response<SignUpResponse>
            ) {
                if (response.isSuccessful) {
                    Log.d("success", response.body().toString())

                    val intent = Intent(this@SignUpActivity, SignInActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }

            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                Log.e("fail", t.message.toString())
            }

        })
    }
}