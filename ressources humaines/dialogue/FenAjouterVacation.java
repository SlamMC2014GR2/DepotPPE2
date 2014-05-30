package dialogue;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
//permet d établir le lien avec la classe GestionDemandes
import controle.GestionDemandes;
import entite.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;



public class FenAjouterVacation extends JFrame {

	private JPanel contentPane;
	private JTextField txt_dateDeb;
	private JTextField txt_dateFin;
	private GestionDemandes gestionBD = new GestionDemandes();  
	private GestAtelierList listeAtel;
	private SimpleDateFormat leformat = new SimpleDateFormat("dd/MM/yyyy");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenAjouterVacation frame = new FenAjouterVacation();
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
	public FenAjouterVacation() {
		setTitle("Maison des Ligues : Cr\u00E9er Vacation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Propri\u00E9t\u00E9s de la Vacation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(0, 11, 421, 133);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel JLabel_idAtelier = new JLabel("ID de l'Atelier");
		JLabel_idAtelier.setBounds(10, 30, 100, 30);
		panel.add(JLabel_idAtelier);
		
		final JComboBox comboBox_idAtelier = new JComboBox();
		comboBox_idAtelier.setBounds(98, 35, 75, 20);
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
				
		JLabel JLabel_dateDeb = new JLabel("Date de d\u00E9but");
		JLabel_dateDeb.setBounds(10, 79, 100, 30);
		panel.add(JLabel_dateDeb);
		
		JLabel JLabel_dateFin = new JLabel("Date de fin");
		JLabel_dateFin.setBounds(200, 79, 100, 30);
		panel.add(JLabel_dateFin);
		
		txt_dateDeb = new JTextField();
		txt_dateDeb.setText("00/00/0000");
		txt_dateDeb.setBounds(98, 82, 75, 25);
		panel.add(txt_dateDeb);
		txt_dateDeb.setColumns(10);
		
		txt_dateFin = new JTextField();
		txt_dateFin.setText("99/99/9999");
		txt_dateFin.setBounds(288, 82, 75, 25);
		panel.add(txt_dateFin);
		txt_dateFin.setColumns(10);
		
		JButton btn_enregistrer = new JButton("Enregistrer");
		btn_enregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String deb = txt_dateDeb.getText();
				String fin = txt_dateFin.getText();
				Date dd = null;
				Date df = null;
				try {
					dd = new Date(leformat.parse(deb).getTime());
					df = new Date(leformat.parse(fin).getTime());
					if(!deb.equals("00/00/0000") && !fin.equals("99/99/9999")){
						if(dd.before(df) || dd.equals(df)){
							gestionBD.enregistrerVacation(Integer.parseInt(comboBox_idAtelier.getSelectedItem().toString()), dd, df);
							JOptionPane.showMessageDialog(null, "Création Vacation effectuée.",
									"Information", JOptionPane.INFORMATION_MESSAGE);
							txt_dateDeb.setText("");
							txt_dateFin.setText("");
						}
						else{
							JOptionPane.showMessageDialog(null, "Date invalide. La date de début doit être inférieure ou égale à la date de fin.",
									"ERREUR", JOptionPane.ERROR_MESSAGE);
							}
					}
					else{
						JOptionPane.showMessageDialog(null, "Date invalide. Vous devez choisir une date de début et une date de fin valides.",
								"ERREUR", JOptionPane.ERROR_MESSAGE);
					}
						
					}
				 catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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
				FenAjouterVacation.this.dispose() ;
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
				FenAjouterVacation.this.dispose();
			}
		});
		btn_retourMenu.setBounds(271, 155, 110, 40);
		contentPane.add(btn_retourMenu);
	}
}
