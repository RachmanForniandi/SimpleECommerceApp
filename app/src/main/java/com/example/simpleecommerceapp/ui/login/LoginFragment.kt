package com.example.simpleecommerceapp.ui.login

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.simpleecommerceapp.R
import com.example.simpleecommerceapp.models.login.ResponseLogin
import com.example.simpleecommerceapp.utils.hide
import com.example.simpleecommerceapp.utils.show
import kotlinx.android.synthetic.main.login_fragment.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.yesButton

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)


        cvLogin.onClick {
            viewModel.loginUser(etUsername.text.toString(),etPassword.text.toString())
        }

        attachToObserve()
    }

    private fun attachToObserve() {
        viewModel.responseLogin.observe(this, Observer { showResponse(it) })
        viewModel.apiError.observe(this, Observer { showLoginError(it) })

        viewModel.isEmpty.observe(this, Observer { showEmpty(it) })
        viewModel.isLoading.observe(this, Observer { showLoading(it) })
    }

    private fun showLoading(it: Boolean?) {
        if(it ?:false){
            pbLogin.show()
        }else{
            pbLogin.hide()
        }
    }

    private fun showEmpty(it: Boolean?) {
        if(it ?:false){
            alert("Username or Pass are required.Please fill it.","Message"){
                yesButton {}
            }.show()
        }
    }


    private fun showLoginError(it: Throwable) {
        alert(it?.message.toString(),"Error"){
            yesButton {}
        }.show()
    }

    private fun showResponse(it:ResponseLogin){
        if(it?.status ?:false){
            alert("Login success","Confirmation"){
                yesButton {}
            }.show()
        }else{
            alert("username or Password invalid","Confirmation"){
                yesButton {}
            }.show()
        }
    }

}
