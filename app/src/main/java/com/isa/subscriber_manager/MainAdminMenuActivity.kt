package com.isa.subscriber_manager

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class MainAdminMenuActivity : AppCompatActivity()
{

    private var Add_Subscriber_Button: Button? = null
    private var View_All_SubscribersButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_admin_menu)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        Add_Subscriber_Button = findViewById<View>(R.id.btn_admin_add_subscriber) as Button
        View_All_SubscribersButton = findViewById<View>(R.id.btn_admin_viewAll_subscribers) as Button
    }

    fun goToAdd(view: View?) {
        Add_Subscriber_Button!!.setOnClickListener {
            val i = Intent(
                this@MainAdminMenuActivity,Admin_Add_Subscriber_Activity::class.java)
            startActivity(i)
        }
    }

    fun goToViewAll(view: View?) {
        View_All_SubscribersButton!!.setOnClickListener {
            val i = Intent(
                this@MainAdminMenuActivity,ViewAllSubscribers::class.java)
            startActivity(i)
        }
    }

}