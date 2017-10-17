package machine.toolbox;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Maths {

	public static Matrix4f createTransformationMatrix(Vector3f translation, float rx, float ry, float rz, float scale) {
		Matrix4f matrix = new Matrix4f();
		matrix.translate(translation);
		matrix.rotate((float) Math.toRadians(rx), 1.0f, 0.0f, 0.0f);
		matrix.rotate((float) Math.toRadians(ry), 0.0f, 1.0f, 0.0f);
		matrix.rotate((float) Math.toRadians(rz), 0.0f, 0.0f, 1.0f);
		matrix.scale(scale);

		return matrix;
	}

}
