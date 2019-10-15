package com.monika.jethani.yasma.posts.view

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.monika.jethani.yasma.R

import com.monika.jethani.yasma.posts.models.Post

import kotlinx.android.synthetic.main.fragment_post.view.*

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MyPostRecyclerViewAdapter(
    private val mListener: PostFragment.OnListFragmentInteractionListener?
) : RecyclerView.Adapter<MyPostRecyclerViewAdapter.ViewHolder>() {
    private var posts: List<Post> = listOf()
    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Post
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_post, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = posts[position]

        holder.mIdView.text = "Post: " + item.id
        holder.mTitleView.text = "Title: " + item.title
        holder.mBodyView.text = "Body: " + item.body

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = posts.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mTitleView: TextView = mView.postTitle
        val mBodyView: TextView = mView.postBody
        val mIdView: TextView = mView.postId

        override fun toString(): String {
            return super.toString() + " '" + mBodyView.text + "'"
        }
    }

    fun replaceItems(items: List<Post>) {
        this.posts = items
        notifyDataSetChanged()
    }
}
