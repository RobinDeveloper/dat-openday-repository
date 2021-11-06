package GeometryManipulation;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

public class SphereDrawSketch extends PApplet {
    private PImage image;
    private PGraphics maskImage;
    private float intensity = 200;
    private float positionX;
    private float positionY = height/2;
    private float speed = 10;

    @Override
    public void settings() {
        size(1920,1080);
    }

    @Override
    public void setup() {
        surface.setTitle("Mask");

        image = loadImage("../img/Background.png");
        image.resize(width, height);
        image.loadPixels();
    }

    @Override
    public void draw() {
        if(positionX >= width - intensity)
            speed = -10;
        if(positionX <= 0 + intensity)
            speed = 10;

        background(255);
        maskImage = createGraphics(width,height);
        maskImage.beginDraw();
        maskImage.circle(positionX, height/2, intensity);
        maskImage.endDraw();
        image.mask(maskImage);
        image(image,0,0);

        positionX += speed;
    }
}
