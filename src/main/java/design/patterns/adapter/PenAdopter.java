package design.patterns.adapter;

public class PenAdopter implements Pen{

    PilotPen pp = new PilotPen(); //Object Composition

    @Override
    public void write(String str){
        pp.mark(str);
    }
}
