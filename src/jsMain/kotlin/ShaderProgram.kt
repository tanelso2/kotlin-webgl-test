import com.tanelso2.glmatrix.Mat3
import com.tanelso2.glmatrix.Mat4
import org.khronos.webgl.Float32Array
import org.khronos.webgl.WebGLProgram
import org.khronos.webgl.WebGLRenderingContext
import org.khronos.webgl.WebGLUniformLocation

class ShaderProgram(private val webgl: WebGLRenderingContext) {
    val program: WebGLProgram =
        webgl.createProgram() ?: throw IllegalStateException("Failed to create shader program")

    fun useProgram() {
        webgl.useProgram(program)
    }

    fun compile(vertexShaderSource: String, fragmentShaderSource: String) {
        val vertexShader = webgl.getVertexShader(vertexShaderSource)
        val fragmentShader = webgl.getFragmentShader(fragmentShaderSource)
        webgl.attachShader(program, vertexShader)
        webgl.attachShader(program, fragmentShader)
        webgl.linkProgram(program)
        useProgram()
    }

    fun setupAttribute(name: String, array: Float32Array) {
        val attribute = webgl.getAttribLocation(program, name)
        webgl.enableVertexAttribArray(attribute)
        val buffer = webgl.createBuffer()
        webgl.bindBuffer(WebGLRenderingContext.ARRAY_BUFFER, buffer)
        webgl.bufferData(WebGLRenderingContext.ARRAY_BUFFER, array, WebGLRenderingContext.STATIC_DRAW)
        webgl.vertexAttribPointer(attribute, 3, WebGLRenderingContext.FLOAT, false, 0, 0)
    }

    fun getUniformLocation(name: String) : WebGLUniformLocation =
        webgl.getUniformLocation(program, name)
            ?: throw IllegalArgumentException("Could not find uniform named '$name'")

    fun setupUniformMat4(name: String, value: Mat4, transpose: Boolean = false) {
        webgl.uniformMatrix4fv(getUniformLocation(name), transpose, value.array)
    }

    fun setupUniformMat3(name: String, value: Mat3, transpose: Boolean = false) {
        webgl.uniformMatrix3fv(getUniformLocation(name), transpose, value.array)
    }

    fun setupUniformFloat(name: String, value: Float) {
        webgl.uniform1f(getUniformLocation(name), value)
    }

    fun setupUniformVec3Float(name: String, value: Array<Float>) {
        webgl.uniform3fv(getUniformLocation(name), value)
    }
}