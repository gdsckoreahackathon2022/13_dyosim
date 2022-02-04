package com.gdsc.forestfire

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.IOException
import java.io.InputStream

class MainActivity : AppCompatActivity() {

	var items = arrayOf("대구", "서울", "부산")
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		val spinner = findViewById<View>(R.id.spinner) as Spinner
		val cityAdapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, items)
		cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
		spinner.adapter = cityAdapter
		spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
			override fun onItemSelected(
				parent: AdapterView<*>?,
				view: View?,
				position: Int,
				id: Long
			) {
				//TODO("Not yet implemented")
			}

			override fun onNothingSelected(parent: AdapterView<*>?) {
				//TODO("Not yet implemented")
			}
		}

		// api 호출 결과를 List 형태로 넣어드렸습니다 ^^7
		var apiResult: List<FireInfo> = emptyList()
		val job = GlobalScope.launch {
			val apiCaller = ApiCaller()
			apiResult = apiCaller.getAllForestFireData()
		}

		// 쓰레드 관련한 문제로 이 코드 블럭 이후에 코드 작성해주세요.
		runBlocking {
			job.join()
		}

		// 여기부터 apiResult에 값이 들어가있어요.
		// apiResult를 사용해서 뷰에 그려줍시다.
	}
}