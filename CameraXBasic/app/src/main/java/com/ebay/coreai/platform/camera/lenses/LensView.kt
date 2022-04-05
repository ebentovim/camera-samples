package com.ebay.coreai.platform.camera.lenses

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.ebay.coreai.platform.camera.MainActivity
import com.ebay.coreai.platform.camera.R
import com.ebay.coreai.platform.camera.utils.ObservableHorizontalScrollView

class LensView : View {
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        Log.d("LensView", "constructor")
        val lenses =
            (context as MainActivity).findViewById<ObservableHorizontalScrollView>(R.id.LensHorizontalScrollView)
            //this.findViewById<ObservableHorizontalScrollView>(R.id.LensHorizontalScrollView)
        //lenses.registerLens(this)
        // ...
    }

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0) {
    }



}
