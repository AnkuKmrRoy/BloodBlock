package com.mr.mrtestapplication.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.annotation.NonNull
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.leopold.mvvm.ui.BindingFragment
import com.mr.mrtestapplication.R
import com.mr.mrtestapplication.connectivity.model.ImageListData
import com.mr.mrtestapplication.connectivity.model.PaginationData
import com.mr.mrtestapplication.databinding.FragmentImageListBinding
import com.mr.mrtestapplication.ui.adapter.ImageAdapter
import com.mr.mrtestapplication.ui.adapter.onImageClickedListener
import com.mr.mrtestapplication.utils.MrTestConstants
import com.mr.mrtestapplication.utils.MrTestConstants.Companion.PAGE_LIMIT
import com.mr.mrtestapplication.view_model.ImageListViewModel
import kotlinx.android.synthetic.main.fragment_image_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class ImageListFragment : BindingFragment<FragmentImageListBinding>(), onImageClickedListener {


    private var gridLayoutManager:GridLayoutManager? = null
    private  val imageListViewModel: ImageListViewModel by viewModel()
    var imagesList = ArrayList<ImageListData>()
    var adapter: ImageAdapter? = null
    var isLoading = false
    var rowsArrayList: ArrayList<String> = ArrayList()
    var currentPageLimit = PAGE_LIMIT*10;
    companion object {
        @JvmStatic
        fun newInstance() = ImageListFragment()

    }

    override fun getLayoutResId() = R.layout.fragment_image_list;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.imageListViewModel = imageListViewModel
        binding.setLifecycleOwner(this)

        gridLayoutManager = GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)
        rvImageList?.layoutManager = gridLayoutManager
        rvImageList.setHasFixedSize(true)

        displayImages()
    }


    fun displayImages(){
        imageListViewModel.getServicesData(MrTestConstants.API_KEY, currentPageLimit, "g")?.observe(
            viewLifecycleOwner,
            Observer {
                if (it != null) {
                    adapter = ImageAdapter(requireContext(), it, this)
                    rvImageList.adapter = adapter
                }
            })

    }

    override fun imageClicked(url: String) {
        val imageClicked = Bundle().apply {
            putString("IMAGE_URL", url)
        }
        activity?.findNavController(R.id.nav_host_fragment)?.navigate(
            R.id.action_ImageListFragment_to_ViewImageFragment,
            imageClicked
        )

    }

    private fun initScrollListener() {
        rvImageList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(@NonNull recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(@NonNull recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (!isLoading) {
                    if (linearLayoutManager != null ) {

                        loadMore()
                        isLoading = true
                    }
                }
            }
        })
    }

    private fun loadMore() {
        val handler = Handler()
        handler.postDelayed(Runnable {
            //imageList.removeAt(imageList.size - 1)
            //val scrollPosition: Int = imageList.size-1
            //adapter?.notifyItemRemoved(scrollPosition)

            currentPageLimit = currentPageLimit + 25
            //while (currentPageLimit  <= imageListViewModel.totalCount) {
                displayImages()
            //}
            adapter?.notifyDataSetChanged()
            isLoading = false
        }, 2000)
    }

}