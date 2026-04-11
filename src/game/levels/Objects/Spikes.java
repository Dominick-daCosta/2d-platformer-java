package game.levels.objects;

import city.cs.engine.*;
import java.awt.Color;


public class Spikes extends StaticBody {

    private static final Shape spikesShape = new BoxShape(10f, 1f);


    public Spikes(World world) {
        super(world, spikesShape);
        this.setFillColor(Color.RED);
        this.setLineColor(Color.BLACK);
    }







}
