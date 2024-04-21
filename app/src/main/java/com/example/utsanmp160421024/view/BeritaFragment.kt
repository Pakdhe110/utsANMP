package com.example.utsanmp160421024.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.utsanmp160421024.databinding.FragmentBeritaBinding
import com.example.utsanmp160421024.viewModel.BeritaViewModel


class BeritaFragment : Fragment() {
    private lateinit var binding:FragmentBeritaBinding
    private lateinit var viewModel: BeritaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBeritaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(viewModel::class.java)
        viewModel.refresh()

        observeViewModel()
        binding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnRead.setOnClickListener{
            val action = BeritaListFragmentDirections.actionDetailBerita()
            Navigation.findNavController(it).navigate(action)
        }
    }
    fun observeViewModel(){

    }

}