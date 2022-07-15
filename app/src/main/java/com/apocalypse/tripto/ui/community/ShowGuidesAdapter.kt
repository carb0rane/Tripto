package com.apocalypse.tripto.ui.community

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.apocalypse.tripto.R
import kotlinx.android.synthetic.main.feed_suggested_guides_cardview.view.*

class ShowGuidesAdapter(val context: Context, val feed_list: List<GuideReturnData>) :
    RecyclerView.Adapter<ShowGuidesAdapter.ViewHolder>() {
    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        var userPost: TextView
//        var name: TextView
//        var imgviewPostPic: ImageView
//        var imgviewProfilePic: ImageView

        init {
            userPost = itemview.tvGuideDescription
//            name = itemview.tvHomeFeedUserFullName
//            imgviewPostPic = itemview.imageViewHomeFeedMainImage
//            imgviewProfilePic = itemview.imageViewHomeFeedUserProfile

        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ShowGuidesAdapter.ViewHolder {
        var itemview = LayoutInflater.from(context).inflate(R.layout.feed_suggested_guides_cardview, parent, false)
        return ViewHolder(itemview)
    }
    override fun onBindViewHolder(
        holder: ShowGuidesAdapter.ViewHolder,
        position: Int
    ) {
        // holder.name.text = feed_list[position].location
        holder.userPost.text=feed_list[position].desc.toString()
        // val userDetails=feed_list[position].userId
//        if (feed_list[position].img != null) {
//            Picasso.get().load(feed_list[position].img).resize(1000, 1000)
//                .centerInside() .into(holder.imgviewPostPic)
//        }
//        if(userDetails?.profile_pic!=null){
//            Picasso.get().load(userDetails.profile_pic).resize(100, 100)
//                .centerInside() .into(holder.imgviewProfilePic)
//        }
    }

    override fun getItemCount(): Int {
        return feed_list.size
    }


}