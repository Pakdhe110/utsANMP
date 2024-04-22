package com.example.utsanmp160421024.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.utsanmp160421024.R
import com.example.utsanmp160421024.databinding.FragmentDetailBeritaBinding
import com.example.utsanmp160421024.model.Paragraf
import com.example.utsanmp160421024.viewModel.BeritaViewModel
import com.example.utsanmp160421024.viewModel.ParagraphViewModel
import com.squareup.picasso.Picasso


class DetailBeritaFragment : Fragment() {
    private lateinit var binding: FragmentDetailBeritaBinding
    private lateinit var paragraphviewModel: ParagraphViewModel
    private lateinit var artikelViewModel: BeritaViewModel
    private val listParagraph = arrayListOf<Paragraf>()
    var index = 0
    var max = 1

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
        Log.d("TAG", idArtikel)

        artikelViewModel = ViewModelProvider(this).get(BeritaViewModel::class.java)
        artikelViewModel.artikelDetail(idArtikel)

        paragraphviewModel = ViewModelProvider(this).get(ParagraphViewModel::class.java)
        paragraphviewModel.fetch(idArtikel)

        Log.d("TAG", "Detail View Model 1")

        obserViewModel()
        Log.d("TAG", "Detail View Model 2")

        binding.btnNext.setOnClickListener{
            if(index < max -1){
                index++
                paragraphviewModel.fetch(idArtikel)
            }
        }
        binding.btnBack.setOnClickListener{
            if (index > 0){
                index--
                paragraphviewModel.fetch(idArtikel)
            }

        }

    }

    fun obserViewModel(){
        artikelViewModel.artLD.observe(viewLifecycleOwner, Observer {
            artikel ->
            if (artikel != null){
                Log.d("TAG", "Artikel LD " + artikel.pic)
                Picasso.get().load(artikel.pic).into(binding.imageView)
                binding.txtJudulDetail.text = artikel.judul
                binding.txtUsernameDetail.text= "@" + artikel.username
                Log.d("TAG", "Detail, Artikel View Model")
            }

        })
            paragraphviewModel.paragraphLD.observe(viewLifecycleOwner, Observer {
                paragraf ->
                if (paragraf != null && paragraf.isNotEmpty()){
                    binding.txtParagraphDetail.text = paragraf[index].isi
                    max = paragraf.size
                }


            })



    }

}