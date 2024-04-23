
import com.tanelso2.glmatrix.Mat3
import com.tanelso2.glmatrix.Mat4
import com.tanelso2.glmatrix.Vec3
import org.khronos.webgl.Float32Array
import org.khronos.webgl.WebGLProgram
import org.khronos.webgl.WebGLShader
import org.w3c.dom.HTMLCanvasElement
import kotlinx.browser.document
import kotlinx.browser.window
import org.khronos.webgl.WebGLUniformLocation
import kotlin.math.PI
import org.khronos.webgl.WebGLRenderingContext as GL

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

class ShaderProgram(private val webgl: GL) {
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
        println(webgl.getProgramInfoLog(program))
    }

    fun setupAttribute(name: String, array: Float32Array) {
        val attribute = webgl.getAttribLocation(program, name)
        webgl.enableVertexAttribArray(attribute)
        val vertexPositionBuffer = webgl.createBuffer()
        webgl.bindBuffer(GL.ARRAY_BUFFER, vertexPositionBuffer)
        webgl.bufferData(GL.ARRAY_BUFFER, array, GL.STATIC_DRAW)
        webgl.vertexAttribPointer(attribute, 3, GL.FLOAT, false, 0, 0)
    }

    fun getUniformLocation(name: String): WebGLUniformLocation =
        webgl.getUniformLocation(program, name)
            ?: throw IllegalArgumentException("Could not find uniform named '$name'")

    fun setupUniformVec3(name: String, value: Vec3) {
        webgl.uniform3fv(getUniformLocation(name), value.array)
    }

    fun setupUniformVec3(name: String, value: Array<Float>) {
        webgl.uniform3fv(getUniformLocation(name), value)
    }

    fun setupUniform1Float(name: String, value: Float) {
        webgl.uniform1f(getUniformLocation(name), value)
    }

    fun setupUniformMat3(name: String, value: Mat3, transpose: Boolean = false) {
        webgl.uniformMatrix3fv(getUniformLocation(name), transpose, value.array)
    }

    fun setupUniformMat4(name: String, value: Mat4, transpose: Boolean = false) {
        webgl.uniformMatrix4fv(getUniformLocation(name), transpose, value.array)
    }
}

class LightSource(var pos: Array<Float>, var ambientColor: Array<Float>, var diffuseColor: Array<Float>, var specularColor: Array<Float>)

class TeapotObj(private val webgl: GL, private val objLoader: ObjLoader, private val shaderProgram: ShaderProgram) {
    val faceIndexBuffer = webgl.createBuffer()
    val vertices = objLoader.getVertices()
    val normals = objLoader.getVertexNormals()
    val faces = objLoader.getFaces()
    val numFaces = objLoader.getNumFaces()
    val pMatrix = Mat4.perspective(PI / 3, 16.0 / 9.0, 0.1, 60.0)
    val vMatrix = Mat4.lookAt(Vec3(20, 20, 20), Vec3(0, 0, 0), Vec3(0, 0, 0))

    fun draw(scaleFactor: Double, rotation: Double, shininess: Double, light: LightSource) {
        println("attempting to draw the teapot")
        shaderProgram.apply {
            useProgram()
            setupAttribute("aVertexPosition", vertices)
            setupAttribute("aVertexNormal", normals)
            setupUniform1Float("shininess", shininess.toFloat())
            setupUniformMat4("uPMatrix", pMatrix)
            setupUniformMat4("uVMatrix", vMatrix)
            setupUniformVec3("uLightPos", light.pos)
            setupUniformVec3("uAmbientColor", light.ambientColor)
            setupUniformVec3("uDiffuseColor", light.diffuseColor)
            setupUniformVec3("uSpecularColor", light.specularColor)
        }

        val mMatrix = Mat4()
        mMatrix.scale(scaleFactor)
        mMatrix.rotateX(PI / 2)
        mMatrix.rotateY(rotation)
        shaderProgram.setupUniformMat4("uMMatrix", mMatrix)

        val nMatrix = Mat3.fromMat4(vMatrix * mMatrix)
        nMatrix.transpose()
        nMatrix.invert()
        shaderProgram.setupUniformMat3("uNMatrix", nMatrix)

        webgl.bindBuffer(GL.ELEMENT_ARRAY_BUFFER, faceIndexBuffer)
        webgl.bufferData(GL.ELEMENT_ARRAY_BUFFER, faces, GL.STATIC_DRAW)

        webgl.drawElements(GL.TRIANGLES, numFaces * 3, GL.UNSIGNED_SHORT, 0)
    }
}

class Main {
    val canvas: HTMLCanvasElement = document.getElementById("webglCanvas") as HTMLCanvasElement
    val webgl: GL = canvas.getContext("webgl") as GL
    val shaderProgram: ShaderProgram = ShaderProgram(webgl)

    val scaleFactor by HTMLInputProperty("scaleInput")

    val lightPos: Array<Float> by ArrayOfInputsProperty(
        "lightPosX",
        "lightPosY",
        "lightPosZ"
    )

    val shininess by HTMLInputProperty("shininessInput")

    val rotationSpeed by HTMLInputProperty("rotationSpeedInput")

    var lightSource: LightSource? = null

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
    val teapot: TeapotObj by lazy { TeapotObj(webgl, objFileLoader, shaderProgram) }

    fun setup() {
        if(resourceLoader.allLoaded()) {
            println("All loaded, compiling shaderProgram")
            shaderProgram.compile(resourceLoader[vertexShaderLocation]!!, resourceLoader[fragmentShaderLocation]!!)
            println("Done compiling")

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
            lightSource = LightSource(
                lightPos,
                ambientColor,
                diffuseColor,
                specularColor
            )

            window.requestAnimationFrame { render() }
        } else {
            //enable javascript to context switch and finish our requests
            window.requestAnimationFrame { setup() }
        }
    }


    var rotation = 0.0

    fun draw() {
        rotation += rotationSpeed
        lightSource?.pos = lightPos
        teapot.draw(scaleFactor, rotation, shininess, lightSource!!)
        webgl.flush()
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
        val wrapper = Main()
        window.requestAnimationFrame { wrapper.setup() }
    }
}
