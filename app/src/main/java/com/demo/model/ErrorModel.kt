data class ErrorModel(
    val errorMessage: String,
    val errorCode: Int?,
    val dataObject: Any?
)