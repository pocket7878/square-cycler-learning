package jp.pocket7878.squarecyclerlearning.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.cycler.Recycler
import com.squareup.cycler.toDataSource
import jp.pocket7878.squarecyclerlearning.R
import jp.pocket7878.squarecyclerlearning.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding
    private lateinit var recycler: Recycler<MainItem>

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
            row<MainItem.FruitItem, View> {
                create(R.layout.view_fruit_item) {
                    val fruitNameLabel = view.findViewById<TextView>(R.id.fruit_name)
                    bind { i, fruitItem ->
                        fruitNameLabel.text = fruitItem.fruitName
                    }
                }
            }

            row<MainItem.NumberItem, View> {
                create(R.layout.view_number_item) {
                    val numberLabel = view.findViewById<TextView>(R.id.number_name)
                    bind { i, numberItem ->
                        numberLabel.text = numberItem.number.toString()
                    }
                }
            }

            row<MainItem.ColorItem, ColoredItemView> {
                create { context ->
                    view = ColoredItemView(context)
                    val colorView = view.findViewById<View>(R.id.color_view)
                    bind { i, colorItem ->
                        colorView.setBackgroundColor(colorItem.color)
                    }
                }
            }
        }

        viewModel.mainData.observe(viewLifecycleOwner) {
            recycler.data = it
        }
    }
}
