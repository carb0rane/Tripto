package com.apocalypse.tripto.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.apocalypse.tripto.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.feed_rcview.view.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class FeedAdapter(val context: Context, val feed_list: List<PostItem>) :
    RecyclerView.Adapter<FeedAdapter.ViewHolder>() {
    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        var userPost: TextView
        var name: TextView
        var imgviewPostPic: ImageView
        var imgviewProfilePic: ImageView

        init {
            userPost = itemview.tvHomeFeedPostDescription
            name = itemview.tvHomeFeedUserFullName
            imgviewPostPic = itemview.imageViewHomeFeedMainImage
            imgviewProfilePic = itemview.imageViewHomeFeedUserProfile

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedAdapter.ViewHolder {

        var itemview = LayoutInflater.from(context).inflate(R.layout.feed_rcview, parent, false)
        return ViewHolder(itemview)
    }

    override fun onBindViewHolder(holder: FeedAdapter.ViewHolder, position: Int) {
        // holder.userPost.text=feed_list[position].img.toString()
        holder.name.text = feed_list[position].userId?.name.toString()
        holder.userPost.text=feed_list[position].desc.toString()
        val userDetails=feed_list[position].userId
        if (feed_list[position].img != null) {
            Picasso.get().load(feed_list[position].img).resize(1000, 1000)
                .centerInside() .into(holder.imgviewPostPic)
        }
        if(userDetails?.profile_pic!=null){
            Picasso.get().load(userDetails.profile_pic).resize(100, 100)
                .centerInside() .into(holder.imgviewProfilePic)
        }

    }

    override fun getItemCount(): Int {
        return feed_list.size
    }

}