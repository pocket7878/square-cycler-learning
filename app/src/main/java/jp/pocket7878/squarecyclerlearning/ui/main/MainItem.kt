package jp.pocket7878.squarecyclerlearning.ui.main

interface MainItem {
    val id: Long

    data class FruitItem(override val id: Long, val fruitName: String): MainItem
    data class NumberItem(override val id: Long, val number: Int): MainItem
    data class ColorItem(override val id: Long, val color: Int): MainItem
}

data class ExtraItem(val message: String)