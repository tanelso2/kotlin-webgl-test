precision mediump float;

uniform vec3 uLightPos;
uniform vec3 uAmbientColor;
uniform vec3 uDiffuseColor;
uniform vec3 uSpecularColor;

varying vec3 vertPos;
varying vec3 normalInterp;

const float shininess = 4.0;

void main(void) {
    vec3 normal = normalize(normalInterp);
    vec3 lightDir = normalize(uLightPos - vertPos);
    float diffuseWeight = max(dot(lightDir, normal), 0.0);
    float specularWeight = 0.0;

    if (diffuseWeight > 0.0) {
        vec3 viewDir = normalize(-vertPos);
        vec3 halfDir = normalize(lightDir + viewDir);
        float specAngle = max(dot(halfDir, normal), 0.0);
        specularWeight = pow(specAngle, shininess);
    }

    vec3 colorLinear = uAmbientColor +
        uDiffuseColor * diffuseWeight +
        uSpecularColor * specularWeight;

    gl_FragColor = vec4(colorLinear, 1.0);
}
