package com.collins.shaders;

import org.joml.Matrix4f;

public class StaticShader extends ShaderProgram {

    private static final String VERTEX_FILE_STRING = "src/main/java/com/collins/shaders/vertexShader.vs";
    private static final String FRAGMENT_FILE_STRING = "src/main/java/com/collins/shaders/fragmentShader.fs";

    private int location_transformationMatrix;

    public StaticShader() {
        super(VERTEX_FILE_STRING, FRAGMENT_FILE_STRING);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoords");
    }

    @Override
    protected void getAllUniformLocations() {
        location_transformationMatrix = super.getUniformLocation("transformationMatrix");
    }

    public void loadTransformationMatrix(Matrix4f matrix) {
        super.loadMatrix(location_transformationMatrix, matrix);
    }
}
