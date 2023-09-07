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

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.text.Format;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@SuppressWarnings("serial")
public class RegistroHuesped extends JFrame {

	private JPanel contentPane;
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtTelefono;
	private JTextField txtNreserva;
	private JDateChooser txtFechaN;
	private final JTextFieldDateEditor editorFecha;
	private final JComboBox<NationalityRequestDTO> txtNacionalidad;
	private final JPanel btnExit;
	private final JPanel btnGuardar;
	private JLabel labelExit;
	int xMouse, yMouse;


	//
	private final ReservasView jFrameRegistrarHuesped;
	private final ReservationController reservationController;
	private final GuestController guestController;
	private final NationalityController nationalityController;
	private final ReservationRequestDTO reservationRequestDTO;
	private GuestRequestDTO guestRequestDTO;

	/**
	 * Create the frame.
	 */
	public RegistroHuesped(ReservasView jFrameRegistrarHuesped, ReservationRequestDTO reservationRequestDTO) {


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

		//

		this.jFrameRegistrarHuesped = jFrameRegistrarHuesped;
		this.reservationController = new ReservationController();
		this.guestController = new GuestController();
		this.nationalityController = new NationalityController();
		this.reservationRequestDTO = reservationRequestDTO;


		JLabel lblTitulo = new JLabel("Registro Huesped");
		lblTitulo.setBounds(606, 15, 250, 42);
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto Black", Font.PLAIN, 24));
		contentPane.add(lblTitulo);

		JLabel lblCedula = new JLabel("Cedula");
		lblCedula.setBounds(562, 68, 253, 24);
		lblCedula.setForeground(SystemColor.textInactiveText);
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
		separator_1_2_.setForeground(new Color(12, 138, 199));
		separator_1_2_.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(562, 139, 253, 24);
		lblNombre.setForeground(SystemColor.textInactiveText);
		lblNombre.setFont(new Font("Roboto Black", Font.PLAIN, 20));
		contentPane.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Roboto", Font.PLAIN, 18));
		txtNombre.setBounds(560, 163, 285, 33);
		txtNombre.setBackground(Color.WHITE);
		txtNombre.setColumns(10);
		txtNombre.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtNombre);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setBounds(560, 197, 289, 2);
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2);

		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(560, 205, 255, 24);
		lblApellido.setForeground(SystemColor.textInactiveText);
		lblApellido.setFont(new Font("Roboto Black", Font.PLAIN, 20));
		contentPane.add(lblApellido);

		txtApellido = new JTextField();
		txtApellido.setFont(new Font("Roboto", Font.PLAIN, 18));
		txtApellido.setBounds(560, 235, 285, 33);
		txtApellido.setColumns(10);
		txtApellido.setBackground(Color.WHITE);
		txtApellido.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtApellido);

		JSeparator separator_1_2_1 = new JSeparator();
		separator_1_2_1.setBounds(560, 269, 289, 2);
		separator_1_2_1.setForeground(new Color(12, 138, 199));
		separator_1_2_1.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_1);

		JLabel lblFechaN = new JLabel("Fecha de Nacimiento");
		lblFechaN.setBounds(560, 281, 255, 24);
		lblFechaN.setForeground(SystemColor.textInactiveText);
		lblFechaN.setFont(new Font("Roboto Black", Font.PLAIN, 20));
		contentPane.add(lblFechaN);

		txtFechaN = new JDateChooser();
		editorFecha = (JTextFieldDateEditor) txtFechaN.getDateEditor();
		txtFechaN.setBounds(560, 308, 285, 36);
		txtFechaN.getCalendarButton().setIcon(new ImageIcon(RegistroHuesped.class.getResource("/imagenes/icon-reservas.png")));
		txtFechaN.getCalendarButton().setBackground(SystemColor.textHighlight);
		txtFechaN.getCalendarButton().setBounds(267, 1, 21, 31);
		txtFechaN.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 11));
		txtFechaN.setDateFormatString("yyyy-MM-dd");
		txtFechaN.setBackground(Color.WHITE);
		txtFechaN.setFont(new Font("Roboto", Font.PLAIN, 18));
		contentPane.add(txtFechaN);

		JSeparator separator_1_2_2 = new JSeparator();
		separator_1_2_2.setBounds(560, 344, 289, 2);
		separator_1_2_2.setForeground(new Color(12, 138, 199));
		separator_1_2_2.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_2);

		JLabel lblNacionalidad = new JLabel("Nacionalidad");
		lblNacionalidad.setBounds(560, 353, 255, 24);
		lblNacionalidad.setForeground(SystemColor.textInactiveText);
		lblNacionalidad.setFont(new Font("Roboto Black", Font.PLAIN, 20));
		contentPane.add(lblNacionalidad);

		txtNacionalidad = new JComboBox<>();
		cargarComboNacionalidad();
		txtNacionalidad.setBounds(560, 381, 289, 36);
		txtNacionalidad.setBackground(SystemColor.text);
		txtNacionalidad.setFont(new Font("Roboto", Font.PLAIN, 18));

		contentPane.add(txtNacionalidad);

		JSeparator separator_1_2_3 = new JSeparator();
		separator_1_2_3.setBounds(560, 418, 288, 2);
		separator_1_2_3.setForeground(new Color(12, 138, 199));
		separator_1_2_3.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_3);

		JLabel lblTelefono = new JLabel("Teléfono");
		lblTelefono.setBounds(562, 433, 253, 24);
		lblTelefono.setForeground(SystemColor.textInactiveText);
		lblTelefono.setFont(new Font("Roboto Black", Font.PLAIN, 20));
		contentPane.add(lblTelefono);

		txtTelefono = new JTextField();
		txtTelefono.setFont(new Font("Roboto", Font.PLAIN, 18));
		txtTelefono.setBounds(560, 454, 285, 33);
		txtTelefono.setColumns(10);
		txtTelefono.setBackground(Color.WHITE);
		txtTelefono.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtTelefono);

		JSeparator separator_1_2_4 = new JSeparator();
		separator_1_2_4.setBounds(560, 488, 289, 2);
		separator_1_2_4.setForeground(new Color(12, 138, 199));
		separator_1_2_4.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_4);

		JLabel lblNumeroReserva = new JLabel("Número de Reserva");
		lblNumeroReserva.setBounds(560, 504, 253, 24);
		lblNumeroReserva.setForeground(SystemColor.textInactiveText);
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
		separator_1_2_5.setForeground(new Color(12, 138, 199));
		separator_1_2_5.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_5);

		btnGuardar = new JPanel();
		eventoGuardar();
		btnGuardar.setBounds(723, 580, 122, 35);
		btnGuardar.setLayout(null);
		btnGuardar.setBackground(new Color(12, 138, 199));
		contentPane.add(btnGuardar);
		btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		
		JLabel labelGuardar = new JLabel("GUARDAR");
		labelGuardar.setHorizontalAlignment(SwingConstants.CENTER);
		labelGuardar.setForeground(Color.WHITE);
		labelGuardar.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelGuardar.setBounds(0, 0, 122, 35);
		btnGuardar.add(labelGuardar);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 489, 634);
		panel.setBackground(new Color(12, 138, 199));
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
				jFrameRegistrarHuesped.setVisible(true);
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
			public void mouseClicked(MouseEvent e) {
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
	private void cargarComboNacionalidad() {

		// SE CARGAN TODAS LAS NACIONALIDADES
		nationalityController.getAllNationality().forEach(
				nationality ->
						txtNacionalidad.addItem(nationality)
		);

	}
	private NationalityRequestDTO obtenerNacionalidad(NationalityRequestDTO nacionalidad) {

		ComboBoxModel<NationalityRequestDTO> model =  txtNacionalidad.getModel();
		NationalityRequestDTO nationality = null;

		for (int i = 0; i < model.getSize(); i++) {

			NationalityRequestDTO item = model.getElementAt(i);

			if (item.getId().equals(nacionalidad.getId()))
				nationality = item;
		}

		return nationality;
	}
	private void consultarParametroCedula(String cedula) {

		Optional<GuestDTO> guest = guestController.getGuestsByCedula(cedula).stream().findFirst();

		guest.ifPresentOrElse(
						this::cargarDatosHuesped,
						this::limpiarCampos
		);

	}
	private void paramastarde() {

		Instant instantDateBirth = txtFechaN.getDate().toInstant();
		LocalDate dateBirth = instantDateBirth.atZone(ZoneId.systemDefault()).toLocalDate();

		NationalityRequestDTO nationalityRequestDTO = (NationalityRequestDTO) txtNacionalidad.getSelectedItem();

		crearGuestRequestDTO(txtCedula.getText(),
				txtNombre.getText(),
				txtApellido.getText(),
				dateBirth,
				txtTelefono.getText(),
				nationalityRequestDTO);
	}
	private void limpiarCampos() {

		List<JTextField> campos = List.of(txtNombre, txtApellido, txtTelefono);

		campos.forEach(campo -> {
			campo.setText("");
			campo.setEditable(true);
		});

		editorFecha.setText("");
		editorFecha.setEditable(true);
		txtNacionalidad.setEnabled(true);
	}
	private void cargarDatosHuesped(GuestDTO guestDTO) {

		crearGuestRequestDTO(guestDTO.getCedula(),
											guestDTO.getFirstName(),
											guestDTO.getLastName(),
											guestDTO.getDateOfBirth(),
											guestDTO.getPhone(),
											guestDTO.getNationality()
		);

		//SE LE AGREGAN LOS DATOS DEL HUESPED YA EXISTENTE EN LA BD
		txtCedula.setText(guestDTO.getCedula());
		txtNombre.setText(guestDTO.getFirstName());
		txtApellido.setText(guestDTO.getLastName());
		txtFechaN.setDate(configurarFecha(guestDTO.getDateOfBirth()));
		txtNacionalidad.setSelectedItem(obtenerNacionalidad(guestDTO.getNationality()));
		txtTelefono.setText(guestDTO.getPhone());

		//DESABILITAMOS LOS CAMPOS
		txtNombre.setEditable(false);
		txtApellido.setEditable(false);
		editorFecha.setEditable(false);
		txtNacionalidad.setEnabled(false);
		txtTelefono.setEditable(false);
	}

	private void crearGuestRequestDTO(String cedula,
														  String firsName,
														  String lastName,
														  LocalDate dateOfBirth,
														  String phone,
														  NationalityRequestDTO nationality) {

		this.guestRequestDTO = new GuestRequestDTO(cedula, firsName, lastName, dateOfBirth, phone, nationality);
	}

	private Date configurarFecha(LocalDate fecha) {

		//Permite cambiar el formato de un LocalDate a Date
		return Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant());
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
