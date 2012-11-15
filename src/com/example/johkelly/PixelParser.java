package com.example.johkelly;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PixelParser {
	private ArrayList<ArrayList<Pixel>> pixels = new ArrayList<ArrayList<Pixel>>();
	private int width;
	private int height;

	public PixelParser(int width, int height, BufferedImage bi){
		this.setWidth(width);
		this.setHeight(height);
		for(int i = 0; i < height; i++){
			pixels.add(i, new ArrayList<Pixel>());
			for(int j = 0; j < width; j++){
				pixels.get(i).add(j, new Pixel(bi.getRGB(j, i), j, i));
			}
		}
	}

	public Pixel getPixel(int x, int y){
		return pixels.get(x).get(y);
	}

	public ArrayList<ArrayList<Pixel>> getPixels(){
		return pixels;
	}

	public ArrayList<Line> getRowJobs() {
		ArrayList<Line> jobs = new ArrayList<Line>();
		Integer start, end, currBW;
		for(ArrayList<Pixel> row : pixels){
			start = end = null;
			currBW = 0;
			for(Pixel p : row){

				// If the pixel is not null and we were previously reading null pixels
				if(p.getBw() != 0 && start == null){
					// Start a new line tracking state starting with this pixel
					start = p.getX();
					end = p.getX();
					currBW = p.getBw();
				}
				// If the pixel is not null and we were previously reading a line
				else if(p.getBw() != 0 && start != null){
					// If it's the same color, keep moving our line rightwards
					if(p.getBw() == currBW){
						end = p.getX();
					}
					// Otherwise, finish the job and start a new one if necessary
					else{
						// All pixels are processed per line, so they share a y-coord
						jobs.add(new Line(start, p.getY(), end, p.getY(), currBW));
						if(p.getBw() != 0){
							// Start a new line tracking state starting with this pixel
							start = p.getX();
							end = p.getX();
							currBW = p.getBw();
						}
						else{
							start = end = null;
							currBW = p.getBw();
						}
					}
				}
				
			}
		}
		return jobs;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
