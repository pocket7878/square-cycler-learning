package jp.pocket7878.squarecyclerlearning.ui.main

interface MainItem {
    data class FruitItem(val fruitName: String): MainItem
    data class NumberItem(val number: Int): MainItem
    data class ColorItem(val color: Int): MainItem
}

data class ExtraItem(val message: String)