package com.example.johkelly;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class JackSpriteMaker {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//TODO: Populate flags dynamically		
		if(args.length != 1){
			System.out.println("usage: java SpriteJack <filename>.<ext>");
			return;
		}
		
		BufferedImage imgBuff;
		
		// Get the image
		try {
			File imgSource = new File(args[0]);
			imgBuff = ImageIO.read(imgSource);
		} catch (IOException e) {
			// TODO maybe do something special
			e.printStackTrace();
			return;
		}
		System.out.println("Successfully opened "+args[0]);
		
		// Put the image data into a data structure and parse it
		try {
			PixelParser pp = new PixelParser(imgBuff.getWidth(), imgBuff.getHeight(), imgBuff);
			String filename = args[0].substring(0, args[0].indexOf('.'));
			PixelWriter pw = new PixelWriter(pp, filename);
			pw.parseAndWrite();
			System.out.println("Finished writing to " + filename+".txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
