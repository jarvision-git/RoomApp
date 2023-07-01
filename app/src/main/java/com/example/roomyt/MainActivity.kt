package com.example.roomyt

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomyt.databinding.ActivityMainBinding
import com.example.roomyt.databinding.DialogAddBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val ContactDao=(application as ContactApplication).db.dao()



        binding.fabAdd.setOnClickListener {
            showDialog(ContactDao)

        }

        lifecycleScope.launch{
            ContactDao.fetchAllEmployees().collect{
                val list=ArrayList(it)
                setupListOfDataIntoRecyclerView(list,ContactDao)
            }
        }
    }

    private fun showDialog(contactDao:ContactDao) {
        val customDialog= Dialog(this)
        val dialogBinding=DialogAddBinding.inflate(layoutInflater)
        customDialog.setContentView(dialogBinding.root)
        customDialog.setCanceledOnTouchOutside(false)
        dialogBinding.btnSave.setOnClickListener {
            val fName=dialogBinding.etFN.text.toString()
            val LName=dialogBinding.etLN.text.toString()
            val PNo=dialogBinding.etPN.text.toString()

            lifecycleScope.launch {
                contactDao.upsertContact(Contact(firstName = fName, lastName = LName, phoneNumber = PNo))
                Toast.makeText(applicationContext,"Record Saved", Toast.LENGTH_SHORT).show()
            }
            customDialog.dismiss()

        }
        customDialog.show()

    }

    private fun setupListOfDataIntoRecyclerView(contactList:ArrayList<Contact>,contactDao:ContactDao)
    {
        if (contactList.isNotEmpty()) {
            val itemAdapter = ItemAdapter(contactList)

            binding.Rv.layoutManager = LinearLayoutManager(this)
            binding.Rv.adapter = itemAdapter
        }
        else
        {
            binding.Rv.visibility= View.GONE
        }

    }



}