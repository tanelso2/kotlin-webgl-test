import com.tanelso2.glmatrix.Mat4
import com.tanelso2.glmatrix.Vec3
import com.tanelso2.glmatrix.mat4
import org.khronos.webgl.*
import org.khronos.webgl.WebGLRenderingContext as GL
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.HTMLElement
import kotlin.browser.document
import kotlin.browser.window

class HTMLElements {
    val container: HTMLElement = document.createElement("div") as HTMLElement
    val canvas: HTMLCanvasElement = document.createElement("canvas") as HTMLCanvasElement
    var webgl: GL

    val windowWidth = 800
    val windowHeight = 600

    var counter = 0

    val p: List<Point> = listOf(
            Point(-0.8f, 0.8f, 0.0f),
            Point(-0.2f, 0.8f, 0.0f),
            Point(0.2f, 0.8f, 0.0f),
            Point(0.8f, 0.8f, 0.0f),
            Point(-0.8f, 0.6f, 0.0f),
            Point(-0.2f, 0.6f, 0.0f),
            Point(0.2f, 0.6f, 0.0f),
            Point(0.8f, 0.6f, 0.0f),

            Point(-0.8f, -0.6f, 0.0f),
            Point(-0.2f, -0.6f, 0.0f),
            Point(0.2f, -0.6f, 0.0f),
            Point(0.8f, -0.6f, 0.0f),
            Point(-0.8f, -0.8f, 0.0f),
            Point(-0.2f, -0.8f, 0.0f),
            Point(0.2f, -0.8f, 0.0f),
            Point(0.8f, -0.8f, 0.0f)
    )

    val trianglesArray: List<Point> = listOf(
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
    val colorList: List<Float> = trianglesArray.flatMap { it.color }
    val shaderProgram: WebGLProgram

    init {
        container.setAttribute("style", "position: relative")
        canvas.setAttribute("style", "position: absolute; left: 0px; top: 0px; z-index: 10; width: ${windowWidth}px; height: ${windowHeight}px")
        document.body?.appendChild(container)
        container.appendChild(canvas)


        webgl = canvas.getContext("webgl") as GL
        webgl.viewport(0, 0, canvas.width, canvas.height)

        shaderProgram = webgl.createProgram() ?: throw IllegalStateException("Could not initialize shader program")
        val vertexShader = getVertexShader(webgl)
        val fragmentShader = getFragmentShader(webgl)
        webgl.attachShader(shaderProgram, vertexShader)
        webgl.attachShader(shaderProgram, fragmentShader)
        webgl.linkProgram(shaderProgram)
        webgl.useProgram(shaderProgram)

        val vertexPositionAttribute = webgl.getAttribLocation(shaderProgram, "aVertexPosition")
        webgl.enableVertexAttribArray(vertexPositionAttribute)
        val vertexPositionBuffer = webgl.createBuffer()
        webgl.bindBuffer(GL.ARRAY_BUFFER, vertexPositionBuffer)
        val vertexArray = vertexList.toTypedArray()
        webgl.bufferData(GL.ARRAY_BUFFER, Float32Array(vertexArray), GL.STATIC_DRAW)
        webgl.vertexAttribPointer(vertexPositionAttribute, 3, GL.FLOAT, false, 0, 0)

        val vertexColorAttribute = webgl.getAttribLocation(shaderProgram, "aVertexColor")
        webgl.enableVertexAttribArray(vertexColorAttribute)
        val vertexColorBuffer = webgl.createBuffer()
        webgl.bindBuffer(GL.ARRAY_BUFFER, vertexColorBuffer)
        val colorArray = colorList.toTypedArray()
        webgl.bufferData(GL.ARRAY_BUFFER, Float32Array(colorArray), GL.STATIC_DRAW)
        webgl.vertexAttribPointer(vertexColorAttribute, 4, GL.FLOAT, false, 0, 0)
    }

    var rotZ = 2 * Math.PI
    var transZ = 2 * Math.PI
    fun draw() {
        rotZ -= 0.02
        transZ += 0.02
        val transX = 0f
        val transY = 0f
        val transZ = Math.sin(transZ).toFloat()
        val mvMatrix = Mat4()
        mvMatrix.translate(Vec3(transX, transY, transZ))
        mvMatrix.scale(Vec3(1f, 1f, 1f))
        mvMatrix.rotateX(0f)
        mvMatrix.rotateY(0f)
        mvMatrix.rotateZ(rotZ.toFloat())
        val translateMatrix = Mat4()
        translateMatrix.translate(Vec3(0f, 0f, -1.5f))
        val perspectiveMatrix = Mat4()
        perspectiveMatrix.perspective(1f, (Math.PI / 2).toFloat(), 0.5f, 100f)
        val finalMatrix = perspectiveMatrix.multiply(translateMatrix).multiply(mvMatrix)
        val mvMatrixUniform = webgl.getUniformLocation(shaderProgram, "uMVMatrix")
        webgl.uniformMatrix4fv(mvMatrixUniform, false, finalMatrix.array)
        webgl.drawArrays(GL.TRIANGLES, 0, trianglesArray.size)

    }

    fun render() {
        webgl.clearColor(0f, 0f, 0f, 1f)
        webgl.clear(GL.COLOR_BUFFER_BIT or GL.DEPTH_BUFFER_BIT)
        draw()
        window.requestAnimationFrame { render() }
    }
}

data class Point(val x: Float, val y: Float, val z: Float) {
    val color: List<Float> = listOf(
            Math.random().toFloat(),
            Math.random().toFloat(),
            Math.random().toFloat(),
            1f
    )
}

fun main(args: Array<String>) {
    val html = HTMLElements()
    html.webgl.clearColor(0f, 0f, 0f, 1f)
    window.requestAnimationFrame { html.render() }
}
