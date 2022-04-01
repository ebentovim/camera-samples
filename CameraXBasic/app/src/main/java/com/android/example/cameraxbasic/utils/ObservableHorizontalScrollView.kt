package com.android.example.cameraxbasic.utils

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import com.android.example.cameraxbasic.databinding.FragmentCameraBinding

class ObservableHorizontalScrollView(context: Context?, attrs: AttributeSet?, defStyle: Int) :
    HorizontalScrollView(context, attrs, defStyle) {
    interface OnScrollListener {
        fun onScrollChanged(
            scrollView: ObservableHorizontalScrollView?,
            x: Int,
            y: Int,
            oldX: Int,
            oldY: Int
        )

        fun onEndScroll(scrollView: ObservableHorizontalScrollView?) {
            Log.d("Scroll touch", "scroll ended")
        }
    }

    private var mIsScrolling = false
    private var mIsTouching = false
    private var mScrollingRunnable: Runnable? = null
    var onScrollListener: OnScrollListener? = null

    constructor(context: Context?) : this(context, null, 0)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0) {
        Log.d("Scroll touch", "constructor")
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        val action: Int = ev.getAction()
        Log.d("Scroll touch", ev.toString())
        if (action == MotionEvent.ACTION_MOVE) {
            mIsTouching = true
            mIsScrolling = true
        } else if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL) {
            if (mIsTouching && !mIsScrolling) {
                if (onScrollListener != null) {
                    onScrollListener!!.onEndScroll(this)
                }
            }
            mIsTouching = false
        }
        return super.onTouchEvent(ev)
    }

    override fun onScrollChanged(x: Int, y: Int, oldX: Int, oldY: Int) {
        super.onScrollChanged(x, y, oldX, oldY)
        if (Math.abs(oldX - x) > 0) {
            if (mScrollingRunnable != null) {
                removeCallbacks(mScrollingRunnable)
            }
            mScrollingRunnable = object : Runnable {
                override fun run() {
                    if (mIsScrolling && !mIsTouching) {
                        if (onScrollListener != null) {
                            onScrollListener!!.onEndScroll(this@ObservableHorizontalScrollView)
                        }
                    }
                    mIsScrolling = false
                    mScrollingRunnable = null
                }
            }
            postDelayed(mScrollingRunnable, 200)
        }
        if (onScrollListener != null) {
            onScrollListener!!.onScrollChanged(this, x, y, oldX, oldY)
        }
    }
}