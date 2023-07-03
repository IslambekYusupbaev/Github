package com.example.githubclone.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.githubclone.R
import com.example.githubclone.data.models.ItemsRepoData
import com.example.githubclone.databinding.ItemSearchRepoBinding

class SearchRepoAdapter :
    PagingDataAdapter<ItemsRepoData, SearchRepoAdapter.RepoViewHolder>(diffCallBack) {

    inner class RepoViewHolder(private val binding: ItemSearchRepoBinding) :
        ViewHolder(binding.root) {
            fun bind() {
                val d = getItem(absoluteAdapterPosition)
                binding.apply {
                    if (d != null) {
                        Glide.with(ivProf)
                            .load(d.owner.avatar_url)
                            .into(ivProf)
                    }
                    appName.text = d!!.name
                    username.text = d.owner.login
                    tvDesc.text = d.description
                    tvStar.text = d.stargazers_count.toString()
                    language.text = d.language

                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder(
            ItemSearchRepoBinding.bind(
                LayoutInflater.from(
                    parent.context
                ).inflate(R.layout.item_search_repo, parent, false)
            )
        )
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind()
    }

    private object diffCallBack : DiffUtil.ItemCallback<ItemsRepoData>() {
        override fun areItemsTheSame(oldItem: ItemsRepoData, newItem: ItemsRepoData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ItemsRepoData, newItem: ItemsRepoData): Boolean {
            return oldItem == newItem
        }

    }
}