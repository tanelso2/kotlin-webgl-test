import kotlin.browser.window

class ResourceLoader(vararg resourceLocations: String) {
    private class ResourceInfo {
        var loaded = false
        var value: String? = null
    }
    private val resourceMap: MutableMap<String, ResourceInfo> = HashMap()
    private var fetchParams = js("({})")
    init {
        fetchParams.method = "GET"
        fetchParams.cache = "no-store"
        fetchParams.mode = "same-origin"
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
                    resourceMap[uri]?.value = text as String
                    return@then null //to please the compiler
                }
    }

    fun get(name: String): String? {
        return resourceMap[name]?.value
    }

    fun allLoaded(): Boolean {
        return resourceMap.all { it.value.loaded }
    }

}
