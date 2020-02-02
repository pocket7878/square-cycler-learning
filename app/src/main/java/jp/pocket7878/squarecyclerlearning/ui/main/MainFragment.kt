package jp.pocket7878.squarecyclerlearning.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.cycler.Recycler
import com.squareup.cycler.toDataSource
import jp.pocket7878.squarecyclerlearning.R
import jp.pocket7878.squarecyclerlearning.databinding.MainFragmentBinding
import kotlinx.android.synthetic.main.view_fruit_item.view.*

class MainFragment : Fragment() {

    interface Item {
        data class FruitItem(val fruitName: String): Item
        data class NumberItem(val number: Int): Item
    }

    private lateinit var binding: MainFragmentBinding
    private lateinit var recycler: Recycler<Item>

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mainRecycler.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onResume() {
        super.onResume()

        recycler = Recycler.adopt(binding.mainRecycler) {
            row<Item.FruitItem, View> {
                create(R.layout.view_fruit_item) {
                    val fruitNameLabel = view.findViewById<TextView>(R.id.fruit_name)
                    bind { i, fruitItem ->
                        fruitNameLabel.text = fruitItem.fruitName
                    }
                }
            }

            row<Item.NumberItem, View> {
                create(R.layout.view_number_item) {
                    val numberLabel = view.findViewById<TextView>(R.id.number_name)
                    bind { i, numberItem ->
                        numberLabel.text = numberItem.number.toString()
                    }
                }
            }
        }

        recycler.data = listOf(
            Item.NumberItem(10),
            Item.FruitItem("Apple"),
            Item.NumberItem(42),
            Item.FruitItem("Orange")
        ).toDataSource()
    }
}
