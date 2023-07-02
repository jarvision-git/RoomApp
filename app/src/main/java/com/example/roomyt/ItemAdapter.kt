package com.example.roomyt

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomyt.databinding.ItemBinding

class ItemAdapter(private val items:ArrayList<Contact>,
                  private val deleteListener:(id:Int)->Unit
):RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    class ViewHolder(binding:ItemBinding):RecyclerView.ViewHolder(binding.root) {
        val llMain = binding.llMain
        val firstName=binding.tvFN
        val lastName=binding.tvLN
        val phoneNumber=binding.tvPhone
        val delete=binding.ivDelete
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val context=holder.itemView.context
        val item=items[position]
        holder.firstName.text=item.firstName
        holder.lastName.text=item.lastName
        holder.phoneNumber.text=item.phoneNumber

        holder.delete.setOnClickListener {
            deleteListener.invoke(item.id)
        }


    }
}