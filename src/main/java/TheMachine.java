import static org.lwjgl.glfw.GLFW.glfwPollEvents;

import org.lwjgl.Version;

import machine.core.DisplayManager;
import machine.core.Renderer;
import machine.model.Loader;
import machine.model.RawModel;
import machine.model.TexturedModel;
import machine.shader.StaticShader;
import machine.texture.ModelTexture;

public class TheMachine {

	private DisplayManager displayManager;
	private Renderer renderer;
	private Loader loader;

	private void init() {
		DisplayManager displayManager = new DisplayManager();
		displayManager.setWidth(1024);
		displayManager.setHeight(768);
		this.displayManager = displayManager;
		displayManager.init();

		this.renderer = new Renderer(displayManager);
		renderer.init();

		this.loader = new Loader();
	}

	private void loop() {

		float[] vertices = { //
				-0.5f, 0.5f, 0f, //
				-0.5f, -0.5f, 0f, //
				// 0.5f, -0.5f, 0f, //
				0.5f, -0.5f, 0f, //
				0.5f, 0.5f, 0f // , //
				// -0.5f, 0.5f, 0f ///
		};

		int[] indices = { //
				0, 1, 3, //
				3, 1, 2 //
		};
		
		float[] textureCoords = {
				0, 0, //V0
				0, 1, //V1
				1, 1, //V2
				1, 0
		};

		RawModel model = loader.loadToVAO(vertices, textureCoords, indices);
		ModelTexture texture = new ModelTexture(loader.loadTexture("image"));
		TexturedModel texturedModel = new TexturedModel(model, texture);
		
		StaticShader shader = new StaticShader();
		
		// Run the rendering loop until the user has attempted to close
		// the window or has pressed the ESCAPE key.
		while (!displayManager.shouldCloseWindow()) {
			renderer.beginRender();
			shader.start();
			renderer.render(texturedModel);
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
