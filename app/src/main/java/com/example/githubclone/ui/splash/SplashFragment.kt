package com.example.githubclone.ui.splash

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.githubclone.ui.MainActivity
import com.example.githubclone.R
import com.example.githubclone.data.local.LocalStorage
import com.example.githubclone.databinding.FragmentSplashBinding
import kotlinx.coroutines.delay

class SplashFragment : Fragment(R.layout.fragment_splash) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenResumed {
            delay(2300)
            if (LocalStorage().isLogin) {
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToContainerHomeFragment())
            } else {
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToFirstFragment())
            }
        }
    }
}