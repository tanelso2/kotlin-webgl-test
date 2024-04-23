
import org.w3c.dom.HTMLCanvasElement
import kotlinx.browser.document
import kotlinx.browser.window
import org.khronos.webgl.WebGLRenderingContext as GL

class Main {
    private val canvas: HTMLCanvasElement = document.getElementById("webglCanvas") as HTMLCanvasElement
    private val webgl: GL = canvas.getContext("webgl") as GL
    private val shaderProgram: ShaderProgram = ShaderProgram(webgl)

    private val scaleFactor by HTMLInputProperty("scaleInput")

    private val lightPos: Array<Float> by ArrayOfInputsProperty(
        "lightPosX",
        "lightPosY",
        "lightPosZ"
    )

    private val shininess by HTMLInputProperty("shininessInput")

    private val rotationSpeed by HTMLInputProperty("rotationSpeedInput")

    private val vertexShaderLocation = "vertex-shader.glsl"
    private val fragmentShaderLocation = "frag-shader.glsl"
    private val objFileLocation = "teapot.obj"
    private val resourceList = arrayOf(
            fragmentShaderLocation,
            vertexShaderLocation,
            objFileLocation
    )

    private val resourceLoader = ResourceLoader(*resourceList)
    private val teapot: TeapotObj by lazy { TeapotObj(webgl, resourceLoader[objFileLocation]!!, shaderProgram)}
    private val light: LightSource = LightSource(
        lightPos,
        arrayOf(0.1f, 0.1f, 0.1f),
        arrayOf(0.4f, 0.4f, 0.0f),
        arrayOf(1.0f, 1.0f, 1.0f)
    )

    init {
        webgl.enable(GL.DEPTH_TEST)
    }

    fun setup() {
        if (resourceLoader.allLoaded()) {
            val vertexShaderSource = resourceLoader[vertexShaderLocation]!!
            val fragmentShaderSource = resourceLoader[fragmentShaderLocation]!!
            shaderProgram.compile(vertexShaderSource, fragmentShaderSource)

            window.requestAnimationFrame { render() }
        } else {
            //enable javascript to context switch and finish our requests
            window.requestAnimationFrame { setup() }
        }
    }


    var rotation = 0.0

    fun draw() {
        //setupAttributes()
        light.pos = lightPos
        rotation += rotationSpeed

        teapot.draw(scaleFactor, rotation, shininess, light)
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
