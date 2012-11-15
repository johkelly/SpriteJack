package com.example.johkelly;

public class Line {
	public int x1, x2, y1, y2;
	public int color; // <0 black, 0 don't draw, >0 white
	public Line(int x1, int y1, int x2, int y2, int c){
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		color = c;
	}
}
