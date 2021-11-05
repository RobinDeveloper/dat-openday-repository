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

    @Override
    public void settings() {
        size(1920,1080);
    }

    @Override
    public void setup() {
        image = loadImage("../img/Background.png");
        image.resize(width, height);
        image.loadPixels();
    }

    @Override
    public void draw() {
        positionX = mouseX;

        background(255);
        maskImage = createGraphics(width,height);
        maskImage.beginDraw();
        maskImage.circle(positionX, height/2, mouseY);
        maskImage.endDraw();
        image.mask(maskImage);
        image(image,0,0);
    }
}
