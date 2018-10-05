package gui;

import commands.AddDoctorCommand;
import entities.Surgeon;
import java.util.*;
import java.lang.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DoctorAddPanel extends JPanel{
        /* Declare the components of the panel needed by inner classes. */

    /**
     * A text area to be used to display an error if one should occur.
     */
    JTextArea error = null;

    /**
     * A panel for the entry of the name of a new doctor.
     */
    ValueEntryPanel namePanel;

    /**
     * A panel for the entry of the of a new Surgeon.
     */
    ValueEntryPanel SurgeonPanel;

    /**
     * Create the panel to obtain data for the creation of a doctor, and to cause the doctor to be
     * created.
     */
    public DoctorAddPanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(Box.createVerticalGlue());

        // add a label with a prompt to enter the doctor data
        JLabel prompt = new JLabel("Enter Doctor Information");
        prompt.setMaximumSize(prompt.getPreferredSize());
        add(prompt);
        prompt.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalGlue());

        // add a panel with the field for the entry of the doctor's name
        namePanel = new ValueEntryPanel("name");
        namePanel.setMaximumSize(namePanel.getPreferredSize());
        add(namePanel);
        namePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalGlue());

        // add a panel with the field for the entry of the doctor or the surgeon
        SurgeonPanel = new ValueEntryPanel("Is this Doctor a Surgeon?\n(true OR false)");
        SurgeonPanel.setMaximumSize(SurgeonPanel.getPreferredSize());
        add(SurgeonPanel);
        SurgeonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalGlue());

        // add a button to submit the information and create the doctor
        JButton submitButton = new JButton("Submit");
        submitButton.setMaximumSize(submitButton.getPreferredSize());
        add(submitButton);
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.addActionListener(new DoctorAddPanel.SubmitListener());
        add(Box.createVerticalGlue());

        JButton T = new JButton(("True"));
       // T.
    }

    /**
     * The class listening for the press of the submit button. It accesses the name, and uses them to add a new doctor to the system.
     */
    private class SubmitListener implements ActionListener {
        /**
         * When the submit button is pressed, access the name entered, and use
         * them to add a new doctor to the system.
         */
        public void actionPerformed(ActionEvent event) {
            if (error != null) {
                // remove error from the previous submission
                remove(error);
                error = null;
            }
            String name = namePanel.getValueAsString();
            boolean surgeonPan;

            if (!SurgeonPanel.getValueAsString().equals("true") && !SurgeonPanel.getValueAsString().equals("false")) {
                error = new JTextArea(SplitString.at("Please insert boolean (true OR false)", 45));
                error.setMaximumSize(error.getPreferredSize());
                add(error);
                error.setAlignmentX(Component.CENTER_ALIGNMENT);
                revalidate();
            }
            else{
                surgeonPan = Boolean.parseBoolean(SurgeonPanel.getValueAsString());

                AddDoctorCommand addDoctor = new AddDoctorCommand();
                addDoctor.addDoctor(name, surgeonPan);
                if (addDoctor.wasSuccessful()) {
                    getTopLevelAncestor().setVisible(false);
                } else {
                    error = new JTextArea(SplitString.at(addDoctor.getErrorMessage(), 45));
                    error.setMaximumSize(error.getPreferredSize());
                    add(error);
                    error.setAlignmentX(Component.CENTER_ALIGNMENT);
                    add(Box.createVerticalGlue());
                    revalidate();
                }
            }
        }
    }
    public static final long serialVersionUID = 1;
}
