package com.demo.viewmodel

import ErrorModel
import Provider
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demo.interfaces.IViewCallback
import com.demo.model.ResponseDTO

class MenuListViewModel : ViewModel() {

    private var provider: Provider
    private var menuListResponse: MutableLiveData<ResponseDTO>
    private var errorModel: MutableLiveData<ErrorModel>

    init {
        provider = Provider()
        menuListResponse = MutableLiveData()
        errorModel = MutableLiveData()
    }

    fun onError(): MutableLiveData<ErrorModel>? {
        return errorModel
    }

    fun onOrderListResponse(): MutableLiveData<ResponseDTO> {
        return menuListResponse
    }

    fun getOrderListData() {
        val callBack = object : IViewCallback<ResponseDTO> {
            override fun onSuccess(dataObject: ResponseDTO?) {
                menuListResponse.postValue(dataObject)

            }
            override fun onError(
                errorMessage: String,
                errorCode: Int,
                dataObject: ResponseDTO?
            ) {
                errorModel.postValue(ErrorModel(errorMessage, errorCode, dataObject))
            }
        }
        provider.executeOrderListTask(callBack)
    }
}