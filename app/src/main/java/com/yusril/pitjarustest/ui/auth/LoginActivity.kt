package com.yusril.pitjarustest.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.yusril.pitjarustest.R
import com.yusril.pitjarustest.data.db.StoreDao
import com.yusril.pitjarustest.data.db.StoreDatabase
import com.yusril.pitjarustest.data.model.Stores
import com.yusril.pitjarustest.databinding.ActivityLoginBinding
import com.yusril.pitjarustest.ui.MainActivity
import com.yusril.pitjarustest.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var storeDao: StoreDao
    private lateinit var storeDatabase: StoreDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        storeDatabase = StoreDatabase.getDatabase(this)
         storeDao = storeDatabase.storeDao()

        binding.btnLogin.setOnClickListener {
            val username = binding.edtUsername.text.toString()
            val password = binding.edtPassword.text.toString()

            Log.d("username",username)
            Log.d("pass",password)

            if (username.isNotEmpty() && password.isNotEmpty()){
                viewModel.loginResponse(username, password)
                viewModel.getLoginResponse().observe(this) {
                    when (it.status) {
                        Resource.Status.LOADING -> {
                            Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show()
                        }
                        Resource.Status.SUCCESS -> {
                            if (username.isNotEmpty() && password.isNotEmpty()){
                                if (it.data?.status != null && it.data.status != "failure" ) {
                                    Toast.makeText(this, "Login Sukses", Toast.LENGTH_SHORT).show()
                                    val storeData = it.data.stores
                                    CoroutineScope(Dispatchers.IO).launch {
                                        if (storeDao.getAllStores().isEmpty()){
                                            storeDao.insert(storeData)
                                        }
                                    }
                                    startActivity(Intent(this,MainActivity::class.java))
                                    finish()
                                }else{
                                    Toast.makeText(this, it.data?.message, Toast.LENGTH_SHORT).show()
                                }
                            }else{
                                Toast.makeText(this, "username atau password tidak boleh kosong", Toast.LENGTH_SHORT).show()
                            }

                        }
                        Resource.Status.ERROR -> {
                            Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

            }else{
                Toast.makeText(this, "username atau password tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }

        }

    }


}