import org.khronos.webgl.WebGLRenderingContext as GL
import org.khronos.webgl.WebGLShader

fun GL.getShader(source: String, shaderType: Int): WebGLShader {
    val shader = this.createShader(shaderType)
    this.shaderSource(shader, source)
    this.compileShader(shader)
    if(!(this.getShaderParameter(shader, GL.COMPILE_STATUS) as Boolean)) {
        println(this.getShaderInfoLog(shader))
    }
    return shader ?: throw IllegalStateException("Shader is null!")
}

fun GL.getFragmentShader(source: String): WebGLShader = this.getShader(source, GL.FRAGMENT_SHADER)
fun GL.getVertexShader(source: String): WebGLShader = this.getShader(source, GL.VERTEX_SHADER)
