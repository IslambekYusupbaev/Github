package com.example.githubclone.ui.paging

import android.annotation.SuppressLint
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.githubclone.data.models.ItemsRepoData
import com.example.githubclone.retrofit.GitHubApi
import okio.IOException
import retrofit2.HttpException

private const val TMDB_STARTING_PAGE_INDEX = 1
const val NETWORK_PAGE_SIZE = 25

class PageSource(
    private val service: GitHubApi,
    private val query: String
) : PagingSource<Int, ItemsRepoData>() {
    override fun getRefreshKey(state: PagingState<Int, ItemsRepoData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    @SuppressLint("SuspiciousIndentation")
    override suspend fun load(params: LoadParams<Int>): PagingSource.LoadResult<Int, ItemsRepoData> {
        val pageIndex = params.key ?: TMDB_STARTING_PAGE_INDEX
        return try {
            val response = service.searchRepoByRepoName(query, pageIndex)
            Log.d("TTTT", "$pageIndex")
            val words = response.body()!!.items
            val nextKey =
                if (words.isEmpty()) {
                    null
                } else {
                    Log.d("TTTT", "${params.loadSize}")
                    pageIndex + (params.loadSize / NETWORK_PAGE_SIZE)
                }
            LoadResult.Page(
                data = words,
                prevKey = if (pageIndex == TMDB_STARTING_PAGE_INDEX) null else pageIndex,
                nextKey = nextKey
            )
        } catch (exception : IOException) {
            return LoadResult.Error(exception)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }
}