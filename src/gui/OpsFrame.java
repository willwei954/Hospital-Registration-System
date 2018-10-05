package gui;

import javax.swing.*;

public class OpsFrame extends JFrame {

    public static final int DEFAULT_WIDTH = 350;

    public static final int DEFAULT_HEIGHT = 200;

    /**
     * Create the frame for the operations.
     */
    public OpsFrame() {
        setTitle("Operations");
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
        OpsPanel panel = new OpsPanel();
        add(panel);
    }

    public static final long serialVersionUID = 1;
}
