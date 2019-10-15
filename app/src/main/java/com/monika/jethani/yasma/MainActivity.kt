package com.monika.jethani.yasma

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.monika.jethani.yasma.albums.view.AlbumFragment
import com.monika.jethani.yasma.albums.AlbumFragmentDirections
import com.monika.jethani.yasma.albums.models.Album
import com.monika.jethani.yasma.comments.models.Comment
import com.monika.jethani.yasma.photos.models.Photo
import com.monika.jethani.yasma.photos.view.PhotoFragment
import com.monika.jethani.yasma.posts.models.Post
import com.monika.jethani.yasma.posts.view.PostFragment
import com.monika.jethani.yasma.posts.view.PostFragmentDirections

class MainActivity : AppCompatActivity(), PostFragment.OnListFragmentInteractionListener,
    AlbumFragment.OnAlbumFragmentInteractionListener,
    CommentFragment.OnCommentsFragmentInteractionListener,
    PhotoFragment.OnPhotosFragmentInteractionListener {

    override fun onListFragmentInteraction(item: Photo?) {}

    override fun onListFragmentInteraction(item: Comment?) {}

    override fun onListFragmentInteraction(item: Album?) {
        val albumId = item?.id
        val action = AlbumFragmentDirections.actionAlbumFragmentToPhotoFragment(albumId.toString())
        findNavController(R.id.nav_host_fragment).navigate(action)
    }

    override fun onListFragmentInteraction(item: Post?) {
        val postId = item?.id
        val action = PostFragmentDirections.actionPostFragmentToCommentFragment(postId.toString())
        findNavController(R.id.nav_host_fragment).navigate(action)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.postFragment, R.id.albumFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

}
