package views;

import DTO.ReservationRequestDTO;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import controller.ReservationController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.List;


@SuppressWarnings("serial")
public class UpdateReservasView extends JFrame {

	private final JPanel contentPane;
	public static JDateChooser txtFechaEntrada;
	public static JDateChooser txtFechaSalida;
	public static JComboBox<String> txtFormaPago;
	int xMouse, yMouse;
	private final JPanel btnGuardar;
	private final JPanel btnexit;
	private final JLabel labelExit;
	private final JLabel lblGuardar;

	//
	private final ReservationController reservationController;
	private  final ReservationRequestDTO reservationRequestDTO;

	private final Busqueda jFrameBusqueda;


	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public UpdateReservasView(Busqueda jFrameBusqueda, ReservationRequestDTO reservationRequestDTO, ReservationController reservationController) {

		super("Editar Reserva");
		setIconImage(Toolkit.getDefaultToolkit().getImage(UpdateReservasView.class.getResource("/imagenes/aH-40px.png")));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 910, 560);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);


		//SE OBTIENEN LOS DATOS PARA AUTOCARGAR EL FORMULARIO
		this.reservationRequestDTO = reservationRequestDTO;
		this.reservationController = reservationController;
		this.jFrameBusqueda = jFrameBusqueda;
		

		// PANEL DEL FORMULARIO
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 910, 560);
		contentPane.add(panel);
		panel.setLayout(null);
		
		// Código que crea los elementos de la interfáz gráfica

		JLabel lblTitulo = new JLabel("Actualizar Datos");
		lblTitulo.setBounds(109, 80, 250, 42);
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto", Font.BOLD, 24));
		panel.add(lblTitulo);


		JLabel lblCheckIn = new JLabel("Fecha Check In");
		lblCheckIn.setForeground(SystemColor.textInactiveText);
		lblCheckIn.setBounds(68, 176, 169, 24);
		lblCheckIn.setFont(new Font("Roboto Black", Font.PLAIN, 20));
		panel.add(lblCheckIn);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(SystemColor.textHighlight);
		separator_1_2.setBounds(68, 235, 289, 2);
		separator_1_2.setBackground(SystemColor.textHighlight);
		panel.add(separator_1_2);

		txtFechaEntrada = new JDateChooser();
		txtFechaEntrada.setDate(configurarFecha(reservationRequestDTO.getCheckIn()));
		txtFechaEntrada.getCalendarButton().setBackground(SystemColor.textHighlight);
		txtFechaEntrada.getCalendarButton().setIcon(new ImageIcon(UpdateReservasView.class.getResource("/imagenes/icon-reservas.png")));
		txtFechaEntrada.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 12));
		txtFechaEntrada.setBounds(68, 201, 289, 35);
		txtFechaEntrada.getCalendarButton().setBounds(268, 0, 21, 33);
		txtFechaEntrada.setBackground(Color.WHITE);
		txtFechaEntrada.setBorder(new LineBorder(SystemColor.window));
		txtFechaEntrada.setDateFormatString("yyyy-MM-dd");
		txtFechaEntrada.setFont(new Font("Roboto", Font.PLAIN, 18));
		panel.add(txtFechaEntrada);

		
		JLabel lblCheckOut = new JLabel("Fecha Check Out");
		lblCheckOut.setForeground(SystemColor.textInactiveText);
		lblCheckOut.setBounds(68, 261, 187, 24);
		lblCheckOut.setFont(new Font("Roboto Black", Font.PLAIN, 20));
		panel.add(lblCheckOut);

		txtFechaSalida = new JDateChooser();
		txtFechaSalida.setDate(configurarFecha(reservationRequestDTO.getCheckOut()));
		txtFechaSalida.getCalendarButton().setIcon(new ImageIcon(UpdateReservasView.class.getResource("/imagenes/icon-reservas.png")));
		txtFechaSalida.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 11));
		txtFechaSalida.setBounds(68, 286, 289, 35);
		txtFechaSalida.getCalendarButton().setBounds(267, 1, 21, 31);
		txtFechaSalida.setBackground(Color.WHITE);
		txtFechaSalida.setFont(new Font("Roboto", Font.PLAIN, 18));
		txtFechaSalida.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				//Activa el evento, después del usuario seleccionar las fechas se debe calcular el valor de la reserva
			}
		});
		txtFechaSalida.setDateFormatString("yyyy-MM-dd");
		txtFechaSalida.getCalendarButton().setBackground(SystemColor.textHighlight);
		txtFechaSalida.setBorder(new LineBorder(new Color(255, 255, 255), 0));
		panel.add(txtFechaSalida);

		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setForeground(SystemColor.textHighlight);
		separator_1_1.setBounds(68, 321, 289, 11);
		separator_1_1.setBackground(SystemColor.textHighlight);
		panel.add(separator_1_1);

		JLabel lblFormaPago = new JLabel("Forma de Pago");
		lblFormaPago.setForeground(SystemColor.textInactiveText);
		lblFormaPago.setBounds(68, 352, 187, 24);
		lblFormaPago.setFont(new Font("Roboto Black", Font.PLAIN, 20));
		panel.add(lblFormaPago);

		JSeparator separator_1_3 = new JSeparator();
		separator_1_3.setForeground(SystemColor.textHighlight);
		separator_1_3.setBackground(SystemColor.textHighlight);
		separator_1_3.setBounds(68, 425, 289, 2);
		panel.add(separator_1_3);

		txtFormaPago = new JComboBox<>();
		txtFormaPago.addItem(reservationRequestDTO.getMethodPayment());
		txtFormaPago.setBounds(68, 387, 289, 38);
		txtFormaPago.setBackground(SystemColor.text);
		txtFormaPago.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		txtFormaPago.setFont(new Font("Roboto", Font.PLAIN, 16));
		cargarCombo();
		//txtFormaPago.setModel(new DefaultComboBoxModel<>(new String[] {"Tarjeta de Crédito", "Tarjeta de Débito", "Dinero en efectivo"}));
		panel.add(txtFormaPago);


		//PANEL DEL LOGO
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(428, 0, 482, 560);
		panel_1.setBackground(new Color(12, 138, 199));
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel logo = new JLabel("");
		logo.setBounds(197, 68, 104, 107);
		panel_1.add(logo);
		logo.setIcon(new ImageIcon(UpdateReservasView.class.getResource("/imagenes/Ha-100px.png")));
		
		JLabel imagenFondo = new JLabel("");
		imagenFondo.setBounds(0, 140, 500, 409);
		panel_1.add(imagenFondo);
		imagenFondo.setBackground(Color.WHITE);
		imagenFondo.setIcon(new ImageIcon(UpdateReservasView.class.getResource("/imagenes/reservas-img-3.png")));

												
		// Componentes para dejar la interfaz con estilo Material Design
		btnexit = new JPanel();
		eventoSalir();
		btnexit.setLayout(null);
		btnexit.setBackground(new Color(12, 138, 199));
		btnexit.setBounds(429, 0, 53, 36);
		panel_1.add(btnexit);
		
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
		header.setBackground(Color.WHITE);
		panel.add(header);

		btnGuardar = new JPanel();
		eventoGuardar();
		btnGuardar.setLayout(null);
		btnGuardar.setBackground(SystemColor.textHighlight);
		btnGuardar.setBounds(238, 493, 122, 35);
		panel.add(btnGuardar);
		btnGuardar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		lblGuardar = new JLabel("GUARDAR");
		lblGuardar.setHorizontalAlignment(SwingConstants.CENTER);
		lblGuardar.setForeground(Color.WHITE);
		lblGuardar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblGuardar.setBounds(0, 0, 122, 35);
		btnGuardar.add(lblGuardar);

	}

	private void eventoGuardar(){

		this.btnGuardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (!(UpdateReservasView.txtFechaEntrada.getDate() != null && UpdateReservasView.txtFechaSalida.getDate() != null))
					JOptionPane.showMessageDialog(null, "Debes llenar todos los campos.");

				else if(UpdateReservasView.txtFechaEntrada.getDate().after(UpdateReservasView.txtFechaSalida.getDate()))
					JOptionPane.showMessageDialog(null, "Error en las fechas");

				else
					modificarReserva();
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

	private void cargarCombo() {

		String eleccionBase = reservationRequestDTO.getMethodPayment();
		List<String> eleccionCombo = new ArrayList<>(){{
			add("Tarjeta de Crédito");
			add("Tarjeta de Débito");
			add("Dinero en efectivo");
		}};

		eleccionCombo.forEach(eleccion ->{
			if(!eleccion.equals(eleccionBase))
				txtFormaPago.addItem(eleccion);
		});
	}
	private Date configurarFecha(LocalDate fecha) {

		//Permite cambiar el formato de un LocalDate a Date
		return Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	private void modificarReserva() {

		try {
			//Permite cambiar el formato de un Date a un LocalDate
			Instant instantCheckIn = txtFechaEntrada.getDate().toInstant();
			Instant instantCheckOut= txtFechaSalida.getDate().toInstant();

			LocalDate checkIn = instantCheckIn.atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate checkOut = instantCheckOut.atZone(ZoneId.systemDefault()).toLocalDate();

			this.reservationRequestDTO.setCheckIn(checkIn);
			this.reservationRequestDTO.setCheckOut(checkOut);
			this.reservationRequestDTO.setMethodPayment(String.valueOf(txtFormaPago.getSelectedItem()));

			if(this.reservationController.updateReservation(this.reservationRequestDTO))
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
