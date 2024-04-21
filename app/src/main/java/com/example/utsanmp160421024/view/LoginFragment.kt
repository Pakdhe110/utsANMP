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
import com.example.utsanmp160421024.databinding.FragmentLoginBinding
import com.example.utsanmp160421024.viewModel.UserViewModel


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: UserViewModel
    var username = ""
    var pass = ""

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
        Log.d("TAG", "Login Fragment")

        binding.btnLogin.setOnClickListener{
            username = binding.txtUsername.text.toString()
            pass =  binding.txtPassword.text.toString()

            viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
            viewModel.login(username, pass)

            viewModel.userLD.observe(viewLifecycleOwner, Observer {
                user ->
                if(user != null){
                    Log.d("TAG", "Berhasil Login 3")
                    val action = LoginFragmentDirections.actionBeritaList(username)
                    Navigation.findNavController(it).navigate(action)
                }
                else if (username == "" || pass == ""){
                    Toast.makeText(context, "Username atau password kosong", Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(context, "Username atau password salah", Toast.LENGTH_LONG).show()
                    Log.d("TAG", "Login Salah ")
                }
            })
        }
        binding.txtCreate.setOnClickListener{
            val action = LoginFragmentDirections.actionRegister()
            Navigation.findNavController(it).navigate(action)
        }
    }


}