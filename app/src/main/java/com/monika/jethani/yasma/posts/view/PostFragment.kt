package com.monika.jethani.yasma.posts.view

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
import com.monika.jethani.yasma.posts.viewmodel.PostsVMFactory
import com.monika.jethani.yasma.posts.models.Post

import com.monika.jethani.yasma.posts.viewmodel.PostsViewModel
import kotlinx.android.synthetic.main.fragment_post_list.*

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [PostFragment.OnListFragmentInteractionListener] interface.
 */
class PostFragment : Fragment() {


    private var listener: OnListFragmentInteractionListener? = null

    private lateinit var model: PostsViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_post_list, container, false)

        return view

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        list.layoutManager = LinearLayoutManager(context)

        val postAdapter = MyPostRecyclerViewAdapter(listener)
        list.adapter = postAdapter
        model = activity?.run {
            ViewModelProviders.of(
                this,
                PostsVMFactory(RepositoryFactory.createPostRepository())
            )[PostsViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        model.allPosts.observe(this@PostFragment.activity!!, Observer {
            // Update the UI

            postAdapter.replaceItems(it)

        })

        model.getPosts()
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
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
    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction(item: Post?)
    }

}
