package views;

import DTO.GuestRequestDTO;
import DTO.NationalityRequestDTO;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import controller.GuestController;
import controller.NationalityController;
import service.util.ConfigureDates;

import javax.sound.midi.Soundbank;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

@SuppressWarnings("serial")
public class UpdateHuespedView extends JFrame {

	private final JPanel contentPane;
	private final JTextField txtCedula;
	private final JTextField txtNombre;
	private final JTextField txtApellido;
	private final JTextField txtTelefono;
	private final JDateChooser txtFechaN;
	private final JTextFieldDateEditor editorFecha;
	private final JComboBox<NationalityRequestDTO> txtNacionalidad;
	private final JLabel labelExit;
	private final JPanel btnExit;
	private final JPanel btnGuardar;
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
		lblTitulo.setForeground(new Color(54, 55, 83));
		lblTitulo.setFont(new Font("Roboto Black", Font.BOLD, 24));
		contentPane.add(lblTitulo);

		JLabel lblCedula = new JLabel("Cedula");
		lblCedula.setBounds(562, 70, 253, 24);
		lblCedula.setForeground(new Color(0, 0, 0, 180));
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
		separator_1_2_.setForeground(new Color(54, 55, 83));
		separator_1_2_.setBackground(new Color(54, 55, 83));
		contentPane.add(separator_1_2_);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(562, 145, 253, 24);
		lblNombre.setForeground(new Color(0, 0, 0, 180));
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
		separator_1_2.setForeground(new Color(54, 55, 83));
		separator_1_2.setBackground(new Color(54, 55, 83));
		contentPane.add(separator_1_2);

		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(560, 220, 255, 24);
		lblApellido.setForeground(new Color(0, 0, 0, 180));
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
		separator_1_2_1.setForeground(new Color(54, 55, 83));
		separator_1_2_1.setBackground(new Color(54, 55, 83));
		contentPane.add(separator_1_2_1);

		JLabel lblFechaN = new JLabel("Fecha de Nacimiento");
		lblFechaN.setBounds(560, 296, 255, 24);
		lblFechaN.setForeground(new Color(0, 0, 0, 180));
		lblFechaN.setFont(new Font("Roboto Black", Font.PLAIN, 20));
		contentPane.add(lblFechaN);

		txtFechaN = new JDateChooser();
		editorFecha = (JTextFieldDateEditor) txtFechaN.getDateEditor();
		txtFechaN.setDate(ConfigureDates.mapperLocalDateToData(this.guestRequestDTO.getDateOfBirth()));
		txtFechaN.setBounds(560, 328, 285, 35);
		txtFechaN.getCalendarButton().setIcon(new ImageIcon(UpdateHuespedView.class.getResource("/imagenes/icon-reservas.png")));
		txtFechaN.getCalendarButton().setBackground(new Color(29, 27, 49));
		txtFechaN.getCalendarButton().setBounds(267, 1, 21, 31);
		txtFechaN.getCalendarButton().setCursor(new Cursor(Cursor.HAND_CURSOR));
		txtFechaN.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 11));
		txtFechaN.setDateFormatString("yyyy-MM-dd");
		txtFechaN.setBackground(Color.WHITE);
		txtFechaN.setFont(new Font("Roboto", Font.PLAIN, 18));
		contentPane.add(txtFechaN);

		JSeparator separator_1_2_2 = new JSeparator();
		separator_1_2_2.setBounds(560, 365, 289, 2);
		separator_1_2_2.setForeground(new Color(54, 55, 83));
		separator_1_2_2.setBackground(new Color(54, 55, 83));
		contentPane.add(separator_1_2_2);

		JLabel lblNacionalidad = new JLabel("Nacionalidad");
		lblNacionalidad.setBounds(560, 376, 255, 24);
		lblNacionalidad.setForeground(new Color(0, 0, 0, 180));
		lblNacionalidad.setFont(new Font("Roboto Black", Font.PLAIN, 20));
		contentPane.add(lblNacionalidad);

		txtNacionalidad = new JComboBox<>();
		txtNacionalidad.addItem(guestRequestDTO.getNationality()); // PONEMOS POR DEFECTO LA ELECCÓN DE LA BD
		txtNacionalidad.setBounds(560, 410, 289, 36);
		txtNacionalidad.setForeground(new Color(54, 55, 83));
		txtNacionalidad.setBackground(SystemColor.text);
		txtNacionalidad.setFont(new Font("Roboto", Font.PLAIN, 20));
		cargarComboNacionalidad();
		contentPane.add(txtNacionalidad);

		JSeparator separator_1_2_3 = new JSeparator();
		separator_1_2_3.setBounds(560, 448, 288, 2);
		separator_1_2_3.setForeground(new Color(54, 55, 83));
		separator_1_2_3.setBackground(new Color(54, 55, 83));
		contentPane.add(separator_1_2_3);

		JLabel lblTelefono = new JLabel("Teléfono");
		lblTelefono.setBounds(562, 463, 253, 24);
		lblTelefono.setForeground(new Color(0, 0, 0, 180));
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
		separator_1_2_4.setForeground(new Color(54, 55, 83));
		separator_1_2_4.setBackground(new Color(54, 55, 83));
		contentPane.add(separator_1_2_4);

		btnGuardar = new JPanel();
		eventoGuardar();
		btnGuardar.setBounds(723, 560, 122, 35);
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

		//PANEL DEL LOGO
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 489, 634);
		panel.setBackground(new Color(54, 55, 83));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel imagenFondo = new JLabel("");
		imagenFondo.setBounds(0, 121, 479, 502);
		panel.add(imagenFondo);
		imagenFondo.setIcon(new ImageIcon(UpdateHuespedView.class.getResource("/imagenes/updateHuesped.png")));
		
		JLabel logo = new JLabel("");
		logo.setBounds(194, 39, 104, 107);
		panel.add(logo);
		logo.setIcon(new ImageIcon(UpdateHuespedView.class.getResource("/imagenes/Ha-100px.png")));

		btnExit = new JPanel();
		eventoSalir();
		btnExit.setLayout(null);
		btnExit.setBackground(Color.WHITE);
		btnExit.setBounds(857, 0, 53, 36);
		contentPane.add(btnExit);

		
		labelExit = new JLabel("X");
		labelExit.setForeground(Color.BLACK);
		labelExit.setBounds(0, 0, 53, 36);
		btnExit.add(labelExit);
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
		this.btnGuardar.addMouseListener(new MouseAdapter() {
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
						ConfigureDates.mapperDataToLocalDate(txtFechaN.getDate());
					}catch (NullPointerException ignore){}

						if (txtFechaN.getDate() == null ||
								txtCedula.getText().isEmpty() ||
								txtNombre.getText().isEmpty() ||
								txtApellido.getText().isEmpty() ||
								txtTelefono.getText().isEmpty()){

							JOptionPane.showMessageDialog(contentPane,
																			"Debes llenar todos los campos.",
																			"Error",
																			JOptionPane.ERROR_MESSAGE);

						}else if(ConfigureDates.isUnderAge()) {

							JOptionPane.showMessageDialog(contentPane,
																			"El huesped debe ser mayor de edad",
																			"Error",
																			JOptionPane.ERROR_MESSAGE);
							editorFecha.setText("");
						}else {
							modificarHuesped();
						}
				}
			});
	}
	private void eventoSalir() {

		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				jFrameBusqueda.setVisible(true);
				setVisible(false);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnExit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnExit.setBackground(Color.WHITE);
				labelExit.setForeground(Color.BLACK);
			}
		});
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

			NationalityRequestDTO nationalityRequestDTO = (NationalityRequestDTO) txtNacionalidad.getSelectedItem();

			guestRequestDTO.setCedula(txtCedula.getText());
			guestRequestDTO.setFirsName(txtNombre.getText());
			guestRequestDTO.setLastName(txtApellido.getText());
			guestRequestDTO.setDateOfBirth(ConfigureDates.getDateOfBirth());
			guestRequestDTO.setPhone(txtTelefono.getText());
			guestRequestDTO.setNationality(nationalityRequestDTO);

			if(this.guestController.updateGuest(this.guestRequestDTO))
				JOptionPane.showMessageDialog(contentPane,
																" Huesped actualizado con éxito!",
																"Actualización correcta",
																JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(contentPane,
																" Ha ocurrido un error inesperado",
																"Error",
																JOptionPane.ERROR_MESSAGE);

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
