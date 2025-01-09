package com.geeks.hw32.ui.fragments

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.geeks.hw32.data.models.User
import com.geeks.hw32.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() = with(binding){
        buttonSend.setOnClickListener {
            val name = etName.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString().toIntOrNull()

            if (name.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches() && password != null) {
                val user = User(name, email, password)
                val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(user)
                findNavController().navigate(action)
            }else{
                buttonSend.isEnabled = false
            }
        }

        etName.addTextChangedListener { checkFields() }
        etEmail.addTextChangedListener { checkFields() }
        etPassword.addTextChangedListener { checkFields() }
    }

    private fun checkFields() = with(binding){
        val name = etName.text.toString()
        val email = etEmail.text.toString()
        val password = etPassword.text.toString()

        buttonSend.isEnabled = name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()
    }
}