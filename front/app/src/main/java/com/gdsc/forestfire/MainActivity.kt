package com.gdsc.forestfire

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
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
	}
	private fun getJsonString(): String? {
		var json = ""
		try {
			val `is`: InputStream = assets.open("FFinfo.json")
			val fileSize: Int = `is`.available()
			val buffer = ByteArray(fileSize)
			`is`.read(buffer)
			`is`.close()
			json = String(buffer, "UTF-8")
		} catch (ex: IOException) {
			ex.printStackTrace()
		}
		return json
	}
}