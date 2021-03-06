package com.example.him

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.him.databinding.ActivityOrderListBinding

// 주문 목록 UI를 구현하는 Boundary 클래스.
class OrderListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrderListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        // View Binding 완료. 아래부터 작성.
    }

    override fun onStart() {
        super.onStart()

        val userId = intent.getStringExtra("userId")
        if (userId != null) {
            OrderManagementSystem().isProvider(this, binding, userId)
            showOrders()
            OrderManagementSystem().show(this, binding, userId)
            binding.navigateMainButton.setOnClickListener { moveMainPage() }
            binding.registerOrderButton.setOnClickListener { moveRegisterOrderPage(userId) }
        } else {
            Log.d("Response", "userId: null")
            Toast.makeText(this, "로그인 정보를 확인할 수 없습니다.", Toast.LENGTH_SHORT).show()
            moveLoginPage()
        }
    }

    fun showOrders() {
        OrderManagementSystem().show(this, binding, intent.getStringExtra("userId")!!)
    }

    private fun moveLoginPage() {
        startActivity(Intent(this, LoginActivity::class.java))
        finishAffinity()
    }

    private fun moveMainPage() {
        finish()
    }

    private fun moveRegisterOrderPage(userId: String) {
        startActivity(Intent(this, RegisterOrderActivity::class.java).putExtra("userId", userId))
    }
}