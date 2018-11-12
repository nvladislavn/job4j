package ru.job4j.profession;

/**
 * Doctor
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 06.11.2018
 */
public class Doctor extends Profession {

    public Diagnose heal(Patient patient) {
        Diagnose diag = new Diagnose();
        return diag;
    }

    /**
     * cure method
     *
     * @param patient
     * @return when curing is successful then true, else false.
     */
    public boolean cure(Patient patient) {
        return true;
    }
}
