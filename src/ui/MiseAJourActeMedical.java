package ui;

import modele.ActeMedical;
import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.Calendar;
import javax.swing.SpinnerDateModel;
import java.text.SimpleDateFormat;

public class MiseAJourActeMedical extends JFrame {
    private JTextField txtIDDossier, txtIDActe, txtIDPatient, txtMedecin;
    private JComboBox<String> cmbTypeActe;
    private JSpinner dateSpinner;
    private JButton btnChercher, btnAjouter, btnModifier, btnSupprimer, btnEffacer;
    private ActeMedical acteMedical;

    public MiseAJourActeMedical() {
        // Configuration de la fenêtre
        setTitle("Gestion des Actes Médicaux");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialisation de l'acte médical
        acteMedical = new ActeMedical();

        // Création des composants
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Création des labels et champs
        JLabel lblIDDossier = new JLabel("ID Dossier Médical:");
        txtIDDossier = new JTextField(20);
        JLabel lblIDActe = new JLabel("ID Acte Médical:");
        txtIDActe = new JTextField(20);
        JLabel lblIDPatient = new JLabel("ID Patient:");
        txtIDPatient = new JTextField(20);
        JLabel lblTypeActe = new JLabel("Type Acte Médical:");
        String[] typesActe = {"Consultation", "Chirurgie", "Radiologie", "Analyse", "Autre"};
        cmbTypeActe = new JComboBox<>(typesActe);
        JLabel lblDate = new JLabel("Date Acte Médical:");

        // Configuration du spinner de date
        SpinnerDateModel dateModel = new SpinnerDateModel();
        dateSpinner = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd");
        dateSpinner.setEditor(dateEditor);

        JLabel lblMedecin = new JLabel("Médecin:");
        txtMedecin = new JTextField(20);

        // Ajout des composants avec GridBagLayout
        gbc.gridx = 0; gbc.gridy = 0;
        mainPanel.add(lblIDActe, gbc);
        gbc.gridx = 1;
        mainPanel.add(txtIDActe, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        mainPanel.add(lblIDDossier, gbc);
        gbc.gridx = 1;
        mainPanel.add(txtIDDossier, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        mainPanel.add(lblIDPatient, gbc);
        gbc.gridx = 1;
        mainPanel.add(txtIDPatient, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        mainPanel.add(lblTypeActe, gbc);
        gbc.gridx = 1;
        mainPanel.add(cmbTypeActe, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        mainPanel.add(lblDate, gbc);
        gbc.gridx = 1;
        mainPanel.add(dateSpinner, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        mainPanel.add(lblMedecin, gbc);
        gbc.gridx = 1;
        mainPanel.add(txtMedecin, gbc);

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

        gbc.gridx = 0; gbc.gridy = 6;
        gbc.gridwidth = 2;
        mainPanel.add(buttonPanel, gbc);

        // Ajout des écouteurs d'événements
        btnChercher.addActionListener(e -> chercherActeMedical());
        btnAjouter.addActionListener(e -> ajouterActeMedical());
        btnModifier.addActionListener(e -> modifierActeMedical());
        btnSupprimer.addActionListener(e -> supprimerActeMedical());
        btnEffacer.addActionListener(e -> effacerChamps());

        // Ajout du panel principal
        add(mainPanel);

        // Rendre la fenêtre visible
        setVisible(true);
    }

    private void chercherActeMedical() {
        try {
            int id = Integer.parseInt(txtIDActe.getText());
            acteMedical.chercherActeMedical(id);

            // Mise à jour des champs
            txtIDDossier.setText(String.valueOf(acteMedical.getIDDossierMedical()));
            txtIDPatient.setText(String.valueOf(acteMedical.getIDPatient()));
            cmbTypeActe.setSelectedItem(acteMedical.getTypeActeMedical());
            dateSpinner.setValue(acteMedical.getDateActeMedical());
            txtMedecin.setText(acteMedical.getMedecinActeMedical());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer un ID valide", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void ajouterActeMedical() {
        if (validerChamps()) {
            acteMedical.setIDDossierMedical(Integer.parseInt(txtIDDossier.getText()));
            acteMedical.setIDPatient(Integer.parseInt(txtIDPatient.getText()));
            acteMedical.setTypeActeMedical((String) cmbTypeActe.getSelectedItem());
            acteMedical.setDateActeMedical((Date) dateSpinner.getValue());
            acteMedical.setMedecinActeMedical(txtMedecin.getText());

            acteMedical.ajouterActeMedical();
            effacerChamps();
            JOptionPane.showMessageDialog(this, "Acte médical ajouté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    private void modifierActeMedical() {
        if (validerChamps() && !txtIDActe.getText().isEmpty()) {
            acteMedical.setIDActeMedical(Integer.parseInt(txtIDActe.getText()));
            acteMedical.setIDDossierMedical(Integer.parseInt(txtIDDossier.getText()));
            acteMedical.setIDPatient(Integer.parseInt(txtIDPatient.getText()));
            acteMedical.setTypeActeMedical((String) cmbTypeActe.getSelectedItem());
            acteMedical.setDateActeMedical((Date) dateSpinner.getValue());
            acteMedical.setMedecinActeMedical(txtMedecin.getText());

            acteMedical.modifierActeMedical();
            JOptionPane.showMessageDialog(this, "Acte médical modifié avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void supprimerActeMedical() {
        if (!txtIDActe.getText().isEmpty()) {
            int confirmation = JOptionPane.showConfirmDialog(this,
                    "Êtes-vous sûr de vouloir supprimer cet acte médical ?",
                    "Confirmation",
                    JOptionPane.YES_NO_OPTION);

            if (confirmation == JOptionPane.YES_OPTION) {
                acteMedical.supprimerActeMedical(Integer.parseInt(txtIDActe.getText()));
                effacerChamps();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez d'abord chercher un acte médical", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void effacerChamps() {
        txtIDActe.setText("");
        txtIDDossier.setText("");
        txtIDPatient.setText("");
        cmbTypeActe.setSelectedIndex(0);
        dateSpinner.setValue(new Date()); // Réinitialise à la date actuelle
        txtMedecin.setText("");
    }

    private boolean validerChamps() {
        if (txtIDDossier.getText().isEmpty() ||
                txtIDPatient.getText().isEmpty() ||
                txtMedecin.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Veuillez remplir tous les champs obligatoires",
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            Integer.parseInt(txtIDDossier.getText());
            Integer.parseInt(txtIDPatient.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Les IDs doivent être des nombres",
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MiseAJourActeMedical());
    }
}