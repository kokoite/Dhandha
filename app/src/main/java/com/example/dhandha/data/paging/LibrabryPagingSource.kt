package com.example.dhandha.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.dhandha.data.local.dao.LibraryUserDao
import com.example.dhandha.data.local.entity.LibraryUserEntity
import kotlinx.coroutines.delay

class LibraryPagingSource (private val dao: LibraryUserDao, private val searchQuery: String): PagingSource<Int, LibraryUserEntity>() {
    override fun getRefreshKey(state: PagingState<Int, LibraryUserEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LibraryUserEntity> {
        return try {

            val currentPage: Int = params.key ?: 0
            if(currentPage > 0) {
                delay(1000)
            }
            val offset = (currentPage)*params.loadSize

            val rentUsers = dao.fetchPaginatedUsers(searchQuery,offset, params.loadSize)
            val prevKey = if(currentPage == 0) null else currentPage-1
            val nextKey = if(rentUsers.size < params.loadSize) null else currentPage + 1
            Log.d(TAG, "pagination loading ${offset} ${rentUsers.count()} $currentPage $prevKey $nextKey ${params.loadSize} ")
            LoadResult.Page(
                data = rentUsers,
                prevKey,
                nextKey
            )
        } catch (error: Error) {
            LoadResult.Error(error)
        }
    }
}