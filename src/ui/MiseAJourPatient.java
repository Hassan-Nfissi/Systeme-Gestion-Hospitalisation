package ui;

import modele.Patient;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class MiseAJourPatient extends JFrame {
    private JTextField txtID, txtNCIN, txtNomPrenom, txtAdresse, txtTel;
    private JCheckBox chkMutuelle;
    private JComboBox<String> cmbTypeMutuelle;
    private JButton btnChercher, btnAjouter, btnModifier, btnSupprimer, btnEffacer;
    private Patient patient;

    public MiseAJourPatient() {
        // Configuration de la fenêtre
        setTitle("Gestion des Patients");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialisation du patient
        patient = new Patient();

        // Création des composants
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Création des labels et champs
        JLabel lblID = new JLabel("ID Patient:");
        txtID = new JTextField(20);
        JLabel lblNCIN = new JLabel("NCIN:");
        txtNCIN = new JTextField(20);
        JLabel lblNomPrenom = new JLabel("Nom et Prénom:");
        txtNomPrenom = new JTextField(20);
        JLabel lblAdresse = new JLabel("Adresse:");
        txtAdresse = new JTextField(20);
        JLabel lblTel = new JLabel("Téléphone:");
        txtTel = new JTextField(20);
        JLabel lblMutuelle = new JLabel("Mutuelle:");
        chkMutuelle = new JCheckBox();
        JLabel lblTypeMutuelle = new JLabel("Type Mutuelle:");
        String[] typesMutuelle = {"CNOPS", "CNSS", "FAR", "AUTRE"};
        cmbTypeMutuelle = new JComboBox<>(typesMutuelle);

        // Ajout des composants avec GridBagLayout
        gbc.gridx = 0; gbc.gridy = 0;
        mainPanel.add(lblID, gbc);
        gbc.gridx = 1;
        mainPanel.add(txtID, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        mainPanel.add(lblNCIN, gbc);
        gbc.gridx = 1;
        mainPanel.add(txtNCIN, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        mainPanel.add(lblNomPrenom, gbc);
        gbc.gridx = 1;
        mainPanel.add(txtNomPrenom, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        mainPanel.add(lblAdresse, gbc);
        gbc.gridx = 1;
        mainPanel.add(txtAdresse, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        mainPanel.add(lblTel, gbc);
        gbc.gridx = 1;
        mainPanel.add(txtTel, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        mainPanel.add(lblMutuelle, gbc);
        gbc.gridx = 1;
        mainPanel.add(chkMutuelle, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        mainPanel.add(lblTypeMutuelle, gbc);
        gbc.gridx = 1;
        mainPanel.add(cmbTypeMutuelle, gbc);

        // Panel pour les boutons
        JPanel buttonPanel = new JPanel();
        btnChercher = new JButton("Chercher");
        btnAjouter = new JButton("Ajouter");
        btnModifier = new JButton("Modifier");
        btnSupprimer = new JButton("Supprimer");
        btnEffacer = new JButton("Effacer");

        buttonPanel.add(btnChercher);
        buttonPanel.add(btnAjouter);
        buttonPanel.add(btnModifier);
        buttonPanel.add(btnSupprimer);
        buttonPanel.add(btnEffacer);

        gbc.gridx = 0; gbc.gridy = 7;
        gbc.gridwidth = 2;
        mainPanel.add(buttonPanel, gbc);

        // Ajout des écouteurs d'événements
        btnChercher.addActionListener(e -> chercherPatient());
        btnAjouter.addActionListener(e -> ajouterPatient());
        btnModifier.addActionListener(e -> modifierPatient());
        btnSupprimer.addActionListener(e -> supprimerPatient());
        btnEffacer.addActionListener(e -> effacerChamps());

        // Ajout du panel principal
        add(mainPanel);

        // Rendre la fenêtre visible
        setVisible(true);
    }

    private void chercherPatient() {
        try {
            int id = Integer.parseInt(txtID.getText());
            patient.chercherPatient(id);

            // Mise à jour des champs
            txtNCIN.setText(patient.getNCIN());
            txtNomPrenom.setText(patient.getNomPrenomPatient());
            txtAdresse.setText(patient.getAdressePatient());
            txtTel.setText(patient.getTelPatient());
            chkMutuelle.setSelected(patient.isMutuellePatient());
            cmbTypeMutuelle.setSelectedItem(patient.getTypeMutuellePatient());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer un ID valide", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void ajouterPatient() {
        if (validerChamps()) {
            patient.setNCIN(txtNCIN.getText());
            patient.setNomPrenomPatient(txtNomPrenom.getText());
            patient.setAdressePatient(txtAdresse.getText());
            patient.setTelPatient(txtTel.getText());
            patient.setMutuellePatient(chkMutuelle.isSelected());
            patient.setTypeMutuellePatient((String) cmbTypeMutuelle.getSelectedItem());

            patient.ajouterPatient();
            effacerChamps();
            JOptionPane.showMessageDialog(this, "Patient ajouté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void modifierPatient() {
        if (validerChamps() && !txtID.getText().isEmpty()) {
            patient.setIDPatient(Integer.parseInt(txtID.getText()));
            patient.setNCIN(txtNCIN.getText());
            patient.setNomPrenomPatient(txtNomPrenom.getText());
            patient.setAdressePatient(txtAdresse.getText());
            patient.setTelPatient(txtTel.getText());
            patient.setMutuellePatient(chkMutuelle.isSelected());
            patient.setTypeMutuellePatient((String) cmbTypeMutuelle.getSelectedItem());

            patient.modifierPatient();
            JOptionPane.showMessageDialog(this, "Patient modifié avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void supprimerPatient() {
        if (!txtID.getText().isEmpty()) {
            int confirmation = JOptionPane.showConfirmDialog(this,
                    "Êtes-vous sûr de vouloir supprimer ce patient ?",
                    "Confirmation",
                    JOptionPane.YES_NO_OPTION);

            if (confirmation == JOptionPane.YES_OPTION) {
                patient.supprimerPatient(Integer.parseInt(txtID.getText()));
                effacerChamps();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez d'abord chercher un patient", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void effacerChamps() {
        txtID.setText("");
        txtNCIN.setText("");
        txtNomPrenom.setText("");
        txtAdresse.setText("");
        txtTel.setText("");
        chkMutuelle.setSelected(false);
        cmbTypeMutuelle.setSelectedIndex(0);
    }

    private boolean validerChamps() {
        if (txtNCIN.getText().isEmpty() ||
                txtNomPrenom.getText().isEmpty() ||
                txtAdresse.getText().isEmpty() ||
                txtTel.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Veuillez remplir tous les champs obligatoires",
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MiseAJourPatient());
    }
}