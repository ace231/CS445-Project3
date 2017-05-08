/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs445.project3;

import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.Sys;

/**
 *
 * @author Alfredo
 */
public class FPCameraController {
    private Vector3f position = null;
    private Vector3f lPosition = null;
    private float yaw = 0.0f;
    private float pitch = 0.0f;
    private Vector3Float me;
    
    public FPCameraController(float x, float y, float z) {
		position = new Vector3f(x, y, z);
		lPosition = new Vector3f(x,y,z);
		lPosition.x = 0f;
		lPosition.y = 15f;
		lPosition.z = 0f;
    }
	
	public void yaw(float amt) {
		yaw += amt;
	}
	
	public void pitch(float amt) {
		pitch -= amt;
	}
	
	public void walkForward(float dist) {
		float xOffset = dist * (float)Math.sin(Math.toRadians(yaw));
		float zOffset = dist * (float)Math.cos(Math.toRadians(yaw));
		position.x -= xOffset;
		position.z += zOffset;
	}
	
	public void walkBackwards(float dist) {
		float xOffset = dist * (float)Math.sin(Math.toRadians(yaw));
		float zOffset = dist * (float)Math.cos(Math.toRadians(yaw));
		position.x += xOffset;
		position.z -= zOffset;
	}
	
	public void strafeLeft(float dist) {
		float xOffset = dist * (float)Math.sin(Math.toRadians(yaw-90));
		float zOffset = dist * (float)Math.cos(Math.toRadians(yaw-90));
		position.x -= xOffset;
		position.z += zOffset;
	}
	
	public void strafeRight(float dist) {
		float xOffset = dist * (float)Math.sin(Math.toRadians(yaw-90));
		float zOffset = dist * (float)Math.cos(Math.toRadians(yaw-90));
		position.x += xOffset;
		position.z -= zOffset;
	}
	
	public void moveUp(float dist) {
		position.y -= dist;
	}
	
	public void moveDown(float dist) {
		position.y += dist;
	}
	
	public void lookThrough() {
        glRotatef(pitch, 1.0f, 0.0f, 0.0f);
        glRotatef(yaw, 0.0f, 1.0f, 0.0f);
        glTranslatef(position.x, position.y, position.z);
	}
        
    public void gameLoop() {
		FPCameraController camera = new FPCameraController(0, 0, 0);
		float dx = 0.0f , dy = 0.0f, dz = 0.0f, lastTime = 0.0f;
		long time = 0;
		float mouseSensitivity = 0.0f, movementSpeed = 0.35f; 
		Mouse.setGrabbed(false);    //true means mouse is paralyzed
		
		while(!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			time = Sys.getTime();
			dx = Mouse.getDX();
			dy = Mouse.getDY();
			camera.yaw(dx * mouseSensitivity);
			camera.pitch(dy * mouseSensitivity);
			
			if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
				camera.walkForward(movementSpeed);
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
				camera.walkBackwards(movementSpeed);
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
				camera.strafeLeft(movementSpeed);
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
				camera.strafeRight(movementSpeed);
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
				camera.moveUp(movementSpeed);
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
				camera.moveDown(movementSpeed);
			}
			
			glLoadIdentity();
			camera.lookThrough();
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			render();
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
	}
	
	private void render() {
		try {
			glBegin(GL_QUADS);
			// Renders top of cube
			glColor3f(0.0f, 0.0f, 1.0f);
			glVertex3f( 1.0f, 1.0f,-1.0f);
			glVertex3f(-1.0f, 1.0f,-1.0f);
			glVertex3f(-1.0f, 1.0f, 1.0f);
			glVertex3f( 1.0f, 1.0f, 1.0f); 

			// Renders bottom of cube
			glColor3f(0.0f, 1.0f, 0.0f);
			glVertex3f(1.0f, -1.0f, 1.0f);
			glVertex3f(-1.0f, -1.0f, 1.0f);
			glVertex3f(-1.0f, -1.0f, -1.0f);
			glVertex3f(1.0f, -1.0f, -1.0f);
			
			// Front of cube
			glColor3f(1.0f, 0.0f, 0.0f);
			glVertex3f(1.0f, 1.0f, 1.0f);
			glVertex3f(-1.0f, 1.0f, 1.0f);
			glVertex3f(-1.0f, -1.0f, 1.0f);
			glVertex3f(1.0f, -1.0f, 1.0f);

			// Back of cube
			glColor3f(1.0f, 1.0f, 0.0f);
			glVertex3f(1.0f, -1.0f, -1.0f);
			glVertex3f(-1.0f, -1.0f, -1.0f);
			glVertex3f(-1.0f, 1.0f, -1.0f);
			glVertex3f(1.0f, 1.0f, -1.0f);
			
			// Right of cube
			glColor3f(1.0f, 0.0f, 1.0f);
			glVertex3f(-1.0f, 1.0f, 1.0f);
			glVertex3f(-1.0f, 1.0f, -1.0f);
			glVertex3f(-1.0f, -1.0f, -1.0f);
			glVertex3f(-1.0f, -1.0f, 1.0f);
			
			// Right of cube
			glColor3f(1.0f, 0.0f, 1.0f);
			glVertex3f(1.0f, -1.0f, 1.0f);
			glVertex3f(1.0f, -1.0f, -1.0f);
			glVertex3f(1.0f, 1.0f, -1.0f);
			glVertex3f(1.0f, 1.0f, 1.0f);
			glEnd();
		}
		catch(Exception e) {
			System.out.println("So an error occurred...");
			e.printStackTrace();
		}
	}
}