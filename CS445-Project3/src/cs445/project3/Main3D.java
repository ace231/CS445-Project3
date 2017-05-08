/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cs445.project3;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.util.glu.GLU;

/**
 *
 * @author Alfredo
 */
public class Main3D {
	private FPCameraController fp = new FPCameraController(0.0f, 0.0f, 0.0f);
	private DisplayMode displayMode;
	
	/**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
           Main3D thing = new Main3D();
           thing.start();
    }
	
	public void start() {
		try {
			createWindow();
			initGL();
			fp.gameLoop();
		}
		catch(Exception e) {
			System.out.println("An error occurred in the main class...");
			e.printStackTrace();
		}
	}
	
	private void createWindow()throws Exception {
		Display.setFullscreen(false);
		DisplayMode d[] = Display.getAvailableDisplayModes();
		for(int i = 0; i < d.length; i++) {
			if(d[i].getWidth() == 640 &&  d[i].getHeight()== 480 && d[i].getBitsPerPixel() == 32) {
				displayMode = d[i];
				break;
			}
		}
		Display.setDisplayMode(displayMode);
		Display.setTitle("CS445 PLACEHOLDER TEXT LOL");
		Display.create();
	}
	
	private void initGL() {
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		GLU.gluPerspective(100.0f, 
                        (float)displayMode.getWidth()/(float)displayMode.getHeight(), 0.1f, 300.0f);
		glMatrixMode(GL_MODELVIEW);
		glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
	}
}
