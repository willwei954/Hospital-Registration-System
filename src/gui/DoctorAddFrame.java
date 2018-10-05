package gui;

import javax.swing.JFrame;

public class DoctorAddFrame extends JFrame {
    /** The standard width for the frame. */
    public static final int DEFAULT_WIDTH = 450;

    /** The standard height for the frame. */
    public static final int DEFAULT_HEIGHT = 300;

    /**
     * Create the frame to add a doctor.
     */
    public DoctorAddFrame() {
        setTitle("Doctor Addition");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        DoctorAddPanel panel = new DoctorAddPanel();
        add(panel);
    }

    public static final long serialVersionUID = 1;
}
