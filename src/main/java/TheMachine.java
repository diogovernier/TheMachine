import static org.lwjgl.glfw.GLFW.glfwPollEvents;

import org.joml.Vector3f;
import org.lwjgl.Version;

import machine.core.DisplayManager;
import machine.core.Renderer;
import machine.entities.Camera;
import machine.entities.Entity;
import machine.model.Loader;
import machine.model.RawModel;
import machine.model.TexturedModel;
import machine.shader.StaticShader;
import machine.texture.ModelTexture;

public class TheMachine {

	private DisplayManager displayManager;
	private Renderer renderer;
	private Loader loader;
	private StaticShader shader;

	private void init() {
		DisplayManager displayManager = new DisplayManager();
		displayManager.setWidth(1024);
		displayManager.setHeight(768);
		this.displayManager = displayManager;
		displayManager.init();

		this.shader = new StaticShader();

		this.renderer = new Renderer(displayManager, shader);
		renderer.init();

		this.loader = new Loader();
	}

	private void loop() {

//		float[] vertices = { //
//				-0.5f, 0.5f, 0f, //
//				-0.5f, -0.5f, 0f, //
//				// 0.5f, -0.5f, 0f, //
//				0.5f, -0.5f, 0f, //
//				0.5f, 0.5f, 0f // , //
//				// -0.5f, 0.5f, 0f ///
//		};
//
//		int[] indices = { //
//				0, 1, 3, //
//				3, 1, 2 //
//		};
//
//		float[] textureCoords = { 0, 0, // V0
//				0, 1, // V1
//				1, 1, // V2
//				1, 0 };

		float[] vertices = {			
				-0.5f,0.5f,-0.5f,	
				-0.5f,-0.5f,-0.5f,	
				0.5f,-0.5f,-0.5f,	
				0.5f,0.5f,-0.5f,		
				
				-0.5f,0.5f,0.5f,	
				-0.5f,-0.5f,0.5f,	
				0.5f,-0.5f,0.5f,	
				0.5f,0.5f,0.5f,
				
				0.5f,0.5f,-0.5f,	
				0.5f,-0.5f,-0.5f,	
				0.5f,-0.5f,0.5f,	
				0.5f,0.5f,0.5f,
				
				-0.5f,0.5f,-0.5f,	
				-0.5f,-0.5f,-0.5f,	
				-0.5f,-0.5f,0.5f,	
				-0.5f,0.5f,0.5f,
				
				-0.5f,0.5f,0.5f,
				-0.5f,0.5f,-0.5f,
				0.5f,0.5f,-0.5f,
				0.5f,0.5f,0.5f,
				
				-0.5f,-0.5f,0.5f,
				-0.5f,-0.5f,-0.5f,
				0.5f,-0.5f,-0.5f,
				0.5f,-0.5f,0.5f
				
		};
		
		float[] textureCoords = {
				
				0,0,
				0,1,
				1,1,
				1,0,			
				0,0,
				0,1,
				1,1,
				1,0,			
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0

				
		};
		
		int[] indices = {
				0,1,3,	
				3,1,2,	
				4,5,7,
				7,5,6,
				8,9,11,
				11,9,10,
				12,13,15,
				15,13,14,	
				16,17,19,
				19,17,18,
				20,21,23,
				23,21,22

		};
		
		RawModel model = loader.loadToVAO(vertices, textureCoords, indices);
		ModelTexture texture = new ModelTexture(loader.loadTexture("image"));
		TexturedModel texturedModel = new TexturedModel(model, texture);

		Entity entity = new Entity(texturedModel, new Vector3f(0, 0, -5), 0, 0, 0, 1);

		Camera camera = new Camera();
		
		
		// Run the rendering loop until the user has attempted to close
		// the window or has pressed the ESCAPE key.
		while (!displayManager.shouldCloseWindow()) {
//			entity.increasePosition(0, 0, -0.1f);
			entity.increaseRotation(1, 1, 0);
			camera.move();
			// entity.increaseRotation(0, 1, 0);
			renderer.beginRender();
			shader.start();
			shader.loadViewMatrix(camera);
			renderer.render(entity, shader);
			shader.stop();
			renderer.finishRender();

			// Poll for window events. The key callback above will only be
			// invoked during this call.
			glfwPollEvents();
		}

		shader.cleanUp();
	}

	private void destroy() {
		displayManager.destroy();
		renderer.destroy();
		loader.cleanUp();
	}

	private void run() {
		System.out.println("Hello LWJGL " + Version.getVersion() + "!");

		init();
		loop();
		destroy();
	}

	public static void main(String[] args) {
		TheMachine machine = new TheMachine();
		machine.run();
	}

}
