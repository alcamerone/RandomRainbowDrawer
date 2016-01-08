import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import javax.imageio.*;

class RandomRainbowDrawer{
	public static final int IMG_WIDTH = 940;
	public static final int IMG_HEIGHT = 700;
	public static final int TILE_HEIGHT = 40;
	public static final int TILE_WIDTH = 20;
	Graphics2D g2;
	
	public static void main(String args[]){
		RandomRainbowDrawer rrd = new RandomRainbowDrawer();
		rrd.run();
	}
	
	public void run(){
		BufferedImage rainbows = new BufferedImage(IMG_WIDTH,IMG_HEIGHT,BufferedImage.TYPE_INT_ARGB);
		g2 = rainbows.createGraphics();
		
		for(int i = 0;i < IMG_HEIGHT; i += 60){
			for(int j = 0;j < IMG_WIDTH;j += 120){
				int[] colorList = generateRandomOrder();
				drawRandomRainbow(j,i,colorList);
			}
		}
		
		//draw basic rainbow in top left corner
		g2.setPaint(Color.red);
		g2.fill(new Rectangle2D.Double(0,0,TILE_WIDTH,TILE_HEIGHT));
		g2.setPaint(Color.orange);
		g2.fill(new Rectangle2D.Double(20,0,TILE_WIDTH,TILE_HEIGHT));
		g2.setPaint(Color.yellow);
		g2.fill(new Rectangle2D.Double(40,0,TILE_WIDTH,TILE_HEIGHT));
		g2.setPaint(Color.green);
		g2.fill(new Rectangle2D.Double(60,0,TILE_WIDTH,TILE_HEIGHT));
		g2.setPaint(Color.blue);
		g2.fill(new Rectangle2D.Double(80,0,TILE_WIDTH,TILE_HEIGHT));
		
		File outputImage = new File("rainbows.png");
		try{
			ImageIO.write(rainbows,"png",outputImage);
		}catch(IOException e){
		}
	}
	
	private int[] generateRandomOrder(){
		Random randomGenerator = new Random();
		ArrayList<Integer> numbers = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5));
		int[] results = new int[5];
		
		for(int i = 0;i < 5;i++){
			results[i] = numbers.remove(randomGenerator.nextInt(numbers.size()));
		}
		
		return results;
	}
	
	private void drawRandomRainbow(int x,int y,int colorList[]){
		for(int i = 0;i < 5;i++){
			drawRainbowTile(x,y,colorList[i]);
			x += TILE_WIDTH;
		}
	}
	
	private void drawRainbowTile(int x,int y,int color){
		switch(color){
			case 1: g2.setPaint(Color.red);
				break;
			case 2: g2.setPaint(Color.orange);
				break;
			case 3: g2.setPaint(Color.yellow);
				break;
			case 4: g2.setPaint(Color.green);
				break;
			case 5: g2.setPaint(Color.blue);
				break;
		}
		g2.fill(new Rectangle2D.Double(x,y,TILE_WIDTH,TILE_HEIGHT));
	}
}
