package com.example.roomyt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.roomyt.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val ContactDao=(application as ContactApplication).db.dao()

        binding.btnAdd.setOnClickListener {
            addRecord(ContactDao)
        }
    }


    fun addRecord(contactDao: ContactDao)
    {
        val fName=binding.tvFN.text.toString()
        val LName=binding.tvLN.text.toString()
        val PNo=binding.tvphno.text.toString()

        lifecycleScope.launch {
            contactDao.upsertContact(Contact(firstName = fName, lastName = LName, phoneNumber = PNo))
            Toast.makeText(applicationContext,"Record Saved", Toast.LENGTH_SHORT).show()
        }


    }
}