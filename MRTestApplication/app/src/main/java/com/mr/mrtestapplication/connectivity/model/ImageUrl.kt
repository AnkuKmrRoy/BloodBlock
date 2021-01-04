package com.mr.mrtestapplication.connectivity.model

data class ImageUrl(val original:Original){

    data class Original(val url:String)
}