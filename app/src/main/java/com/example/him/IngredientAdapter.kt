package com.example.him

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.him.databinding.IngredientRecyclerBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat

class IngredientAdapter(var activity: AppCompatActivity) : RecyclerView.Adapter<Holder>() {
    var listIngredient = listOf<IngredientResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(parent.context).inflate(R.layout.ingredient_recycler, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listIngredient.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val ingredient = listIngredient[position]
        holder.setIngredient(activity, ingredient)
    }
}

class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private lateinit var binding: IngredientRecyclerBinding

    @SuppressLint("SimpleDateFormat")
    fun setIngredient(activity: AppCompatActivity, ingredient: IngredientResponse) {
        binding = IngredientRecyclerBinding.bind(itemView)
        // binding.imageView 사진 처리
        binding.barcodeView.text = ingredient.barcode
        binding.nameView.text = ingredient.name
        binding.shelfLifeView.text =
            SimpleDateFormat("yyyy-MM-dd").format(ingredient.expirationDate)
        binding.priceView.text = "${ingredient.price}"
        binding.editButton.setOnClickListener {
            val userId = activity.intent.getStringExtra("userId")
            activity.startActivity(Intent(activity, IngredientActivity::class.java).putExtra("userId", userId))
        }
        binding.deleteButton.setOnClickListener {
            RetrofitClient.instance.deleteIngredients(ingredient._id).enqueue(object : Callback<MessageResponse> {
                override fun onResponse(
                    call: Call<MessageResponse>,
                    response: Response<MessageResponse>
                ) {
                    Log.d("Response", "결과: ${response.toString()}")

                    val userId = activity.intent.getStringExtra("userId")
                    activity.finish()
                    activity.startActivity(Intent(activity, MainActivity::class.java).putExtra("userId", userId))
                }

                override fun onFailure(call: Call<MessageResponse>, t: Throwable) {
                    Log.d("Response", t.message.toString())
                }
            })
        }
    }
}