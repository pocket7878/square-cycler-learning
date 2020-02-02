package jp.pocket7878.squarecyclerlearning.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.ViewModel
import com.squareup.cycler.DataSource
import com.squareup.cycler.toDataSource

class MainViewModel : ViewModel() {
    private val allItems = listOf(
            MainItem.NumberItem(10),
            MainItem.FruitItem("Apple"),
            MainItem.NumberItem(42),
            MainItem.FruitItem("Orange")
    )

    private val _mainItems = MutableLiveData<List<MainItem>>(allItems)

    val mainData: LiveData<DataSource<MainItem>> = map(_mainItems) { it.toDataSource() }
}
