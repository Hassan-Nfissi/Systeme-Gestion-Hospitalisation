package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuPrincipal extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JPanel mainPanel;
    private JPanel menuPanel;
    private JPanel contentPanel;

    public MenuPrincipal() {
        // Configuration de la fenêtre principale
        setTitle("Gestion Hospitalisation");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialisation des layouts
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        mainPanel = new JPanel(new BorderLayout());

        // Création du panel du menu
        createMenuPanel();

        // Création du panel de contenu initial
        createHomePanel();

        // Création des panels de gestion
        JPanel patientPanel = new JPanel();
        patientPanel.setLayout(new BorderLayout());
        patientPanel.add(new MiseAJourPatient().getContentPane());

        JPanel acteMedicalPanel = new JPanel();
        acteMedicalPanel.setLayout(new BorderLayout());
        acteMedicalPanel.add(new MiseAJourActeMedical().getContentPane());

        // Ajout des panels au CardLayout
        cardPanel.add(contentPanel, "home");
        cardPanel.add(patientPanel, "patients");
        cardPanel.add(acteMedicalPanel, "actes");

        // Assemblage de l'interface
        mainPanel.add(menuPanel, BorderLayout.WEST);
        mainPanel.add(cardPanel, BorderLayout.CENTER);

        // Ajout du panel principal
        add(mainPanel);

        // Création de la barre de menu
        createMenuBar();

        // Rendre la fenêtre visible
        setVisible(true);
    }

    private void createMenuPanel() {
        menuPanel = new JPanel();
        menuPanel.setPreferredSize(new Dimension(200, getHeight()));
        menuPanel.setBackground(new Color(0, 51, 102)); // Bleu foncé
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

        // Titre du menu
        JLabel titleLabel = new JLabel("Menu Principal");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        menuPanel.add(titleLabel);

        // Boutons du menu
        addMenuButton("Accueil", "home", "🏠");
        addMenuButton("Gestion des Patients", "patients", "👤");
        addMenuButton("Gestion des Actes", "actes", "📋");

        // Ajout d'un espace flexible
        menuPanel.add(Box.createVerticalGlue());
    }

    private void addMenuButton(String text, String cardName, String icon) {
        JButton button = new JButton(icon + " " + text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(180, 40));
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 102, 204)); // Bleu vif
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(51, 153, 255));
            }
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(0, 102, 204));
            }
        });

        button.addActionListener(e -> cardLayout.show(cardPanel, cardName));
        menuPanel.add(button);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    }

    private void createHomePanel() {
        contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(new Color(240, 248, 255)); // Bleu clair

        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));
        welcomePanel.setBackground(Color.WHITE);
        welcomePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(135, 206, 250)), // Bordure bleu clair
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JLabel welcomeLabel = new JLabel("Bienvenue dans le Système de Gestion Hospitalière");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeLabel.setForeground(new Color(0, 102, 204)); // Texte bleu foncé

        JLabel descriptionLabel = new JLabel("<html><center>Utilisez le menu à gauche pour accéder aux différentes fonctionnalités</center></html>");
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        descriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        descriptionLabel.setForeground(new Color(70, 130, 180)); // Texte bleu

        // Ajout de l'image
        JLabel imageLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\hassan nfissi\\Desktop\\JAVA cours\\GestionHospitalisation\\src\\ui\\img_5.png"); // Chemin de l'image
        Image image = imageIcon.getImage().getScaledInstance(800, 500, Image.SCALE_SMOOTH); // Redimensionnement
        imageLabel.setIcon(new ImageIcon(image));
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Ajouter les composants au panneau d'accueil
        welcomePanel.add(welcomeLabel);
        welcomePanel.add(Box.createRigidArea(new Dimension(0, 20)));
        welcomePanel.add(descriptionLabel);
        welcomePanel.add(Box.createRigidArea(new Dimension(0, 30)));
        welcomePanel.add(imageLabel);

        contentPanel.add(welcomePanel);
    }


    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menuFichier = new JMenu("Fichier");
        JMenuItem menuItemQuitter = new JMenuItem("Quitter");
        menuItemQuitter.addActionListener(e -> System.exit(0));
        menuFichier.add(menuItemQuitter);

        JMenu menuAide = new JMenu("Aide");
        JMenuItem menuItemAPropos = new JMenuItem("À propos");
        menuItemAPropos.addActionListener(e -> afficherAPropos());
        menuAide.add(menuItemAPropos);

        menuBar.add(menuFichier);
        menuBar.add(menuAide);

        setJMenuBar(menuBar);
    }

    private void afficherAPropos() {
        JOptionPane.showMessageDialog(this,
                "Système de Gestion Hospitalière\n" +
                        "Version 1.0\n" +
                        "© 2024 - Tous droits réservés",
                "À propos",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        SwingUtilities.invokeLater(() -> new MenuPrincipal());
    }
}
