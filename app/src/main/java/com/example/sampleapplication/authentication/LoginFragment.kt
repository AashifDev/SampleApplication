package com.example.sampleapplication.authentication

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat.recreate
import androidx.navigation.fragment.findNavController
import com.example.sampleapplication.mainUi.MainActivity
import com.example.sampleapplication.R
import com.example.sampleapplication.databinding.FragmentLoginBinding
import com.example.sampleapplication.session.PrefManager
import com.google.firebase.auth.FirebaseAuth
import java.util.Locale

class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding
    var mobile_number = ""
    var email = ""
    var password = ""
    var isMobileFieldEnabled:Boolean = false
    lateinit var auth: FirebaseAuth
    lateinit var prefManager: PrefManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        binding.llPhone.visibility = View.GONE
        auth = FirebaseAuth.getInstance()
        prefManager = PrefManager(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        selectEmailAndPhone()

        binding.btnSignUp.setOnClickListener {
            findNavController().navigate(R.id.registerFragment)
        }

        binding.btnLogin.setOnClickListener {
            if (validate()){
                login()
                binding.progressCircular.visibility = View.VISIBLE
                /*startActivity(Intent(context,MainActivity::class.java))
                requireActivity().finish()*/
            }
        }

        binding.eng.setOnClickListener {
            loadLocate()
            setLocate("eng")
            recreate(requireActivity())
        }

        binding.hi.setOnClickListener {
            loadLocate()
            setLocate("hi")
            recreate(requireActivity())
        }
    }

    private fun setLocate(lang: String) {
        val locale = Locale(lang)
        Locale.setDefault(locale)

        val config = Configuration()
        config.locale = locale
        config.setLayoutDirection(locale)
        this.resources.updateConfiguration(config,context?.resources?.displayMetrics)
        prefManager.setLang(lang)
    }

    private fun loadLocate(){
        val language = prefManager.getLang()
        if (language != null) {
            setLocate(language)
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
            binding.llEmail.visibility = View.GONE
            binding.llPhone.visibility = View.VISIBLE
            binding.mobile.setBackgroundResource(R.drawable.select_background)
            binding.email.setBackgroundResource(0)
            isMobileFieldEnabled = true
        }
    }

    private fun login() {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { signIn->
            if (signIn.isSuccessful){
                prefManager.saveUser(email)
                startActivity(Intent(context, MainActivity::class.java))
                requireActivity().finish()
                binding.progressCircular.visibility = View.GONE
            }else{
                Toast.makeText(context, "Invalid Credentials!!", Toast.LENGTH_SHORT).show()
                binding.progressCircular.visibility = View.GONE
            }
        }
    }

    private fun validate(): Boolean {
        when(isMobileFieldEnabled){
            true->{
                mobile_number = binding.etPhone.text.toString()
                password = binding.etPass.text.toString()
                if (TextUtils.isEmpty(mobile_number) && mobile_number ==""){
                    binding.etPhone.error = "Mobile Number is blank"
                    binding.etPhone.requestFocus()
                    return false
                }else if (password.isEmpty() && password == ""){
                    binding.etPass.error = "Password is blank"
                    binding.etPass.requestFocus()
                    return false
                }
            }
            false->{
                email = binding.etEmail.text.toString()
                password = binding.etPass.text.toString()
                if (email.isEmpty() && email ==""){
                    binding.etEmail.error = "Email address is blank"
                    binding.etEmail.requestFocus()
                    return false
                }else if (password.isEmpty() && password == ""){
                    binding.etPass.error = "Password is blank"
                    binding.etPass.requestFocus()
                    return false
                }
            }
        }
        return true
    }


}