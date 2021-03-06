package com.encora.bloodblock.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.encora.bloodblock.R
import com.encora.bloodblock.data.db.entity.LoginData
import com.encora.bloodblock.databinding.ActivityLoginBinding
import com.encora.bloodblock.ui.bloodbank.BloodBankActivity
import com.encora.bloodblock.ui.hospital.HospitalActivity
import com.encora.bloodblock.ui.patient.PatientActivity
import com.leopold.mvvm.ui.BindingActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.log

class LoginActivity : BindingActivity<ActivityLoginBinding>() , View.OnClickListener {

    override fun getLayoutResId() = R.layout.activity_login

    private  val loginViewModel: LoginViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.loginViewModel = loginViewModel
        binding.setLifecycleOwner(this)

        insertUserCredentials()
        btLogin.setOnClickListener(this)
    }


    fun insertUserCredentials(){
        GlobalScope.launch {
            loginViewModel.saveUserCredentials("5555555555","patient")

            loginViewModel.saveUserCredentials("6666666666","hospital")

            loginViewModel.saveUserCredentials("7777777777","bloodbank")
        }

    }

    override fun onClick(v: View?) {
        when(v!!.getId()){
           R.id.btLogin ->{
               if(edtMobileNo.getText().toString() != "") {
                   var loginData:LoginData
                   GlobalScope.async {
                       loginData = loginViewModel.getExistingUser(edtMobileNo.getText().toString())

                       loginCheck(loginData.mob_no, loginData.user_type)
                   }

               }else{
                   Toast.makeText(this,"Enter Mobile Number",Toast.LENGTH_SHORT).show()
               }

           }
        }
    }

    fun loginCheck(mobNo:String,userType:String){
        if(mobNo!!.equals("5555555555") && userType!!.equals("patient")){
            val intent = Intent(this, PatientActivity::class.java)
            startActivity(intent)
            finish()
        }else if(mobNo!!.equals("6666666666") && userType!!.equals("hospital")){
            val intent = Intent(this, HospitalActivity::class.java)
            startActivity(intent)
            finish()
        }else if(mobNo!!.equals("7777777777") && userType!!.equals("bloodbank")){
            val intent = Intent(this, BloodBankActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            Toast.makeText(this,"Invalid User",Toast.LENGTH_SHORT).show()
        }
    }


}