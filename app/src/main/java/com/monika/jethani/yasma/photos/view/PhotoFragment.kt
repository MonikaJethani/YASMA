package com.monika.jethani.yasma.photos.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.monika.jethani.yasma.R
import com.monika.jethani.yasma.RepositoryFactory
import com.monika.jethani.yasma.photos.viewmodel.PhotosVMFactory

import com.monika.jethani.yasma.photos.models.Photo
import com.monika.jethani.yasma.photos.viewmodel.PhotosViewModel
import kotlinx.android.synthetic.main.fragment_photo_list.*

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [PhotoFragment.OnListFragmentInteractionListener] interface.
 */
class PhotoFragment : Fragment() {


    private var listener: OnPhotosFragmentInteractionListener? = null

    private lateinit var model: PhotosViewModel

    val args: PhotoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_photo_list, container, false)

        return view

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        photoList.layoutManager = LinearLayoutManager(context)

        val postAdapter = MyPhotoRecyclerViewAdapter(listener)
        photoList.adapter = postAdapter
        model = activity?.run {
            ViewModelProviders.of(
                this,
                PhotosVMFactory(RepositoryFactory.createPhotosRepository())
            )[PhotosViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        model.allPhotos.observe(this@PhotoFragment.activity!!, Observer {
            // Update the UI

            postAdapter.replaceItems(it)

        })

        model.getPhotos(args.albumId)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnPhotosFragmentInteractionListener) {
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
    interface OnPhotosFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction(item: Photo?)
    }

}
