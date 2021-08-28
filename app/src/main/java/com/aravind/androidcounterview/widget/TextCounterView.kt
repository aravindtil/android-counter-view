package com.aravind.androidcounterview.widget

import android.content.Context
import android.content.res.TypedArray
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.widget.TextViewCompat
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import com.aravind.androidcounterview.R
import com.aravind.androidcounterview.databinding.ItemDigitBinding
import com.aravind.androidcounterview.databinding.ItemDigitListBinding

/**
 * Created by Aravindharaj Natarajan on 29-08-2021.
 */
class TextCounterView : LinearLayout {

    lateinit var layoutInflater: LayoutInflater

    var counterTextAppearance = 0
    var counterTextWidth = 0

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        val a: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.TextCounterView, 0, 0)

        layoutInflater = LayoutInflater.from(context)

        counterTextWidth = a.getDimensionPixelSize(R.styleable.TextCounterView_counterTextWidth, 0)
        counterTextAppearance = a.getResourceId(R.styleable.TextCounterView_counterTextAppearance, 0)

        a.recycle()
    }

    constructor (context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    private val zeroAscii = '0'.toInt()

    /**
     * @param textToSet The text to be animated. Can be in any format(₹1,970, (48%), 4.8)
     */
    fun setText(textToSet: String) {
        removeAllViews()
        // Converting the text to character array
        val array = textToSet.toCharArray()
        array.forEach {
            // If the character is a digit, then add a recycler view
            if (it.isDigit()) {
                val listBinding = ItemDigitListBinding.inflate(layoutInflater)
                listBinding.apply {
                    digitList.tag = it.toInt() - zeroAscii
                    digitList.adapter = DigitAdapter()
                }
                addView(listBinding.root)
                //  Else add a TextView
            } else {
                val textLayout = layoutInflater.inflate(R.layout.item_digit, null)
                val textView = textLayout?.findViewById(R.id.number) as TextView?
                textView?.apply {
                    TextViewCompat.setTextAppearance(this, counterTextAppearance)
                    val params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT)
                    text = it.toString()
                    layoutParams = params
                }
                addView(textLayout)
            }
        }
    }


    /**
     * Function to animate the view
     * @param animate -> true To create a speedometer animation
     * @param animate -> false Set Scroll To Position without any smooth scroll
     */
    fun updateView(animate: Boolean) {
        for (i in 0 until childCount) {
            when (val v: View = getChildAt(i)) {
                is RecyclerView -> {
                    if (animate) startScroll(v.tag.toString().toInt(), v.layoutManager)
                    else v.scrollToPosition(v.tag.toString().toInt())
                }
            }
        }
    }

    /**
     * @param position The Scroll To Position
     * @param layoutManager Layout Manager of the RecyclerView
     */
    private fun startScroll(position: Int, layoutManager: RecyclerView.LayoutManager?) {

        // Giving a delay for the animation to run discrete
        Handler(Looper.getMainLooper()).postDelayed({
            val factor = 500f // Animation duration
            val linearSmoothScroller: LinearSmoothScroller = object : LinearSmoothScroller(context) {
                override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {
                    return factor / displayMetrics.densityDpi
                }
            }

            linearSmoothScroller.targetPosition = position
            layoutManager?.startSmoothScroll(linearSmoothScroller)
        }, 200)
    }

    /**
     * Disabling touch events
     */
    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return true
    }


    /**
     * Adapter for digit counter
     */
    inner class DigitAdapter : RecyclerView.Adapter<DataObjectHolder>() {

        private val digits: ArrayList<Int> = arrayListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): DataObjectHolder {
            val binding = ItemDigitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return DataObjectHolder(binding, binding.root)
        }

        override fun onBindViewHolder(holder: DataObjectHolder, position: Int) = holder.bind(digits[position])

        override fun getItemCount() = digits.size
    }


    inner class DataObjectHolder(val binding: ItemDigitBinding, root: View) : RecyclerView.ViewHolder(root) {

        init {
            binding.number.apply {
                TextViewCompat.setTextAppearance(this, counterTextAppearance)
                val params = layoutParams
                params?.width = counterTextWidth
                layoutParams = params
            }
        }

        fun bind(data: Int) {
            binding.apply {
                model = data
                executePendingBindings()
            }
        }
    }
}