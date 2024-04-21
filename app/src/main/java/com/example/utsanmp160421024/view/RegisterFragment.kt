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
import com.example.utsanmp160421024.databinding.FragmentRegisterBinding
import com.example.utsanmp160421024.viewModel.UserViewModel


class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var viewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegister.setOnClickListener{
            var username = binding.txtUsernameRegister.text.toString()
            var nameFirst = binding.txtFirstNameRegister.text.toString()
            var nameLast = binding.txtLastNameRegister.text.toString()
            var password = binding.txtPasswordRegister.text.toString()

            viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
            viewModel.register(username, password, nameFirst, nameLast)

            viewModel.msgLD.observe(viewLifecycleOwner, Observer {
                result ->
                if (result == "OK"){
                    Toast.makeText(context, "Berhasil Register", Toast.LENGTH_SHORT).show()
                    val action = RegisterFragmentDirections.actionLogin()
                    Navigation.findNavController(it).navigate(action)
                }
                else{
                    Toast.makeText(context, "Gagal Register", Toast.LENGTH_SHORT).show()
                }
            })

        }
    }

}