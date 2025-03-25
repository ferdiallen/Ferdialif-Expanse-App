package main.core.util

/*
suspend inline fun <reified T> HttpClient.safeCall(
    execute: HttpClient.() -> HttpResponse
): Resource<T> {
    val mutex = Mutex()
    return mutex.withLock {
        try {
            val response = execute()
            when (response.status.value) {
                in 200..299 -> Resource.Success(response.body())
                else -> Resource.Error(null, response.status.description)
            }
        } catch (e: CancellationException){
            throw e
        }
        catch (e: Exception) {
            Resource.Error(null, e.message ?: "Unknown Error")
        } catch (e: HttpRequestTimeoutException) {
            Resource.Error(null, e.message ?: "Unknown Error")
        } catch (e: ClientRequestException) {
            Resource.Error(null, e.message ?: "Unknown Error")
        } catch (e: SerializationException) {
            Resource.Error(null, e.message ?: "Unknown Error")
        }

    }
}*/
