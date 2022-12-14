package com.example.whatsappclone.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.whatsappclone.R
import com.example.whatsappclone.UserModel
import com.example.whatsappclone.databinding.ChatUserItemLayoutBinding
import java.text.ParsePosition

class ChatAdapter(var context: Context, var list: ArrayList<UserModel>) : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {
   inner class ChatViewHolder(view: View): RecyclerView.ViewHolder(view) {
       var binding : ChatUserItemLayoutBinding = ChatUserItemLayoutBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        return  ChatViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.chat_user_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        var  user = list[position]
        Glide.with(context).load(user.imageUrl).into(holder.binding.profileAvatar)
        holder.binding.userName.text = user.name
    }

    override fun getItemCount(): Int {
      return  list.size
    }
}