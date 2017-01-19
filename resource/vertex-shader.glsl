attribute vec3 aVertexPosition;
attribute vec4 aVertexColor;

uniform mat4 uMVMatrix;
varying vec4 vColor;

void main(void) {
    gl_Position = uMVMatrix*vec4(aVertexPosition, 1.0);
    vColor = vec4(
        aVertexPosition.y*0.33 + 0.05,
        0.0,
        0.0,
        1.0
    );
}
