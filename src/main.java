import GeometryManipulation.*;
import processing.core.PApplet;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.util.Random;

class Main extends PApplet {
    enum Sketch{
        sphereSketch,
        maskSketch,
        drawingSketch,
        Kinetic,
        Combined
    }

    private static PApplet Sketcher;

    public static void main(String[] args){

        Sketch sketch = Sketch.Combined;

        runSketch(sketch);
    }
    static boolean canExitSketch = false;

    private static void runSketch(Sketch _sketch) {
        PApplet sketch = null;
        String[] args = null;

        switch (_sketch)
        {
            case sphereSketch -> {
                args = new String[]{"Sphere"};
                sketch = new SphereSketch();
            }
            case maskSketch -> {
                args = new String[]{"Mask"};
                sketch = new SphereDrawSketch();
            }
            case drawingSketch -> {
                args = new String[]{"Drawing"};
                sketch = new DrawingSketch();
            }
            case Kinetic -> {
                args = new String[]{"Kinetic"};
                sketch = new KineticGrid();
            }
            case Combined -> {
                args = new String[]{"Combined"};
                sketch = new CombinedSketch();
            }
        }

        PApplet.runSketch(args, sketch);

    }
}