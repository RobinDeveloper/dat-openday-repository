package GeometryManipulation;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

public class KineticGrid extends PApplet {

    private PImage image;
    private float intensity = 100;

    @Override
    public void settings() {
        size(1920,1080);
    }


    @Override
    public void setup() {
        image = loadImage("../img/Background.png");
        image.resize(width, height);
        image.loadPixels();

        image(image,0,0);
    }

    @Override
    public void draw() {
        background(0,0,255);

        //image(image,0,0);

        kineticEffect();

        fill(255);
    }

    private void kineticEffect()
    {
        int tilesX = 10;
        int tilesY = 10;

        int tileW = (width/tilesX - mouseX + width); //invert this
        int tileH = (height/tilesY - 10);

        for (int x = 0; x < tilesX; x++) {
            for (int y = 0; y < tilesY; y++) {

                float sinWave = sin(frameCount * (0.05f) * ((x * 0.2f) * (y * 0.5f)))*intensity;
                float cosWave = cos(tilesX * (0.05f) * ((x * 0.2f) * (y * 0.5f)))*intensity;

                //SOURCE
                int sourceX = (x * tileW + (int)(sinWave));
                int sourceY = y * tileH + (int)(cosWave);
                int sourceW = tileW;
                int sourceH = tileH;

                //DESTIINATION
                int destionationX = (x * tileW)/* + (int)(sinWave)*/;
                int destionationY = y * tileH;
                int destionationW = tileW;
                int destionationH = tileH;

                copy(image, sourceX, sourceY, sourceW, sourceH, destionationX, destionationY, destionationW, destionationH);
            }
        }
    }
}
