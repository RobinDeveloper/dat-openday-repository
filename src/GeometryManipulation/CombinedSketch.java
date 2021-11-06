package GeometryManipulation;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

import java.util.Hashtable;
import java.util.Random;

public class CombinedSketch extends PApplet {

    private PImage backgroundImage;
    private PImage datImage;

    private boolean drawingSketch;
    private boolean kineticSketch;
    private boolean sphereDrawSketch;
    private boolean sphereSketch;

    //drawingSketchValues;
    private float intensityDrawing = 10;

    //sphereDrawingSketch
    private float intensityMask = 200;
    private float positionXMask;
    private float speedDrawing = 10;
    private PGraphics maskImage;
    
    //SphereSketch
    private int offsetSphere = 50;
    private float positionXSphere;
    private float intensitySphere = 25;
    private float speedSphere = 100;
    private Hashtable<String, Integer> colourAtPixelSphere;

    //KineticSketch
    private float intensityKinetic = 100;

    @Override
    public void settings() {
        size(1920,1080);
    }

    @Override
    public void setup() {
        loadImages();
        setupDrawingSketch();
    }

    @Override
    public void draw() {
        if(drawingSketch)
            drawDrawingSketch();

        if(kineticSketch)
            drawKineticSketch();

        if(sphereDrawSketch)
            drawMaskSketch();

        if(sphereSketch)
            drawSphereSketch();


    }

    private void selectNewSketch()
    {
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        Random rand = new Random();
                        int random = rand.nextInt(4);

                        switch (random){

                            case 0 -> {
                                setupDrawingSketch();
                            }
                            case 1 -> {
                                setupMaskSketch();
                            }
                            case 2 -> {
                                setupKinetichSketch();
                            }
                            case 3 -> {
                                setupDrawingSketch();
                            }
                        }
                    }
                },
                20000
        );
    }

    private void loadImages(){
        backgroundImage = loadImage("../img/Background.png");
        backgroundImage.resize(width,height);
        backgroundImage.loadPixels();

        datImage = loadImage("../img/DinosaurdPileUp.jpg");
        datImage.resize(width,height);
        datImage.loadPixels();
    }

    private void setupKinetichSketch(){
        background(255);
        image(backgroundImage,0,0);
        drawingSketch = false;
        kineticSketch = true;
        sphereDrawSketch = false;
        sphereSketch = false;
        selectNewSketch();
    }

    private void setupSphereSketch(){

        background(255);
        drawingSketch = false;
        kineticSketch = false;
        sphereDrawSketch = false;
        sphereSketch = true;

        colourAtPixelSphere = new Hashtable<String, Integer>();

        offsetSphere = (int)intensitySphere;

        for (int x = 0; x < width / offsetSphere; x++) {
            for (int y = 0; y < height/offsetSphere; y++) {
                String xy = (x * offsetSphere)+""+(y * offsetSphere);
                colourAtPixelSphere.put(xy, datImage.get(x * offsetSphere,y * offsetSphere));
            }
        }

        strokeWeight(offsetSphere);

        drawSphereSketch();
        selectNewSketch();
    }

    private void setupMaskSketch(){

        background(255);
        drawingSketch = false;
        kineticSketch = false;
        sphereDrawSketch = true;
        sphereSketch = false;
        selectNewSketch();
    }

    private void setupDrawingSketch(){
        background(255);

        frameCount = 0;
        drawingSketch = true;
        kineticSketch = false;
        sphereDrawSketch = false;
        sphereSketch = false;
        selectNewSketch();
    }

    private void drawKineticSketch(){
        background(0,0,255);

        image(backgroundImage,0,0);

        kineticEffect();

        fill(255);
    }

    private void drawSphereSketch(){
        if(positionXSphere >= width - intensitySphere)
            speedSphere = -10;
        if(positionXSphere <= 0 + intensitySphere)
            speedSphere = 10;

        background(255);
        for (int x = 0; x < width / offsetSphere; x++) {
            for (int y = 0; y < height / offsetSphere; y++) {
                int pixelColour = colourAtPixelSphere.get((x*offsetSphere)+""+(y*offsetSphere));
                stroke(pixelColour);

                float dist = dist(positionXSphere,height/2,x * offsetSphere,height/2);

                if(dist <= 250)
                    line(x * offsetSphere, y * offsetSphere + random(0,15) / 2, x * offsetSphere, height);
                else
                    line(x * offsetSphere, y * offsetSphere + random(0,500) / 2, x * offsetSphere, height);
            }
        };

        fill(color(0,0,255));
        textSize(64);
        //textMode(CENTER);
        text("DESIGN, ART, \nand TECHNOLOGY", 75, height/2);

        positionXSphere += speedSphere;
    }

    private void drawMaskSketch(){
        if(positionXMask >= width - intensityMask)
            speedDrawing = -10;
        if(positionXMask <= 0 + intensityMask)
            speedDrawing = 10;

        background(255);
        maskImage = createGraphics(width,height);
        maskImage.beginDraw();
        maskImage.circle(positionXMask, height/2, intensityMask);
        maskImage.endDraw();
        backgroundImage.mask(maskImage);
        image(backgroundImage,0,0);

        positionXMask += speedDrawing;
    }

    private void drawDrawingSketch(){
        if(drawingSketch)
            for (int i = 0; i < 200; i++) {
                translate((width/2) - (backgroundImage.width/2), (height/2) - (backgroundImage.height/2));
                drawStroke();
                translate(-((width/2) - (backgroundImage.width/2)), -((height/2) - (backgroundImage.height/2)));
            }
    }

    private void kineticEffect()
    {
        int tilesX = 10;
        int tilesY = 10;

        int tileW = (width/tilesX - (int)sin(frameCount)); //invert this
        int tileH = (height/tilesY - 10);

        for (int x = 0; x < tilesX; x++) {
            for (int y = 0; y < tilesY; y++) {

                float sinWave = sin(frameCount * (0.05f) * ((x * 0.2f) * (y * 0.5f)))*intensityKinetic;
                float cosWave = cos(tilesX * (0.05f) * ((x * 0.2f) * (y * 0.5f)))*intensityKinetic;

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

                copy(backgroundImage, sourceX, sourceY, sourceW, sourceH, destionationX, destionationY, destionationW, destionationH);
                //copy(image, destionationX, destionationY, destionationW, destionationH, sourceX, sourceY, sourceW, sourceH);
            }
        }
    }

    private void drawStroke()
    {
        int x = (int)random(backgroundImage.width);
        int y = (int)random(backgroundImage.height);

        int colorAtPixel = backgroundImage.get(x,y);

        stroke(colorAtPixel);
        var weight = map(frameCount, 0, 2000, 25,0 );

        if(weight <= 0)
            drawingSketch = false;

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
