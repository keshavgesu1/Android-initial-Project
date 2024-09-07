package com.example.smarttask.bindingadapters

import android.net.Uri
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.smarttask.R
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputLayout
import java.io.File

@BindingAdapter("validationError")
fun passwordValidator(textInputLayout: TextInputLayout, errorMessage: String?) {
    textInputLayout.error = errorMessage
    if (!errorMessage.isNullOrEmpty())
    {
        textInputLayout.requestFocus()
    }
}


@BindingAdapter("valueAttrChanged")
fun MaterialAutoCompleteTextView.setListener(listener: InverseBindingListener?) {
    this.onItemSelectedListener = if (listener != null) {
        object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                listener.onChange()
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                listener.onChange()
            }
        }
    } else {
        null
    }
}
@get:InverseBindingAdapter(attribute = "value")
@set:BindingAdapter("value")
var MaterialAutoCompleteTextView.selectedValue: Any?
    get() = if (listSelection != ListView.INVALID_POSITION) adapter.getItem(listSelection) else null
    set(value) {
        val newValue = value ?: adapter.getItem(0)
        setText(newValue.toString(), true)
        if (adapter is ArrayAdapter<*>) {
            val position = (adapter as ArrayAdapter<Any?>).getPosition(newValue)
            listSelection = position
        }
    }
@BindingAdapter("entries", "itemLayout", "textViewId", requireAll = false)
fun MaterialAutoCompleteTextView.bindAdapter(entries: Array<Any?>, @LayoutRes itemLayout: Int?, @IdRes textViewId: Int?) {
    val adapter = when {
        itemLayout == null -> {
            ArrayAdapter(context, android.R.layout.simple_list_item_1, entries)
        }
        textViewId == null -> {
            ArrayAdapter(context, itemLayout, entries)
        }
        else -> {
            ArrayAdapter(context, itemLayout, textViewId, entries)
        }
    }
    setAdapter(adapter)
}

@BindingAdapter("entries", "itemLayout", "textViewId", requireAll = false)
fun MaterialAutoCompleteTextView.bindAdapter(entries: List<Any?>, @LayoutRes itemLayout: Int?, @IdRes textViewId: Int?) {
    val adapter = when {
        itemLayout == null -> {
            ArrayAdapter(context, android.R.layout.simple_list_item_1, entries)
        }
        textViewId == null -> {
            ArrayAdapter(context, itemLayout, entries)
        }
        else -> {
            ArrayAdapter(context, itemLayout, textViewId, entries)
        }
    }
    setAdapter(adapter)
}

@BindingAdapter("imageUri")
fun ImageView.setImageUrl(imagePath: String?) {
    if (!imagePath.isNullOrEmpty())
    {
        if (imagePath.startsWith("http"))
        {
            Glide.with(context).load(imagePath).placeholder(R.drawable.ic_person_outline_black_24dp).error(R.drawable.ic_person_outline_black_24dp).into(this)

        }else{
            Glide.with(context).load(Uri.fromFile(File(imagePath))).placeholder(R.drawable.ic_person_outline_black_24dp).error(R.drawable.ic_person_outline_black_24dp).into(this)

        }
    }else{
       // Glide.with(context).load("").apply(RequestOptions.circleCropTransform()).placeholder(R.drawable.ic_person_outline_black_24dp).error(R.drawable.ic_person_outline_black_24dp).into(this)
    }
}

@BindingAdapter("imageUriCircle")
fun ImageView.setImageUrlCircle(imagePath: String?) {
    if (!imagePath.isNullOrEmpty())
    {
        if (imagePath.startsWith("http")){
            Glide.with(context).load(imagePath).apply(RequestOptions.circleCropTransform()).placeholder(R.drawable.ic_person_outline_black_24dp).error(R.drawable.ic_person_outline_black_24dp).into(this)

        }else{
            Glide.with(context).load(Uri.fromFile(File(imagePath))).apply(RequestOptions.circleCropTransform()).placeholder(R.drawable.ic_person_outline_black_24dp).error(R.drawable.ic_person_outline_black_24dp).into(this)

        }
    }else{
        Glide.with(context).load("").apply(RequestOptions.circleCropTransform()).placeholder(R.drawable.ic_person_outline_black_24dp).error(R.drawable.ic_person_outline_black_24dp).into(this)
    }
}