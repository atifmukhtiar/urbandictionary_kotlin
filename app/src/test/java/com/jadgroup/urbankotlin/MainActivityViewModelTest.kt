package com.jadgroup.urbankotlin

import com.jadgroup.urbankotlin.pojos.AlbumList
import com.jadgroup.urbankotlin.networks.RetroClient
import okhttp3.internal.notify
import okhttp3.internal.wait
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class MainActivityViewModelTest {



    private val lock = Any()

    @Test
    fun testApiResponseOK() {
        RetroClient.getClient()?.getAlbumList("")?.enqueue(object : Callback<AlbumList?> {
            override fun onResponse(
                call: Call<AlbumList?>,
                response: Response<AlbumList?>
            ) {
                Assert.assertEquals(200, response.code().toLong())
                println("test pass")
                synchronized(lock) { lock.notify() }
            }

            override fun onFailure(call: Call<AlbumList?>, t: Throwable) {
                Assert.fail()
                println("fail")
                synchronized(lock) { lock.notify() }
            }
        })
        synchronized(lock) { lock.wait() }
    }

    /*@Mock
    val viewModel: ViewModelMainActivity = mock(ViewModelMainActivity::class.java);

    @Test
    fun test() {
        val viewModelMainActivity = Mockito.mock(ViewModelMainActivity::class.java);
        val album = Album("defination", "", 1);
        val albumList = ArrayList<Album>()
        albumList.add(album)
        viewModel.getAlbumLiveData().postValue(albumList)

        viewModelMainActivity.getAlbumLiveData().observeForever(Observer {
            assertEquals(albumList.get(0).thumbsUp, it.get(0).thumbsUp)
        })
    }


    var dictionaryAdapter = DictionaryAdapter()
    val rv_dictionary = mock(RecyclerView::class.java)

    @Test
    public fun testAdapterList() {

        val album = Album("defination", "", 1);
        val albumList = ArrayList<Album>()
        albumList.add(album)

        rv_dictionary.apply {
            layoutManager = LinearLayoutManager(context)
            dictionaryAdapter = mock(DictionaryAdapter::class.java)
            adapter = dictionaryAdapter
        }
        dictionaryAdapter.updateData(albumList)
        dictionaryAdapter.itemCount
    }*/

}