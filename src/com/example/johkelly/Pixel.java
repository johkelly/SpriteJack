package com.example.johkelly;

import java.awt.Color;

class Pixel{
	private Color color;
	private int bw; // >0 black, 0 don't draw, <0 white
	private int x, y;
	public Pixel(int source, int x, int y){
		color = new Color(source);
		this.x = x;
		this.y = y;
		setToBlackOrWhite();
	}
	public void setToBlackOrWhite(){
		if(color.getRed() <= 10 && color.getGreen() <= 10 && color.getBlue() <= 10){
			bw = 1;
		} else{
			bw = -1;
		}
	}
	public void flip(){
		bw *= -1;
	}
	public void setBW(int i){
		bw = i;
	}
	public Color getColor() {
		return color;
	}
	public int getBw() {
		return bw;
	}
	public void setBw(int bw) {
		this.bw = bw;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setColor(Color color) {
		this.color = color;
	}
}