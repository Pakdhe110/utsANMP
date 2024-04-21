package com.example.utsanmp160421024.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.utsanmp160421024.R
import com.example.utsanmp160421024.databinding.FragmentDetailBeritaBinding
import com.example.utsanmp160421024.viewModel.BeritaViewModel
import com.example.utsanmp160421024.viewModel.ParagraphViewModel


class DetailBeritaFragment : Fragment() {
    private lateinit var binding: FragmentDetailBeritaBinding
    private lateinit var paragraphviewModel: ParagraphViewModel
    private lateinit var artikelViewModel: BeritaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBeritaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}