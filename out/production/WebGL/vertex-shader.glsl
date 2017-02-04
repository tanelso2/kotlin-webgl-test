attribute vec3 aVertexPosition;
attribute vec3 aVertexNormal;

uniform mat4 uMVMatrix;
uniform mat3 uNMatrix;
varying vec3 vertPos;
varying vec3 normalInterp;

void main(void) {
    vec4 vertPos4 = uMVMatrix * vec4(aVertexPosition, 1.0);
    gl_Position = vertPos4;
    vertPos = vec3(vertPos4) / vertPos4.w;
    normalInterp = uNMatrix * aVertexNormal;
}
