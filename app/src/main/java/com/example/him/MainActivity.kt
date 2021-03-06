package com.example.him

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.him.databinding.ActivityMainBinding

// 식재료 목록 UI를 구현하는 Boundary 클래스.
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        // View Binding 완료. 아래부터 작성.
    }

    override fun onStart() {
        super.onStart()

        val userId = intent.getStringExtra("userId")
        if (userId != null) {
            showIngredients()
            binding.logoutButton.setOnClickListener { moveLoginPage() }
            binding.navigateEditUserButton.setOnClickListener { moveEditUserPage(userId) }
            binding.registerOrderButton.setOnClickListener { moveRegisterIngredientPage(userId) }
            binding.navigateOrderButton.setOnClickListener { moveOrderListPage(userId) }
        } else {
            Log.d("Response", "userId: null")
            Toast.makeText(this, "로그인 정보를 확인할 수 없습니다.", Toast.LENGTH_SHORT).show()
            moveLoginPage()
        }
    }

    fun showIngredients() {
        IngredientManagementSystem().show(this, binding, intent.getStringExtra("userId")!!)
    }

    private fun moveLoginPage() {
        startActivity(Intent(this, LoginActivity::class.java))
        finishAffinity()
    }

    private fun moveEditUserPage(userId: String) {
        startActivity(Intent(this, EditUserActivity::class.java).putExtra("userId", userId))
    }

    private fun moveRegisterIngredientPage(userId: String) {
        startActivity(Intent(this, IngredientActivity::class.java).putExtra("userId", userId))
    }

    private fun moveOrderListPage(userId: String) {
        startActivity(Intent(this, OrderListActivity::class.java).putExtra("userId", userId))
    }
}