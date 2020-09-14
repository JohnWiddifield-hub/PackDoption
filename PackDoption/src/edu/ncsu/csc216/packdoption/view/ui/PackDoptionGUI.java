package edu.ncsu.csc216.packdoption.view.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;

import edu.ncsu.csc216.packdoption.model.animals.Animal;
import edu.ncsu.csc216.packdoption.model.animals.Cat;
import edu.ncsu.csc216.packdoption.model.animals.Dog;
import edu.ncsu.csc216.packdoption.model.animals.Animal.Size;
import edu.ncsu.csc216.packdoption.model.animals.Dog.Breed;
import edu.ncsu.csc216.packdoption.model.manager.PackDoptionManager;
import edu.ncsu.csc216.packdoption.model.rescue.Rescue;
import edu.ncsu.csc216.packdoption.model.rescue.RescueList;
import edu.ncsu.csc216.packdoption.util.Date;
import edu.ncsu.csc216.packdoption.util.Note;
import edu.ncsu.csc216.packdoption.util.SimpleListIterator;
import edu.ncsu.csc216.packdoption.util.SortedLinkedList;

/**
 * PackDoption GUI
 * 
 * @author Jessica Young Schmidt
 */
public class PackDoptionGUI extends JFrame implements ActionListener {
	/** ID number used for object serialization. */
	private static final long serialVersionUID = 1L;
	/** Title for top of GUI. */
	private static final String APP_TITLE = "PackDoption";
	/** Text for the File Menu. */
	private static final String FILE_MENU_TITLE = "File";
	/** Text for the New PackDoption menu item. */
	private static final String NEW_FILE_TITLE = "New";
	/** Text for the Load PackDoption menu item. */
	private static final String LOAD_FILE_TITLE = "Load";
	/** Text for the Save menu item. */
	private static final String SAVE_FILE_TITLE = "Save";
	/** Text for the Quit menu item. */
	private static final String QUIT_TITLE = "Quit";
	/** Menu bar for the GUI that contains Menus. */
	private JMenuBar menuBar;
	/** Menu for the GUI. */
	private JMenu menu;
	/** Menu item for creating a new list of Rescues. */
	private JMenuItem itemNewFile;
	/** Menu item for loading a file. */
	private JMenuItem itemLoadFile;
	/** Menu item for saving the list to a file. */
	private JMenuItem itemSaveFile;
	/** Menu item for quitting the program. */
	private JMenuItem itemQuit;

	/** Rescue that is selected */
	private Rescue selectedRescue;
	/** Animal that is selected */
	private Animal selectedAnimal;

	/** Panel for Card Layout **/
	private JPanel panel;

	/** Date across the top - label */
	private JLabel lblDate;
	/** Date across the top - textfield */
	private JTextField txtDate;
	/** Date across the top - panel */
	private JPanel pnlDate;

	/** CardLayout for views */
	private CardLayout cardLayout;
	/** Constant to identify Rescues for CardLayout. */
	private static final String RESCUES_PANEL = "RescuesPanel";
	/** Constant to identify Animals for CardLayout. */
	private static final String ANIMALS_PANEL = "AnimalsPanel";
	/** Rescues panel - we only need one instance, so it's final. */
	private final RescuesPanel pnlRescues = new RescuesPanel();
	/** Animals panel - we only need one instance, so it's final. */
	private final AnimalsPanel pnlAnimals = new AnimalsPanel();

	/** Type of filters for rescue */
	private static final String[] FILTER_TYPE = { "All", "Adopted", "Available Dogs", "Available Cats", "Available by Age",
			"Available by Days Available for Adoption" };

	/** Current filter */
	private String filter;

	/**
	 * Starts the GUI for the PackDoption application.
	 * 
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		new PackDoptionGUI();
	}

	/**
	 * Constructs the GUI.
	 */
	public PackDoptionGUI() {
		super();

		// Set up general GUI info
		setSize(1500, 500);
		setLocation(50, 50);
		setTitle(APP_TITLE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setUpMenuBar();

		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				doExit();
			}

		});

		initializeGUI();

		// Set the GUI visible
		setVisible(true);
	}

	/**
	 * Initializes GUI
	 */
	private void initializeGUI() {

		// // Construct panels
		pnlDate = new JPanel();
		lblDate = new JLabel("Date: ");
		txtDate = new JTextField(30);
		LocalDate today = LocalDate.now();
		txtDate.setText(today.getMonthValue() + "/" + today.getDayOfMonth() + "/" + today.getYear());
		txtDate.addActionListener(this);
		txtDate.setEditable(false);
		txtDate.setEnabled(false);
		pnlDate.add(lblDate);
		pnlDate.add(txtDate);

		this.add(pnlDate, BorderLayout.PAGE_START);

		cardLayout = new CardLayout();

		panel = new JPanel();
		panel.setLayout(cardLayout);
		panel.add(pnlRescues, RESCUES_PANEL);
		panel.add(pnlAnimals, ANIMALS_PANEL);
		cardLayout.show(panel, RESCUES_PANEL);

		this.add(panel, BorderLayout.CENTER);

		filter = "All";
	}

	/**
	 * Makes the GUI Menu bar that contains options for loading a file containing
	 * issues or for quitting the application.
	 */
	private void setUpMenuBar() {
		// Construct Menu items
		menuBar = new JMenuBar();
		menu = new JMenu(FILE_MENU_TITLE);
		itemNewFile = new JMenuItem(NEW_FILE_TITLE);
		itemLoadFile = new JMenuItem(LOAD_FILE_TITLE);
		itemSaveFile = new JMenuItem(SAVE_FILE_TITLE);
		itemQuit = new JMenuItem(QUIT_TITLE);
		itemNewFile.addActionListener(this);
		itemLoadFile.addActionListener(this);
		itemSaveFile.addActionListener(this);
		itemQuit.addActionListener(this);

		// Start with save button disabled
		itemSaveFile.setEnabled(false);

		// Build Menu and add to GUI
		menu.add(itemNewFile);
		menu.add(itemLoadFile);
		menu.add(itemSaveFile);
		menu.add(itemQuit);
		menuBar.add(menu);
		this.setJMenuBar(menuBar);
	}

	/**
	 * Exits the GUI
	 */
	private void doExit() {
		if (PackDoptionManager.getInstance().isChanged()) {
			doSaveFile();
		}

		if (!PackDoptionManager.getInstance().isChanged()) {
			System.exit(NORMAL);
		} else { // Did NOT save when prompted to save
			JOptionPane.showMessageDialog(this,
					"PackDoption changes have not been saved. " + "Your changes will not be saved.", "Saving Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Saves GUI to file
	 */
	private void doSaveFile() {
		try {
			PackDoptionManager instance = PackDoptionManager.getInstance();
			JFileChooser chooser = new JFileChooser("./");
			FileNameExtensionFilter filterExt = new FileNameExtensionFilter("PackDoption files (md)", "md");
			chooser.setFileFilter(filterExt);
			chooser.setMultiSelectionEnabled(false);
			if (instance.getFilename() != null) {
				chooser.setSelectedFile(new File(instance.getFilename()));
			}
			int returnVal = chooser.showSaveDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				String filename = chooser.getSelectedFile().getAbsolutePath();
				if (chooser.getSelectedFile().getName().trim().equals("")
						|| !chooser.getSelectedFile().getName().endsWith(".md")) {
					throw new IllegalArgumentException();
				}
				instance.setFilename(filename);
				instance.saveFile(filename);
			}
			itemLoadFile.setEnabled(false);
			itemNewFile.setEnabled(false);
			txtDate.setEditable(true);
			txtDate.setEnabled(true);
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(this, "File not saved.", "Saving Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Loads rescue results from file
	 */
	private void doLoadFile() {
		try {
			PackDoptionManager instance = PackDoptionManager.getInstance();
			JFileChooser chooser = new JFileChooser("./");
			FileNameExtensionFilter filterExt = new FileNameExtensionFilter("PackDoption files (md)", "md");
			chooser.setFileFilter(filterExt);
			chooser.setMultiSelectionEnabled(false);
			int returnVal = chooser.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				instance.loadFile(chooser.getSelectedFile().getAbsolutePath());
			}
			itemLoadFile.setEnabled(false);
			itemNewFile.setEnabled(false);
			txtDate.setEditable(true);
			txtDate.setEnabled(true);
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(this, "Error opening file.", "Opening Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Performs an action based on the given ActionEvent.
	 * 
	 * @param e user event that triggers an action.
	 */
	public void actionPerformed(ActionEvent e) {
		PackDoptionManager instance = PackDoptionManager.getInstance();

		if (e.getSource() == itemNewFile) {
			doSaveFile();
			instance.newList();
		} else if (e.getSource() == itemLoadFile) {
			doLoadFile();
		} else if (e.getSource() == itemSaveFile) {
			doSaveFile();
		} else if (e.getSource() == itemQuit) {
			doExit();
		}

		// txtDate
		if (!Date.isValidDate(txtDate.getText())) {
			// Note that it could be about ordering of dates
			JOptionPane.showMessageDialog(PackDoptionGUI.this, "Date is invalid date (" + txtDate.getText() + ").");
		} else {
			update();
		}

	}

	/**
	 * Inner class that creates the look and behavior for the JPanel that shows the
	 * list of rescues.
	 * 
	 * @author Jessica Young Schmidt
	 */
	private class RescuesPanel extends JPanel implements ActionListener {

		private static final long serialVersionUID = 1L;
		private JList<Rescue> listRescues;
		private JButton btnAdd;
		private JButton btnUnselect;
		private JButton btnViewAnimals;
		private JButton btnViewAvailableDogs;
		private JButton btnViewAvailableCats;
		private JButton btnViewAdopted;
		private JLabel lblName;
		private JTextField txtName;
		private JLabel lblInfo;
		private DefaultListModel<Rescue> listModel;
		private JTable table;
		private VetResultTableModel model;

		private JButton btnNext;
		private JButton btnRemove;
		private JTextArea lblInfoNext;
		private JLabel lblNote;
		private JTextField txtNote;

		/**
		 * Constructs Rescues panel
		 */
		public RescuesPanel() {

			// Construct components
			listModel = new DefaultListModel<Rescue>();

			listRescues = new JList<Rescue>(listModel);
			listRescues.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listRescues.addListSelectionListener(new ListSelectionListener() {

				@Override
				public void valueChanged(ListSelectionEvent e) {
					if (!e.getValueIsAdjusting()) {
						selectedRescue = listRescues.getSelectedValue();
						PackDoptionGUI.this.update();
					}
				}
			});

			btnAdd = new JButton("Add Rescue");
			btnAdd.addActionListener(this);
			btnUnselect = new JButton("Unselect Rescue");
			btnUnselect.addActionListener(this);
			btnViewAnimals = new JButton("View Animals");
			btnViewAnimals.addActionListener(this);
			btnViewAdopted = new JButton("View Adopted");
			btnViewAdopted.addActionListener(this);
			btnViewAvailableDogs = new JButton("View Available Dogs");
			btnViewAvailableDogs.addActionListener(this);
			btnViewAvailableCats = new JButton("View Available Cats");
			btnViewAvailableCats.addActionListener(this);
			lblName = new JLabel("Rescue Name");
			lblInfo = new JLabel("");
			txtName = new JTextField(30);
			JScrollPane scroll = new JScrollPane(listRescues);

			btnAdd.setEnabled(false);
			btnUnselect.setEnabled(false);
			txtName.setEnabled(false);
			btnViewAnimals.setEnabled(false);
			btnViewAdopted.setEnabled(false);
			btnViewAvailableDogs.setEnabled(false);
			btnViewAvailableCats.setEnabled(false);

			setLayout(new GridLayout(1, 2));

			JPanel left = new JPanel();
			left.setLayout(new GridLayout(2, 1));
			left.add(scroll);

			// Set up border
			Border lowerEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
			TitledBorder border = BorderFactory.createTitledBorder(lowerEtched, "Rescues");
			left.setBorder(border);
			left.setToolTipText("Rescues");

			// Rescues area with buttons
			JPanel rescue = new JPanel();
			rescue.setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 0;
			c.gridy = 0;
			rescue.add(btnAdd, c);
			c.gridx = 1;
			c.gridy = 0;
			rescue.add(btnUnselect);
			c.gridx = 0;
			c.gridy = 1;
			rescue.add(lblName, c);
			c.gridx = 1;
			c.gridy = 1;
			rescue.add(txtName, c);
			c.gridx = 0;
			c.gridy = 2;
			rescue.add(btnViewAnimals, c);
			c.gridx = 1;
			c.gridy = 2;
			rescue.add(btnViewAdopted, c);
			c.gridx = 0;
			c.gridy = 3;
			rescue.add(btnViewAvailableDogs, c);
			c.gridx = 1;
			c.gridy = 3;
			rescue.add(btnViewAvailableCats, c);
			c.gridx = 0;
			c.gridy = 4;
			c.gridwidth = 2;
			rescue.add(lblInfo, c);
			left.add(rescue);

			add(left);

			JPanel vet = new JPanel();

			// Set up border
			Border lowerEtchedVet = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
			TitledBorder borderVet = BorderFactory.createTitledBorder(lowerEtchedVet, "Veterinarian Appointments");
			vet.setBorder(borderVet);
			vet.setToolTipText("Veterinarian Appointments");

			// Set up table
			model = new VetResultTableModel();
			table = new JTable(model);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setPreferredScrollableViewportSize(new Dimension(600, 100));
			table.setFillsViewportHeight(true);
			table.setEnabled(false);

			JScrollPane scrollTable = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

			vet.setLayout(new GridLayout(2, 1));
			vet.add(scrollTable);

			btnNext = new JButton("Info for Next Appointment");
			btnNext.addActionListener(this);
			btnRemove = new JButton("Remove Next & Add Note");
			btnRemove.addActionListener(this);
			lblInfoNext = new JTextArea("");
			lblInfoNext.setPreferredSize(new Dimension(150, 100));
			lblNote = new JLabel("Note for Next");
			txtNote = new JTextField(30);
			lblInfoNext.setLineWrap(true);
			lblInfoNext.setWrapStyleWord(true);
			lblInfoNext.setEditable(false);
			JPanel appInfo = new JPanel();
			appInfo.setLayout(new GridBagLayout());
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 0;
			c.gridy = 0;
			c.gridwidth = 1;
			appInfo.add(btnNext, c);
			c.gridx = 1;
			c.gridy = 0;
			appInfo.add(btnRemove, c);
			c.gridx = 0;
			c.gridy = 1;
			c.gridwidth = 2;
			appInfo.add(lblInfoNext, c);
			c.gridx = 0;
			c.gridy = 2;
			c.gridwidth = 1;
			appInfo.add(lblNote, c);
			c.gridx = 1;
			c.gridy = 2;
			appInfo.add(txtNote, c);

			txtNote.setEnabled(false);
			txtNote.setEditable(false);
			btnNext.setEnabled(false);
			btnRemove.setEnabled(false);

			vet.add(appInfo);

			add(vet);
		}

		/**
		 * Updates the Rescues Panel
		 */
		public void updateRescues() {
			PackDoptionManager instance = PackDoptionManager.getInstance();
			RescueList list = instance.getRescueList();

			for (int i = 0; i < list.size(); i++) {
				if (!listModel.contains(list.getRescue(i))) {
					listModel.add(i, list.getRescue(i));
				}

			}

			btnAdd.setEnabled(true);
			btnUnselect.setEnabled(true);
			txtName.setEnabled(true);

			if (selectedRescue != null) {
				txtName.setText(selectedRescue.getName());
				txtName.setEditable(false);
				btnViewAnimals.setEnabled(true);
				btnViewAdopted.setEnabled(true);
				btnViewAvailableDogs.setEnabled(true);
				btnViewAvailableCats.setEnabled(true);
				lblInfo.setText(selectedRescue.numAnimalsAvailable() + " Available, "
						+ selectedRescue.numAnimalsAdopted() + " Adopted");
				if (selectedRescue.getAppointments().isEmpty()) {
					txtNote.setEnabled(false);
					txtNote.setEditable(false);
					btnNext.setEnabled(false);
					btnRemove.setEnabled(false);
				} else {
					txtNote.setEnabled(true);
					txtNote.setEditable(true);
					btnNext.setEnabled(true);
					btnRemove.setEnabled(true);
				}
			} else {
				txtName.setText("");
				txtName.setEditable(true);
				btnViewAnimals.setEnabled(false);
				btnViewAdopted.setEnabled(false);
				btnViewAvailableDogs.setEnabled(false);
				btnViewAvailableCats.setEnabled(false);
				lblInfo.setText("");
				txtNote.setEnabled(false);
				txtNote.setEditable(false);
				btnNext.setEnabled(false);
				btnRemove.setEnabled(false);
			}

			model.updateResultData();
		}

		/**
		 * Performs an action based on the given ActionEvent.
		 * 
		 * @param e user event that triggers an action.
		 */
		public void actionPerformed(ActionEvent e) {
			if (!Date.isValidDate(txtDate.getText())) {
				// Note that it could be about ordering of dates
				JOptionPane.showMessageDialog(PackDoptionGUI.this, "Date is invalid date (" + txtDate.getText() + ").");
			} else if (e.getSource() == btnUnselect) {
				selectedRescue = null;
				listRescues.clearSelection();
			} else if (e.getSource() == btnAdd) {
				if (selectedRescue == null) {
					try {
						String name = txtName.getText().trim();
						if (name.length() == 0) {
							JOptionPane.showMessageDialog(this, "Cannot add Rescue with empty name.");
						} else {
							PackDoptionManager.getInstance().getRescueList().addRescue(name.trim());
							PackDoptionManager.getInstance().setChanged(true);
							PackDoptionGUI.this.update();
						}
					} catch (Exception exp) {
						JOptionPane.showMessageDialog(this, "Cannot add: " + exp.getMessage());
					}
				} else {
					JOptionPane.showMessageDialog(this, "Rescue selected. In order to add, unselect rescue.");
				}
			} else if (e.getSource() == btnViewAnimals) {
				filter = "All";
				PackDoptionGUI.this.update();
				cardLayout.show(panel, ANIMALS_PANEL);
			} else if (e.getSource() == btnViewAdopted) {
				filter = "Adopted";
				PackDoptionGUI.this.update();
				cardLayout.show(panel, ANIMALS_PANEL);
			} else if (e.getSource() == btnViewAvailableDogs) {
				filter = "Available Dogs";
				PackDoptionGUI.this.update();
				cardLayout.show(panel, ANIMALS_PANEL);
			} else if (e.getSource() == btnViewAvailableCats) {
				filter = "Available Cats";
				PackDoptionGUI.this.update();
				cardLayout.show(panel, ANIMALS_PANEL);
			} else if (e.getSource() == btnNext) {
				lblInfoNext.setText(selectedRescue.getAppointments().element().toString());
				txtNote.setText("");
				PackDoptionGUI.this.update();
			} else if (e.getSource() == btnRemove) {
				lblInfoNext.setText("");
				Animal animal = selectedRescue.getAppointments().element();
				try {
					Date date = new Date(txtDate.getText());
					Note note = new Note(date, txtNote.getText());
					animal.addNote(note);
					selectedRescue.getAppointments().remove();
					txtNote.setText("");
					PackDoptionManager.getInstance().setChanged(true);
				} catch (Exception exp) {
					JOptionPane.showMessageDialog(this, "Cannot remove appointment: " + exp.getMessage());
				}
				PackDoptionGUI.this.update();
			}

		}

		/**
		 * Inner class that creates the look and behavior for the table of appointments.
		 * 
		 * @author Jessica Young Schmidt
		 */
		private class VetResultTableModel extends ResultTableModel {

			/** ID number used for object serialization. */
			private static final long serialVersionUID = 1L;

			public void updateResultData() {
				if (selectedRescue != null) {
					setData(selectedRescue.getAppointmentsAsArray(new Date(txtDate.getText()))); // IllegalArgumentException
																									// caught elsewhere

				} else {
					setData(null);
				}
			}
		}

	}

	/**
	 * Inner class that creates the look and behavior for the JPanel that shows the
	 * list of Animals.
	 * 
	 * @author Jessica Young Schmidt
	 */
	private class AnimalsPanel extends JPanel implements ActionListener {

		private static final long serialVersionUID = 1L;

		private JTable table;
		private AnimalResultTableModel model;

		private JButton btnAdd;
		private JButton btnUnselect;
		private JButton btnQueue;
		private JButton btnAll;
		private JButton btnBack;
		private JButton btnEdit;
		private JButton btnFilterAge;
		private JButton btnFilterDays;
		private JTextField txtMinAge;
		private JTextField txtMaxAge;
		private JTextField txtMinDays;
		private JTextField txtMaxDays;
		private JLabel lblFilter;

		// Edit Add
		private JLabel lblType;
		private JComboBox<String> cbType;
		private JLabel lblName;
		private JTextField txtName;
		private JLabel lblBirthday;
		private JTextField txtBirthday;
		private JLabel lblSize;
		private JComboBox<Animal.Size> cbSize;
		private JLabel lblHouse;
		private JComboBox<String> cbHouse;
		private JLabel lblKids;
		private JComboBox<String> cbKids;
		private JLabel lblDateEnterRescue;
		private JTextField txtDateEnterRescue;
		private JLabel lblDateAdopted;
		private JTextField txtDateAdopted;
		private JLabel lblOwner;
		private JTextField txtOwner;
		private JLabel lblNotes;
		private JTextArea txtNotes;
		private JLabel lblBreed;
		private JComboBox<Dog.Breed> cbBreed;

		public AnimalsPanel() {
			// Set up border
			Border lowerEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
			TitledBorder border = BorderFactory.createTitledBorder(lowerEtched, "Animals");
			setBorder(border);
			setToolTipText("Animals");

			// Set up table
			model = new AnimalResultTableModel();
			table = new JTable(model);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setPreferredScrollableViewportSize(new Dimension(600, 100));
			table.setFillsViewportHeight(true);

			JScrollPane scrollTable = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			ListSelectionModel selectionModel = table.getSelectionModel();

			selectionModel.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					int row = table.getSelectedRow();
					if (row >= 0) {
						selectedAnimal = selectedRescue.getAnimal(model.getValueAt(row, 0).toString(),
								new Date(model.getValueAt(row, 2).toString()));
					}
					PackDoptionGUI.this.update();
				}
			});

			btnUnselect = new JButton("Unselect");
			btnUnselect.addActionListener(this);
			btnBack = new JButton("Back to Rescue");
			btnBack.addActionListener(this);
			btnAdd = new JButton("Add");
			btnAdd.addActionListener(this);
			btnAll = new JButton("All Animals");
			btnAll.addActionListener(this);
			btnQueue = new JButton("Add Animal to Vet Queue");
			btnQueue.addActionListener(this);
			btnFilterAge = new JButton("Filter Available by Age (Years)");
			btnFilterAge.addActionListener(this);
			btnFilterDays = new JButton("Filter Available by Days Available for Adoption");
			btnFilterDays.addActionListener(this);
			txtMinAge = new JTextField(30);
			txtMaxAge = new JTextField(30);
			txtMinDays = new JTextField(30);
			txtMaxDays = new JTextField(30);
			lblFilter = new JLabel("Current filter: ");

			btnAdd.setEnabled(false);
			btnUnselect.setEnabled(false);

			setLayout(new GridLayout(1, 2));

			JPanel left = new JPanel();
			left.setLayout(new GridLayout(2, 1));
			left.add(scrollTable);

			// Rescues area with buttons
			JPanel pnlFilter = new JPanel();
			pnlFilter.setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 0;
			c.gridy = 0;
			pnlFilter.add(lblFilter, c);
			c.gridx = 1;
			c.gridy = 0;
			pnlFilter.add(btnBack, c);
			c.gridx = 2;
			c.gridy = 0;
			pnlFilter.add(btnUnselect, c);
			c.gridx = 1;
			c.gridy = 2;
			pnlFilter.add(new JLabel("Minimum"), c);
			c.gridx = 2;
			c.gridy = 2;
			pnlFilter.add(new JLabel("Maximum"), c);

			c.gridx = 0;
			c.gridy = 3;
			pnlFilter.add(btnFilterAge, c);
			c.gridx = 1;
			c.gridy = 3;
			pnlFilter.add(txtMinAge, c);
			c.gridx = 2;
			c.gridy = 3;
			pnlFilter.add(txtMaxAge, c);
			c.gridx = 0;
			c.gridy = 4;
			pnlFilter.add(btnFilterDays, c);
			c.gridx = 1;
			c.gridy = 4;
			pnlFilter.add(txtMinDays, c);
			c.gridx = 2;
			c.gridy = 4;
			pnlFilter.add(txtMaxDays, c);
			c.gridx = 0;
			c.gridy = 5;
			pnlFilter.add(btnAll, c);
			c.gridx = 1;
			c.gridy = 5;
			pnlFilter.add(btnQueue, c);

			left.add(pnlFilter);

			add(left);

			//////////

			JPanel pnlEditAdd = new JPanel();

			lblType = new JLabel("Animal Type");
			String[] types = { "Dog", "Cat" };
			cbType = new JComboBox<String>(types);
			cbType.addActionListener(this);
			lblName = new JLabel("Animal Name");
			txtName = new JTextField(30);
			lblBirthday = new JLabel("Animal Birthday");
			txtBirthday = new JTextField(30);
			lblDateEnterRescue = new JLabel("Date Enter Rescue");
			txtDateEnterRescue = new JTextField(30);
			lblDateAdopted = new JLabel("Date Adopted");
			txtDateAdopted = new JTextField(30);
			lblOwner = new JLabel("Owner");
			txtOwner = new JTextField(30);
			lblBreed = new JLabel("Breed (Dog only)");
			cbBreed = new JComboBox<Dog.Breed>(Dog.Breed.values());
			lblSize = new JLabel("Animal Size");
			cbSize = new JComboBox<Animal.Size>(Animal.Size.values());
			lblHouse = new JLabel("House Trained");
			String[] yesNo = { "Yes", "No" };
			cbHouse = new JComboBox<String>(yesNo);
			lblKids = new JLabel("Good with Kids");
			cbKids = new JComboBox<String>(yesNo);

			lblNotes = new JLabel("Animal Notes");
			txtNotes = new JTextArea(30, 5);
			txtNotes.setLineWrap(true);
			txtNotes.setWrapStyleWord(true);
			txtNotes.setEditable(false); // can't add notes here - only add with appointment or from file

			btnAdd = new JButton("Add");
			btnAdd.addActionListener(this);
			btnEdit = new JButton("Edit");
			btnEdit.addActionListener(this);
			btnEdit.setEnabled(false);

			pnlEditAdd.setLayout(new GridLayout(12, 2));

			pnlEditAdd.add(lblType);

			pnlEditAdd.add(cbType);

			pnlEditAdd.add(lblName);

			pnlEditAdd.add(txtName);

			pnlEditAdd.add(lblBirthday);

			pnlEditAdd.add(txtBirthday);

			pnlEditAdd.add(lblSize);

			pnlEditAdd.add(cbSize);

			pnlEditAdd.add(lblHouse);

			pnlEditAdd.add(cbHouse);

			pnlEditAdd.add(lblKids);

			pnlEditAdd.add(cbKids);

			pnlEditAdd.add(lblNotes);

			pnlEditAdd.add(txtNotes);

			pnlEditAdd.add(lblDateEnterRescue);

			pnlEditAdd.add(txtDateEnterRescue);

			pnlEditAdd.add(lblDateAdopted);

			pnlEditAdd.add(txtDateAdopted);

			pnlEditAdd.add(lblOwner);

			pnlEditAdd.add(txtOwner);

			pnlEditAdd.add(lblNotes);

			pnlEditAdd.add(txtNotes);
			pnlEditAdd.add(lblBreed);
			pnlEditAdd.add(cbBreed);

			pnlEditAdd.add(btnAdd);
			pnlEditAdd.add(btnEdit);
			add(pnlEditAdd);
		}

		/**
		 * Performs an action based on the given ActionEvent.
		 * 
		 * @param e user event that triggers an action.
		 */
		public void actionPerformed(ActionEvent e) {
			if (!Date.isValidDate(txtDate.getText())) {
				// Note that is could be about the ordering of the dates
				JOptionPane.showMessageDialog(PackDoptionGUI.this, "Date is invalid date (" + txtDate.getText() + ").");
			} else if (e.getSource() == btnBack) {
				selectedAnimal = null;
				filter = "All";
				table.clearSelection();
				cardLayout.show(panel, RESCUES_PANEL);
			} else if (e.getSource() == btnAll) {
				filter = "All";
				PackDoptionGUI.this.update();
			} else if (e.getSource() == btnUnselect) {
				selectedAnimal = null;
				table.clearSelection();
			} else if (e.getSource() == btnQueue) {
				if (selectedAnimal != null) {
					if (!selectedRescue.addAppointment(selectedAnimal)) {
						JOptionPane.showMessageDialog(this, "Appointment was not added");
					} else {
						PackDoptionManager.getInstance().setChanged(true);
					}
				} else {
					JOptionPane.showMessageDialog(this, "Must select animal to add to vet queue.");
				}
				PackDoptionGUI.this.update();
			} else if (e.getSource() == btnAdd) {
				if (selectedAnimal == null) {
					try {
						String name = txtName.getText().trim();
						Date birthday = new Date(txtBirthday.getText().trim());
						Date dateEnterRescue = new Date(txtDateEnterRescue.getText().trim());
						Date dateAdopted;
						String owner;
						boolean adopted;
						if (txtDateAdopted.getText().trim().equals("")) {
							adopted = false;
							dateAdopted = null;
							owner = null;
						} else {
							adopted = true;
							dateAdopted = new Date(txtDateAdopted.getText().trim());
							owner = txtOwner.getText();
						}
						Size size = (Size) cbSize.getSelectedItem();
						boolean house = ((String) cbHouse.getSelectedItem()).contentEquals("Yes");
						boolean kids = ((String) cbKids.getSelectedItem()).contentEquals("Yes");

						Breed breed = (Dog.Breed) cbBreed.getSelectedItem();
						String type = (String) cbType.getSelectedItem();
						if (type.equals("Dog")) { // DOG
							Dog d = new Dog(name, birthday, size, house, kids, new SortedLinkedList<Note>(),
									dateEnterRescue, adopted, dateAdopted, owner, breed);
							selectedRescue.addAnimal(d);
						} else {// CAT
							Cat c = new Cat(name, birthday, size, house, kids, new SortedLinkedList<Note>(),
									dateEnterRescue, adopted, dateAdopted, owner);
							selectedRescue.addAnimal(c);

						}
						PackDoptionManager.getInstance().setChanged(true);
						PackDoptionGUI.this.update();
					} catch (Exception exp) {
						JOptionPane.showMessageDialog(this, "Cannot add: " + exp.getMessage());
					}
				} else {
					JOptionPane.showMessageDialog(this, "Animal selected. In order to add, unselect animal.");
				}
			} else if (e.getSource() == btnEdit) {
				if (selectedAnimal != null) {
					try {
						Date dateAdopted;
						String owner;
						boolean adopted;
						if (txtDateAdopted.getText().trim().equals("")) {
							adopted = false;
							dateAdopted = null;
							owner = null;
						} else {
							adopted = true;
							dateAdopted = new Date(txtDateAdopted.getText().trim());
							owner = txtOwner.getText();
						}
						Size size = (Size) cbSize.getSelectedItem();
						selectedAnimal.setSize(size);
						selectedAnimal.setAdoptionInfo(adopted, dateAdopted, owner);
						PackDoptionManager.getInstance().setChanged(true);
						PackDoptionGUI.this.update();
					} catch (Exception exp) {
						JOptionPane.showMessageDialog(this, "Cannot edit: " + exp.getMessage());
					}
				} else {
					JOptionPane.showMessageDialog(this, "Animal selected. In order to edit, select animal.");
				}
			} else if (e.getSource() == btnFilterAge) {
				selectedAnimal = null;
				filter = FILTER_TYPE[4];
				PackDoptionGUI.this.update();
			} else if (e.getSource() == btnFilterDays) {
				selectedAnimal = null;
				filter = FILTER_TYPE[5];
				PackDoptionGUI.this.update();
			} else if (e.getSource() == cbType) {
				if (cbType.getSelectedItem().equals("Cat")) {
					cbBreed.setVisible(false);
					lblBreed.setVisible(false);
				} else {
					cbBreed.setVisible(true);
					lblBreed.setVisible(true);
				}
			}
		}

		/**
		 * Inner class that creates the look and behavior for the table of animals.
		 * 
		 * @author Jessica Young Schmidt
		 */
		private class AnimalResultTableModel extends ResultTableModel {

			/** ID number used for object serialization.  */
			private static final long serialVersionUID = 1L;

			public void updateResultData() {
				if (selectedRescue != null) {
					setData(null);
					// Other than all
					SortedLinkedList<Animal> animalList = null;
					if (filter.equals(FILTER_TYPE[1])) {
						animalList = selectedRescue.animalsAdopted();
					} else if (filter.equals(FILTER_TYPE[2])) {
						animalList = selectedRescue.availableDogs();
					} else if (filter.equals(FILTER_TYPE[3])) {
						animalList = selectedRescue.availableCats();
					} else if (filter.equals(FILTER_TYPE[4])) {
						try {
							animalList = selectedRescue.availableAnimalsAge(new Date(txtDate.getText()),
									Integer.parseInt(txtMinAge.getText()), Integer.parseInt(txtMaxAge.getText()));
						} catch (NumberFormatException nfe) {
							JOptionPane.showMessageDialog(PackDoptionGUI.this, "Age ranges are not integers.");
							filter = FILTER_TYPE[0];
							txtMinAge.setText("");
							txtMaxAge.setText("");
						} catch (IllegalArgumentException e) {
							JOptionPane.showMessageDialog(PackDoptionGUI.this, "Age ranges are not valid.");
							filter = FILTER_TYPE[0];
							txtMinAge.setText("");
							txtMaxAge.setText("");
						}
					} else if (filter.equals(FILTER_TYPE[5])) {
						try {
							animalList = selectedRescue.availableAnimalsDayRange(new Date(txtDate.getText()),
									Integer.parseInt(txtMinDays.getText()), Integer.parseInt(txtMaxDays.getText()));
						} catch (NumberFormatException nfe) {
							JOptionPane.showMessageDialog(PackDoptionGUI.this, "Day ranges are not integers.");
							filter = FILTER_TYPE[0];
							txtMinDays.setText("");
							txtMaxDays.setText("");
						} catch (IllegalArgumentException e) {
							JOptionPane.showMessageDialog(PackDoptionGUI.this, "Day ranges are not valid.");
							filter = FILTER_TYPE[0];
							txtMinDays.setText("");
							txtMaxDays.setText("");
						}
					}
					if (animalList != null) {
						SimpleListIterator<Animal> it = animalList.iterator();
						Date date = new Date(txtDate.getText());
						Object[][] data = new Object[animalList.size()][];
						int i = 0;
						while (it.hasNext()) {
							data[i++] = it.next().getAnimalAsArray(date);
						}
						setData(data);
					} else {// ALL
						setData(selectedRescue.getAnimalsAsArray(new Date(txtDate.getText()))); // IllegalArgumentException
																								// caught elsewhere

						txtMinAge.setText("");
						txtMaxAge.setText("");
						txtMinDays.setText("");
						txtMaxDays.setText("");
					}
				} else {
					setData(null);
				}
			}
		}

		public void updateAnimals() {
			btnAdd.setEnabled(true);
			btnUnselect.setEnabled(true);
			btnBack.setEnabled(true);
			btnEdit.setEnabled(true);

			if (selectedAnimal != null) {
				if (selectedAnimal instanceof Dog) {
					cbType.setSelectedItem("Dog");
					cbBreed.setSelectedItem(((Dog) selectedAnimal).getBreed());
					cbBreed.setVisible(true);
					lblBreed.setVisible(true);
				} else {
					cbType.setSelectedItem("Cat");
					cbBreed.setVisible(false);
					lblBreed.setVisible(false);
				}
				cbType.setEditable(false);
				cbBreed.setEditable(false);
				cbType.setEnabled(false);
				cbBreed.setEnabled(false);
				txtName.setEditable(false);
				txtBirthday.setEditable(false);
				cbHouse.setEnabled(false);
				cbKids.setEnabled(false);
				cbHouse.setEditable(false);
				cbKids.setEditable(false);
				txtDateEnterRescue.setEditable(false);
				txtNotes.setEditable(false);
				txtName.setText(selectedAnimal.getName());
				txtBirthday.setText(selectedAnimal.getBirthday().toString());
				txtDateEnterRescue.setText(selectedAnimal.getDateEnterRescue().toString());
				if (selectedAnimal.adopted()) {
					txtDateAdopted.setText(selectedAnimal.getDateAdopted().toString());
					txtOwner.setText(selectedAnimal.getOwner());
				} else {
					txtDateAdopted.setText("");
					txtOwner.setText("");
				}
				txtNotes.setText(selectedAnimal.getNotes().toString());
				cbSize.setSelectedItem(selectedAnimal.getSize());
				if (selectedAnimal.isGoodWithKids()) {
					cbKids.setSelectedItem("Yes");
				} else {
					cbKids.setSelectedItem("No");
				}

				if (selectedAnimal.isHouseTrained()) {
					cbHouse.setSelectedItem("Yes");
				} else {
					cbHouse.setSelectedItem("No");
				}

			} else {
				txtName.setText("");
				txtBirthday.setText("");
				txtDateEnterRescue.setText("");
				txtDateAdopted.setText("");
				txtOwner.setText("");
				txtNotes.setText("");
				cbKids.setSelectedIndex(0);
				cbHouse.setSelectedIndex(0);
				cbSize.setSelectedIndex(0);
				cbType.setSelectedIndex(0);
				cbType.setEnabled(true);
				cbBreed.setEnabled(true);
				txtName.setEnabled(true);
				txtBirthday.setEnabled(true);
				txtName.setEditable(true);
				txtBirthday.setEditable(true);
				cbHouse.setEnabled(true);
				cbKids.setEnabled(true);
				txtDateEnterRescue.setEnabled(true);
				txtNotes.setEnabled(true);
				txtDateEnterRescue.setEditable(true);
				cbBreed.setVisible(true);
				lblBreed.setVisible(true);
			}

			model.updateResultData();
			lblFilter.setText("Current filter: " + filter);
		}

	}

	/**
	 * Table model
	 * 
	 * @author Sarah Heckman
	 * @author Jessica Young Schmidt
	 */
	private abstract class ResultTableModel extends AbstractTableModel {

		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;
		/** Column names for the table */
		private String[] columnNames = { "Name", "Type", "Birthday", "Age", "Age Category", "Adopted",
				"Days in Rescue" };
		/** Data stored in the table */
		private Object[][] data;

		/**
		 * Constructs ResultTableModel
		 */
		public ResultTableModel() {
			updateResultData();
		}

		/**
		 * Returns count for columns
		 * 
		 * @return count for columns
		 */
		public int getColumnCount() {
			return columnNames.length;
		}

		/**
		 * Returns count for rows
		 * 
		 * @return count for rows
		 */
		public int getRowCount() {
			if (data == null)
				return 0;
			return data.length;
		}

		/**
		 * Returns the column name at the given index.
		 * 
		 * @return the column name at the given column.
		 */
		public String getColumnName(int col) {
			return columnNames[col];
		}

		/**
		 * Returns value at given row and col
		 * 
		 * @param row row to return
		 * @param col col to return
		 * @return value at given row and col
		 */
		public Object getValueAt(int row, int col) {
			if (data == null)
				return null;
			return data[row][col];
		}

		/**
		 * Returns data array
		 * 
		 * @return data array
		 */
		public Object[][] getData() {
			return data;
		}

		/**
		 * Sets data array based on parameters
		 * 
		 * @param arr data array
		 */
		public void setData(Object[][] arr) {
			data = arr;
		}

		/**
		 * Sets the given value to the given {row, col} location.
		 * 
		 * @param value  Object to modify in the data.
		 * @param row    location to modify the data.
		 * @param column location to modify the data.
		 */
		public void setValueAt(Object value, int row, int col) {
			data[row][col] = value;
			fireTableCellUpdated(row, col);
		}

		public abstract void updateResultData();

	}

	/**
	 * Updates GUI as needed
	 */
	protected void update() {
		try {
			pnlRescues.updateRescues();
			pnlAnimals.updateAnimals();
		} catch (IllegalArgumentException e) {
			// Note that it could be about ordering of dates
			JOptionPane.showMessageDialog(PackDoptionGUI.this, "Date is invalid date (" + txtDate.getText() + ").");
		}
		if (PackDoptionManager.getInstance().isChanged()) {
			itemSaveFile.setEnabled(true);
		} else {
			itemSaveFile.setEnabled(false);
		}
		this.repaint();
		this.validate();
	}
}