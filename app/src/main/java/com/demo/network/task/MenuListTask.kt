package com.demo.network.task

import com.demo.interfaces.IViewCallback
import com.demo.model.ResponseDTO
import com.ontracpt.network.task.BaseTask
import retrofit2.Call
import retrofit2.Response

class MenuListTask(callBack: IViewCallback<ResponseDTO>) :
    BaseTask(callBack),retrofit2.Callback<ResponseDTO>  {

    var callBack: IViewCallback<ResponseDTO>

    init {
        this.callBack = callBack
    }
    override fun onFailure(call: Call<ResponseDTO>, t: Throwable) {
        this.callBack.onError(t.message, 0, null)
    }

    override fun onResponse(
        call: Call<ResponseDTO>,
        response: Response<ResponseDTO>
    ) {

        if (response.isSuccessful) {
            this.callBack.onSuccess(response.body())
        } else {
            this.callBack.onError(response.message(), response.code(), response.body())
        }

    }

    fun executeTask() {

            try {
                apiInterface.getCatSubCat().enqueue(this)
            } catch (ex: Exception) {
//                callback.onError(Resource.toString(R.string.something_went_wrong_please_try),0,null);
        }
    }

}
