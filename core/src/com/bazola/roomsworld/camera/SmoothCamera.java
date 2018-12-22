package com.bazola.roomsworld.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector3;
import com.bazola.roomsworld.gamemodel.MapPoint;

public class SmoothCamera extends OrthographicCamera {
	
	private Vector3 targetPosition = new Vector3();
	private float targetZoom = 1;
	
	private float xMin;
	private float xMax;
	private float yMin;
	private float yMax;
	private boolean isBounded = false;
	
	private int minDistance = 1;
	private int speedFactor = 13;
	
	//private GameScreenBigWorld screen = null;
	
	public SmoothCamera(float width, float height) {
		super(width, height);
	}
	
	/*
	public void setScreen(GameScreenBigWorld gameScreenBigWorld) {
		this.screen = gameScreenBigWorld;
	}
	*/
	
	//to restrict zooming based on what it will be
	public float getTargetZoom() {
		return this.targetZoom;
	}
	
	//used by the pinch zoom method because the math is different
	public void setTargetZoom(float targetZoom) {
		this.targetZoom = targetZoom;
		
		if (this.zoom != this.targetZoom) {
		    /*
		    if (this.screen != null) {
		        this.screen.cameraZoomed();
		    }
		    */
		}
	}
	
	//BUG this does not move the parallax camera
	public void moveToGotoPoint(MapPoint point) {
		this.targetPosition.x = point.x;
		this.targetPosition.y = point.y;
	}

	//when setting positions, set both at once so it does not try to adjust
	public void setPosition(float xPos, float yPos, float zPos) {
		this.position.x = xPos;
		this.targetPosition.x = xPos;
		this.position.y = yPos;
		this.targetPosition.y = yPos;
		this.position.z = zPos;
		this.targetPosition.z = zPos;
	}
	
	public void setBounds(float xMin, float xMax, float yMin, float yMax) {
		this.isBounded = true;
		this.xMin = xMin;
		this.xMax = xMax;
		this.yMin = yMin;
		this.yMax = yMax;
	}
	
	//when setting zoom, set both at once so it does not try to adjust
	public void setZoom(float zoom) {
		this.zoom = zoom;
		this.targetZoom = zoom;
	}
	
	//change zoom here
	public void addZoom(float zoom) {
	    /*
		if (this.screen != null) {
			this.screen.cameraZoomed();
		}
		*/
		
		this.targetZoom += zoom;
	}
	
	//change movement here
	public void addMovement(float xPos, float yPos, float zPos) {
	    /*
		if (this.screen != null) {
			this.screen.cameraPanned();
		}
		*/
		
		float boundedX = xPos;
		float boundedY = yPos;
		//float boundedZ = zPos;
		if (this.isBounded) {
			if (this.targetPosition.x + xPos < this.xMin) {
				boundedX = 0;
			} else if (this.targetPosition.x + xPos > this.xMax) {
				boundedX = 0;
			}
			if (this.targetPosition.y + yPos < this.yMin) {
				boundedY = 0;
			} else if (this.targetPosition.y + yPos > this.yMax) {
				boundedY = 0;
			}
		}
		this.targetPosition.add(boundedX, boundedY, zPos);
	}
	
	//call this in the render method
	public void updatePosition(float deltaTime) {
		if (Math.abs(this.position.x - this.targetPosition.x) > this.minDistance ||
			Math.abs(this.position.y - this.targetPosition.y) > this.minDistance) {
			//interpolation method:
			float coeff = deltaTime * this.speedFactor;
			this.position.interpolate(this.targetPosition, coeff, Interpolation.sine);
		} 
		/*
		else {
			this.position.x = this.targetPosition.x;
			this.position.y = this.targetPosition.y;
		}
		*/
	
		int zoomFactor = 10;
		float zoomCoeff = deltaTime * zoomFactor;
		this.zoom = this.lerp(this.zoom, this.targetZoom, zoomCoeff);
	}
	
	//used to interpolate a single value
	private float lerp(float start, float end, float amount) {
	    return (start * (1.0f - amount)) + (end * amount);
	}
}
