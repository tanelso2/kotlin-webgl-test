import com.tanelso2.glmatrix.Mat3
import com.tanelso2.glmatrix.Mat4
import com.tanelso2.glmatrix.Vec3
import org.khronos.webgl.WebGLRenderingContext
import kotlin.math.PI

class TeapotObj(private val webgl: WebGLRenderingContext, private val objFileSource: String, private val shaderProgram: ShaderProgram) {
    private val objLoader = ObjLoader(objFileSource)
    private val faces = objLoader.getFaces()
    private val vertices = objLoader.getVertices()
    private val vertexNormals = objLoader.getVertexNormals()
    private val numFaces = objLoader.getNumFaces()

    private val faceIndexBuffer = webgl.createBuffer()
    private val pMatrix = Mat4.perspective(PI / 3, 16.0 / 9.0, 0.1, 60.0)
    private val vMatrix = Mat4.lookAt(Vec3(20, 20, 20), Vec3(0, 0, 0), Vec3(0, 0, 1))

    private fun setupFaceBuffer() {
        webgl.bindBuffer(WebGLRenderingContext.ELEMENT_ARRAY_BUFFER, faceIndexBuffer)
        webgl.bufferData(WebGLRenderingContext.ELEMENT_ARRAY_BUFFER, faces, WebGLRenderingContext.STATIC_DRAW)
    }

    private fun setupAttributes() {
        shaderProgram.apply {
            setupAttribute("aVertexPosition", vertices)
            setupAttribute("aVertexNormal", vertexNormals)
        }
    }

    private fun setupMatrices(scaleFactor: Double, rotation: Double) {
        val mMatrix = Mat4()
        mMatrix.scale(scaleFactor)
        mMatrix.rotateX(PI / 2)
        mMatrix.rotateY(rotation)

        val nMatrix = Mat3.fromMat4(vMatrix * mMatrix)
        nMatrix.transpose()
        nMatrix.invert()

        shaderProgram.apply {
            setupUniformMat3("uNMatrix", nMatrix)
            setupUniformMat4("uPMatrix", pMatrix)
            setupUniformMat4("uVMatrix", vMatrix)
            setupUniformMat4("uMMatrix", mMatrix)
        }
    }

    private fun setupLight(light: LightSource) {
        shaderProgram.apply {
            setupUniformVec3Float("uLightPos", light.pos)
            setupUniformVec3Float("uAmbientColor", light.ambientColor)
            setupUniformVec3Float("uDiffuseColor", light.diffuseColor)
            setupUniformVec3Float("uSpecularColor", light.specularColor)
        }
    }

    fun draw(scaleFactor: Double, rotation: Double, shininess: Double, light: LightSource) {
        shaderProgram.useProgram()
        setupAttributes()
        setupFaceBuffer()
        setupMatrices(scaleFactor, rotation)
        setupShininess(shininess)
        setupLight(light)
        webgl.drawElements(WebGLRenderingContext.TRIANGLES, numFaces * 3, WebGLRenderingContext.UNSIGNED_SHORT, 0)
    }

    private fun setupShininess(shininess: Double) {
        shaderProgram.setupUniformFloat("shininess", shininess.toFloat())
    }
}