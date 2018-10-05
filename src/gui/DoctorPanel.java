package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import commands.DropAssocCommand;
import commands.AssignDoctorCommand;
import entities.Doctor;


public class DoctorPanel extends JPanel{
    /**
     * Create the panel to display the doctor's information and accept operations on the doctor.
     */
    public DoctorPanel(Doctor doctor) {
        /*
         * The creation of the panel is placed in another method as it needs to be invoked whenever
         * the patient information of the doctor is changed.
         */
        build(doctor);
    }

    /**
     * Fill in the panel to display the patient's information and accept operations on the patient.
     *
     * @param doctor the patient whose information is to be displayed and on whom operations can be
     *        done
     */
    private void build(Doctor doctor) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));



        // add an empty panel to force the add patient and exit components to the bottom
        JPanel emptyPanel = new JPanel();
        add(emptyPanel);
        emptyPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel addPatientPanel = addPatientPanel(doctor);
        add(addPatientPanel);
        addPatientPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        addPatientPanel.setMaximumSize(addPatientPanel.getPreferredSize());

        JPanel checkPatientPanel = checkPatientPanel(doctor);
        add(checkPatientPanel);
        checkPatientPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        checkPatientPanel.setMaximumSize(checkPatientPanel.getPreferredSize());

        JPanel removePatientPanel = removePatientPanel(doctor);
        add(removePatientPanel);
        removePatientPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        removePatientPanel.setMaximumSize(removePatientPanel.getPreferredSize());

        add(new JLabel("  ")); // blank line in the panel for spacing
        final JButton exitButton = new JButton("Exit");
        add(exitButton);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                exitButton.getTopLevelAncestor().setVisible(false);
            }
        });
    }

    /**
     * A panel to add a doctor-patient association for this patient. The panel as a prompt to enter
     * the patient's health number, and a field to enter the number.
     *
     * @param doctor the current doctor
     * @return a panel to associate a new patient with this doctor
     */
    private JPanel addPatientPanel(final Doctor doctor) {
        JPanel addPatientPanel = new JPanel();
        addPatientPanel.add(new JLabel("Add patient"));
        final JTextField textField = new JTextField(10);
        addPatientPanel.add(textField);
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                int HealthNumber = Integer.parseInt(textField.getText());
                AssignDoctorCommand addAssoc = new AssignDoctorCommand();
                addAssoc.assignDoctor(doctor.getName(), HealthNumber);
                if (addAssoc.wasSuccessful()) {
                    // recreate the panel as it has changed
                    removeAll();
                    build(doctor);
                    revalidate();
                } else {
                    JOptionPane.showMessageDialog(DoctorPanel.this, addAssoc.getErrorMessage());
                }
            }
        });
        return addPatientPanel;
    }

    private JPanel removePatientPanel(final Doctor doctor) {
        JPanel removePatientPanel = new JPanel();
        removePatientPanel.add(new JLabel("Remove patient"));
        final JTextField textField = new JTextField(10);
        removePatientPanel.add(textField);
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                int HealthNumber = Integer.parseInt(textField.getText());
                DropAssocCommand dropAssoc = new DropAssocCommand();
                dropAssoc.dropAssociation(doctor.getName(), HealthNumber);
                if (dropAssoc.wasSuccessful()) {
                    // recreate the panel as it has changed
                    removeAll();
                    build(doctor);
                    revalidate();
                } else {
                    JOptionPane.showMessageDialog(DoctorPanel.this, dropAssoc.getErrorMessage());
                }
            }
        });
        return removePatientPanel;
    }

    /**
     * A panel to display the health number of a patient for the doctor. Also, a button is provided to remove
     * the association of this patient with the doctor.
     *
     * @param doctor a doctor of
     * @return the panel to display the health number of the patient, with a button to remove the
     *         patient-doctor association
     */
    private JPanel checkPatientPanel(final Doctor doctor) {
        JPanel checkPatientPanel = new JPanel();
        checkPatientPanel.add(new JLabel("check patient"));
        final JTextField textField = new JTextField(10);
        checkPatientPanel.add(textField);
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String valueAsString = textField.getText();
                int healthNum = -1;
                if (valueAsString != null && valueAsString.length() > 0) {
                    try {
                        healthNum = Integer.parseInt(valueAsString);
                    } catch (NumberFormatException e) {
                        textField.setText("Not int: " + textField.getText());
                        textField.revalidate();
                        return;
                    }
                    PatientFrame frame = null;
                    try {
                        frame = new PatientFrame(healthNum);
                    } catch (RuntimeException e) {
                        textField.setText("Invalid id: " + textField.getText());
                        textField.revalidate();
                        return;
                    }
                    frame.setLocation(300, 300);
                    frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    frame.setVisible(true);
                    textField.setText("");
                    textField.revalidate();
                } else {
                    textField.setText("Empty field: " + textField.getText());
                    textField.revalidate();
                }
            }
        });
        return checkPatientPanel;
    }
    public static final long serialVersionUID = 1;
}
