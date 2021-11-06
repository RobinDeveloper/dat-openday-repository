package GeometryManipulation;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.util.Hashtable;

//where mousx is make it less noisy
public class SphereSketch extends PApplet {

    private PImage image;
    private int offset = 50;
    private int screenshotsTaken = 0;

    private float positionX;
    private float intensity = 25;
    private float speed = 100;

    private Hashtable<String, Integer> colourAtPixel;

    @Override
    public void settings() {
        size(1920,1080);
    }

    @Override
    public void setup() {
        surface.setTitle("Sphere");

        colourAtPixel = new Hashtable<String, Integer>();
        image = loadImage("../img/DinosaurdPileUp.jpg");
        image.resize(width, height);
        image.loadPixels();
        background(255);

        offset = (int)intensity;

        for (int x = 0; x < width / offset; x++) {
            for (int y = 0; y < height/offset; y++) {
                String xy = (x * offset)+""+(y * offset);
                colourAtPixel.put(xy, image.get(x * offset,y * offset));
            }
        }

        strokeWeight(offset);

        drawLines();
    }

    private void drawLines()
    {
        if(positionX >= width - intensity)
            speed = -10;
        if(positionX <= 0 + intensity)
            speed = 10;

        background(0);
        for (int x = 0; x < width / offset; x++) {
            for (int y = 0; y < height / offset; y++) {
                int pixelColour = colourAtPixel.get((x*offset)+""+(y*offset));
                stroke(pixelColour);

                float dist = dist(positionX,height/2,x * offset,height/2);

                if(dist <= 250)
                   line(x * offset, y * offset + random(0,15) / 2, x * offset, height);
                else
                    line(x * offset, y * offset + random(0,500) / 2, x * offset, height);
            }
        };

        fill(color(0,0,255));
        textSize(64);
        //textMode(CENTER);
        text("DESIGN, ART, \nand TECHNOLOGY", 75, height/2);

        positionX += speed;
    }

    @Override
    public void draw() {
        drawLines();
    }
}

//teilchen/exampls/lessonsX12_TearableSpring/lessonsX12_TearableSpring
//toxiclibs/examples/joinedCaternary/joinedCaternary
