package dialogue;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.ButtonGroup;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import java.awt.FlowLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
//permet d établir le lien avec la classe GestionDemandes
import controle.GestionDemandes;
import entite.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class FenMenu extends JFrame {

	private JPanel contentPane;
	private ButtonGroup groupeboutons = new ButtonGroup();
	private GestionDemandes gestionBD = new GestionDemandes();  
	private GestAtelierList listeAtel;
	private GestVacationList listeVaca;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenMenu frame = new FenMenu();
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
	public FenMenu() {
		setTitle("Maison des Ligues : Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 340, 336);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Que d\u00E9sirez-vous faire ?", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(0, 11, 324, 241);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 141, 317, 100);
		panel.add(panel_2);
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Autres param\u00E8tres", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnEditerUneVacation = new JButton("Editer une vacation");
		btnEditerUneVacation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listeVaca=gestionBD.chargeVacation();
				if(listeVaca.Nbelement()>0){
				FenEditVacation LaFenEditVacation = new FenEditVacation();
				LaFenEditVacation.setVisible(true);
				FenMenu.this.dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "Action Impossible. Aucune Vacation existante.",
							"ERREUR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel_2.add(btnEditerUneVacation);
		
		JButton btnGrerLesInscriptions = new JButton("G\u00E9rer les inscriptions");
		panel_2.add(btnGrerLesInscriptions);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "G\u00E9rer les ajouts", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(0, 33, 317, 97);
		panel.add(panel_1);
		
		final JRadioButton rdbtn_atelier = new JRadioButton("Atelier");
		rdbtn_atelier.setBounds(20, 20, 70, 32);
		
		final JRadioButton rdbtn_theme = new JRadioButton("Th\u00E8me");
		rdbtn_theme.setBounds(120, 20, 70, 32);
		
		final JRadioButton rdbtn_vacation = new JRadioButton("Vacation");
		rdbtn_vacation.setBounds(220, 20, 91, 32);
		panel_1.setLayout(null);
		panel_1.add(rdbtn_atelier);
		panel_1.add(rdbtn_theme);
		panel_1.add(rdbtn_vacation);
		btnGrerLesInscriptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FenParticipant LaFenParticipant = new FenParticipant();
				LaFenParticipant.setVisible(true);
				FenMenu.this.dispose();
			}
		});
		
		JButton btnCrer = new JButton("Cr\u00E9er");
		btnCrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtn_atelier.isSelected()){
					FenAjouter LaFenAjouter = new FenAjouter();
					LaFenAjouter.setVisible(true);
					FenMenu.this.dispose();
				}
				else if(rdbtn_theme.isSelected()){	
					listeAtel=gestionBD.chargeAtelier();
					if(listeAtel.Nbelement()>0){
						FenAjouterTheme LaFenAjouterTheme = new FenAjouterTheme();
						LaFenAjouterTheme.setVisible(true);
						FenMenu.this.dispose();
					}				
					else {
						JOptionPane.showMessageDialog(null, "Impossible de créer un thème. Vous devez créer au préalable au moins un atelier.",
								"ERREUR", JOptionPane.ERROR_MESSAGE);
					}
				}
				else if(rdbtn_vacation.isSelected()){
					listeAtel=gestionBD.chargeAtelier();
					if(listeAtel.Nbelement()>0){
						FenAjouterVacation LaFenAjouterVacation = new FenAjouterVacation();
						LaFenAjouterVacation.setVisible(true);
						FenMenu.this.dispose();
					}
					else {
						JOptionPane.showMessageDialog(null, "Impossible de créer une vacation. Vous devez créer au préalable au moins un atelier.",
								"ERREUR", JOptionPane.ERROR_MESSAGE);
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Vous devez sélectionner le type d'ajout.",
							"ERREUR", JOptionPane.ERROR_MESSAGE);
				}
					
			}
		});
		btnCrer.setBounds(93, 54, 119, 32);
		panel_1.add(btnCrer);
		groupeboutons.add(rdbtn_atelier);
		groupeboutons.add(rdbtn_theme);
		groupeboutons.add(rdbtn_vacation);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reponse=JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir quitter ?",
						"Maison Des Ligues", JOptionPane.YES_NO_OPTION);
				if(reponse==JOptionPane.YES_OPTION){
				FenMenu.this.dispose() ;
			//	controle.ControleConnexion.getControleConnexion().fermetureSession();
				System.exit(0);
				}
			}
		});
		btnQuitter.setBounds(107, 263, 89, 23);
		contentPane.add(btnQuitter);
	}
	
}
