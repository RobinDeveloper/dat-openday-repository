import GeometryManipulation.DrawingSketch;
import GeometryManipulation.KineticGrid;
import GeometryManipulation.SphereDrawSketch;
import GeometryManipulation.SphereSketch;
import processing.core.PApplet;

class Main extends PApplet {
    enum Sketch{
        sphereSketch,
        maskSketch,
        drawingSketch,
        Kinetic
    }

    public static void main(String[] args){
        runSketch(Sketch.sphereSketch);
    }

    private static void runSketch(Sketch _sketch) {
        PApplet sketch = null;
        String[] args = null;

        switch (_sketch)
        {
            case sphereSketch -> {
                args = new String[]{"SphereSketch"};
                sketch = new SphereSketch();
            }
            case maskSketch -> {
                args = new String[]{"SphereDrawSketch"};
                sketch = new SphereDrawSketch();
            }
            case drawingSketch -> {
                args = new String[]{"DrawingSketch"};
                sketch = new DrawingSketch();
            }
            case Kinetic -> {
                args = new String[]{"KinetichGrid"};
                sketch = new KineticGrid();
            }
        }

        PApplet.runSketch(args, sketch);
    }
}