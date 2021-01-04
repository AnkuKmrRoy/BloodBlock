package com.mr.mrtestapplication.ui.fragment

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.leopold.mvvm.ui.BindingFragment
import com.mr.mrtestapplication.databinding.FragmentViewImageBinding

import com.mr.mrtestapplication.R
import com.mr.mrtestapplication.view_model.ImageListViewModel
import kotlinx.android.synthetic.main.fragment_view_image.*
import kotlinx.android.synthetic.main.image_entry.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ViewImageFragment : BindingFragment<FragmentViewImageBinding>() {
    private  val imageListViewModel: ImageListViewModel by viewModel()
    companion object {
        @JvmStatic
        fun newInstance() = ViewImageFragment()
    }

    override fun getLayoutResId() = R.layout.fragment_view_image;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.imageViewModel = imageListViewModel
        binding.setLifecycleOwner(this)
        val imageUrl = arguments?.getString("IMAGE_URL")
        Glide.with(context).load(imageUrl).into(gifImageDetails)
    }

}