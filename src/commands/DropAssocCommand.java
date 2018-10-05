package commands;

import entities.Patient;
import entities.Doctor;
import containers.PatientSetAccess;
import containers.DoctorSetAccess;

/**
 * Command to drop the association between a doctor and a patient.
 */
public class DropAssocCommand extends CommandStatus {
    /**
     * Drop the association between a doctor and a patient.
     * 
     * @param name the name of the doctor
     * @param patientNum the health number of the patient
     */
    public void dropAssociation(String name, int patientNum) {
        Patient p = PatientSetAccess.dictionary().get(patientNum);
        if (p == null) {
            successful = false;
            errorMessage = "There is no patient with health number " + patientNum;
        } else
            successful = true;

        Doctor d = DoctorSetAccess.dictionary().get(name);
        if (d == null) {
            if (successful)
                errorMessage = "There is no doctor with name " + name;
            else
                errorMessage = errorMessage + " and there is no doctor with name " + name;
            successful = false;
        }
        if (!successful)
            return;

        if (!d.hasPatient(patientNum)) {
            successful = false;
            errorMessage = "This doctor is not associated with this patient.";
            return;
        } else if (!p.hasDoctor(name)) {
            successful = false;
            errorMessage =
                    "This doctor and this patient are incorrectly associated."
                            + "  The doctor has the patient, "
                            + "but the patient does not have the doctor";
            return;
        }

        p.removeDoctor(name);
        d.removePatient(patientNum);
        successful = true;
    }
}
