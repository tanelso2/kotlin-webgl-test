import org.khronos.webgl.WebGLRenderingContext
import org.khronos.webgl.WebGLShader

private val vertexShaderSource = """
   attribute vec3 aVertexPosition;
   attribute vec4 aVertexColor;

    uniform mat4 uMVMatrix;
    varying vec4 vColor;

    void main(void) {
        gl_Position = uMVMatrix*vec4(aVertexPosition, 1.0);
        vColor = aVertexColor;
    }
"""

private val fragmentShaderSource = """
    precision mediump float;
    varying vec4 vColor;
    void main(void) {
            gl_FragColor = vColor;
     }
"""

fun getFragmentShader(webgl: WebGLRenderingContext): WebGLShader = getShader(webgl, fragmentShaderSource, WebGLRenderingContext.FRAGMENT_SHADER)
fun getVertexShader(webgl: WebGLRenderingContext): WebGLShader = getShader(webgl, vertexShaderSource, WebGLRenderingContext.VERTEX_SHADER)


private fun getShader(webgl: WebGLRenderingContext, shaderSource: String, shaderType: Int): WebGLShader {
    val shader = webgl.createShader(shaderType)
    webgl.shaderSource(shader, shaderSource)
    webgl.compileShader(shader)
    return shader ?: throw IllegalStateException("Shader is null!")
}


