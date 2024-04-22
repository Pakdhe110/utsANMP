package com.example.utsanmp160421024.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.utsanmp160421024.R
import com.example.utsanmp160421024.databinding.FragmentDetailBeritaBinding
import com.example.utsanmp160421024.model.Paragraf
import com.example.utsanmp160421024.viewModel.BeritaViewModel
import com.example.utsanmp160421024.viewModel.ParagraphViewModel


class DetailBeritaFragment : Fragment() {
    private lateinit var binding: FragmentDetailBeritaBinding
    private lateinit var paragraphviewModel: ParagraphViewModel
    private lateinit var artikelViewModel: BeritaViewModel
    private val listParagraph = arrayListOf<Paragraf>()
    var index = 0

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

        var idArtikel = DetailBeritaFragmentArgs.fromBundle(requireArguments()).idArtikel
        paragraphviewModel = ViewModelProvider(this).get(ParagraphViewModel::class.java)
        paragraphviewModel.fetch(idArtikel)

        binding.btnNext.setOnClickListener{
            index++
            paragraphviewModel.fetch(idArtikel)
        }
        binding.btnBack.setOnClickListener{

        }

    }

}