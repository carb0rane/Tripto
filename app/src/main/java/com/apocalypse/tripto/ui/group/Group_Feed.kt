package com.apocalypse.tripto.ui.group

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.apocalypse.tripto.R
import kotlinx.android.synthetic.main.feed_rcview.view.*
import kotlinx.android.synthetic.main.group_feed.*
import kotlinx.android.synthetic.main.group_feed.view.*
import org.w3c.dom.Text

class Group_Feed(val context: Context, val feed_list: List<GroupDataItem>) :
    RecyclerView.Adapter<Group_Feed.ViewHolder>() {
    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        val grpname: TextView
        val grpdate:TextView
        val parentLayout:CardView


        init {
            grpname = itemview.grpname
            grpdate = itemview.grppend
            parentLayout=itemview.grp_feed

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Group_Feed.ViewHolder {

        var itemview = LayoutInflater.from(context).inflate(R.layout.group_feed, parent, false)
//        itemview.grp_feed.setOnClickListener {
//           Log.d("This is test","or more")
//            val i = Intent(context,GroupDetails::class.java)
//            i.addFlags(FLAG_ACTIVITY_NEW_TASK)
//            startActivity(context,i,null)
//        }
        return ViewHolder(itemview)
    }

    override fun onBindViewHolder(holder: Group_Feed.ViewHolder, position: Int) {
        // holder.userPost.text=feed_list[position].img.toString()
holder.parentLayout.setOnClickListener{
    Log.d("This is test"," : "+feed_list[position]._id)
    val i = Intent(context,GroupDetails::class.java)
    i.addFlags(FLAG_ACTIVITY_NEW_TASK)
    i.putExtra("group_id",feed_list[position]._id)
    startActivity(context,i,null)

}
        holder.grpname.text = feed_list[position]._id.toString()
        holder.grpdate.text = feed_list[position].createdAt.toString()
//        if (feed_list[position].img != null) {
//            Picasso.get().load(feed_list[position].img).into(holder.imgview)
//        }

    }

    override fun getItemCount(): Int {
        return feed_list.size
    }

}