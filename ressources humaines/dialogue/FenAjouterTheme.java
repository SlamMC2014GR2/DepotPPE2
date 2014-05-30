package dialogue;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
//permet d établir le lien avec la classe GestionDemandes
import controle.GestionDemandes;
import entite.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FenAjouterTheme extends JFrame {

	private JPanel contentPane;
	private JTextField txt_libelléTheme;
	private GestionDemandes gestionBD = new GestionDemandes();  
	private GestAtelierList listeAtel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenAjouterTheme frame = new FenAjouterTheme();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FenAjouterTheme() {
		setTitle("Maison des Ligues : Cr\u00E9er un Th\u00E8me");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 440, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Propri\u00E9t\u00E9s du Th\u00E8me", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(0, 11, 421, 119);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel JLabel_libelléTheme = new JLabel("Libell\u00E9");
		JLabel_libelléTheme.setBounds(10, 20, 100, 30);
		panel.add(JLabel_libelléTheme);
		
		txt_libelléTheme = new JTextField();
		txt_libelléTheme.setBounds(90, 20, 288, 30);
		panel.add(txt_libelléTheme);
		txt_libelléTheme.setColumns(10);
		
		JLabel JLabel_idAtelier = new JLabel("ID de l'Atelier");
		JLabel_idAtelier.setBounds(10, 74, 132, 20);
		panel.add(JLabel_idAtelier);
		
		final JComboBox comboBox_idAtelier = new JComboBox();
				comboBox_idAtelier.setBounds(139, 74, 75, 20);
		panel.add(comboBox_idAtelier);
		listeAtel=gestionBD.chargeAtelier();
		int taille = listeAtel.Nbelement();
		comboBox_idAtelier.setMaximumRowCount(taille);
		for (int ind =0; ind < taille; ind++){
			comboBox_idAtelier.addItem(listeAtel.elt(ind).getNoatelier());
		}
		comboBox_idAtelier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// permet de récupérer le num de l'atelier
				System.out.println(comboBox_idAtelier.getSelectedItem().toString());
			}
		});
		
		
		
		JButton btn_enregistrer = new JButton("Enregistrer");
		btn_enregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txt_libelléTheme.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Action Impossible. Vous devez choisir un libellé pour le thème.",
							"ERREUR", JOptionPane.ERROR_MESSAGE);
				}
				else{
					gestionBD.enregistrerTheme(txt_libelléTheme.getText(), Integer.parseInt(comboBox_idAtelier.getSelectedItem().toString()));
					txt_libelléTheme.setText("");
					JOptionPane.showMessageDialog(null, "Création Thème effectuée.",
							"Information", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btn_enregistrer.setBounds(31, 155, 110, 40);
		contentPane.add(btn_enregistrer);
		
		JButton btn_quitter = new JButton("Quitter");
		btn_quitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reponse=JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir quitter ?",
						"Maison Des Ligues", JOptionPane.YES_NO_OPTION);
				if(reponse==JOptionPane.YES_OPTION){
				FenAjouterTheme.this.dispose() ;
			//	controle.ControleConnexion.getControleConnexion().fermetureSession();
				System.exit(0);
				}
							
			}
		});
		btn_quitter.setBounds(151, 155, 110, 40);
		contentPane.add(btn_quitter);
		
		JButton btn_retourMenu = new JButton("Retour Menu");
		btn_retourMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FenMenu LaFenMenu = new FenMenu();
				LaFenMenu.setVisible(true);
				FenAjouterTheme.this.dispose();
			}
		});
		btn_retourMenu.setBounds(271, 155, 110, 40);
		contentPane.add(btn_retourMenu);
	}
}
