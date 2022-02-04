package com.gdsc.forestfire


import Sub
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import java.util.*
import android.widget.LinearLayout
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		val dateItems = listOf("서울", "인천", "경기", "강원", "충북", "충남", "세종", "대전", "경북", "경남", "대구", "부산", "울산", "전북", "전남","광주", "제주" )

		val spinner = findViewById<View>(R.id.spinner) as Spinner
		val cityAdapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, dateItems)

		cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
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
		val job = GlobalScope.launch {
			val apiCaller = ApiCaller()
			apiResult = apiCaller.getAllForestFireData(code)
		}

		runBlocking {
			job.join()
		}

		val layoutInflater = LayoutInflater.from(this);
		val container = findViewById<LinearLayout>(R.id.itemLayout)
		container.removeAllViews()
		for(element in apiResult){
			val view = layoutInflater.inflate(R.layout.sub_layout, null, false)

			val text1 = view.findViewById<TextView>(R.id.textViewSub)
			text1.text = element.date

			val text100 = view.findViewById<TextView>(R.id.textView100)
			text100.text = element.meanVal.toString()

			val icon = view.findViewById<ImageView>(R.id.icon)
			if (element.predict == 0) {
				icon.setImageResource(R.drawable.mark1)
			}
			else if (element.predict == 1) {
				icon.setImageResource(R.drawable.mark2)
			}
			else if (element.predict == 2) {
				icon.setImageResource(R.drawable.mark3)
			}
			else {
				icon.setImageResource(R.drawable.mark4)
			}

			container.addView(view)
		}
	}
}