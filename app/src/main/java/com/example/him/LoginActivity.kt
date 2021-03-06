package com.example.him

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.him.databinding.ActivityLoginBinding

// 로그인 UI를 구현하는 Boundary 클래스.
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        // View Binding 완료. 아래부터 작성.

        binding.signInButton.setOnClickListener { moveRegisterPage() }
        binding.loginButton.setOnClickListener { loginHandler() }
    }

    private fun loginHandler() {
        val id = binding.idEdit.text.toString()
        val password = binding.passwordEdit.text.toString()
        UserManagementSystem().login(this, id, password)
    }

    private fun moveRegisterPage() {
        startActivity(Intent(this, RegisterUserActivity::class.java))
    }
}