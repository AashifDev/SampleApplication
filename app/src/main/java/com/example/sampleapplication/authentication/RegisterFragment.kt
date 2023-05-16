package com.example.sampleapplication.authentication

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.sampleapplication.R
import com.example.sampleapplication.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth


class RegisterFragment : Fragment() {
    lateinit var binding: FragmentRegisterBinding
    var mobile_number = ""
    var email = ""
    var password = ""
    var conPassword = ""
    var isMobileFieldEnabled:Boolean = false
    lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)
        binding.llPhone.visibility = View.GONE
        auth = FirebaseAuth.getInstance()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        selectEmailAndPhone()

        binding.btnRegister.setOnClickListener {
            if (validate()){
                register()
            }
        }
    }

    private fun register() {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { register->

        }
    }

    private fun selectEmailAndPhone() {
        binding.email.setBackgroundResource(R.drawable.select_background)

        binding.email.setOnClickListener {
            binding.llEmail.visibility = View.VISIBLE
            binding.llPhone.visibility = View.GONE
            binding.email.setBackgroundResource(R.drawable.select_background)
            binding.mobile.setBackgroundResource(0)
            isMobileFieldEnabled = false
            //isEmailFieldEnabled = true
        }

        binding.mobile.setOnClickListener {
         /*   binding.llEmail.visibility = View.GONE
            binding.llPhone.visibility = View.VISIBLE
            binding.mobile.setBackgroundResource(R.drawable.select_background)
            binding.email.setBackgroundResource(0)
            isMobileFieldEnabled = true*/
            Toast.makeText(context, "Can't go with mobile", Toast.LENGTH_SHORT).show()
        }
    }

    private fun validate(): Boolean {

        when(isMobileFieldEnabled){
            true->{
                mobile_number = binding.etPhone.text.toString()
                password = binding.etPass.text.toString()
                conPassword = binding.etConPass.text.toString()
                if (TextUtils.isEmpty(mobile_number) && mobile_number ==""){
                    binding.etPhone.error = "Mobile Number is blank"
                    binding.etPhone.requestFocus()
                    return false
                }else if (password.isEmpty() && password == ""){
                    binding.etPass.error = "Password is blank"
                    binding.etPass.requestFocus()
                    return false
                }else if (conPassword != password){
                    binding.etConPass.error = "Password does not matched"
                    binding.etConPass.requestFocus()
                    return false
                }
            }
            false->{
                email = binding.etEmail.text.toString()
                password = binding.etPass.text.toString()
                conPassword = binding.etConPass.text.toString()
                if (email.isEmpty() && email ==""){
                    binding.etEmail.error = "Email address is blank"
                    binding.etEmail.requestFocus()
                    return false
                }else if (password.isEmpty() && password == ""){
                    binding.etPass.error = "Password is blank"
                    binding.etPass.requestFocus()
                    return false
                }else if ( conPassword != password){
                    binding.etConPass.error = "Password does not matched"
                    binding.etConPass.requestFocus()
                    return false
                }
            }
        }
        return true
    }
}