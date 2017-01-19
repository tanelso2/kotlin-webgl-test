
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

    val p: List<Point> = listOf(
            Point(-0.8, 0.8, 0.0),
            Point(-0.2, 0.8, 0.0),
            Point(0.2, 0.8, 0.0),
            Point(0.8, 0.8, 0.0),
            Point(-0.8, 0.6, 0.0),
            Point(-0.2, 0.6, 0.0),
            Point(0.2, 0.6, 0.0),
            Point(0.8, 0.6, 0.0),

            Point(-0.8, -0.6, 0.0),
            Point(-0.2, -0.6, 0.0),
            Point(0.2, -0.6, 0.0),
            Point(0.8, -0.6, 0.0),
            Point(-0.8, -0.8, 0.0),
            Point(-0.2, -0.8, 0.0),
            Point(0.2, -0.8, 0.0),
            Point(0.8, -0.8, 0.0)
    )

    val trianglesArray: List<Point> = listOf(
            //top
            p[0], p[4], p[1],
            p[1], p[4], p[5],
            p[1], p[5], p[2],
            p[2], p[5], p[6],
            p[2], p[6], p[3],
            p[3], p[6], p[7],

            //middle
            p[5], p[9], p[6],
            p[6], p[9], p[10],

            //bottom
            p[8], p[12], p[9],
            p[9], p[12], p[13],
            p[9], p[13], p[10],
            p[10], p[13], p[14],
            p[10], p[14], p[11],
            p[11], p[14], p[15]
    )

    val vertexList: List<Float> = trianglesArray.flatMap { listOf(it.x, it.y, it.z) }
    val colorList: List<Float> = trianglesArray.flatMap { it.color.list() }

    private val vertexShaderLocation = "vertex-shader.glsl"
    private val fragmentShaderLocation = "frag-shader.glsl"
    private val objFileLocation = "teapot.obj"
    val resourceList = arrayOf(
            fragmentShaderLocation,
            vertexShaderLocation,
            objFileLocation
    )

    val resourceLoader = ResourceLoader(*resourceList)
    val objFileLoader: ObjLoader by lazy { ObjLoader(resourceLoader.get(objFileLocation)!!) }

    fun setup() {
        if(resourceLoader.allLoaded()) {
            val vertexShaderSource = resourceLoader.get(vertexShaderLocation)!!
            val vertexShader = getVertexShader(vertexShaderSource)
            val fragmentShaderSource = resourceLoader.get(fragmentShaderLocation)!!
            val fragmentShader = getFragmentShader(fragmentShaderSource)
            webgl.attachShader(shaderProgram, vertexShader)
            webgl.attachShader(shaderProgram, fragmentShader)
            webgl.linkProgram(shaderProgram)
            webgl.useProgram(shaderProgram)

            val vertexPositionAttribute = webgl.getAttribLocation(shaderProgram, "aVertexPosition")
            webgl.enableVertexAttribArray(vertexPositionAttribute)
            val vertexPositionBuffer = webgl.createBuffer()
            webgl.bindBuffer(GL.ARRAY_BUFFER, vertexPositionBuffer)
            val vertexArray = objFileLoader.getVertices()
            webgl.bufferData(GL.ARRAY_BUFFER, vertexArray, GL.STATIC_DRAW)
            webgl.vertexAttribPointer(vertexPositionAttribute, 3, GL.FLOAT, false, 0, 0)

            val vertexColorAttribute = webgl.getAttribLocation(shaderProgram, "aVertexColor")
            webgl.enableVertexAttribArray(vertexColorAttribute)
            val vertexColorBuffer = webgl.createBuffer()
            webgl.bindBuffer(GL.ARRAY_BUFFER, vertexColorBuffer)
            val colorArray = objFileLoader.getColors()
            webgl.bufferData(GL.ARRAY_BUFFER, colorArray, GL.STATIC_DRAW)
            webgl.vertexAttribPointer(vertexColorAttribute, 4, GL.FLOAT, false, 0, 0)

            window.requestAnimationFrame { render() }
        } else {
            //enable javascript to context switch and finish our requests
            window.requestAnimationFrame { setup() }
        }
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
        val translateMatrix = Mat4()
        translateMatrix.translate(Vec3(0f, 0f, -5.5f))
        val perspectiveMatrix = Mat4()
        perspectiveMatrix.perspective(1, (Math.PI / 2).toFloat(), 0.5f, 100f)
        val finalMatrix = perspectiveMatrix.multiply(translateMatrix).multiply(mvMatrix)
        val mvMatrixUniform = webgl.getUniformLocation(shaderProgram, "uMVMatrix")
        webgl.uniformMatrix4fv(mvMatrixUniform, false, finalMatrix.array)
        webgl.drawArrays(GL.TRIANGLES, 0, objFileLoader.getNumPoints())
    }

    fun render() {
        webgl.clearColor(0f, 0f, 0f, 1f)
        webgl.clear(GL.COLOR_BUFFER_BIT or GL.DEPTH_BUFFER_BIT)
        draw()
        window.requestAnimationFrame { render() }
    }
}

data class Point(val x: Float, val y: Float, val z: Float, val color: Color) {
    constructor(x: Number, y: Number, z: Number):
            this(x.toFloat(), y.toFloat(), z.toFloat(), Color.randomColor())

    data class Color(val r: Float, val g: Float, val b: Float, val a: Float) {
        constructor(r: Number, g: Number, b: Number, a:Number):
            this(r.toFloat(), g.toFloat(), b.toFloat(), a.toFloat())

        companion object {
            fun randomColor() = Color(
                    Math.random(),
                    Math.random(),
                    Math.random(),
                    1.0
            )
        }

        fun list() = listOf(r, g, b, a)
    }
}

fun main(args: Array<String>) {
    document.body?.onload = {
        val html = WebGLWrapper()
        window.requestAnimationFrame { html.setup() }
    }
}
