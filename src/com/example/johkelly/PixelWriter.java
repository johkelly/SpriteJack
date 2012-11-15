package com.example.johkelly;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PixelWriter {
	private PixelParser pp;
	private BufferedWriter out;
	private String spriteName;
	public boolean ignoreAllWhite = false;
	public boolean ignoreExteriorWhite = false;

	public PixelWriter(PixelParser pp, String filename) throws IOException {
		this.pp = pp;
		out = new BufferedWriter(new FileWriter(filename+".txt"));
		spriteName = filename;
	}

	public void parseAndWrite(){
		//TODO: Parse the data
		//TODO: Special logic
		for(ArrayList<Pixel> row : pp.getPixels()){
			for(Pixel p : row){
				p.setToBlackOrWhite();
			}
		}
		writeHeader(pp.getWidth(), pp.getHeight());
		try {
			out.write("\tdo Screen.setColor(true);");
			out.newLine();
			for(Line job : pp.getRowJobs()){
				if(job.color == 1){
					writeDraw(job);
				}
			}
			out.write("\tdo Screen.setColor(false);");
			out.newLine();
			for(Line job : pp.getRowJobs()){
				if(job.color == -1){
					writeDraw(job);
				}
			}
			writeFooter();
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeDraw(Line l){
		try {
			out.write("\tdo Screen.drawLine(("+l.x1+"+x), ("+l.y1+"+y), ("+l.x2+"+x), ("+l.y2+"+y));\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeHeader(int width, int height){
		try {
			out.write("method void drawSprite"+spriteName+"(int x, int y) {");
			out.newLine();
			out.write("\tif( ((x+"+width+")>509) | ((y+"+height+")>253) ) {");
			out.newLine();
			out.write("\t\treturn;");
			out.newLine();
			out.write("\t}");
			out.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	private void writeFooter(){
		try {
			out.write("\treturn;");
			out.newLine();
			out.write("}");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
