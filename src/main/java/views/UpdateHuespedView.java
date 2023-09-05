package views;

import DTO.GuestRequestDTO;
import DTO.NationalityRequestDTO;
import com.toedter.calendar.JDateChooser;
import controller.GuestController;
import controller.NationalityController;
import modelo.Nationality;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.Format;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@SuppressWarnings("serial")
public class UpdateHuespedView extends JFrame {

	private JPanel contentPane;
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtTelefono;
	private static JDateChooser txtFechaN;
	private JComboBox<NationalityRequestDTO> txtNacionalidad;
	private JLabel labelExit;
	private JPanel btnexit;
	private JPanel btnguardar;
	int xMouse, yMouse;

	//

	private final GuestController guestController;
	private final GuestRequestDTO guestRequestDTO;
	private final NationalityController nationalityController;
	private final Busqueda jFrameBusqueda;

	/**
	 * Create the frame.
	 */
	public UpdateHuespedView(Busqueda jFrameBusqueda, GuestRequestDTO guestRequestDTO, GuestController guestController) {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(UpdateHuespedView.class.getResource("/imagenes/lOGO-50PX.png")));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 910, 634);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.text);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPane.setLayout(null);

		//SE OBTIENEN LOS DATOS PARA AUTOCARGAR EL FORMULARIO
		this.guestController = guestController;
		this.guestRequestDTO = guestRequestDTO;
		this.nationalityController = new NationalityController();
		this.jFrameBusqueda = jFrameBusqueda;

		JLabel lblTitulo = new JLabel("Actualizar Datos");
		lblTitulo.setBounds(606, 15, 234, 42);
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto Black", Font.PLAIN, 24));
		contentPane.add(lblTitulo);

		JLabel lblCedula = new JLabel("Cedula");
		lblCedula.setBounds(562, 70, 253, 24);
		lblCedula.setForeground(SystemColor.textInactiveText);
		lblCedula.setFont(new Font("Roboto Black", Font.PLAIN, 20));
		contentPane.add(lblCedula);

		txtCedula = new JTextField();
		txtCedula.setText(this.guestRequestDTO.getCedula());
		txtCedula.setFont(new Font("Roboto", Font.PLAIN, 18));
		txtCedula.setBounds(560, 97, 285, 33);
		txtCedula.setBackground(Color.WHITE);
		txtCedula.setColumns(10);
		txtCedula.setBorder(BorderFactory.createEmptyBorder());
		contentPane.add(txtCedula);

		JSeparator separator_1_2_ = new JSeparator();
		separator_1_2_.setBounds(560, 132, 289, 2);
		separator_1_2_.setForeground(new Color(12, 138, 199));
		separator_1_2_.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(562, 145, 253, 24);
		lblNombre.setForeground(SystemColor.textInactiveText);
		lblNombre.setFont(new Font("Roboto Black", Font.PLAIN, 20));
		contentPane.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setText(this.guestRequestDTO.getFirsName());
		txtNombre.setFont(new Font("Roboto", Font.PLAIN, 18));
		txtNombre.setBounds(560, 175, 285, 33);
		txtNombre.setBackground(Color.WHITE);
		txtNombre.setColumns(10);
		txtNombre.setBorder(BorderFactory.createEmptyBorder());
		contentPane.add(txtNombre);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setBounds(560, 210, 289, 2);
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2);

		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(560, 220, 255, 24);
		lblApellido.setForeground(SystemColor.textInactiveText);
		lblApellido.setFont(new Font("Roboto Black", Font.PLAIN, 20));
		contentPane.add(lblApellido);

		txtApellido = new JTextField();
		txtApellido.setText(this.guestRequestDTO.getLastName());
		txtApellido.setFont(new Font("Roboto", Font.PLAIN, 18));
		txtApellido.setBounds(560, 247, 285, 33);
		txtApellido.setColumns(10);
		txtApellido.setBackground(Color.WHITE);
		txtApellido.setBorder(BorderFactory.createEmptyBorder());
		contentPane.add(txtApellido);

		JSeparator separator_1_2_1 = new JSeparator();
		separator_1_2_1.setBounds(560, 282, 289, 2);
		separator_1_2_1.setForeground(new Color(12, 138, 199));
		separator_1_2_1.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_1);

		JLabel lblFechaN = new JLabel("Fecha de Nacimiento");
		lblFechaN.setBounds(560, 296, 255, 24);
		lblFechaN.setForeground(SystemColor.textInactiveText);
		lblFechaN.setFont(new Font("Roboto Black", Font.PLAIN, 20));
		contentPane.add(lblFechaN);

		txtFechaN = new JDateChooser();
		txtFechaN.setDate(configurarFecha(this.guestRequestDTO.getDateOfBirth()));
		txtFechaN.setBounds(560, 328, 285, 35);
		txtFechaN.getCalendarButton().setIcon(new ImageIcon(UpdateHuespedView.class.getResource("/imagenes/icon-reservas.png")));
		txtFechaN.getCalendarButton().setBackground(SystemColor.textHighlight);
		txtFechaN.getCalendarButton().setBounds(267, 1, 21, 31);
		txtFechaN.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 11));
		txtFechaN.setDateFormatString("yyyy-MM-dd");
		txtFechaN.setBackground(Color.WHITE);
		txtFechaN.setFont(new Font("Roboto", Font.PLAIN, 18));
		contentPane.add(txtFechaN);

		JSeparator separator_1_2_2 = new JSeparator();
		separator_1_2_2.setBounds(560, 365, 289, 2);
		separator_1_2_2.setForeground(new Color(12, 138, 199));
		separator_1_2_2.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_2);

		JLabel lblNacionalidad = new JLabel("Nacionalidad");
		lblNacionalidad.setBounds(560, 376, 255, 24);
		lblNacionalidad.setForeground(SystemColor.textInactiveText);
		lblNacionalidad.setFont(new Font("Roboto Black", Font.PLAIN, 20));
		contentPane.add(lblNacionalidad);

		txtNacionalidad = new JComboBox<>();
		txtNacionalidad.addItem(guestRequestDTO.getNationality()); // PONEMOS POR DEFECTO LA ELECCÓN DE LA BD
		txtNacionalidad.setBounds(560, 410, 289, 36);
		txtNacionalidad.setBackground(SystemColor.text);
		txtNacionalidad.setFont(new Font("Roboto", Font.PLAIN, 18));
		cargarComboNacionalidad();
		contentPane.add(txtNacionalidad);

		JSeparator separator_1_2_3 = new JSeparator();
		separator_1_2_3.setBounds(560, 448, 288, 2);
		separator_1_2_3.setForeground(new Color(12, 138, 199));
		separator_1_2_3.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_3);

		JLabel lblTelefono = new JLabel("Teléfono");
		lblTelefono.setBounds(562, 463, 253, 24);
		lblTelefono.setForeground(SystemColor.textInactiveText);
		lblTelefono.setFont(new Font("Roboto Black", Font.PLAIN, 20));
		contentPane.add(lblTelefono);

		txtTelefono = new JTextField();
		txtTelefono.setText(this.guestRequestDTO.getPhone());
		txtTelefono.setFont(new Font("Roboto", Font.PLAIN, 18));
		txtTelefono.setBounds(560, 493, 285, 33);
		txtTelefono.setColumns(10);
		txtTelefono.setBackground(Color.WHITE);
		txtTelefono.setBorder(BorderFactory.createEmptyBorder());
		contentPane.add(txtTelefono);

		JSeparator separator_1_2_4 = new JSeparator();
		separator_1_2_4.setBounds(560, 528, 289, 2);
		separator_1_2_4.setForeground(new Color(12, 138, 199));
		separator_1_2_4.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_4);

		btnguardar = new JPanel();
		eventoGuardar();
		btnguardar.setBounds(723, 560, 122, 35);
		btnguardar.setLayout(null);
		btnguardar.setBackground(new Color(12, 138, 199));
		contentPane.add(btnguardar);
		btnguardar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		JLabel labelGuardar = new JLabel("GUARDAR");
		labelGuardar.setHorizontalAlignment(SwingConstants.CENTER);
		labelGuardar.setForeground(Color.WHITE);
		labelGuardar.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelGuardar.setBounds(0, 0, 122, 35);
		btnguardar.add(labelGuardar);

		//PANEL DEL LOGO
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 489, 634);
		panel.setBackground(new Color(12, 138, 199));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel imagenFondo = new JLabel("");
		imagenFondo.setBounds(0, 121, 479, 502);
		panel.add(imagenFondo);
		imagenFondo.setIcon(new ImageIcon(UpdateHuespedView.class.getResource("/imagenes/registro.png")));
		
		JLabel logo = new JLabel("");
		logo.setBounds(194, 39, 104, 107);
		panel.add(logo);
		logo.setIcon(new ImageIcon(UpdateHuespedView.class.getResource("/imagenes/Ha-100px.png")));

		btnexit = new JPanel();
		eventoSalir();
		btnexit.setLayout(null);
		btnexit.setBackground(new Color(12, 138, 199));
		btnexit.setBounds(857, 0, 53, 36);
		contentPane.add(btnexit);

		
		labelExit = new JLabel("X");
		labelExit.setForeground(Color.WHITE);
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
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

	private void eventoGuardar(){

	this.btnguardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (!(UpdateHuespedView.txtFechaN.getDate() != null &&
						txtCedula.getText() != null &&
						txtNombre.getText() != null &&
						txtApellido.getText() != null &&
						txtTelefono.getText() != null)) {

					JOptionPane.showMessageDialog(null, "Debes llenar todos los campos.");

				}else {
					modificarHuesped();
				}
			}
		});


	}
	private void eventoSalir() {

		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jFrameBusqueda.setVisible(true);
				setVisible(false);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnexit.setBackground(new Color(12, 138, 199));
				labelExit.setForeground(Color.white);
			}
		});
	}

	private Date configurarFecha(LocalDate fecha) {

		//Permite cambiar el formato de un LocalDate a Date
		return Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	private void cargarComboNacionalidad() {

		// SE CARGAN TODAS LAS NACIONALIDADES EXCEPTO LA QUE YA POSEÉ COMO REGISTRADA
		nationalityController.getAllNationality().forEach(
				nationality ->{
					if(!nationality.equals(guestRequestDTO.getNationality())) {
						txtNacionalidad.addItem(nationality);
					}
		});

	}

	private void modificarHuesped() {

		try {
			//Permite cambiar el formato de un Date a un LocalDate
			Instant instantDateBirth = txtFechaN.getDate().toInstant();

			LocalDate dateBirth = instantDateBirth.atZone(ZoneId.systemDefault()).toLocalDate();

			NationalityRequestDTO nationalityRequestDTO = (NationalityRequestDTO) txtNacionalidad.getSelectedItem();

			guestRequestDTO.setCedula(txtCedula.getText());
			guestRequestDTO.setFirsName(txtNombre.getText());
			guestRequestDTO.setLastName(txtApellido.getText());
			guestRequestDTO.setDateOfBirth(dateBirth);
			guestRequestDTO.setPhone(txtTelefono.getText());
			guestRequestDTO.setNationality(nationalityRequestDTO);

			if(this.guestController.updateGuest(this.guestRequestDTO))
				JOptionPane.showMessageDialog(this,  " Item actualizado con éxito!");
			else
				JOptionPane.showMessageDialog(this,  " Ha ocurrido un error inesperado");

		}catch (ArrayIndexOutOfBoundsException ignored){}

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
