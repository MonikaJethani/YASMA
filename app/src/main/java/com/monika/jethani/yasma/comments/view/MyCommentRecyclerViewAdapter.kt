
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.monika.jethani.yasma.R
import com.monika.jethani.yasma.comments.models.Comment
import kotlinx.android.synthetic.main.fragment_comment.view.*

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MyCommentRecyclerViewAdapter(
    private val mListener: CommentFragment.OnCommentsFragmentInteractionListener?
) : RecyclerView.Adapter<MyCommentRecyclerViewAdapter.ViewHolder>() {


    private var comments: List<Comment> = listOf()
    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Comment
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_comment, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = comments[position]

        holder.mIdView.text = "Comment: " + item.id
        holder.mNameView.text = "Name: " + item.name
        holder.mEmailView.text = "Name: " + item.email
        holder.mBodyView.text = "Body: " + item.body

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = comments.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mNameView: TextView = mView.commentName
        val mEmailView: TextView = mView.commentEmail
        val mBodyView: TextView = mView.commentBody
        val mIdView: TextView = mView.commentId

        override fun toString(): String {
            return super.toString() + " '" + mBodyView.text + "'"
        }
    }

    fun replaceItems(items: List<Comment>) {
        this.comments = items
        notifyDataSetChanged()
    }
}
