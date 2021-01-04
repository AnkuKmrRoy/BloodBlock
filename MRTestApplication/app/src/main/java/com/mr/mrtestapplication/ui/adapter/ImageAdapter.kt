package com.mr.mrtestapplication.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mr.mrtestapplication.databinding.ImageEntryBinding
import com.mr.mrtestapplication.R
import com.mr.mrtestapplication.connectivity.model.ImageListData

class ImageAdapter(private val context: Context,private val imageList:ArrayList<ImageListData>,private val imageClickedListener: onImageClickedListener) : RecyclerView.Adapter<ImageListRecyclerViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImageListRecyclerViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ImageEntryBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.image_entry, parent, false)
        return ImageListRecyclerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: ImageListRecyclerViewHolder, position: Int) {
        holder.bind(context,imageList[position], imageClickedListener)
    }


}

class ImageListRecyclerViewHolder(val binding: ImageEntryBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(context: Context,imagesList: ImageListData, clickListener: onImageClickedListener?) {

        Glide.with(context).load(imagesList.images!!.original!!.url).into(binding.gifImage)

        binding.gifImage.setOnClickListener {
            clickListener!!.imageClicked(imagesList.images!!.original!!.url)
        }
    }

}


interface onImageClickedListener {
    fun imageClicked(url: String)
}

/*
class ImageAdapter : BaseAdapter{
    var imagessList = ArrayList<ImageListData>()
    var context: Context? = null
    var imageClickedListener:onImageClickedListener? = null

    constructor(
        context: Context,
        foodsList: ArrayList<ImageListData>,
        imageClickedListener: onImageClickedListener
    ) : super() {
        this.context = context
        this.imagessList = foodsList
        this.imageClickedListener = imageClickedListener
    }

    override fun getCount(): Int {
        return imagessList.size
    }

    override fun getItem(position: Int): Any {
        return imagessList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val imagesList = this.imagessList[position]

        var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var foodView = inflator.inflate(R.layout.image_entry, null)
        Glide.with(context).load(imagesList.images!!.original!!.url).into(foodView.gifImage)


        foodView.gifImage.setOnClickListener{
            //Toast.makeText(context,"CLicked",Toast.LENGTH_SHORT).show()
            imageClickedListener!!.imageClicked(imagesList.images!!.original!!.url)
        }


        return foodView
    }

    interface onImageClickedListener {
        fun imageClicked(url: String)

    }


}*/
