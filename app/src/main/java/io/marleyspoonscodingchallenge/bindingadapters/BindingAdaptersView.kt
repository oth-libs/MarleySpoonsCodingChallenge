package io.marleyspoonscodingchallenge.bindingadapters

import android.view.View
import androidx.databinding.BindingAdapter
import io.marleyspoonscodingchallenge.extensions.visibleOrGone

@BindingAdapter("app:visibleOrGone") fun visibleOrGone(view: View, visible: Boolean?) {
  visible ?: return
  view.visibleOrGone(visible)
}



