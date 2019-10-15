
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
import com.monika.jethani.yasma.comments.viewmodel.CommentsVMFactory
import com.monika.jethani.yasma.comments.models.Comment
import com.monika.jethani.yasma.comments.viewmodel.CommentsViewModel
import kotlinx.android.synthetic.main.fragment_comment_list.*


/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [PostFragment.OnListFragmentInteractionListener] interface.
 */
class CommentFragment : Fragment() {


    private var listener: OnCommentsFragmentInteractionListener? = null

    private lateinit var model: CommentsViewModel

    val args: CommentFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_comment_list, container, false)

        return view

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        commentList.layoutManager = LinearLayoutManager(context)

        val postAdapter = MyCommentRecyclerViewAdapter(listener)
        commentList.adapter = postAdapter
        model = activity?.run {
            ViewModelProviders.of(
                this,
                CommentsVMFactory(RepositoryFactory.createCommentsRepository())
            )[CommentsViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        model.allComments.observe(this@CommentFragment.activity!!, Observer {
            // Update the UI

            postAdapter.replaceItems(it)

        })

        model.getComments(args.PostId)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnCommentsFragmentInteractionListener) {
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
    interface OnCommentsFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction(item: Comment?)
    }

}
