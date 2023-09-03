package views;

import DTO.GuestDTO;
import DTO.ReservationByParametersDTO;
import DTO.ReservationDTO;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import controller.GuestController;
import controller.ReservationController;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import javax.swing.table.TableColumnModel;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	//Componente de la interfaz
	private final JPanel contentPane;
	private final JTabbedPane panel;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private final JPanel btnbuscar;
	private DefaultTableModel modeloReserva;
	private DefaultTableModel modeloHuesped;
	private final JTextField txtBuscar;
	public static JDateChooser txtFechaEntrada;
	public static JDateChooser txtFechaSalida;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;

	//Controllers
	private final ReservationController reservationController;
	private final GuestController guestController;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
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
	public Busqueda() {

		//INICIALIZAMOS LOS CONTROLADORES
		reservationController = new ReservationController();
		guestController = new GuestController();

		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);

		
		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 30, 300, 42);
		contentPane.add(lblNewLabel_4);
		
		panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		//componentes que crean y cargan las tablas
		tablaReserva();
		tablaHuesped();

		//Codigo para saber en qué tap esta
	/*panel.addChangeListener((ChangeEvent e) -> {
				int selectedIndex = panel.getSelectedIndex();
				if (selectedIndex == 0) {
					System.out.println("Tab 1 selected");

				} else if (selectedIndex == 1) {
					System.out.println("Tab 2 selected");
				}
			}
		);*/

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 30, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);

		//La fecha de ingreso
		txtFechaEntrada = new JDateChooser();
		JTextFieldDateEditor editorFechaEntrada = (JTextFieldDateEditor) txtFechaEntrada.getDateEditor();
		//EVENTO
		eventoFechas(editorFechaEntrada, "Check In");
		txtFechaEntrada.getCalendarButton().setBackground(SystemColor.textHighlight);
		txtFechaEntrada.getCalendarButton().setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/icon-reservas.png")));
		txtFechaEntrada.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 12));
		txtFechaEntrada.setBounds(200, 115, 150, 35);
		txtFechaEntrada.getCalendarButton().setBounds(368, 0, 21, 33);
		txtFechaEntrada.setBackground(Color.WHITE);
		txtFechaEntrada.setBorder(new LineBorder(SystemColor.window));
		txtFechaEntrada.setDateFormatString("yyyy-MM-dd");
		txtFechaEntrada.setFont(new Font("Roboto", Font.PLAIN, 18));
		contentPane.add(txtFechaEntrada);

		//La fecha de salida
		txtFechaSalida = new JDateChooser();
		JTextFieldDateEditor editorFechaSalida = (JTextFieldDateEditor) txtFechaSalida.getDateEditor();
		//EVENTO
		eventoFechas(editorFechaSalida, "Check Out");
		txtFechaSalida.getCalendarButton().setBackground(SystemColor.textHighlight);
		txtFechaSalida.getCalendarButton().setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/icon-reservas.png")));
		txtFechaSalida.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 12));
		txtFechaSalida.setBounds(380, 115, 150, 35);
		txtFechaSalida.getCalendarButton().setBounds(368, 0, 21, 33);
		txtFechaSalida.setBackground(Color.WHITE);
		txtFechaSalida.setBorder(new LineBorder(SystemColor.window));
		txtFechaSalida.setDateFormatString("yyyy-MM-dd");
		txtFechaSalida.setFont(new Font("Roboto", Font.PLAIN, 18));
		contentPane.add(txtFechaSalida);

		//campo de texto de buscar
		txtBuscar = new JTextField();
		eventoTxtBuscar();
		txtBuscar.requestFocus(true);
		txtBuscar.setBounds(589, 115, 150, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);

		//separado del texto
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(589, 148, 150, 2);
		contentPane.add(separator_1_2);

		//Panel que simula el boton de buscar
		btnbuscar = new JPanel();
		eventoBtnBuscar(btnbuscar, panel);
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 115, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel btnEditar = new JPanel();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		
		JPanel btnEliminar = new JPanel();
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);
		
		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
	}

	private void tablaReserva() {

		this.tbReservas = new JTable() {

			//Se habilitan para editar solo las columna que se necesitan
			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 2 || column == 3;
			}
		};
		tbReservas.getTableHeader().setReorderingAllowed(false);
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 14));
		modeloReserva = (DefaultTableModel) tbReservas.getModel();
		modeloReserva.addColumn("ID");
		modeloReserva.addColumn("Numero de Reserva");
		modeloReserva.addColumn("Fecha Check In");
		modeloReserva.addColumn("Fecha Check Out");
		modeloReserva.addColumn("Valor");
		modeloReserva.addColumn("Forma de Pago");
		modeloReserva.addColumn("Estado");
		modeloReserva.addColumn("Cedula del Huesped");
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		this.panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table, null);
		scroll_table.setVisible(true);

		//SE DEFINEN EL TAMAÑO DE LAS COLUMNAS
		TableColumnModel columnModel = tbReservas.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(20);
		columnModel.getColumn(1).setPreferredWidth(134);
		columnModel.getColumn(2).setPreferredWidth(134);
		columnModel.getColumn(3).setPreferredWidth(134);
		columnModel.getColumn(4).setPreferredWidth(100);
		columnModel.getColumn(5).setPreferredWidth(134);
		columnModel.getColumn(6).setPreferredWidth(70);
		columnModel.getColumn(7).setPreferredWidth(134);

		cargarTablaReserva(reservationController.getAllReservation());
	}

	private void tablaHuesped() {

		this.tbHuespedes = new JTable() {

			//Se habilitan para editar solo las columna que se necesitan
			@Override
			public boolean isCellEditable(int row, int column) {
				return column==1 | column==2 | column==3 | column==4 | column==7;
			}
		};

		tbHuespedes.getTableHeader().setReorderingAllowed(false);
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 14));
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("ID");
		modeloHuesped.addColumn("Cedula de Huesped");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Estado");
		modeloHuesped.addColumn("Telefono");
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);

		this.panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);

		//SE DEFINEN EL TAMAÑO DE LAS COLUMNAS
		TableColumnModel columnModel = tbHuespedes.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(20);
		columnModel.getColumn(1).setPreferredWidth(134);
		columnModel.getColumn(2).setPreferredWidth(134);
		columnModel.getColumn(3).setPreferredWidth(134);
		columnModel.getColumn(4).setPreferredWidth(134);
		columnModel.getColumn(5).setPreferredWidth(198);
		columnModel.getColumn(6).setPreferredWidth(70);
		columnModel.getColumn(7).setPreferredWidth(134);

		cargarTablaHuesped(guestController.getAllGuest());
	}

	private void eventoFechas(JTextFieldDateEditor editorFecha, String placeHolder) {
		editorFecha.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (editorFecha.getText().equals(placeHolder)) {
					placeHolderFechas(editorFecha,"");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (editorFecha.getText().isEmpty()) {
					placeHolderFechas(editorFecha, placeHolder);
				}
			}
		});
	}

	private void eventoTxtBuscar(){
		this.txtBuscar.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				checkEmpty();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				checkEmpty();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				checkEmpty();
			}

			private void checkEmpty() {
				if (txtBuscar.getText().isEmpty()) {
					limpiarTabla();
					cargarTablaReserva(reservationController.getAllReservation());
				}
			}
		});
	}

	private void eventoBtnBuscar(JPanel btnBuscar, JTabbedPane panel){
		btnBuscar.addMouseListener(new MouseAdapter() {

			//permite detectar de que Tap Panel está activo y asi poder llamar los metodos necesarios según el Tap
			@Override
			public void mousePressed(MouseEvent e) {

				int selectedIndex = panel.getSelectedIndex();

				if (selectedIndex == 0) {

					LocalDate checkIn = null;
					LocalDate checkOut = null;

					try {

						Instant instantCheckIn = txtFechaEntrada.getDate().toInstant();
						Instant instantCheckOut= txtFechaSalida.getDate().toInstant();
						checkIn = instantCheckIn.atZone(ZoneId.systemDefault()).toLocalDate();
						checkOut = instantCheckOut.atZone(ZoneId.systemDefault()).toLocalDate();

					}catch(NullPointerException ex){

					}

					limpiarTabla();
					consultaParametrosReserva(txtBuscar.getText().toUpperCase(), checkIn, checkOut);
					placeHolderFechas((JTextFieldDateEditor) txtFechaEntrada.getDateEditor(),"Check In");
					placeHolderFechas((JTextFieldDateEditor) txtFechaSalida.getDateEditor(),"Check Out");
				}else
					System.out.println("huesped");
			}
		});
	}

	private void limpiarTabla() {
		modeloReserva.getDataVector().clear();
	}

	private void placeHolderFechas(JTextFieldDateEditor editorFecha, String placeHolder){
		editorFecha.setText(placeHolder);
		editorFecha.setForeground(Color.GRAY);
	}

	private  void consultaParametrosReserva(String cod, LocalDate checkIn, LocalDate checkOut) {

		//SE CREA EL DTO QUE VA ALMACENAR LA INFORMACIÓN
		ReservationByParametersDTO reservationByParametersDTO = new ReservationByParametersDTO();
		reservationByParametersDTO.setReservationCod(cod);
		reservationByParametersDTO.setCheckIn(checkIn);
		reservationByParametersDTO.setCheckOut(checkOut);

		List<ReservationDTO> reservationList = this.reservationController.getReservationByParameters(reservationByParametersDTO);

		if (reservationList.isEmpty()){
			JOptionPane.showMessageDialog(this, "No se encontraron coincidencias");
		}
		else
			cargarTablaReserva(reservationList);

	}

	private void cargarTablaReserva(List<ReservationDTO> list) {

			try {
				list.forEach(
						reserva -> this.modeloReserva.addRow(
								new Object[] {
										reserva.getId(),
										reserva.getReservationCod(),
										reserva.getCheckIn(),
										reserva.getCheckOut(),
										reserva.getCost(),
										reserva.getMethodPayment(),
										reserva.getStatus(),
										reserva.getGuestDni()
								})
				);

			} catch (Exception e) {
				throw new RuntimeException(e);
			}
	}

	private void cargarTablaHuesped(List<GuestDTO> guests) {

		try {
			guests.forEach(
					guest -> this.modeloHuesped.addRow(
							new Object[] {
									guest.getId(),
									guest.getCedula(),
									guest.getFirstName(),
									guest.getLastName(),
									guest.getDateOfBirth(),
									guest.getNationality(),
									guest.getStatus(),
									guest.getPhone()
							})
			);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	 private void headerMousePressed(MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
}
}
