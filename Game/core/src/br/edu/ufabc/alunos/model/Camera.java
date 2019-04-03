package br.edu.ufabc.alunos.model;

public class Camera {

	private float cameraX = 0f;
	public float getCameraX() {
		return cameraX;
	}

	public float getCameraY() {
		return cameraY;
	}

	private float cameraY = 0f;
	
	public void update(float newCamX, float newCamY) {
		// +0.5f to center the camera on the player's tile.
		this.cameraX = newCamX+0.5f;
		this.cameraY = newCamY+0.5f;
	}
}
