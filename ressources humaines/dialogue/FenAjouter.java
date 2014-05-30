package dialogue;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//permet d établir le lien avec la classe GestionDemandes
import controle.GestionDemandes;
import entite.*;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class FenAjouter extends JFrame {
	private static final long serialVersionUID = 1L;
	// propriété pour établir le lien avec la classe GestionDemandes
	private GestionDemandes gestionBD = new GestionDemandes();  
	private JPanel jContentPrincipal = null;
	private JPanel jContent_propAtelier = null;
	private JButton btn_quitter = null;
	private GestAtelierList listeAtel;
	private ButtonGroup groupeboutons = new ButtonGroup(); 
	private JLabel jLabel_nom = null;
	private JLabel jLabel_libelléAtelier;
	private JButton btn_enregistrer = null;
	private JTextField txt_libelléAtelier = null;
	private JSpinner spinner = null;
	private JSpinner spinner_nbPlacesAtelier;
	private JLabel lblNombreDePlaces = null;
	private JLabel lbl_nbPlacesAtelier;
	private JButton btn_retourMenu;
	
	private void regroupeboutons()
	{
		groupeboutons.clearSelection();
	}

	/*** This is the default constructor */
	public FenAjouter() {
		super();
		initialize();
	}
	/** This method initializes this @return void */
	private void initialize() {
		this.setSize(440, 250);
		this.setContentPane(getJContentPrincipal());
		this.setTitle("Maison des Ligues : Cr\u00E9er un Atelier");
	}
	/*** This method initializes jContentPane @return javax.swing.JPanel */
	private JPanel getJContentPrincipal() {
		if (jContentPrincipal == null) {
			// mise en forme de la fenetre
			jContentPrincipal = new JPanel();
			jContentPrincipal.setLayout(null);
			jContentPrincipal.add(getjContentInformations());
			jContentPrincipal.add(getBtn_quitter(), null);
			jContentPrincipal.add(getBtn_enregistrer(), null);
			jContentPrincipal.add(getBtn_retourMenu());
			regroupeboutons();
			btn_enregistrer.setVisible(true);


		}
		return jContentPrincipal;
	}
	private JPanel getjContentInformations() {
		if (jContent_propAtelier == null) {
			jContent_propAtelier = new JPanel();
			jContent_propAtelier.setToolTipText("");
			jContent_propAtelier.setLayout(null);
			jContent_propAtelier.setLayout(null);
			Border Bord = BorderFactory.createTitledBorder(" Informations ");
			jContent_propAtelier.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Propri\u00E9t\u00E9s de l'Atelier", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			jContent_propAtelier.setBounds(new Rectangle(0, 11, 421, 119));
			jLabel_libelléAtelier = new JLabel("Libell\u00E9");
			jLabel_libelléAtelier.setBounds(new Rectangle(10, 20, 100, 30));
			jContent_propAtelier.add(jLabel_libelléAtelier, null);
			jContent_propAtelier.add(getTxt_libelléAtelier(), null);
			spinner_nbPlacesAtelier = new JSpinner();
			spinner_nbPlacesAtelier.setBounds(175, 74, 48, 20);
			jContent_propAtelier.add(spinner_nbPlacesAtelier);
			lbl_nbPlacesAtelier = new JLabel("Nombre de places");
			lbl_nbPlacesAtelier.setBounds(10, 74, 132, 20);
			jContent_propAtelier.add(lbl_nbPlacesAtelier);
		}
		return jContent_propAtelier;
	}
	/*** This method initializes btn_quitter@return javax.swing.JButton	 */
	private JButton getBtn_quitter() {
		if (btn_quitter == null) {
			btn_quitter = new JButton();
			btn_quitter.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int reponse=JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir quitter ?",
							"Maison Des Ligues", JOptionPane.YES_NO_OPTION);
					if(reponse==JOptionPane.YES_OPTION){
					FenAjouter.this.dispose() ;
	//				controle.ControleConnexion.getControleConnexion().fermetureSession();
					System.exit(0);
					}
				}
			});
			btn_quitter.setBounds(new Rectangle(151, 155, 110, 40));
			btn_quitter.setText("Quitter");
		}
		return btn_quitter;
	}
	/*** This method initializes btn_ok	@return javax.swing.JButton	*/
	private JButton getBtn_enregistrer() {
		if (btn_enregistrer == null) {
			btn_enregistrer = new JButton();
			btn_enregistrer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(txt_libelléAtelier.getText().equals("")){
						JOptionPane.showMessageDialog(null, "Action Impossible. Vous devez choisir un libellé pour l'atelier.",
								"ERREUR", JOptionPane.ERROR_MESSAGE);
					}
					else{
						if(Integer.parseInt(spinner_nbPlacesAtelier.getValue().toString())>0){
					gestionBD.enregistrerAtelier(txt_libelléAtelier.getText(), Integer.parseInt(spinner_nbPlacesAtelier.getValue().toString()));
					txt_libelléAtelier.setText("");
					spinner_nbPlacesAtelier.setValue(0);
					JOptionPane.showMessageDialog(null, "Création Atelier effectuée.",
							"Information", JOptionPane.INFORMATION_MESSAGE);
						}
						else{
							JOptionPane.showMessageDialog(null, "Action Impossible. Vous devez spécifier un nombre de places.",
									"ERREUR", JOptionPane.ERROR_MESSAGE);
						}						
					}
				}
			});
			btn_enregistrer.setBounds(new Rectangle(31, 155, 110, 40));
			btn_enregistrer.setText("Enregistrer");
			btn_enregistrer.setVisible(false);							
		}
		return btn_enregistrer;
	}
	/*** This method initializes txt_nom @return javax.swing.JTextField	 */
	private JTextField getTxt_libelléAtelier() {
		if (txt_libelléAtelier == null) {
			txt_libelléAtelier = new JTextField();
			txt_libelléAtelier.setBounds(new Rectangle(90, 20, 288, 30));
		}
		return txt_libelléAtelier;
	}
	private JButton getBtn_retourMenu() {
		if (btn_retourMenu == null) {
			btn_retourMenu = new JButton("Retour Menu");
			btn_retourMenu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					FenMenu LaFenMenu = new FenMenu();
					LaFenMenu.setVisible(true);
					FenAjouter.this.dispose();
				}
			});
			btn_retourMenu.setBounds(271, 155, 110, 40);
		}
		return btn_retourMenu;
	}
}  
