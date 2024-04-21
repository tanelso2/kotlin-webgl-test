
import com.tanelso2.glmatrix.Mat3
import com.tanelso2.glmatrix.Mat4
import com.tanelso2.glmatrix.Vec3
import org.khronos.webgl.Float32Array
import org.khronos.webgl.WebGLProgram
import org.khronos.webgl.WebGLShader
import org.w3c.dom.HTMLCanvasElement
import kotlinx.browser.document
import kotlinx.browser.window
import kotlin.math.PI
import org.khronos.webgl.WebGLRenderingContext as GL

class WebGLWrapper {
    val canvas: HTMLCanvasElement = document.getElementById("webglCanvas") as HTMLCanvasElement
    val webgl: GL = canvas.getContext("webgl") as GL
    val shaderProgram: WebGLProgram = webgl.createProgram() ?: throw IllegalStateException("Could not initialize shader program")

    val scaleFactor by HTMLInputProperty("scaleInput")

    val lightPos: Array<Float> by ArrayOfInputsProperty(
        "lightPosX",
        "lightPosY",
        "lightPosZ"
    )

    val shininess by HTMLInputProperty("shininessInput")

    val rotationSpeed by HTMLInputProperty("rotationSpeedInput")

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

            println("Vertex array size = ${objFileLoader.getVertices().length}")
            println("Vertex normal array size = ${objFileLoader.getVertexNormals().length}")


            val ambientColor = arrayOf(
                    0.1f,
                    0.1f,
                    0.1f
            )
            val diffuseColor = arrayOf(
                    0.4f,
                    0.4f,
                    0.0f
            )
            val specularColor = arrayOf(
                    1.0f,
                    1.0f,
                    1.0f
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
        println(webgl.getProgramInfoLog(shaderProgram))
        webgl.useProgram(shaderProgram)
    }

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

    var rotation = 0.0

    fun draw() {
        setupUniformVec3Float(lightPos, "uLightPos")

        val shininessUniform = webgl.getUniformLocation(shaderProgram, "shininess")
        webgl.uniform1f(shininessUniform, shininess.toFloat())

        val pMatrix = Mat4()
        pMatrix.perspective(PI / 3, 16.0 / 9.0, 0.1, 60.0)

        val vMatrix = Mat4()
        vMatrix.lookAt(Vec3(20,20,20), Vec3(0,0,0), Vec3(0,0,1))

        val mMatrix = Mat4()
        mMatrix.scale(scaleFactor)
        rotation += rotationSpeed
        mMatrix.rotateX(PI / 2)
        mMatrix.rotateY(rotation)

        val nMatrix = Mat3.fromMat4(vMatrix * mMatrix)
        nMatrix.transpose()
        nMatrix.invert()

        val nMatrixUniform = webgl.getUniformLocation(shaderProgram, "uNMatrix")
        webgl.uniformMatrix3fv(nMatrixUniform, false, nMatrix.array)

        val pMatrixUniform = webgl.getUniformLocation(shaderProgram, "uPMatrix")
        webgl.uniformMatrix4fv(pMatrixUniform, false, pMatrix.array)

        val vMatrixUniform = webgl.getUniformLocation(shaderProgram, "uVMatrix")
        webgl.uniformMatrix4fv(vMatrixUniform, false, vMatrix.array)

        val mMatrixUniform = webgl.getUniformLocation(shaderProgram, "uMMatrix")
        webgl.uniformMatrix4fv(mMatrixUniform, false, mMatrix.array)

        webgl.drawElements(GL.TRIANGLES, objFileLoader.getNumFaces() * 3, GL.UNSIGNED_SHORT, 0)
    }

    fun render() {
        webgl.clearColor(0f, 0f, 0f, 1f)
        webgl.clear(GL.COLOR_BUFFER_BIT or GL.DEPTH_BUFFER_BIT)
        draw()
        window.requestAnimationFrame { render() }
    }
}

fun main() {
    document.body?.onload = {
        val wrapper = WebGLWrapper()
        window.requestAnimationFrame { wrapper.setup() }
    }
}
