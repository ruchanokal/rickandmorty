package com.characters.rickandmorty.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.characters.rickandmorty.R
import com.characters.rickandmorty.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding : FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : MainViewModel
    private var charactersAdapter  = CharactersAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = androidx.lifecycle.ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getData()
        initializeRecyclerViews()
        observeLiveData()

    }


    private fun initializeRecyclerViews() {

        val llm  =  LinearLayoutManager(requireContext())
        binding.charactersRecyclerView.layoutManager = llm
        binding.charactersRecyclerView.adapter = charactersAdapter

    }


    private fun observeLiveData() {

        with(viewModel){

            isLoading.observe(viewLifecycleOwner, Observer {
                binding.progressBar.isVisible = it
            })

            cloudResponse.observe(viewLifecycleOwner,Observer{
                it?.let {
                    setRecyclerView(it.results)
                }
            })

            errorMessage.observe(viewLifecycleOwner,Observer{
                it?.let {
                    binding.errorTextView.visibility = View.VISIBLE
                    binding.errorTextView.text = it
                }
            })
        }

    }


    private fun setRecyclerView(dataList : List<com.characters.rickandmorty.model.Result>) {

        val llm  =  LinearLayoutManager(requireContext())
        binding.charactersRecyclerView.visibility = View.VISIBLE
        binding.charactersRecyclerView.layoutManager = llm
        binding.charactersRecyclerView.adapter = charactersAdapter
        charactersAdapter.refreshList(dataList)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}