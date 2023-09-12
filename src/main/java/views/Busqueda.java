package views;

import DTO.*;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import controller.GuestController;
import controller.NationalityController;
import controller.ReservationController;
import service.util.ConfigureDates;
import util.MessageBox;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.time.LocalDate;
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
	private final JPanel btnLoad;
	private final JPanel btnEditar;
	private  final JPanel btnEliminar;
	private DefaultTableModel modeloReserva;
	private DefaultTableModel modeloHuesped;
	private final JTextField txtBuscar;
	public static JDateChooser txtFechaEntrada;
	public static JDateChooser txtFechaSalida;
	private final JLabel labelAtras;
	private final JLabel labelExit;
	private final JPanel btnExit;
	private final JPanel btnAtras;
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

		//INICIALIZAMOS LOS CONTROLADORES
		guestController = new GuestController(new NationalityController());
		reservationController = new ReservationController();


		JLabel lblTitulo = new JLabel("Sistema de Busqueda");
		lblTitulo.setForeground(new Color(54, 55, 83));
		lblTitulo.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblTitulo.setBounds(331, 30, 300, 42);
		contentPane.add(lblTitulo);

		panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(54, 55, 83));
		panel.setForeground(Color.WHITE);
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		//componentes que crean y cargan las tablas
		tablaReserva();
		tablaHuesped();

		//Codigo para saber en qué tap esta
		eventoTapTablas();

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


		//La fecha de ingreso
		txtFechaEntrada = new JDateChooser();
		JTextFieldDateEditor editorFechaEntrada = (JTextFieldDateEditor) txtFechaEntrada.getDateEditor();
		//EVENTO
		eventoFechas(editorFechaEntrada, "Check In");
		txtFechaEntrada.getCalendarButton().setBackground(SystemColor.textHighlight);
		txtFechaEntrada.getCalendarButton().setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/icon-reservas.png")));
		txtFechaEntrada.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 12));
		txtFechaEntrada.getCalendarButton().setCursor(new Cursor(Cursor.HAND_CURSOR));
		txtFechaEntrada.getCalendarButton().setBackground(new Color(29, 27, 49));
		txtFechaEntrada.getCalendarButton().setBounds(368, 0, 21, 33);
		txtFechaEntrada.setBounds(200, 115, 150, 35);
		txtFechaEntrada.setBackground(Color.WHITE);
		txtFechaEntrada.setBorder(new LineBorder(SystemColor.window));
		txtFechaEntrada.setDateFormatString("yyyy-MM-dd");
		txtFechaEntrada.setFont(new Font("Roboto", Font.PLAIN, 16));
		contentPane.add(txtFechaEntrada);

		//La fecha de salida
		txtFechaSalida = new JDateChooser();
		JTextFieldDateEditor editorFechaSalida = (JTextFieldDateEditor) txtFechaSalida.getDateEditor();
		//EVENTO
		eventoFechas(editorFechaSalida, "Check Out");
		txtFechaSalida.transferFocus();
		txtFechaSalida.getCalendarButton().setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/icon-reservas.png")));
		txtFechaSalida.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 12));
		txtFechaSalida.getCalendarButton().setCursor(new Cursor(Cursor.HAND_CURSOR));
		txtFechaSalida.getCalendarButton().setBackground(new Color(29, 27, 49));
		txtFechaSalida.getCalendarButton().setBounds(368, 0, 21, 33);
		txtFechaSalida.setBounds(380, 115, 150, 35);
		txtFechaSalida.setBackground(Color.WHITE);
		txtFechaSalida.setBorder(new LineBorder(SystemColor.window));
		txtFechaSalida.setDateFormatString("yyyy-MM-dd");
		txtFechaSalida.setFont(new Font("Roboto", Font.PLAIN, 16));
		contentPane.add(txtFechaSalida);

		//campo de texto de buscar
		txtBuscar = new JTextField();
		placeHolderTxtBuscar("Numero de Reserva");
		eventoTxtBuscar();
		txtBuscar.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtBuscar.setBounds(589, 115, 150, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);

		//separado del texto
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(54, 55, 83));
		separator_1_2.setBackground(new Color(54, 55, 83));
		separator_1_2.setBounds(589, 148, 150, 2);
		contentPane.add(separator_1_2);

		//Panel que simula el boton de buscar
		btnbuscar = new JPanel();
		eventoBtnBuscar(panel);
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(29, 27, 49));
		btnbuscar.setBounds(748, 115, 122, 35);
		btnbuscar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);

		JLabel lblBuscar = new JLabel("Buscar");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.BOLD, 18));


		btnLoad = new JPanel();
		eventoLoad(panel);
		btnLoad.setLayout(null);
		btnLoad.setBackground(Color.WHITE);
		btnLoad.setBounds(570, 508, 40, 40);
		btnLoad.setCursor(new Cursor(Cursor.HAND_CURSOR));
		//btnLoad.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK));
		contentPane.add(btnLoad);

		JLabel lblLoad = new JLabel();
		lblLoad.setBounds(0, 0, 40, 40);
		lblLoad.setBackground(SystemColor.textHighlight);
		lblLoad.setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/load.png")));
		lblLoad.setFont(new Font("Roboto", Font.PLAIN, 12));
		btnLoad.add(lblLoad);
		//lblLoad.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK));
		lblLoad.setHorizontalAlignment(SwingConstants.CENTER);


		//PANEL QUE SIMULA EL BOTÓN DE EDITAR
		btnEditar = new JPanel();
		eventoBtnModificar(panel);
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(29, 27, 49));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);

		JLabel lblEditar = new JLabel("Editar");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.BOLD, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);

		//PANEL QUE SIMULA EL BOTÓN DE ElIMINAR
		btnEliminar = new JPanel();
		eventoBtnEliminar(panel);
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(29, 27, 49));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);

		JLabel lblEliminar = new JLabel("Eliminar");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.BOLD, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);

		btnAtras = new JPanel();
		eventoRegresar();
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);

		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);

		btnExit = new JPanel();
		eventoSalir();
		btnExit.setLayout(null);
		btnExit.setBackground(Color.WHITE);
		btnExit.setBounds(857, 0, 53, 36);
		header.add(btnExit);

		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnExit.add(labelExit);
	}

	private void tablaReserva() {

		//INHABILITAMOS TODAS LAS COLUMNAS PARA QUE NO PUEDAS EDITARSE
		this.tbReservas = new JTable() {

			//Se habilitan para editar solo las columna que se necesitan
			@Override
			public boolean isCellEditable(int row, int column) {
				//return column == 2 || column == 3;
				return false;
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
		this.panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservadoNegro.png")), scroll_table, null);
		this.panel.setForegroundAt(0, Color.BLACK);
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

		//INHABILITAMOS TODAS LAS COLUMNAS PARA QUE NO PUEDAS EDITARSE
		this.tbHuespedes = new JTable() {

			//Se habilitan para editar solo las columna que se necesitan
			@Override
			public boolean isCellEditable(int row, int column) {
				//return column==1 | column==2 | column==3 | column==4 | column==7;
				return false;
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
		this.panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/buscarUsuariosBlanco.png")), scroll_tableHuespedes, null);
		this.panel.setForegroundAt(1, null);
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

	private void eventoTapTablas() {

		//EVENTO QUE CONTROLA CUANDO DEBEN SALIR LOS CAMPOS FECHAS
		panel.addChangeListener((ChangeEvent e) -> {

					int selectedIndex = panel.getSelectedIndex();

					if (selectedIndex == 0) {
						//PERMITE CAMBIAR EL COLOR DE LETRA Y EL ICONO DEL TAP
						headerDesignTapPanel(selectedIndex,
												1,
												"reservadoNegro",
												"buscarUsuariosBlanco");

						eventoTxtBuscar();
						txtFechaEntrada.setVisible(true);
						txtFechaSalida.setVisible(true);

					} else if (selectedIndex == 1) {
						headerDesignTapPanel(selectedIndex,
												0,
												"buscarUsuariosNegro",
												"reservadoBlanco");

						eventoTxtBuscar();
						txtFechaEntrada.setVisible(false);
						txtFechaSalida.setVisible(false);
					}
				}
		);
	}

	private void headerDesignTapPanel(int indexSelect, int indexNotSelect, String IconActive, String iconInactive) {

		//Cambiamos el color de la letra y el icono de los Tap que están en el Panel
		panel.setForegroundAt(indexSelect, Color.BLACK);
		panel.setIconAt(indexSelect, new ImageIcon(Busqueda.class.getResource("/imagenes/"+IconActive+".png")) );

		panel.setForegroundAt(indexNotSelect, null);
		panel.setIconAt(indexNotSelect, new ImageIcon(Busqueda.class.getResource("/imagenes/"+iconInactive+".png")) );

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

	private void placeHolderFechas(JTextFieldDateEditor editorFecha, String placeHolder){
		editorFecha.setText(placeHolder);
		editorFecha.setForeground(Color.GRAY);
	}

	private void eventoSalir() {
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
				btnExit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}
			@Override
			public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
				btnExit.setBackground(Color.white);
				labelExit.setForeground(Color.black);
			}
		});
	}

	private void eventoRegresar() {
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(29, 27, 49));
				labelAtras.setForeground(Color.white);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setBackground(Color.white);
				labelAtras.setForeground(Color.black);
			}
		});
	}

	private void eventoTxtBuscar(){
		this.txtBuscar.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {

				if (txtBuscar.getText().equals("Numero de Reserva") || txtBuscar.getText().equals("Cedula")) {
					placeHolderTxtBuscar("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {

				int selectedIndex = panel.getSelectedIndex();

				if (txtBuscar.getText().isEmpty()) {

					if(selectedIndex == 0) {
						placeHolderTxtBuscar("Numero de Reserva");
						limpiarTabla(modeloReserva);
						cargarTablaReserva(reservationController.getAllReservation());

					}else if (selectedIndex == 1){

						placeHolderTxtBuscar("Cedula");
						limpiarTabla(modeloHuesped);
						cargarTablaHuesped(guestController.getAllGuest());
					}
				}
			}
		});

	}

	private void eventoBtnBuscar(JTabbedPane panel){
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnbuscar.setBackground(new Color(54, 55, 83));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnbuscar.setBackground(new Color(29, 27, 49));
			}

			@Override
			public void mousePressed(MouseEvent e) {

				//permite detectar de que Tap Panel está activo y asi poder llamar los metodos necesarios según el Tap
				int selectedIndex = panel.getSelectedIndex();

				if (selectedIndex == 0) {

					ConfigureDates.resetDates();

					try {

						ConfigureDates.mapperDataToLocalDate(txtFechaEntrada.getDate(), txtFechaSalida.getDate());
					}catch(NullPointerException ignore){}

					limpiarTabla(modeloReserva);
					consultaParametrosReserva(txtBuscar.getText().toUpperCase(),
															ConfigureDates.getCheckIn(),
															ConfigureDates.getCheckOut());

					placeHolderFechas((JTextFieldDateEditor) txtFechaEntrada.getDateEditor(),"Check In");
					placeHolderFechas((JTextFieldDateEditor) txtFechaSalida.getDateEditor(),"Check Out");
				}else{
					limpiarTabla(modeloHuesped);
					consultarParametroCedula(txtBuscar.getText());
				}
			}

		});
	}

	private void eventoLoad(JTabbedPane panel) {
		this.btnLoad.addMouseListener(new MouseAdapter() {

			//permite detectar de que Tap Panel está activo y asi poder llamar los metodos necesarios según el Tap
			@Override
			public void mousePressed(MouseEvent e) {

				int selectedIndex = panel.getSelectedIndex();

				if (selectedIndex == 0) {
					limpiarTabla(modeloReserva);
					cargarTablaReserva(reservationController.getAllReservation());
				} else {
					limpiarTabla(modeloHuesped);
					cargarTablaHuesped(guestController.getAllGuest());
				}
			}
		});
	}

	private void eventoBtnModificar(JTabbedPane panel){

		this.btnEditar.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				btnEditar.setBackground(new Color(54, 55, 83));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnEditar.setBackground(new Color(29, 27, 49));
			}

			//permite detectar de que Tap Panel está activo y asi poder llamar los metodos necesarios según el Tap
			@Override
			public void mousePressed(MouseEvent e) {

				int selectedIndex = panel.getSelectedIndex();

				if (selectedIndex == 0) {
					enviarDatosReserva();

				}else if (selectedIndex == 1){
					enviarDatosHuesped();
				}
			}
		});
	}

	private void eventoBtnEliminar(JTabbedPane panel){
		this.btnEliminar.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				btnEliminar.setBackground(new Color(54, 55, 83));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnEliminar.setBackground(new Color(29, 27, 49));
			}

			//permite detectar de que Tap Panel está activo y asi poder llamar los metodos necesarios según el Tap
			@Override
			public void mousePressed(MouseEvent e) {

				int selectedIndex = panel.getSelectedIndex();

				if (selectedIndex == 0) {
					eliminarReserva();
					limpiarTabla(modeloReserva);
					cargarTablaReserva(reservationController.getAllReservation());
				}else if (selectedIndex == 1){
					eliminarHuesped();
					limpiarTabla(modeloHuesped);
					cargarTablaHuesped(guestController.getAllGuest());
				}
			}

		});
	}

	private void limpiarTabla(DefaultTableModel modelo) {
		modelo.getDataVector().clear();
	}

	private void placeHolderTxtBuscar(String placeHolder){
		txtBuscar.setText(placeHolder);
		txtBuscar.setForeground(Color.GRAY);
	}

	private  void consultaParametrosReserva(String cod, LocalDate checkIn, LocalDate checkOut) {

		//SE CREA EL DTO QUE VA ALMACENAR LA INFORMACIÓN
		ReservationByParametersDTO reservationByParametersDTO = new ReservationByParametersDTO();
		reservationByParametersDTO.setReservationCod(cod.equals("NUMERO DE RESERVA") ? null : cod);
		reservationByParametersDTO.setCheckIn(checkIn);
		reservationByParametersDTO.setCheckOut(checkOut);

		List<ReservationDTO> reservationList = this.reservationController.getReservationByParameters(reservationByParametersDTO);

		if (reservationList.isEmpty())
			MessageBox.messageBasic(contentPane, "No se encontraron coincidencias");
		else
			cargarTablaReserva(reservationList);

	}

	private void consultarParametroCedula(String cedula) {

		List<GuestDTO> guestList = guestController.getGuestsByCedula(cedula.equals("Cedula") ? null : cedula);

		if (guestList.isEmpty())
			JOptionPane.showMessageDialog(contentPane, "No se encontraron coincidencias");
		else
			cargarTablaHuesped(guestList);
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

	private boolean tieneFilaElegida(JTable table) {
		return table.getSelectedRowCount() == 0 || table.getSelectedColumnCount() == 0;
	}

	private void enviarDatosReserva(){

		if(tieneFilaElegida(tbReservas)) {
			JOptionPane.showMessageDialog(contentPane, "Por favor, elige una Reserva");
			return;
		}

		try {

			String checkIn =  String.valueOf(modeloReserva.getValueAt(tbReservas.getSelectedRow(),2));
			String checkOut =  String.valueOf(modeloReserva.getValueAt(tbReservas.getSelectedRow(),3));

			ReservationRequestDTO reservationRequestDTO = new ReservationRequestDTO(
					Long.valueOf(modeloReserva.getValueAt(tbReservas.getSelectedRow(),0).toString()),
					LocalDate.parse(checkIn),
					LocalDate.parse(checkOut),
					String.valueOf(modeloReserva.getValueAt(tbReservas.getSelectedRow(),5))
			);

			UpdateReservasView updateReserva = new UpdateReservasView(Busqueda.this,
																											reservationRequestDTO,
																											reservationController);
			updateReserva.setVisible(true);

		}catch(ArrayIndexOutOfBoundsException ignored){}
	}

	private void enviarDatosHuesped(){

		if(tieneFilaElegida(tbHuespedes)) {
			MessageBox.messageBasic(contentPane, "Por favor, elige un Huesped");
			return;
		}

		try {

			String date =  String.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(),4));

			GuestRequestDTO guestRequestDTO = new GuestRequestDTO(
					Long.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(),0).toString()),
					String.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(),1).toString()),
					String.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(),2).toString()),
					String.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(),3).toString()),
					LocalDate.parse(date),
					String.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(),7).toString()),
					(NationalityRequestDTO) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(),5)
			);

			UpdateHuespedView updateHuespedView = new UpdateHuespedView(Busqueda.this,
																													guestRequestDTO,
																													guestController);
			updateHuespedView.setVisible(true);

		}catch (ArrayIndexOutOfBoundsException ignored){}
	}

	private void eliminarReserva(){

		if (tieneFilaElegida(tbReservas)) {
			MessageBox.messageBasic(contentPane, "Por favor, elige una Reserva");
			return;
		}

		try {

			Long reservaID = Long.valueOf(modeloReserva.getValueAt(tbReservas.getSelectedRow(), 0).toString());
			String reservaCod = String.valueOf(modeloReserva.getValueAt(tbReservas.getSelectedRow(), 1).toString());

			//CLASE DEFINIDA PARA MANEJAR LAS VENTANAS DE INFORMACIÓN
			int result = MessageBox.messageTypeOption(contentPane,
							"¿Estas Seguro(a) que deseas Eliminar la Reserva "+ reservaCod +"?",
							"Eliminación de la Reserva");

			if (MessageBox.validateOption(result)) {

				if(this.reservationController.softDeleteReservation(reservaID)) {
					modeloReserva.removeRow(tbReservas.getSelectedRow());

					MessageBox.messageBasic(contentPane,
														" Reserva eliminado con éxito!",
														"Eliminado correctamente",
														JOptionPane.INFORMATION_MESSAGE);
				}else
					MessageBox.messageBasic(contentPane, " Ha ocurrido un error inesperado");
			}
		}catch (ArrayIndexOutOfBoundsException ignore){}
	}

	private void eliminarHuesped(){

		if (tieneFilaElegida(tbHuespedes)) {
			MessageBox.messageBasic(contentPane, "Por favor, elige una Huesped");
			return;
		}

		try {

			Long huespedID = Long.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
			String huespedCedula = String.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 1).toString());

			//CLASE DEFINIDA PARA MANEJAR LAS VENTANAS DE INFORMACIÓN
			int result = MessageBox.messageTypeOption(contentPane,
					"¿Estas Seguro(a) que deseas Eliminar el Huesped con CC. "+ huespedCedula +"?",
					"Eliminación del Huesped");

			if (MessageBox.validateOption(result)) {

				if(this.guestController.softDeleteGuest(huespedID)) {
					modeloReserva.removeRow(tbReservas.getSelectedRow());
					MessageBox.messageBasic(contentPane,
															" Huesped eliminada con éxito!",
															"Delete",
															JOptionPane.ERROR_MESSAGE);
				}else
					MessageBox.messageBasic(contentPane, "Ha ocurrido un error inesperado");
			}

		}catch (ArrayIndexOutOfBoundsException ignore){
			System.out.println("esta tirando al excepción");
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
