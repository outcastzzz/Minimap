package com.example.myapplication.presentation

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentSplashScreenBinding
import com.example.myapplication.presentation.adapters.BuildingItemAdapter
import javax.inject.Inject


class SplashScreenFragment : Fragment() {

    private val component by lazy {
        (requireActivity().application as MinimapApp).component
    }

    private var _binding: FragmentSplashScreenBinding? = null
    private val binding: FragmentSplashScreenBinding
        get() = _binding ?: throw RuntimeException("FragmentSplashScreenBinding == null")

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: MainViewModel

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        animateLogo()
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory)[MainViewModel::class.java]
        viewModel.getListOfBuildings()
        launchWelcomeFragment()
    }

    private fun animateLogo() {
        val scaleX = ObjectAnimator.ofFloat(binding.loadIv, "scaleX", 1f, 1.2f, 1f)
        scaleX.repeatCount = Animation.INFINITE
        val scaleY = ObjectAnimator.ofFloat(binding.loadIv, "scaleY", 1f, 1.2f, 1f)
        scaleY.repeatCount = Animation.INFINITE
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(scaleY, scaleX)
        animatorSet.duration = 1000
        animatorSet.start()
    }

    private fun launchWelcomeFragment() {
        findNavController().navigate(R.id.action_splashScreenFragment_to_welcomeFragment)
    }

}