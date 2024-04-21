package com.example.utsanmp160421024.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.utsanmp160421024.databinding.FragmentBeritaBinding
import com.example.utsanmp160421024.databinding.FragmentBeritaListBinding
import com.example.utsanmp160421024.databinding.FragmentDetailBeritaBinding
import com.example.utsanmp160421024.model.Artikel
import com.squareup.picasso.Picasso

class BeritaListAdapter(val artikelList:  ArrayList<Artikel>):RecyclerView.Adapter<BeritaListAdapter.ArtikelViewHoler>() {
    class ArtikelViewHoler(var binding: FragmentBeritaBinding)
        :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtikelViewHoler {
        val binding = FragmentBeritaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArtikelViewHoler(binding)
    }

    override fun onBindViewHolder(holder: ArtikelViewHoler, position: Int) {
        Log.d("TAG", "Berita List Adapter")
        val picasso = Picasso.Builder(holder.itemView.context)
        picasso.listener{picasso, uri, exception ->
            exception.printStackTrace()
        }
        picasso.build().load(artikelList[position].pic).into(holder.binding.imgBerita)
        holder.binding.txtJudul.text = artikelList[position].judul
        holder.binding.txtParagraph.text = artikelList[position].paragrafAbstract
        holder.binding.txtUsernameBerita.text = "@" + artikelList[position].username

        holder.binding.btnRead.setOnClickListener{
            val idArtikel = artikelList[position].id
            val action = BeritaListFragmentDirections.actionDetailBerita()
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return artikelList.size
    }

    fun updateArtikelList(newArtikelList: ArrayList<Artikel>){
        artikelList.clear()
        artikelList.addAll(newArtikelList)
        notifyDataSetChanged()
    }
}