package com.example.githubclone.ui.profile

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.githubclone.R
import com.example.githubclone.databinding.FragmentProfileBinding
import com.example.githubclone.presentation.MainViewModel
import com.example.githubclone.ui.adapters.PopularRepoAdapter
import com.example.githubclone.ui.nestedgraph.ContainerHomeFragmentDirections
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.ldralighieri.corbind.view.clicks

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val binding by viewBinding(FragmentProfileBinding::bind)
    private val viewModel by viewModel<MainViewModel>()
    private lateinit var navController: NavController
    private lateinit var adapter: PopularRepoAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initData()
        initObservers()


        lifecycleScope.launchWhenResumed {
            viewModel.getUserProfileInfo()
            viewModel.getUserRepositories()
        }



    }

    private fun initData() {
        adapter = PopularRepoAdapter()
        binding.recyclerView.adapter = adapter
    }

    @SuppressLint("StringFormatMatches")
    fun initObservers() {
        viewModel.getUserPrInfoSuccessFlow.onEach {

            binding.apply {
                Glide.with(profileImage)
                    .load(it.avatar_url)
                    .into(profileImage)
                tvUser.text = it.login
                tvFollowers.text = getString(R.string.tv_followers, it.followers)
                tvFollowing.text = getString(R.string.tv_following, it.following)
                countRepo.text = it.public_repos.toString()
            }
        }.launchIn(lifecycleScope)

        viewModel.getUserRepositoriesFlow.onEach {
            adapter.submitList(it)
        }.launchIn(lifecycleScope)
    }
    @OptIn(FlowPreview::class)
    private fun initListeners() {
        binding.apply {

            tvRepositories.clicks().debounce(200).onEach {
                navController.navigate(
                    ContainerHomeFragmentDirections.actionContainerHomeFragmentToRepositoriesFragment()
                )
            }.launchIn(lifecycleScope)


        }
    }
}