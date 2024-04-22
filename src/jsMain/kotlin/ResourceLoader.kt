import kotlinx.browser.window
import org.w3c.fetch.*

class ResourceLoader(vararg resourceLocations: String) {
    private class ResourceInfo {
        var loaded = false
        var value: String? = null
    }
    private val resourceMap: MutableMap<String, ResourceInfo> = HashMap()
    private val fetchParams = RequestInit(method = "GET", cache = RequestCache.NO_STORE, mode = RequestMode.SAME_ORIGIN)
    init {
        for (loc in resourceLocations) {
            resourceMap.put(loc, ResourceInfo())
            makeRequest(loc)
        }
    }

    private fun makeRequest(uri: String) {
        window.fetch(uri, fetchParams)
                .then {response -> response.text()}
                .then {text ->
                    resourceMap[uri]?.loaded = true
                    resourceMap[uri]?.value = text
                    return@then null //to please the compiler
                }
                .catch {e ->
                    println("Getting $uri failed with $e. Trying again")
                    makeRequest(uri)
                }
    }

    operator fun get(name: String): String? {
        return resourceMap[name]?.value
    }

    fun allLoaded(): Boolean {
        return resourceMap.all { it.value.loaded }
    }

}
