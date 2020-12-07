package com.myanmaritc.votingapp.ui.king

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.myanmaritc.votingapp.api.VotingClient
import com.myanmaritc.votingapp.model.KingQueenModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//get data
class KingViewModel : ViewModel() {

    private var kingModel: MutableLiveData<KingQueenModel> = MutableLiveData()

    fun getKing() =kingModel

    fun loadData() {

        var apiClient = VotingClient()
        var apiCall = apiClient.getKing()

        apiCall.enqueue(object : Callback<KingQueenModel>{
            override fun onFailure(call: Call<KingQueenModel>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<KingQueenModel>,
                response: Response<KingQueenModel>
            ) {
                kingModel.value = response.body()
                Log.e("Response>>>>>", response.body().toString())
            }
        })
    }

}