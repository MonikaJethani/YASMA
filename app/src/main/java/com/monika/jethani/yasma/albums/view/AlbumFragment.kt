package com.monika.jethani.yasma.albums.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.monika.jethani.yasma.R
import com.monika.jethani.yasma.RepositoryFactory
import com.monika.jethani.yasma.albums.viewmodel.AlbumsVMFactory

import com.monika.jethani.yasma.albums.models.Album
import com.monika.jethani.yasma.albums.viewmodel.AlbumsViewModel
import kotlinx.android.synthetic.main.fragment_album_list.*

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [AlbumFragment.OnListFragmentInteractionListener] interface.
 */
class AlbumFragment : Fragment() {

    private var listener: OnAlbumFragmentInteractionListener? = null

    private lateinit var model: AlbumsViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_album_list, container, false)

        return view

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        albumList.layoutManager = LinearLayoutManager(context)

        val postAdapter = MyAlbumRecyclerViewAdapter(listener)
        albumList.adapter = postAdapter
        model = activity?.run {
            ViewModelProviders.of(
                this,
                AlbumsVMFactory(RepositoryFactory.createAlbumRepository())
            )[AlbumsViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        model.allAlbums.observe(this@AlbumFragment.activity!!, Observer {
            // Update the UI

            postAdapter.replaceItems(it)

        })

        model.getAlbums()
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnAlbumFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson
     * [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnAlbumFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction(item: Album?)
    }
}
