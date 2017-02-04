
import com.tanelso2.glmatrix.Mat3
import com.tanelso2.glmatrix.Mat4
import com.tanelso2.glmatrix.Vec3
import org.khronos.webgl.Float32Array
import org.khronos.webgl.WebGLProgram
import org.khronos.webgl.WebGLShader
import org.w3c.dom.HTMLCanvasElement
import kotlin.browser.document
import kotlin.browser.window
import org.khronos.webgl.WebGLRenderingContext as GL

class WebGLWrapper {
    val canvas: HTMLCanvasElement = document.getElementById("webglCanvas") as HTMLCanvasElement
    val webgl: GL = canvas.getContext("webgl") as GL
    val shaderProgram: WebGLProgram = webgl.createProgram() ?: throw IllegalStateException("Could not initialize shader program")

    init {
        webgl.enable(GL.DEPTH_TEST)
    }

    val windowWidth = 800
    val windowHeight = 600

    private val vertexShaderLocation = "vertex-shader.glsl"
    private val fragmentShaderLocation = "frag-shader.glsl"
    private val objFileLocation = "teapot.obj"
    val resourceList = arrayOf(
            fragmentShaderLocation,
            vertexShaderLocation,
            objFileLocation
    )

    val resourceLoader = ResourceLoader(*resourceList)
    val objFileLoader: ObjLoader by lazy { ObjLoader(resourceLoader[objFileLocation]!!) }

    fun setup() {
        if(resourceLoader.allLoaded()) {
            compileShaderProgram()

            setupAttribute("aVertexPosition", objFileLoader.getVertices())
            setupAttribute("aVertexNormal", objFileLoader.getVertexNormals())

            val lightPos = arrayOf(
                    20.0f,
                    -20.0f,
                    5.5f
            )
            val ambientColor = arrayOf(
                    0.1f,
                    0.2f,
                    0.2f
            )
            val specularColor = arrayOf(
                    0.3f,
                    0.6f,
                    0.0f
            )
            val diffuseColor = arrayOf(
                    0.5f,
                    0.5f,
                    0.5f
            )

            setupUniformVec3Float(lightPos, "uLightPos")
            setupUniformVec3Float(ambientColor, "uAmbientColor")
            setupUniformVec3Float(diffuseColor, "uDiffuseColor")
            setupUniformVec3Float(specularColor, "uSpecularColor")

            val faceIndexBuffer = webgl.createBuffer()
            webgl.bindBuffer(GL.ELEMENT_ARRAY_BUFFER, faceIndexBuffer)
            webgl.bufferData(GL.ELEMENT_ARRAY_BUFFER, objFileLoader.getFaces(), GL.STATIC_DRAW)

            window.requestAnimationFrame { render() }
        } else {
            //enable javascript to context switch and finish our requests
            window.requestAnimationFrame { setup() }
        }
    }

    private fun setupUniformVec3Float(lightPos: Array<Float>, name: String) {
        val lightPositionLoc = webgl.getUniformLocation(shaderProgram, name)
        webgl.uniform3fv(lightPositionLoc, lightPos)
    }

    private fun setupAttribute(attributeName: String, attributeArray: Float32Array) {
        val vertexPositionAttribute = webgl.getAttribLocation(shaderProgram, attributeName)
        webgl.enableVertexAttribArray(vertexPositionAttribute)
        val vertexPositionBuffer = webgl.createBuffer()
        webgl.bindBuffer(GL.ARRAY_BUFFER, vertexPositionBuffer)
        webgl.bufferData(GL.ARRAY_BUFFER, attributeArray, GL.STATIC_DRAW)
        webgl.vertexAttribPointer(vertexPositionAttribute, 3, GL.FLOAT, false, 0, 0)
    }

    private fun compileShaderProgram() {
        val vertexShaderSource = resourceLoader[vertexShaderLocation]!!
        val vertexShader = getVertexShader(vertexShaderSource)
        val fragmentShaderSource = resourceLoader[fragmentShaderLocation]!!
        val fragmentShader = getFragmentShader(fragmentShaderSource)
        webgl.attachShader(shaderProgram, vertexShader)
        webgl.attachShader(shaderProgram, fragmentShader)
        webgl.linkProgram(shaderProgram)
        webgl.useProgram(shaderProgram)
    }

    var rotZ = 2 * Math.PI
    var transZ = 2 * Math.PI

    fun getFragmentShader(source: String): WebGLShader = getShader(source, GL.FRAGMENT_SHADER)
    fun getVertexShader(source: String): WebGLShader = getShader(source, GL.VERTEX_SHADER)


    private fun getShader(shaderSource: String, shaderType: Int): WebGLShader {
        val shader = webgl.createShader(shaderType)
        webgl.shaderSource(shader, shaderSource)
        webgl.compileShader(shader)
        if(!(webgl.getShaderParameter(shader, GL.COMPILE_STATUS) as Boolean)) {
            println(webgl.getShaderInfoLog(shader))
        }
        return shader ?: throw IllegalStateException("Shader is null!")
    }

    fun draw() {
        rotZ -= 0.02
        transZ += 0.02
        val transX = 0f
        val transY = 0f
        val transZ = Math.sin(transZ).toFloat()
        val mvMatrix = Mat4()
        mvMatrix.translate(Vec3(transX, transY, transZ))
        mvMatrix.scale(Vec3(1f, 1f, 1f))
        mvMatrix.rotateX(rotZ)
        mvMatrix.rotateY(0)
        mvMatrix.rotateZ(0)
        val nMatrix = Mat3.fromMat4(mvMatrix)
        nMatrix.transpose()
        nMatrix.invert()
        val nMatrixUniform = webgl.getUniformLocation(shaderProgram, "uNMatrix")
        webgl.uniformMatrix3fv(nMatrixUniform, false, nMatrix.array)
        val translateMatrix = Mat4()
        translateMatrix.translate(Vec3(0f, 0f, -5.5f))
        val perspectiveMatrix = Mat4()
        perspectiveMatrix.perspective(1, (Math.PI / 2).toFloat(), 0.5f, 100f)
        val finalMatrix = perspectiveMatrix * translateMatrix * mvMatrix
        val mvMatrixUniform = webgl.getUniformLocation(shaderProgram, "uMVMatrix")
        webgl.uniformMatrix4fv(mvMatrixUniform, false, finalMatrix.array)
        webgl.drawElements(GL.TRIANGLES, objFileLoader.getNumFaces() * 3, GL.UNSIGNED_SHORT, 0)
    }

    fun render() {
        webgl.clearColor(0f, 0f, 0f, 1f)
        webgl.clear(GL.COLOR_BUFFER_BIT or GL.DEPTH_BUFFER_BIT)
        draw()
        window.requestAnimationFrame { render() }
    }
}

fun main(args: Array<String>) {
    document.body?.onload = {
        val html = WebGLWrapper()
        window.requestAnimationFrame { html.setup() }
    }
}
