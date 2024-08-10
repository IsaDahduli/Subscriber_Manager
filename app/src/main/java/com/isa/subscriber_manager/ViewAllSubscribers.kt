package com.isa.subscriber_manager

import SubscriberAdapter
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewAllSubscribers : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var adapter: SubscriberAdapter
    private lateinit var subscribers: MutableList<Subscriber?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_all_subscribers)

        listView = findViewById(android.R.id.list)

        fetchSubscribers()
    }

    private fun fetchSubscribers() {
        val api = RetrofitClient.api
        val call = api.subscribers

        call.enqueue(object : Callback<List<Subscriber?>> {
            override fun onResponse(call: Call<List<Subscriber?>>, response: Response<List<Subscriber?>>) {
                if (response.isSuccessful) {
                    subscribers = response.body()?.toMutableList() ?: mutableListOf()
                    adapter = SubscriberAdapter(this@ViewAllSubscribers, subscribers)
                    listView.adapter = adapter
                } else {
                    Toast.makeText(this@ViewAllSubscribers, "Failed to retrieve data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Subscriber?>>, t: Throwable) {
                Toast.makeText(this@ViewAllSubscribers, "Error: " + t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
