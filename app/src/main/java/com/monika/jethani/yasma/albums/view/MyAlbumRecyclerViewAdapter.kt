package com.monika.jethani.yasma.albums.view

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.monika.jethani.yasma.R

import com.monika.jethani.yasma.albums.models.Album

import kotlinx.android.synthetic.main.fragment_album.view.*

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MyAlbumRecyclerViewAdapter(
    private val mListener: AlbumFragment.OnAlbumFragmentInteractionListener?
) : RecyclerView.Adapter<MyAlbumRecyclerViewAdapter.ViewHolder>() {
    private var albums: List<Album> = listOf()
    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Album
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_album, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = albums[position]

        holder.mIdView.text = "Album: " + item.id
        holder.mTitleView.text = "Title: " + item.title

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = albums.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mTitleView: TextView = mView.albumTitle
        val mIdView: TextView = mView.albumId

        override fun toString(): String {
            return super.toString() + " '" + mTitleView.text + "'"
        }
    }

    fun replaceItems(items: List<Album>) {
        this.albums = items
        notifyDataSetChanged()
    }
}
