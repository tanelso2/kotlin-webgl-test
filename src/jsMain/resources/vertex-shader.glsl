attribute vec3 aVertexPosition;
attribute vec3 aVertexNormal;

uniform mat4 uMMatrix;
uniform mat4 uVMatrix;
uniform mat4 uPMatrix;
uniform mat3 uNMatrix;

varying vec3 vertPos;
varying vec3 normalInterp;

void main(void) {
    vec4 vertPos4 = uPMatrix * uVMatrix * uMMatrix * vec4(aVertexPosition, 1.0);
    gl_Position = vertPos4;
    gl_PointSize = 10.0;
    vertPos = vec3(vertPos4) / vertPos4.w;
    normalInterp = uNMatrix * aVertexNormal;
}
