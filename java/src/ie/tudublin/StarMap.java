package ie.tudublin;

import java.util.ArrayList;
import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class StarMap extends PApplet {

    ArrayList<Star> stars = new ArrayList<Star>();
    float border;

    void drawGrid() {
        stroke(110, 92, 100);
        textAlign(CENTER, CENTER);
        textSize(20);
        for (int i = -5; i <= 5; i++) {
            float x = map(i, -5, 5, border, width - border);
            line(x, border, x, height - border);
            line(border, x, width - border, x);
            fill(110, 92, 100);
            text(i, x, border * 0.5f);
            text(i, border * 0.5f, x);
        }
    }

    void printStars() {
        for (Star s : stars) {
            System.out.println(s);
        }
    }

    class Star {
        boolean habitable;
        String displayName;
        float distance;
        float xg, yg, zg;
        float absMag;

        public Star(boolean habitable, String displayName, float distance, float xg, float yg, float zg, float absMag) {
            this.habitable = habitable;
            this.displayName = displayName;
            this.distance = distance;
            this.xg = xg;
            this.yg = yg;
            this.zg = zg;
            this.absMag = absMag;
        }

        public float getXg() {
            return xg;
        }

        public float getYg() {
            return yg;
        }

        public float getZg() {
            return zg;
        }

        void render(PApplet pa) {
            float starSize = 16;
            float sc = 0.6f / distance;
            float sx = map(xg / distance, -1, 1, 0, pa.width);
            float sy = map(yg / distance, -1, 1, 0, pa.height);
            pa.stroke(255, 255, 0);
			pa.fill(0, 0, 100);
            pa.line(sx - starSize, sy - starSize, sx + starSize, sy + starSize);
            pa.line(sx - starSize, sy + starSize, sx + starSize, sy - starSize);
            pa.stroke(0, 0, 100);
            pa.line(sx, sy, sx + (zg * sc), sy - (zg * sc));
        }
    }

    void loadStars() {
        Table table = loadTable("HabHYG15ly.csv", "header");
        for (TableRow r : table.rows()) {
            Star s = new Star(
                r.getInt("Hab?") == 1, 
                r.getString("Display Name"), 
                r.getFloat("Distance"),
                r.getFloat("Xg"),
                r.getFloat("Yg"),
                r.getFloat("Zg"),
                r.getFloat("AbsMag")
            );
            stars.add(s);
        }
    }

    public void settings() {
        size(500, 500);
    }

    Star first = null;
    Star second = null;

    public void setup() {
        colorMode(HSB);
        background(0);
        smooth();
        border = width * 0.1f;
        loadStars();
        printStars();
        border = width * 0.1f;
    }

    void drawStars() {
        for (Star s : stars) {
            s.render(this);
        }
    }

    public void draw() 
    {
        background(0);
        drawGrid();
        drawStars();

    }

}