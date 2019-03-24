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
		this.cameraX = newCamX;
		this.cameraY = newCamY;
	}
}
