package com.encora.bloodblock.ui.login

import androidx.databinding.Observable
import androidx.lifecycle.viewModelScope
import com.encora.bloodblock.core.BaseViewModel
import com.encora.bloodblock.data.db.entity.LoginData
import com.encora.bloodblock.data.repositories.BloodBlockRepositories
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: BloodBlockRepositories) : BaseViewModel(), Observable {



    fun saveUserCredentials(uMobileNo:String,uLoginType:String){
        insertProduct(LoginData(uMobileNo,uLoginType))
    }
    fun insertProduct(userCredentials: LoginData): Job =
        viewModelScope.launch {
            repository.insetUserCredentials(userCredentials)

        }

    fun getExistingUser(mNo:String):LoginData{
        return repository.getUser(mNo)
    }




    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        TODO("Not yet implemented")
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        TODO("Not yet implemented")
    }
}