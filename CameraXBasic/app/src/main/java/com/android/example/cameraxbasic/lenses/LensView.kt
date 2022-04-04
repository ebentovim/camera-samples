package com.android.example.cameraxbasic.lenses

import android.content.Context
import android.util.AttributeSet
import android.view.View

class LensView : View {
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        // ...
    }

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0) {}

}
