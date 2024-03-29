package com.monika.jethani.yasma.photos.view

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.monika.jethani.yasma.R
import com.monika.jethani.yasma.photos.models.Photo

import kotlinx.android.synthetic.main.fragment_photo.view.*

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MyPhotoRecyclerViewAdapter(
    private val mListener: PhotoFragment.OnPhotosFragmentInteractionListener?
) : RecyclerView.Adapter<MyPhotoRecyclerViewAdapter.ViewHolder>() {


    private var photos: List<Photo> = listOf()
    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Photo
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_photo, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = photos[position]

        holder.mIdView.text = "Photo: " + item.id
        holder.mTitleView.text = "Title: " + item.title

        Glide.with(holder.itemView)
            .load(item.thumbnailUrl)
            .into(holder.mImageView);

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = photos.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.photoId
        val mImageView: ImageView = mView.photoUrl
        val mTitleView: TextView = mView.photoTitle

        override fun toString(): String {
            return super.toString() + " '" + mTitleView.text + "'"
        }
    }

    fun replaceItems(items: List<Photo>) {
        this.photos = items
        notifyDataSetChanged()
    }
}
