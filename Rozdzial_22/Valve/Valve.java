package Rozdzial_22.Valve;

import javax.swing.*;

public class Valve extends JPanel {
    private boolean on;
    private int level;

    public void setLevel(int level) {
        this.level = level;
    }
    public int getLevel() {
        return level;
    }
    public boolean isOn() {
        return on;
    }
    public void setOn(boolean on) {
        this.on = on;
    }
}
