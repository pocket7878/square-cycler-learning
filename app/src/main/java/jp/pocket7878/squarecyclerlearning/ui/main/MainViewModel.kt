package jp.pocket7878.squarecyclerlearning.ui.main

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.ViewModel
import com.squareup.cycler.DataSource
import com.squareup.cycler.toDataSource

class MainViewModel : ViewModel() {
    private val allItems = listOf(
        MainItem.NumberItem(1,  10),
        MainItem.FruitItem(2,"Apple"),
        MainItem.ColorItem(3,Color.RED),
        MainItem.NumberItem(4,42),
        MainItem.FruitItem(5,"Orange"),
        MainItem.ColorItem(6,Color.YELLOW)
    )

    private val _mainItems = MutableLiveData<List<MainItem>>(allItems)
    val mainData: LiveData<DataSource<MainItem>> = map(_mainItems) { it.toDataSource() }

    private val _extraItem = MutableLiveData<ExtraItem>(ExtraItem("Shuffle"))
    val extraItem: LiveData<ExtraItem> = _extraItem

    fun shuffle() {
        _mainItems.postValue(allItems.shuffled())
    }
}
