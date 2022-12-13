package com.example.grillayout.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.grillayout.R
import kotlinx.coroutines.launch

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val catListRecyclerView = view.findViewById<RecyclerView>(R.id.recyclerView_cats_list)

        // Вот тут ставим GridLayoutManager как layoutManager
        catListRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        viewModel.catListLiveData.observe(viewLifecycleOwner) {
            catListRecyclerView.adapter = CatAdapter(it)
        }

        val catAgeList = view.findViewById<RecyclerView>(R.id.recyclerView_cats_age)
        catAgeList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        viewModel.listCatAgeLiveData.observe(viewLifecycleOwner) {
            catAgeList.adapter = CatAgeAdapter(it,viewLifecycleOwner)
        }

        lifecycleScope.launch {
            viewModel.actions.collect { event ->
                when (event) {
                    is Actions.OpenNewFragment -> {
                        // открываем новый фрагмент
                        Toast.makeText(
                            requireContext(),
                            "Пытаюсь открыть новый фрагмент с параметрами $event",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

}