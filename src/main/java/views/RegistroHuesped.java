package views;

import DTO.GuestDTO;
import DTO.GuestRequestDTO;
import DTO.NationalityRequestDTO;
import DTO.ReservationRequestDTO;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import controller.GuestController;
import controller.NationalityController;
import controller.ReservationController;
import service.util.ConfigureDates;
import util.MessageBox;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("serial")
public class RegistroHuesped extends JFrame {

	private final JPanel contentPane;
	private final JTextField txtCedula;
	private final JTextField txtNombre;
	private final JTextField txtApellido;
	private final JTextField txtTelefono;
	private final JTextField txtNreserva;
	private final JDateChooser txtFechaN;
	private final JTextFieldDateEditor editorFecha;
	private final JComboBox<NationalityRequestDTO> txtNacionalidad;
	private final JPanel btnExit;
	private final JPanel btnGuardar;
	private final JLabel labelExit;
	int xMouse, yMouse;


	//
	private final ReservasView jFrameRegistrarReserva;
	private final ReservationController reservationController;
	private final GuestController guestController;
	private final NationalityController nationalityController;
	private final ReservationRequestDTO reservationRequestDTO;
	private GuestRequestDTO guestRequestDTO;
	private boolean findGuest;

	/**
	 * Create the frame.
	 */
	public RegistroHuesped(ReservasView jFrameRegistrarReserva, ReservationRequestDTO reservationRequestDTO) {


		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistroHuesped.class.getResource("/imagenes/lOGO-50PX.png")));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 910, 634);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.text);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPane.setLayout(null);

		// INICIALIZACIÓN DE LAS VARIABLES

		this.jFrameRegistrarReserva = jFrameRegistrarReserva;
		this.nationalityController = new NationalityController();
		this.guestController = new GuestController(this.nationalityController);
		this.reservationController = new ReservationController(this.guestController);


		this.reservationRequestDTO = reservationRequestDTO;
		this.findGuest = false;


		JLabel lblTitulo = new JLabel("Registro Huesped");
		lblTitulo.setBounds(606, 15, 250, 42);
		lblTitulo.setForeground(new Color(54, 55, 83));
		lblTitulo.setFont(new Font("Roboto Black", Font.BOLD, 24));
		contentPane.add(lblTitulo);

		JLabel lblCedula = new JLabel("Cedula");
		lblCedula.setBounds(562, 68, 253, 24);
		lblCedula.setForeground(new Color(0, 0, 0, 180));
		lblCedula.setFont(new Font("Roboto Black", Font.PLAIN, 20));
		contentPane.add(lblCedula);

		txtCedula = new JTextField();
		consultarPorCedula();
		txtCedula.setFont(new Font("Roboto", Font.PLAIN, 18));
		txtCedula.setBounds(560, 93, 285, 33);
		txtCedula.setBackground(Color.WHITE);
		txtCedula.setColumns(10);
		txtCedula.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtCedula);

		JSeparator separator_1_2_ = new JSeparator();
		separator_1_2_.setBounds(560, 127, 289, 2);
		separator_1_2_.setForeground(new Color(54, 55, 83));
		separator_1_2_.setBackground(new Color(54, 55, 83));
		contentPane.add(separator_1_2_);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(562, 139, 253, 24);
		lblNombre.setForeground(new Color(0, 0, 0, 180));
		lblNombre.setFont(new Font("Roboto Black", Font.PLAIN, 20));
		contentPane.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Roboto", Font.PLAIN, 18));
		txtNombre.setBounds(560, 163, 285, 33);
		txtNombre.setBackground(Color.WHITE);
		txtNombre.setColumns(10);
		txtNombre.setBorder(BorderFactory.createEmptyBorder());
		contentPane.add(txtNombre);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setBounds(560, 197, 289, 2);
		separator_1_2.setForeground(new Color(54, 55, 83));
		separator_1_2.setBackground(new Color(54, 55, 83));
		contentPane.add(separator_1_2);

		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(560, 205, 255, 24);
		lblApellido.setForeground(new Color(0, 0, 0, 180));
		lblApellido.setFont(new Font("Roboto Black", Font.PLAIN, 20));
		contentPane.add(lblApellido);

		txtApellido = new JTextField();
		txtApellido.setFont(new Font("Roboto", Font.PLAIN, 18));
		txtApellido.setBounds(560, 235, 285, 33);
		txtApellido.setColumns(10);
		txtApellido.setBackground(Color.WHITE);
		txtApellido.setBorder(BorderFactory.createEmptyBorder());
		contentPane.add(txtApellido);

		JSeparator separator_1_2_1 = new JSeparator();
		separator_1_2_1.setBounds(560, 269, 289, 2);
		separator_1_2_1.setForeground(new Color(54, 55, 83));
		separator_1_2_1.setBackground(new Color(54, 55, 83));
		contentPane.add(separator_1_2_1);

		JLabel lblFechaN = new JLabel("Fecha de Nacimiento");
		lblFechaN.setBounds(560, 281, 255, 24);
		lblFechaN.setForeground(new Color(0, 0, 0, 180));
		lblFechaN.setFont(new Font("Roboto Black", Font.PLAIN, 20));
		contentPane.add(lblFechaN);

		txtFechaN = new JDateChooser();
		editorFecha = (JTextFieldDateEditor) txtFechaN.getDateEditor();
		eventoBtnFecha();
		eventoTxtFecha();
		txtFechaN.setBounds(560, 308, 285, 36);
		txtFechaN.getCalendarButton().setIcon(new ImageIcon(RegistroHuesped.class.getResource("/imagenes/icon-reservas.png")));
		txtFechaN.getCalendarButton().setBackground(new Color(29, 27, 49));
		txtFechaN.getCalendarButton().setBounds(267, 1, 21, 31);
		txtFechaN.getCalendarButton().setCursor(new Cursor(Cursor.HAND_CURSOR));
		txtFechaN.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 11));
		txtFechaN.setDateFormatString("yyyy-MM-dd");
		txtFechaN.setBackground(Color.WHITE);
		txtFechaN.setFont(new Font("Roboto", Font.PLAIN, 18));
		contentPane.add(txtFechaN);

		JSeparator separator_1_2_2 = new JSeparator();
		separator_1_2_2.setBounds(560, 344, 289, 2);
		separator_1_2_2.setForeground(new Color(54, 55, 83));
		separator_1_2_2.setBackground(new Color(54, 55, 83));
		contentPane.add(separator_1_2_2);

		JLabel lblNacionalidad = new JLabel("Nacionalidad");
		lblNacionalidad.setBounds(560, 353, 255, 24);
		lblNacionalidad.setForeground(new Color(0, 0, 0, 180));
		lblNacionalidad.setFont(new Font("Roboto Black", Font.PLAIN, 20));
		contentPane.add(lblNacionalidad);

		txtNacionalidad = new JComboBox<>();
		cargarComboNacionalidad();
		txtNacionalidad.setBounds(560, 381, 289, 36);
		txtNacionalidad.setForeground(new Color(54, 55, 83));
		txtNacionalidad.setBackground(SystemColor.text);
		txtNacionalidad.setFont(new Font("Roboto", Font.PLAIN, 20));
		contentPane.add(txtNacionalidad);

		JSeparator separator_1_2_3 = new JSeparator();
		separator_1_2_3.setBounds(560, 418, 288, 2);
		separator_1_2_3.setForeground(new Color(54, 55, 83));
		separator_1_2_3.setBackground(new Color(54, 55, 83));
		contentPane.add(separator_1_2_3);

		JLabel lblTelefono = new JLabel("Teléfono");
		lblTelefono.setBounds(562, 433, 253, 24);
		lblTelefono.setForeground(new Color(0, 0, 0, 180));
		lblTelefono.setFont(new Font("Roboto Black", Font.PLAIN, 20));
		contentPane.add(lblTelefono);

		txtTelefono = new JTextField();
		txtTelefono.setFont(new Font("Roboto", Font.PLAIN, 18));
		txtTelefono.setBounds(560, 454, 285, 33);
		txtTelefono.setColumns(10);
		txtTelefono.setBackground(Color.WHITE);
		txtTelefono.setBorder(BorderFactory.createEmptyBorder());
		contentPane.add(txtTelefono);

		JSeparator separator_1_2_4 = new JSeparator();
		separator_1_2_4.setBounds(560, 488, 289, 2);
		separator_1_2_4.setForeground(new Color(54, 55, 83));
		separator_1_2_4.setBackground(new Color(54, 55, 83));
		contentPane.add(separator_1_2_4);

		JLabel lblNumeroReserva = new JLabel("Número de Reserva");
		lblNumeroReserva.setBounds(560, 504, 253, 24);
		lblNumeroReserva.setForeground(new Color(0, 0, 0, 180));
		lblNumeroReserva.setFont(new Font("Roboto Black", Font.PLAIN, 20));
		contentPane.add(lblNumeroReserva);

		txtNreserva = new JTextField();
		txtNreserva.setEditable(false);
		txtNreserva.setText(reservationRequestDTO.getReservationCod());
		txtNreserva.setFont(new Font("Roboto", Font.PLAIN, 18));
		txtNreserva.setBounds(560, 525, 285, 33);
		txtNreserva.setColumns(10);
		txtNreserva.setBackground(Color.WHITE);
		txtNreserva.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtNreserva);

		JSeparator separator_1_2_5 = new JSeparator();
		separator_1_2_5.setBounds(560, 559, 289, 2);
		separator_1_2_5.setForeground(new Color(54, 55, 83));
		separator_1_2_5.setBackground(new Color(54, 55, 83));
		contentPane.add(separator_1_2_5);

		btnGuardar = new JPanel();
		eventoGuardar();
		btnGuardar.setBounds(723, 580, 122, 35);
		btnGuardar.setLayout(null);
		btnGuardar.setBackground(new Color(29, 27, 49));
		contentPane.add(btnGuardar);
		btnGuardar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		JLabel labelGuardar = new JLabel("Guardar");
		labelGuardar.setHorizontalAlignment(SwingConstants.CENTER);
		labelGuardar.setForeground(Color.WHITE);
		labelGuardar.setFont(new Font("Roboto", Font.BOLD, 18));
		labelGuardar.setBounds(0, 0, 122, 35);
		btnGuardar.add(labelGuardar);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 489, 634);
		panel.setBackground(new Color(54, 55, 83));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel imagenFondo = new JLabel("");
		imagenFondo.setBounds(0, 121, 479, 502);
		panel.add(imagenFondo);
		imagenFondo.setIcon(new ImageIcon(RegistroHuesped.class.getResource("/imagenes/registro.png")));
		
		JLabel logo = new JLabel("");
		logo.setBounds(194, 39, 104, 107);
		panel.add(logo);
		logo.setIcon(new ImageIcon(RegistroHuesped.class.getResource("/imagenes/Ha-100px.png")));
		
		btnExit = new JPanel();
		eventoSalir();
		btnExit.setBounds(857, 0, 53, 36);
		contentPane.add(btnExit);
		btnExit.setLayout(null);
		btnExit.setBackground(Color.white);
		
		labelExit = new JLabel("X");
		labelExit.setBounds(0, 0, 53, 36);
		btnExit.add(labelExit);
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(SystemColor.black);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));

		JPanel header = new JPanel();
		header.setBounds(0, 0, 910, 36);
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
		header.setBackground(SystemColor.text);
		header.setOpaque(false);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
	}

	private void eventoSalir() {
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jFrameRegistrarReserva.setVisible(true);
				setVisible(false);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnExit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnExit.setBackground(Color.white);
				labelExit.setForeground(Color.black);
			}
		});
	}
	private void eventoGuardar() {
		btnGuardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnGuardar.setBackground(new Color(54, 55, 83));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnGuardar.setBackground(new Color(29, 27, 49));
			}

			@Override
			public void mousePressed(MouseEvent e) {

				try {
					/*
						SE TOMA DE NUEVO LA FECHA EN CASO DE QUE NO SE ACTIVE EL EVENTO DEL BOTÓN DE LA FECHA
						Y ASI VALIDARLA DE NUEVO.
					 */
					ConfigureDates.mapperDataToLocalDate(txtFechaN.getDate());

				}catch (NullPointerException ignore){}

					if (txtFechaN.getDate() == null ||
							txtCedula.getText().isEmpty() ||
							txtNombre.getText().isEmpty() ||
							txtApellido.getText().isEmpty() ||
							txtTelefono.getText().isEmpty()) {

						MessageBox.messageBasic(contentPane, "Debes llenar todos los campos.");

					}else if(ConfigureDates.isUnderAge()){
						MessageBox.messageBasic(contentPane, "El huesped debe ser mayor de edad");

					}else {
						//ENVIAMOS LOS DATOS PARA EL REGISTRO
						reservationController
								.createReservation(
										reservationRequestDTO,
										findGuest ?
												guestRequestDTO :
												newHuesped()
								);

						MessageBox.messageBasic(contentPane,
																"Reserva hecha satisfactoriamente",
																"Creación Correcta",
																JOptionPane.INFORMATION_MESSAGE);


						//REGRESAMOS A LA VISTA DE RESERVA
						jFrameRegistrarReserva.setVisible(true);
						setVisible(false);
					}
			}
		});
	}
	private void consultarPorCedula(){
		this.txtCedula.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {}

			@Override
			public void focusLost(FocusEvent e) {
				consultarParametroCedula(txtCedula.getText());
			}
		});

	}
	private void eventoBtnFecha() {
		txtFechaN.getCalendarButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//CON ESTO EVITAMOS QUE SE ACTIVE INNECESARIAMENTE EL EVENTO DEL CAMPO CEDULA
				txtCedula.setFocusable(false);
				txtFechaN.requestFocus();
			}
		});
	}
	private void eventoTxtFecha() {
		editorFecha.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {}

			@Override
			public void focusLost(FocusEvent e) {

				//SE VALIDA QUE TENGA LA EDAD MINIMA
				try {
					ConfigureDates.mapperDataToLocalDate(txtFechaN.getDate());

					if (ConfigureDates.isUnderAge()) {
						MessageBox.messageBasic(contentPane, "El huesped debe ser mayor de edad");
						editorFecha.setText("");
					}
				}catch (NullPointerException ignore){}

			}
		});
	}
	private void cargarComboNacionalidad() {

		// SE CARGAN TODAS LAS NACIONALIDADES
		nationalityController.getAllNationality().forEach(
				nationality ->
						txtNacionalidad.addItem(nationality)
		);

	}
	private void consultarParametroCedula(String cedula) {

		Optional<GuestDTO> guest = guestController.getGuestsByCedula(cedula).stream().findFirst();

		guest.ifPresentOrElse(
						this::loadDataOfExistingGuest,
						this::limpiarCampos
		);

	}
	private GuestRequestDTO newHuesped() {

		LocalDate dateBirth = ConfigureDates.getDateOfBirth();

		NationalityRequestDTO nationalityRequestDTO = (NationalityRequestDTO) txtNacionalidad.getSelectedItem();

		 return this.guestRequestDTO = crearGuestRequestDTO(txtCedula.getText(),
																							txtNombre.getText(),
																							txtApellido.getText(),
																							dateBirth,
																							txtTelefono.getText(),
																							nationalityRequestDTO);
	}
	private void loadDataOfExistingGuest(GuestDTO guestDTO) {

		//SE LE SETEAN LOS DATOS PROVENIENTES DEL HUESPED EXISTENTE
		this.guestRequestDTO = crearGuestRequestDTO(guestDTO.getCedula(),
																			guestDTO.getFirstName(),
																			guestDTO.getLastName(),
																			guestDTO.getDateOfBirth(),
																			guestDTO.getPhone(),
																			guestDTO.getNationality()
		);

		//CONFIRMAMOS LA EXISTENCIA DEL HUESPED
		this.findGuest = true;

		//SE LE AGREGAN LOS DATOS DEL HUESPED YA EXISTENTE A LOS CAMPOS
		txtCedula.setText(guestDTO.getCedula());
		txtNombre.setText(guestDTO.getFirstName());
		txtApellido.setText(guestDTO.getLastName());
		txtFechaN.setDate(ConfigureDates.mapperLocalDateToData(guestDTO.getDateOfBirth()));
		txtNacionalidad.setSelectedItem(obtenerNacionalidad(guestDTO.getNationality()));
		txtTelefono.setText(guestDTO.getPhone());

		//DESABILITAMOS LOS CAMPOS
		txtNombre.setEditable(false);
		txtApellido.setEditable(false);
		editorFecha.setEditable(false);
		txtFechaN.getCalendarButton().setEnabled(false);
		txtNacionalidad.setEnabled(false);
		txtTelefono.setEditable(false);
	}
	private NationalityRequestDTO obtenerNacionalidad(NationalityRequestDTO nacionalidad) {

		ComboBoxModel<NationalityRequestDTO> model =  txtNacionalidad.getModel();
		NationalityRequestDTO nationality;

		for (int i = 0; i < model.getSize(); i++) {

			 nationality = model.getElementAt(i);

			if (nationality.getId().equals(nacionalidad.getId()))
				return  nationality;
		}

		return model.getElementAt(0);
	}
	private GuestRequestDTO crearGuestRequestDTO(String cedula,
																			  String firsName,
																			  String lastName,
																			  LocalDate dateOfBirth,
																			  String phone,
																			  NationalityRequestDTO nationality) {

		return new GuestRequestDTO(cedula, firsName, lastName, dateOfBirth, phone, nationality);
	}
	private void limpiarCampos() {

		findGuest = false;
		List<JTextField> campos = List.of(txtNombre, txtApellido, txtTelefono);

		campos.forEach(campo -> {
			campo.setText("");
			campo.setEditable(true);
		});

		editorFecha.setText("");
		editorFecha.setEditable(true);
		txtFechaN.getCalendarButton().setEnabled(true);
		txtNacionalidad.setEnabled(true);
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
