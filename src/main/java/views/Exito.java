package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class Exito extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final ReservasView jFrameRegistrarReserva;
	private final RegistroHuesped jFrameRegistrarHuesped;

	/**
	 * Create the dialog.
	 */
	public Exito(ReservasView jFrameRegistrarReserva, RegistroHuesped jFrameRegistrarHuesped) {

		setIconImage(Toolkit.getDefaultToolkit().getImage(Exito.class.getResource("/imagenes/aH-40px.png")));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 394, 226);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.control);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		contentPanel.setLayout(null);

		//

		this.jFrameRegistrarReserva = jFrameRegistrarReserva;
		this.jFrameRegistrarHuesped = jFrameRegistrarHuesped;

			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(Exito.class.getResource("/imagenes/Ha-100px.png")));
			lblNewLabel.setBounds(123, 11, 100, 100);
			contentPanel.add(lblNewLabel);

		{
			JLabel lblNewLabel_1 = new JLabel("Datos guardados satisfactoriamente");
			lblNewLabel_1.setForeground(new Color(54, 55, 83));
			lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 18));
			lblNewLabel_1.setBounds(27, 122, 322, 21);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setBackground(new Color(54, 55, 83));
				okButton.setFont(new Font("Roboto", Font.PLAIN, 16));
				okButton.setBorder(javax.swing.BorderFactory.createEmptyBorder());
				okButton.setForeground(Color.white);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						jFrameRegistrarHuesped.setVisible(false);
						jFrameRegistrarReserva.setVisible(true);
						setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setBackground(new Color(54, 55, 83));
				cancelButton.setFont(new Font("Roboto", Font.PLAIN, 16));
				cancelButton.setBorder(javax.swing.BorderFactory.createEmptyBorder());
				cancelButton.setForeground(Color.white);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
