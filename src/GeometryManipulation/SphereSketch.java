package GeometryManipulation;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.util.Hashtable;

//where mousx is make it less noisy
public class SphereSketch extends PApplet {

    private PImage image;
    private int offset = 10;
    private int screenshotsTaken = 0;

    private float positionX;
    private float intensity = 50;

    private Hashtable<String, Integer> colourAtPixel;

    @Override
    public void settings() {
        size(1920,1080);
    }

    @Override
    public void setup() {
        colourAtPixel = new Hashtable<String, Integer>();
        image = loadImage("../img/Background.png");
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
        background(0);
        for (int x = 0; x < width / offset; x++) {
            for (int y = 0; y < height / offset; y++) {
                int pixelColour = colourAtPixel.get((x*offset)+""+(y*offset));
                stroke(pixelColour);

                float dist = dist(positionX,height/2,x * offset,height/2);

                if(dist <= 250)
                   line(x * offset, y * offset + random(0,15) / 2, x * offset, height);
                else
                    line(x * offset, y * offset + random(0,positionX) / 2, x * offset, height);
            }
        };
    }

    @Override
    public void draw() {
        positionX = mouseX;
        drawLines();
    }
}

//teilchen/exampls/lessonsX12_TearableSpring/lessonsX12_TearableSpring
//toxiclibs/examples/joinedCaternary/joinedCaternary
