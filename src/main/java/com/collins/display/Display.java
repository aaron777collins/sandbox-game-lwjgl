// package com.collins.display;

// import com.collins.Game;
// import com.collins.display.Models.RawModel;
// import com.collins.entities.Entity;
// import com.collins.entities.Square;
// import com.jogamp.opengl.GL;
// import com.jogamp.opengl.GL2;
// import com.jogamp.opengl.GL3;
// import com.jogamp.opengl.GLAutoDrawable;
// import com.jogamp.opengl.GLEventListener;

// public class Display implements GLEventListener {

//     Game game;
//     public Loader loader;
//     public Renderer renderer;
//     RawModel model;
//     float[] verticies = {
//         //left bottom triangle
//         -0.5f, 0.5f, 0f,
//         -0.5f, -0.5f, 0f,
//         0.5f, -0.5f, 0f,
//         //right top triangle
//         0.5f, -0.5f, 0f,
//         0.5f, 0.5f, 0f,
//         -0.5f, 0.5f, 0f
//     };

//     public Display(Game game) {
//         this.game = game;
//     }

//     @Override
//     public void display(GLAutoDrawable drawable) {
//         final GL gl = drawable.getGL();

//         // gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f );
//         // gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
//         // gl.glLoadIdentity();

//         // for (Entity entity : game.entities) {
//         //     entity.draw(gl);
//         // }

//         // gl.glBegin (GL2.GL_LINES);//static field
//         // gl.glVertex3f(-0.50f,-0.50f,0);
//         // gl.glVertex3f(-0.50f,0.50f,0);
//         // gl.glVertex3f(0.50f,0.50f,0);
//         // gl.glVertex3f(0.50f,-0.50f,0);
//         // gl.glEnd();

//         renderer.prepare();
//         renderer.render(model);
//     }

//     @Override
//     public void dispose(GLAutoDrawable drawable) {
//         System.out.println("Deallocating VBOs and VAOs");
//         loader.cleanUp();
//     }

//     @Override
//     public void init(GLAutoDrawable drawable) {
//         GL3 gl3 = drawable.getGL().getGL3();
//         loader = new Loader(gl3);
//         renderer = new Renderer(gl3);

//         model = loader.loadToVAO(verticies);
//     }

//     @Override
//     public void reshape(GLAutoDrawable drawable, int arg1, int arg2, int arg3, int arg4) {
//         // TODO Auto-generated method stub
        
//     }
    
// }