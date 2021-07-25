package com.demo.view

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.MenuListAdapter
import com.demo.R
import com.demo.model.week_diet_data_whole
import com.demo.service.MyAlarmManager
import com.demo.viewmodel.MenuListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar.*

class HomeActivity : AppCompatActivity() {

    private lateinit var menuListAdapter: MenuListAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var menuListViewModel: MenuListViewModel
    private lateinit var listItem: week_diet_data_whole
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        menuListViewModel = ViewModelProviders.of(this).get(MenuListViewModel::class.java)
        menuListViewModel.getOrderListData()
        obserLiveData()

    }

    private fun obserLiveData() {
        menuListViewModel.onOrderListResponse().observe(this, Observer {
            layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            recyclerViewCustomerOrderList.layoutManager = layoutManager

            val list = arrayListOf<week_diet_data_whole>()
            try {
                for (item in it.weekDietData?.monday!!) {
                    listItem = week_diet_data_whole("Monday", item?.mealTime, item?.food)
                    list.add(listItem)

                    /*Setting Reminder notification*/
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        setReminderNotification(""+item?.mealTime, 1, ""+item?.food+" at "+item?.mealTime)  //---For Monday DAY_OF_WEEK = 2
                    }
                }
            } catch (e: Exception) {e.printStackTrace()
            }

            try {
                for (item in it.weekDietData?.wednesday!!) {
                    listItem = week_diet_data_whole("Wednesday", item?.mealTime, item?.food)
                    list.add(listItem)

                    /*Setting Reminder notification*/
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        setReminderNotification(""+item?.mealTime, 3, ""+item?.food+" at "+item?.mealTime)  //---For Wednesday DAY_OF_WEEK = 2
                    }
                }
            } catch (e: Exception) { e.printStackTrace()
            }

            try {
                for (item in it.weekDietData?.thursday!!) {
                    listItem = week_diet_data_whole("Thursday", item?.mealTime, item?.food)
                    list.add(listItem)

                    /*Setting Reminder notification*/
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        setReminderNotification(""+item?.mealTime, 4, ""+item?.food+" at "+item?.mealTime)  //---For Thursday DAY_OF_WEEK = 2
                    }
                }
            } catch (e: Exception) {e.printStackTrace()
            }
            Log.d("ddd", "list size@@@@@@@@:" + list.size)

            menuListAdapter = MenuListAdapter(this, list)
            recyclerViewCustomerOrderList.adapter = menuListAdapter
        })
        menuListViewModel.onError()?.observe(this) {
            Toast.makeText(this, it.errorMessage, Toast.LENGTH_LONG).show()
        }

        /*Setting Reminder notification*/
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            setReminderNotification("20:59", 7, "Test Test Test at ")  //---For Sunday DAY_OF_WEEK = 7
//        }
    }


    private var reminderHour: Int = 0
    private var reminderMinutes: Int = 0
    @RequiresApi(Build.VERSION_CODES.O)
    private fun setReminderNotification(time: String, dayOfWeek: Int, message: String){

    val dateFormatter = SimpleDateFormat("HH:mm", Locale.ENGLISH)
    try {
        val c = getInstance()
        c.time = dateFormatter.parse(time)
        reminderHour = c[HOUR_OF_DAY]
        reminderMinutes = c[MINUTE]
        println("Hour: $reminderHour")
        println("Minute: $reminderMinutes")
    } catch (e: ParseException) {
        e.printStackTrace()
    }

    Log.d("ddd", "reminderHour: $reminderHour , reminderMinutes: $reminderMinutes")

        var datetimeToAlarm = getInstance(Locale.getDefault())
//        datetimeToAlarm.timeInMillis = System.currentTimeMillis()
        datetimeToAlarm.set(HOUR_OF_DAY, reminderHour)
        datetimeToAlarm.set(MINUTE, reminderMinutes - 5)            //--5 minutes before of actual time
        datetimeToAlarm.set(SECOND, 0)
        datetimeToAlarm.set(MILLISECOND, 0)
        datetimeToAlarm.set(DAY_OF_WEEK, dayOfWeek)

//        val calendar = Calendar.getInstance()
//        calendar.add(Calendar.SECOND, 2)
        MyAlarmManager.setAlarm(applicationContext, datetimeToAlarm.timeInMillis, ""+message)
    }

}