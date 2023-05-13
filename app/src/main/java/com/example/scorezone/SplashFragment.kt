package com.example.scorezone

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.scorezone.databinding.FragmentSplashBinding


class SplashFragment : Fragment() {


    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            Handler(Looper.myLooper()!!).postDelayed(
                {
               findNavController().navigate(R.id.homeFragment)
            },3000

            )
        }

        catch (e:Exception){
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}