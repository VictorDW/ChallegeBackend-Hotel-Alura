package views;

import DTO.ReservationRequestDTO;
import com.toedter.calendar.JDateChooser;
import service.util.ConfigureDates;
import service.util.DataReservationTemporary;
import util.MessageBox;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@SuppressWarnings("serial")
public class ReservasView extends JFrame {

	private final JPanel contentPane;
	private static JTextField txtValor;
	private static JDateChooser txtFechaEntrada;
	private static JDateChooser txtFechaSalida;
	private static JComboBox<String> txtFormaPago;
	int xMouse, yMouse;
	private final JLabel labelExit;
	private final JLabel labelAtras;
	private final JPanel btnExit;
	private final JPanel btnAtras;
	private final JPanel btnSiguiente;

	//
	private ReservationRequestDTO reservationRequestDTO;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReservasView frame = new ReservasView();
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
	public ReservasView() {
		super("Reserva");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ReservasView.class.getResource("/imagenes/aH-40px.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 910, 560);
		contentPane.add(panel);
		panel.setLayout(null);
		
		// Código que crea los elementos de la interfáz gráfica

		JLabel lblTitulo = new JLabel("Sistema de Reserva");
		lblTitulo.setBounds(0, 60, 428, 42);
		lblTitulo.setForeground(new Color(54, 55, 83));
		lblTitulo.setFont(new Font("Roboto", Font.BOLD, 26));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblTitulo);


		//CAMPO DEL VALOR DE LA RESERVA,
		// DEBE ESTAR ARRIBA PARA NO GENERAR NULLPOINTEXCEPTION EN LOS EVENTOS DE LAS FECHAS

		JLabel lblValor = new JLabel("Valor de la Reserva");
		lblValor.setForeground(new Color(0, 0, 0, 180));
		lblValor.setBounds(72, 303, 196, 24);
		lblValor.setFont(new Font("Roboto Black", Font.PLAIN, 20));
		panel.add(lblValor);

		txtValor = new JTextField();
		txtValor.setBackground(SystemColor.text);
		txtValor.setHorizontalAlignment(SwingConstants.RIGHT);
		txtValor.setBounds(68, 328, 289, 33);
		txtValor.setEditable(false);
		txtValor.setFont(new Font("Roboto Black", Font.PLAIN, 20));
		txtValor.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		panel.add(txtValor);
		txtValor.setColumns(10);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(68, 362, 289, 2);
		separator_1.setForeground(new Color(54, 55, 83));
		separator_1.setBackground(new Color(54, 55, 83));
		panel.add(separator_1);

		//FECHA DE INGRESO

		JLabel lblCheckIn = new JLabel("Check In");
		lblCheckIn.setForeground(new Color(0, 0, 0, 180));
		lblCheckIn.setBounds(68, 136, 169, 24);
		lblCheckIn.setFont(new Font("Roboto Black", Font.PLAIN, 20));
		panel.add(lblCheckIn);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setBounds(68, 196, 289, 2);
		separator_1_2.setForeground(new Color(54, 55, 83));
		separator_1_2.setBackground(new Color(54, 55, 83));
		panel.add(separator_1_2);

		txtFechaEntrada = new JDateChooser();
		txtFechaEntrada.setDate(new Date());
		eventoFechaCheckIn();
		txtFechaEntrada.getCalendarButton().setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/icon-reservas.png")));
		txtFechaEntrada.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 12));
		txtFechaEntrada.getCalendarButton().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		txtFechaEntrada.getCalendarButton().setBackground(new Color(29, 27, 49));
		txtFechaEntrada.getCalendarButton().setBounds(268, 0, 21, 33);
		txtFechaEntrada.setBounds(68, 161, 289, 35);
		txtFechaEntrada.setBackground(Color.WHITE);
		txtFechaEntrada.setBorder(new LineBorder(SystemColor.window));
		txtFechaEntrada.setDateFormatString("yyyy-MM-dd");
		txtFechaEntrada.setFont(new Font("Roboto", Font.PLAIN, 18));
		panel.add(txtFechaEntrada);

		//FECHA DE SALIDA

		JLabel lblCheckOut = new JLabel("Check Out");
		lblCheckOut.setForeground(new Color(0, 0, 0, 180));
		lblCheckOut.setBounds(68, 221, 187, 24);
		lblCheckOut.setFont(new Font("Roboto Black", Font.PLAIN, 20));
		panel.add(lblCheckOut);

		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setForeground(new Color(54, 55, 83));
		separator_1_1.setBounds(68, 282, 289, 11);
		separator_1_1.setBackground(new Color(54, 55, 83));
		panel.add(separator_1_1);

		txtFechaSalida = new JDateChooser();
		eventoFechaCheckOut();
		txtFechaSalida.getCalendarButton().setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/icon-reservas.png")));
		txtFechaSalida.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 11));
		txtFechaSalida.getCalendarButton().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		txtFechaSalida.getCalendarButton().setBackground(new Color(29, 27, 49));
		txtFechaSalida.getCalendarButton().setBounds(267, 1, 21, 31);
		txtFechaSalida.setBounds(68, 246, 289, 35);
		txtFechaSalida.setBackground(Color.WHITE);
		txtFechaSalida.setFont(new Font("Roboto", Font.PLAIN, 18));
		txtFechaSalida.setDateFormatString("yyyy-MM-dd");
		txtFechaSalida.setBorder(new LineBorder(new Color(255, 255, 255), 0));
		panel.add(txtFechaSalida);


		//CHECK LIST DE LA FORMA DE PAGO

		JLabel lblFormaPago = new JLabel("Forma de Pago");
		lblFormaPago.setForeground(new Color(0, 0, 0, 180));
		lblFormaPago.setBounds(68, 382, 187, 24);
		lblFormaPago.setFont(new Font("Roboto Black", Font.PLAIN, 20));
		panel.add(lblFormaPago);

		JSeparator separator_1_3 = new JSeparator();
		separator_1_3.setForeground(new Color(54, 55, 83));
		separator_1_3.setBackground(new Color(54, 55, 83));
		separator_1_3.setBounds(68, 456, 289, 2);
		panel.add(separator_1_3);

		txtFormaPago = new JComboBox<>();
		txtFormaPago.setBounds(68, 417, 289, 38);
		txtFormaPago.setBackground(SystemColor.text);
		txtFormaPago.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		txtFormaPago.setFont(new Font("Roboto", Font.PLAIN, 20));
		cargarCombo();
		//txtFormaPago.setModel(new DefaultComboBoxModel<>(new String[] {"Tarjeta de Crédito", "Tarjeta de Débito", "Dinero en efectivo"}));
		panel.add(txtFormaPago);


		JPanel panel_1 = new JPanel();
		panel_1.setBounds(428, 0, 482, 560);
		panel_1.setBackground(new Color(54, 55, 83));
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel logo = new JLabel("");
		logo.setBounds(197, 68, 104, 107);
		panel_1.add(logo);
		logo.setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/Ha-100px.png")));
		
		JLabel imagenFondo = new JLabel("");
		imagenFondo.setBounds(0, 140, 500, 409);
		panel_1.add(imagenFondo);
		imagenFondo.setBackground(Color.WHITE);
		imagenFondo.setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/reservas-img-3.png")));

												
		// Componentes para dejar la interfaz con estilo Material Design
		btnExit = new JPanel();
		eventoSalir();
		btnExit.setLayout(null);
		btnExit.setBackground(new Color(54, 55, 83));
		btnExit.setBounds(429, 0, 53, 36);
		panel_1.add(btnExit);
		
		labelExit = new JLabel("X");
		labelExit.setForeground(Color.WHITE);
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
		header.setBackground(Color.WHITE);
		panel.add(header);
		
		btnAtras = new JPanel();
		eventoAtras();
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));

		btnSiguiente = new JPanel();
		eventoViewHuesped();
		btnSiguiente.setLayout(null);
		btnSiguiente.setBackground(new Color(29, 27, 49));
		btnSiguiente.setBounds(238, 493, 122, 35);
		panel.add(btnSiguiente);
		btnSiguiente.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));

		JLabel lblSiguiente = new JLabel("Siguiente");
		lblSiguiente.setHorizontalAlignment(SwingConstants.CENTER);
		lblSiguiente.setForeground(Color.WHITE);
		lblSiguiente.setFont(new Font("Roboto", Font.BOLD, 18));
		lblSiguiente.setBounds(0, 0, 122, 35);
		btnSiguiente.add(lblSiguiente);
	}

		private void eventoSalir() {
			btnExit.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					MenuPrincipal principal = new MenuPrincipal();
					principal.setVisible(true);
					dispose();
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					btnExit.setBackground(Color.red);
					labelExit.setForeground(Color.white);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					btnExit.setBackground(new Color(54, 55, 83));
					labelExit.setForeground(Color.white);
				}
			});
		}
		private void eventoAtras() {
			btnAtras.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					MenuUsuario usuario = new MenuUsuario();
					usuario.setVisible(true);
					dispose();
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					btnAtras.setBackground(new Color(54, 55, 83));
					labelAtras.setForeground(Color.white);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					btnAtras.setBackground(Color.white);
					labelAtras.setForeground(Color.black);
				}
			});
		}
		private void eventoViewHuesped() {
			btnSiguiente.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseEntered(MouseEvent e) {
					btnSiguiente.setBackground(new Color(54, 55, 83));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					btnSiguiente.setBackground(new Color(29, 27, 49));
				}

				@Override
				public void mouseClicked(MouseEvent e) {

					if (!(ReservasView.txtFechaEntrada.getDate() != null && ReservasView.txtFechaSalida.getDate() != null)) {

						MessageBox.messageBasic(contentPane,"Debes llenar todos los campos");

					} else if (ConfigureDates.validateDateOrder() && ConfigureDates.validateDateCheckIn(LocalDate.now())) {

						missingReserveData();
						RegistroHuesped registro = new RegistroHuesped(ReservasView.this, reservationRequestDTO);
						registro.setVisible(true);

					} else {
						MessageBox.messageBasic(contentPane, "Error en las fechas");
					}
				}
			});
		}
		private void eventoFechaCheckIn() {

			//PERMITE CAPTURAR CUALQUIER EVENTO QUE SUCEDA, SIN EMBARGO LE ESPECIFICAMOS QUE SOLO ESCUCHE LOS DE FECHA
			txtFechaEntrada.addPropertyChangeListener("date", evt -> {

					if ("date".equals(evt.getPropertyName())) {

						try {
							//VALIDAMOS QUE SI LA FECHA DE SALIDA NO ESTA AÚN, TOME LA FECHA ACTUAL
							ConfigureDates.mapperDataToLocalDate(txtFechaEntrada.getDate(),
																						txtFechaSalida.getDate() == null ?
																							new Date() :
																							txtFechaSalida.getDate());

							//VERIFICAMOS QUE EL ORDEN DE LAS FECHAS SEA CORRECTO Y QUE LA FECHA DE ENTRADA NO SEA MENOR A LA FECHA ACTUAL
							if (ConfigureDates.validateDateOrder() && ConfigureDates.validateDateCheckIn(LocalDate.now())) {

								storeReservationData();

							}else{
								MessageBox.messageBasic(contentPane, "Error en las fechas");
								txtValor.setText("0"+" COP");
							}
						}catch (NullPointerException ignored){}
					}
				});
			}
		private void eventoFechaCheckOut() {

		//PERMITE CAPTURAR CUALQUIER EVENTO QUE SUCEDA, SIN EMBARGO LE ESPECIFICAMOS QUE SOLO ESCUCHE LOS DE FECHA
			txtFechaSalida.addPropertyChangeListener("date", evt -> {

				if ("date".equals(evt.getPropertyName())) {

					try {
						ConfigureDates.mapperDataToLocalDate(txtFechaEntrada.getDate(), txtFechaSalida.getDate());

						//VERIFICAMOS QUE EL ORDEN DE LAS FECHAS SEA CORRECTO Y QUE LA FECHA DE SALIDA NO SEA MENOR A LA FECHA ACTUAL
						if (ConfigureDates.validateDateOrder() && ConfigureDates.validateDateCheckOut(LocalDate.now())) {

							storeReservationData();

						}else {
							MessageBox.messageBasic(contentPane, "Error en las fechas");
							txtValor.setText("0"+" COP");
						}
					}catch (NullPointerException ignored){}

				}
			});
		}
		private void storeReservationData() {

			this.reservationRequestDTO = DataReservationTemporary.CreateReservationRequestDTO();

			txtValor.setText(this.reservationRequestDTO.getCost().toString() + " COP (" +
									ConfigureDates.getDaysReservation() + " noches)");
		}
		private void missingReserveData() {
			this.reservationRequestDTO.setReservationCod(DataReservationTemporary.createReservationCod());
			this.reservationRequestDTO.setMethodPayment(String.valueOf(txtFormaPago.getSelectedItem()));
		}
		private void cargarCombo() {
			List<String> eleccionCombo = List.of("Tarjeta de Crédito","Tarjeta de Débito","Dinero en efectivo");

			eleccionCombo.forEach(eleccion -> txtFormaPago.addItem(eleccion));
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
