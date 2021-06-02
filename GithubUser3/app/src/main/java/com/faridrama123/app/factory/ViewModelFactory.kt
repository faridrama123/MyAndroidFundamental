package com.faridrama123.app.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.faridrama123.app.data.GithubRepository
import com.faridrama123.app.di.Injection
import com.faridrama123.app.ui.detail.DetailViewModel
import com.faridrama123.app.ui.detail.follower.FollowerViewModel
import com.faridrama123.app.ui.detail.following.FollowingViewModel
import com.faridrama123.app.ui.favorites.FavoritesViewModel
import com.faridrama123.app.ui.search.SearchViewModel


class ViewModelFactory private constructor(private val githubRepository: GithubRepository) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
                instance ?: synchronized(this) {
                    ViewModelFactory(Injection.provideRepository(context)).apply {
                        instance = this
                    }
                }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SearchViewModel::class.java) -> {
                SearchViewModel(githubRepository) as T
            }

            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(githubRepository) as T
            }
            modelClass.isAssignableFrom(FollowerViewModel::class.java) -> {
                FollowerViewModel(githubRepository) as T
            }

            modelClass.isAssignableFrom(FollowingViewModel::class.java) -> {
                FollowingViewModel(githubRepository) as T
            }

            modelClass.isAssignableFrom(FavoritesViewModel::class.java) -> {
                FavoritesViewModel(githubRepository) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}
