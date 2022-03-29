// package com.collins.display;

// import com.collins.display.Models.RawModel;
// import com.jogamp.opengl.GL;
// import com.jogamp.opengl.GL3;
// import com.jogamp.opengl.GLAutoDrawable;
// import com.jogamp.opengl.GLContext;

// public class Renderer {

//     GL3 gl3;

//     public Renderer(GL3 gl3) {
//         this.gl3 = gl3;
//     }

//     public void prepare() {
//         gl3.glClearColor(1, 0, 0, 1);
//         gl3.glClear(GL3.GL_COLOR_BUFFER_BIT);
//     }

//     public void render(RawModel model) {
//         gl3.glBindVertexArray(model.getVaoID());
//         gl3.glEnableVertexAttribArray(0);
//         gl3.glDrawArrays(GL3.GL_TRIANGLES, 0, model.getVertexCount());
//         gl3.glDisableVertexAttribArray(0);
//         gl3.glBindVertexArray(0);
//     }
// }
