package GeometryManipulation;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

public class DrawingSketch extends PApplet {
    private PImage paintingToTransfer;
    private boolean drawStoke = true;
    private float intensity = 10;

    @Override
    public void settings() {
        size(1920,1080);
    }

    @Override
    public void setup() {
        paintingToTransfer = loadImage("../img/Background.png");
        paintingToTransfer.resize(width,height);
        paintingToTransfer.loadPixels();

        setupDrawingSketches();
    }

    private void setupDrawingSketches(){
        background(255);
    }

    @Override
    public void draw() {
        if(drawStoke)
            for (int i = 0; i < 200; i++) {
                translate((width/2) - (paintingToTransfer.width/2), (height/2) - (paintingToTransfer.height/2));
                drawStroke();
            }
    }


    private void drawStroke()
    {
        int x = (int)random(paintingToTransfer.width);
        int y = (int)random(paintingToTransfer.height);

        int colorAtPixel = paintingToTransfer.get(x,y);

        stroke(colorAtPixel);
        var weight = map(frameCount, 0, 2000, 25,0 );

        if(weight <= 0)
            drawStoke = false;

        strokeWeight(weight);

        push();  // Use push to move and rotate the stroke.
        translate(x, y);
        float n = noise(x * 0.05f, y * 0.05f);
        rotate(radians(map(n, 0, 1, -180, 180)));

        float lengthVariation = random(0.75f, 1.25f);
        //rect(0,0,lengthVariation,lengthVariation);
        ellipse(0,0,lengthVariation,lengthVariation);
        pop();
    }
}
