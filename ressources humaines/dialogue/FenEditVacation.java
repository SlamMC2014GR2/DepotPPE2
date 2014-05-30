package dialogue;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
//permet d établir le lien avec la classe GestionDemandes
import controle.GestionDemandes;
import entite.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class FenEditVacation extends JFrame {

	private JPanel contentPane;
	private JTextField txt_dateDeb;
	private JTextField txt_dateFin;
	private GestionDemandes gestionBD = new GestionDemandes();  
	private GestVacationList listeVacation;   // contient toutes les vacations
	private GestVacationList listeVacation2;  // contient les vacations par num d'atelier
	private Vacation laVacation; // contient la vacation recherchée
	private String atelierSelect  ;
	private SimpleDateFormat leformat = new SimpleDateFormat("dd/MM/yyyy");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenEditVacation frame = new FenEditVacation();
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
	public FenEditVacation() {
		setTitle("Maison des Ligues : Editer Vacation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JButton btn_enregistrer = new JButton("Enregistrer");		
		btn_enregistrer.setBounds(31, 155, 110, 40);
		contentPane.add(btn_enregistrer);
		btn_enregistrer.setEnabled(false);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Choix de la vacation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(0, 11, 421, 80);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel JLabel_idAtelier = new JLabel("ID de l'Atelier");
		JLabel_idAtelier.setBounds(10, 15, 100, 30);
		panel.add(JLabel_idAtelier);
		
		final JComboBox comboBox_idAtelier = new JComboBox();
		comboBox_idAtelier.setBounds(98, 20, 75, 20);
		panel.add(comboBox_idAtelier);
		listeVacation=gestionBD.chargeVacation().SuprDoublon();
		int taille = listeVacation.Nbelement();
		comboBox_idAtelier.setMaximumRowCount(taille);
		for (int ind=0; ind<taille; ind++){
			comboBox_idAtelier.addItem(listeVacation.elt(ind).getNoatelier());
		}
		atelierSelect=listeVacation.elt(0).getNoatelier().toString();
	
			
		
		JLabel JLabel_idVacation = new JLabel("ID de la Vacation");
		JLabel_idVacation.setBounds(202, 15, 100, 30);
		panel.add(JLabel_idVacation);
		
		final JComboBox comboBox_idVacation = new JComboBox();
		comboBox_idVacation.setBounds(312, 20, 75, 20);
		panel.add(comboBox_idVacation);
		listeVacation2 = gestionBD.rechercherLesVacations(atelierSelect);
		int size = listeVacation2.Nbelement();
		comboBox_idVacation.setMaximumRowCount(size);
		for (int i=0 ; i<size ; i++){
			comboBox_idVacation.addItem(listeVacation2.elt(i).getNovacation());
		}
		comboBox_idVacation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txt_dateDeb.setText("");
				txt_dateFin.setText("");
				btn_enregistrer.setEnabled(false);
			}
		});
		
		// Permet de modifier la seconde liste déroulante lorsque l'on change le num d'atelier selectionné
		comboBox_idAtelier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// permet de récupérer le num de l'atelier
				System.out.println(comboBox_idAtelier.getSelectedItem().toString());
				comboBox_idVacation.removeAllItems();
				txt_dateDeb.setText("");
				txt_dateFin.setText("");
				btn_enregistrer.setEnabled(false);
				atelierSelect = comboBox_idAtelier.getSelectedItem().toString();
				listeVacation2 = gestionBD.rechercherLesVacations(atelierSelect);
				int size = listeVacation2.Nbelement();
				comboBox_idVacation.setMaximumRowCount(size);
				for (int i=0 ; i<size ; i++){
					comboBox_idVacation.addItem(listeVacation2.elt(i).getNovacation());
				}
				
			}
		});
		
		// action qui permet d'enregistrer la modification de la vacation sélectionnée
		btn_enregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String deb = txt_dateDeb.getText();
				String fin = txt_dateFin.getText();
				Date dd = null;
				Date df = null;
				if(deb.length()!=10 || !deb.substring(2,3).equals("/") || !deb.substring(5, 6).equals("/")){
					JOptionPane.showMessageDialog(null, "Date invalide. La date doit être au format dd/mm/yyyy.",
							"ERREUR", JOptionPane.ERROR_MESSAGE);
				}
				else{
				try {
					dd = new Date(leformat.parse(deb).getTime());
					df = new Date(leformat.parse(fin).getTime());
					if(dd.before(df) || dd.equals(df)){
					gestionBD.editerVacation(Integer.parseInt(comboBox_idAtelier.getSelectedItem().toString()), Integer.parseInt(comboBox_idVacation.getSelectedItem().toString()), dd, df);
					JOptionPane.showMessageDialog(null, "Modification Vacation effectuée.",
							"Information", JOptionPane.INFORMATION_MESSAGE);
					btn_enregistrer.setEnabled(false);
					txt_dateDeb.setText("");
					txt_dateFin.setText("");
					}
					else{
						JOptionPane.showMessageDialog(null, "Date invalide. La date de début doit être inférieure ou égale à la date de fin.",
								"ERREUR", JOptionPane.ERROR_MESSAGE);
					}
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();				
				}
				}
			}
		});
		
		JButton btnRechercher = new JButton("Rechercher");
		btnRechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(comboBox_idVacation.getSelectedItem().toString());
				laVacation = gestionBD.rechercherLaVacation(comboBox_idAtelier.getSelectedItem().toString(), comboBox_idVacation.getSelectedItem().toString());
				String datedebut = leformat.format(laVacation.getDatedebut());
				String datefin = leformat.format(laVacation.getDatefin());
				txt_dateDeb.setText(datedebut);
				txt_dateFin.setText(datefin);	
				btn_enregistrer.setEnabled(true);
				
			}
		});
		btnRechercher.setBounds(149, 51, 120, 23);
		panel.add(btnRechercher);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Param\u00E8tres \u00E0 modifier", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(0, 90, 421, 65);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel JLabel_dateDeb = new JLabel("Date de d\u00E9but");
		JLabel_dateDeb.setBounds(10, 24, 100, 30);
		panel_1.add(JLabel_dateDeb);
		
		JLabel JLabel_dateFin = new JLabel("Date de fin");
		JLabel_dateFin.setBounds(202, 24, 100, 30);
		panel_1.add(JLabel_dateFin);
		
		txt_dateDeb = new JTextField();
		txt_dateDeb.setBounds(90, 29, 75, 25);
		panel_1.add(txt_dateDeb);
		txt_dateDeb.setColumns(10);
		
		txt_dateFin = new JTextField();
		txt_dateFin.setBounds(264, 29, 75, 25);
		panel_1.add(txt_dateFin);
		txt_dateFin.setColumns(10);
		
		JButton btn_quitter = new JButton("Quitter");
		btn_quitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reponse=JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir quitter ?",
						"Maison Des Ligues", JOptionPane.YES_NO_OPTION);
				if(reponse==JOptionPane.YES_OPTION){
				FenEditVacation.this.dispose() ;
		//	controle.ControleConnexion.getControleConnexion().fermetureSession();
				System.exit(0);
				}
			}
		});
		btn_quitter.setBounds(151, 155, 110, 40);
		contentPane.add(btn_quitter);
		
		JButton btn_retourMenu = new JButton("Retour Menu");
		btn_retourMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FenMenu LaFenMenu = new FenMenu();
				LaFenMenu.setVisible(true);
				FenEditVacation.this.dispose();
			}
		});
		btn_retourMenu.setBounds(271, 155, 110, 40);
		contentPane.add(btn_retourMenu);
	}
}
