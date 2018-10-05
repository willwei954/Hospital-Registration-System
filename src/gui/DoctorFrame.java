package gui;

import containers.DoctorSetAccess;
import entities.Doctor;

import javax.swing.*;

public class DoctorFrame extends JFrame{
    /** The standard width for the frame. */
    public static final int DEFAULT_WIDTH = 350;

    /** The standard height for the frame. */
    public static final int DEFAULT_HEIGHT = 200;

    /**
     * Create the frame to display the information for a doctor.
     *
     * @param name the name of the doctor
     */
    public DoctorFrame(String name) {
        Doctor doctor = DoctorSetAccess.dictionary().get(name);
        if (doctor != null) {
            setTitle(doctor.getName() + " (" + name + ")");
            setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
            DoctorPanel panel = new DoctorPanel(doctor);
            add(panel);
        } else
            throw new RuntimeException("Invalid doctor name " + name);
    }

    public static final long serialVersionUID = 1;
}
