package com.example.utsanmp160421024.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.utsanmp160421024.databinding.FragmentBeritaListBinding
import com.example.utsanmp160421024.viewModel.BeritaViewModel


class BeritaListFragment : Fragment() {
    private lateinit var binding:FragmentBeritaListBinding
    private lateinit var viewModel: BeritaViewModel
    private val artikelListAdapter = BeritaListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentBeritaListBinding.inflate(inflater, container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(BeritaViewModel::class.java)
        viewModel.refresh()

        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter  = artikelListAdapter

        observeViewModel()

        binding.refreshLayoutWeb.setOnClickListener{
            binding.recView.visibility = View.GONE
            binding.txtError.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
            viewModel.refresh()
            binding.refreshLayoutWeb.isRefreshing = false
        }
    }

    fun observeViewModel(){
        viewModel.artikelLD.observe(viewLifecycleOwner, Observer {
            artikelListAdapter.updateArtikelList(it)
        })

        viewModel.artikelLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it ==true){
                binding.txtError?.visibility = View.VISIBLE
            }
            else{
                binding.txtError?.visibility = View.GONE
            }
        })

        viewModel.loadLD.observe(viewLifecycleOwner, Observer {
            if(it == true){
                binding.recView.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
            }
            else{
                binding.recView.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
            }
        })
    }

}