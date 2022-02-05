package com.gdsc.forestfire


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.reflect.Field


class MainActivity : AppCompatActivity() {

	@SuppressLint("DiscouragedPrivateApi")
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		val dateItems = listOf("서울", "인천", "경기", "강원", "충북", "충남", "세종", "대전", "경북", "경남", "대구", "부산", "울산", "전북", "전남","광주", "제주" )

		val spinner = findViewById<Spinner>(R.id.spinner)
		val cityAdapter: ArrayAdapter<String> =
			ArrayAdapter<String>(this, R.layout.spinner_item, dateItems)
		spinner.adapter = cityAdapter

		spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
			override fun onItemSelected(
				parent: AdapterView<*>?,
				view: View?,
				position: Int,
				id: Long
			) {
				renderListView(id.toInt())
			}

			override fun onNothingSelected(parent: AdapterView<*>?) {
			}
		}

		renderListView(0)
	}

	private fun renderListView(code: Int) {
		var apiResult: List<FireInfo> = emptyList()
		try {
			val job = GlobalScope.launch {
				val apiCaller = ApiCaller()
				apiResult = apiCaller.getAllForestFireData(code)
			}

			runBlocking {
				job.join()
			}
		}
		catch(err: Exception) {

		}

		val layoutInflater = LayoutInflater.from(this);
		val container = findViewById<LinearLayout>(R.id.itemLayout)
		container.removeAllViews()
		for(element in apiResult){
			val view = layoutInflater.inflate(R.layout.sub_layout, null, false)

			var date = element.date.toString()
			date = date.substring(5, 13).replace("-", " ").replace("T", " ")
			var info = date + "시 "


			val box = view.findViewById<LinearLayout>(R.id.box)
			val icon = view.findViewById<ImageView>(R.id.icon)
			if (element.predict == 0) {
				box.background = ContextCompat.getDrawable(applicationContext, R.drawable.shape1)
				icon.setImageResource(R.drawable.mark1)
				info += "안전"
			}
			else if (element.predict == 1) {
				box.background = ContextCompat.getDrawable(applicationContext, R.drawable.shape2)
				icon.setImageResource(R.drawable.mark2)
				info += "조심"
			}
			else if (element.predict == 2) {
				box.background = ContextCompat.getDrawable(applicationContext, R.drawable.shape3)
				icon.setImageResource(R.drawable.mark3)
				info += "경계"
			}
			else {
				box.background = ContextCompat.getDrawable(applicationContext, R.drawable.shape4)
				icon.setImageResource(R.drawable.mark4)
				info += "위험"
			}
			val textInfo = view.findViewById<TextView>(R.id.tvInfo)
			textInfo.text = info

			container.addView(view)
		}
	}
}