package com.example.githubclone.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.githubclone.R
import com.example.githubclone.data.models.ItemsData
import com.example.githubclone.data.models.SearchUsersByUsernameResponceData
import com.example.githubclone.databinding.ItemSearchUserBinding

class SearchUserAdapter : ListAdapter<ItemsData, SearchUserAdapter.UserViewHolder>(diffCallBack) {

    inner class UserViewHolder(private val binding: ItemSearchUserBinding): ViewHolder(binding.root) {

        fun bind() {
            val d = getItem(adapterPosition)
            binding.apply {
                Glide.with(ivProf)
                    .load(d.avatar_url)
                    .into(ivProf)

                username.text = d.login
            }
        }
    }

    private object diffCallBack: DiffUtil.ItemCallback<ItemsData>() {
        override fun areItemsTheSame(
            oldItem: ItemsData,
            newItem: ItemsData
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: ItemsData,
            newItem: ItemsData
        ): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            ItemSearchUserBinding.bind(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_search_user, parent, false
                )
            )
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind()
    }
}