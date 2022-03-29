// package com.collins.display;

// import java.nio.FloatBuffer;
// import java.nio.IntBuffer;
// import java.util.ArrayList;
// import java.util.List;

// import com.collins.display.Models.RawModel;
// import com.jogamp.common.nio.Buffers;
// import com.jogamp.opengl.GL;
// import com.jogamp.opengl.GL3;
// import com.jogamp.opengl.GLAutoDrawable;
// import com.jogamp.opengl.GLContext;
// import com.jogamp.opengl.util.GLBuffers;

// public class Loader {

//     private List<IntBuffer> vaos = new ArrayList<IntBuffer>();
//     private List<IntBuffer> vbos = new ArrayList<IntBuffer>();

//     GL3 gl3;

//     public Loader(GL3 gl3) {
//         this.gl3 = gl3;
//     }

//     public RawModel loadToVAO(float[] positions) {
//         int vaoID = createVAO();
//         storeDataInAttributeList(0, positions);
//         unbindVAO();
//         return new RawModel(vaoID, positions.length/3);
//     }

//     private int createVAO() {
//         IntBuffer idArr = GLBuffers.newDirectIntBuffer(1);
//         gl3.glGenVertexArrays(1, idArr);
//         int vaoID = idArr.get(0);
//         gl3.glBindVertexArray(vaoID);
//         vaos.add(idArr);
//         return vaoID;
//     }

//     public void cleanUp() {
//         for (IntBuffer vao:vaos) {
//             gl3.glDeleteVertexArrays(1, vao);
//         }
//         for (IntBuffer vbo:vbos) {
//             gl3.glDeleteVertexArrays(1, vbo);
//         }
//     }

//     private void storeDataInAttributeList(int attributeNumber, float[] data) {
//         IntBuffer idArr = GLBuffers.newDirectIntBuffer(1);
//         gl3.glGenBuffers(1, idArr);
//         vbos.add(idArr);
//         int vboID = idArr.get(0);
//         gl3.glBindBuffer(GL3.GL_ARRAY_BUFFER, vboID);
//         FloatBuffer buffer = storeDataInFloatBuffer(data);
//         gl3.glBufferData(GL3.GL_ARRAY_BUFFER, buffer.capacity() * Buffers.SIZEOF_FLOAT, buffer, GL3.GL_STATIC_DRAW);
//         gl3.glVertexAttribPointer(attributeNumber, 3, GL3.GL_FLOAT, false, 0, 0);
//         gl3.glBindBuffer(GL3.GL_ARRAY_BUFFER, 0);
//     }

//     private FloatBuffer storeDataInFloatBuffer(float[] data) {
//         FloatBuffer buffer = Buffers.newDirectFloatBuffer(data.length);
//         buffer.put(data);
//         buffer.flip();
//         return buffer;
//     }

//     private void unbindVAO() {
//         gl3.glBindVertexArray(0);
//     }

// }
