import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.gdsc.forestfire.R


class Sub : ConstraintLayout {
	constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
		init(context)
	}

	constructor(context: Context) : super(context) {
		init(context)
	}

	private fun init(context: Context) {
		val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
		inflater.inflate(R.layout.sub_layout, this, true)
	}
}