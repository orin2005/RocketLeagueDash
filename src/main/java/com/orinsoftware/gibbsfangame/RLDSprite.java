package com.orinsoftware.gibbsfangame;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class RLDSprite {
	
	protected Image image;
	protected double positionX;
	protected double positionY;
	protected double velocityX;
	protected double velocityY;
	protected double width;
	protected double height;

	public RLDSprite(Image image,
			double positionX,
			double positionY,
			double velocityX,
			double velocityY,
			double width,
			double height) 
	{
		this.image = image;
		this.positionX = positionX;
		this.positionY = positionY;
		this.velocityX = velocityX;
		this.velocityY = velocityY;
		this.width = width;
		this.height = height;
	}
	
	public void update(double time)
	{
		positionX += velocityX * time;
		positionY += velocityY * time;
	}
	
	public Rectangle2D getBoundary()
	{
		return new Rectangle2D(positionX, positionY, width, height);
	}
	
	public boolean intersects(RLDSprite s)
	{
		return s.getBoundary().intersects( this.getBoundary() );
	}
	
	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public double getPositionX() {
		return positionX;
	}

	public void setPositionX(double positionX) {
		this.positionX = positionX;
	}

	public double getPositionY() {
		return positionY;
	}

	public void setPositionY(double positionY) {
		this.positionY = positionY;
	}

	public double getVelocityX() {
		return velocityX;
	}

	public void setVelocityX(double velocityX) {
		this.velocityX = velocityX;
	}

	public double getVelocityY() {
		return velocityY;
	}

	public void setVelocityY(double velocityY) {
		this.velocityY = velocityY;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public abstract void render(GraphicsContext gc);

}
