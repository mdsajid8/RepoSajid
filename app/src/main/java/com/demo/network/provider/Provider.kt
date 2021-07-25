import com.demo.interfaces.IViewCallback
import com.demo.model.ResponseDTO
import com.demo.network.task.MenuListTask

class Provider {

    fun executeOrderListTask(callBack: IViewCallback<ResponseDTO>) {
        val task = MenuListTask(callBack)
        task.executeTask()
    }

}