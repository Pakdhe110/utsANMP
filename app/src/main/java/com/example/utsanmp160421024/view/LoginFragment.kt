package com.example.utsanmp160421024.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.utsanmp160421024.R
import com.example.utsanmp160421024.databinding.FragmentLoginBinding
import com.example.utsanmp160421024.viewModel.UserViewModel


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnLogin.setOnClickListener{

            viewModel = ViewModelProvider(this).get(UserViewModel::class.java)


            val action = LoginFragmentDirections.actionBeritaList()
            Navigation.findNavController(it).navigate(action)

        }

        binding.txtCreate.setOnClickListener{
            val action = LoginFragmentDirections.actionRegister()
            Navigation.findNavController(it).navigate(action)
        }
    }
}