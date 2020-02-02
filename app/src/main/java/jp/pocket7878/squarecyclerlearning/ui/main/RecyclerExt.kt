package jp.pocket7878.squarecyclerlearning.ui.main

import android.view.View
import android.widget.TextView
import com.squareup.cycler.Recycler
import com.squareup.cycler.RecyclerApiMarker
import jp.pocket7878.squarecyclerlearning.R

@RecyclerApiMarker
inline fun <I : Any> Recycler.Config<I>.extraClickableItem(
    noinline onClick: (View) -> Unit
) {
    extraItem<ExtraItem, View> {
        create(R.layout.view_extra_item) {
            val button = view.findViewById<TextView>(R.id.extra_item_label)
            bind { extraItem ->
                button.text = extraItem.message
                button.setOnClickListener {
                    onClick(it)
                }
            }
        }
    }
}