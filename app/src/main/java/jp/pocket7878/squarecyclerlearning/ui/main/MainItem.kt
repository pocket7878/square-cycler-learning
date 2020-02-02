package jp.pocket7878.squarecyclerlearning.ui.main

interface MainItem {
    data class FruitItem(val fruitName: String): MainItem
    data class NumberItem(val number: Int): MainItem
}