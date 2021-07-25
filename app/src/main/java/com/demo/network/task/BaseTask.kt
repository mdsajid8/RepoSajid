package com.ontracpt.network.task

import com.demo.interfaces.IViewCallback
import com.demo.network.ApiClient
import com.demo.network.request.Payload
import com.ontracpt.network.ApiInterface
import okhttp3.RequestBody

abstract class BaseTask(private val callback: IViewCallback<*>) {

    val apiInterface: ApiInterface
    private var payload: Payload? = null

    init {
        apiInterface = ApiClient.getClient().create(ApiInterface::class.java)
    }

    fun setPayload(payload: Payload?) {
        this.payload = payload
    }

    fun getRequest(): RequestBody? {
        try {
            return RequestBody.create(
                okhttp3.MediaType.parse("application/json; charset=utf-8"),
                payload.toString()
            )
        } catch (e: Exception) {
            return null
        }
    }
    companion object {
        val TAG = BaseTask::class.simpleName
    }
}
