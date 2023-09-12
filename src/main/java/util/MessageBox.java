package util;

import javax.swing.*;
import java.awt.*;

/**
 * Clase que maneja los mensajes de error e informativos.
 */
public final class MessageBox {

    /**
     * Permite darle estilo y manejo al panel de informativo con opciones
     * @param parentComponent //contenedor donde se va a mostrar el mensaje
     * @param message
     * @param titulo
     * @return un entero que permite obtener desde donde se ejecuta, la opción seleccionada
     */
    public static int messageTypeOption(Component parentComponent, String message, String titulo) {

        return JOptionPane.showConfirmDialog(parentComponent,
                     componenteMessage(message),
                    titulo,
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE,
                    new ImageIcon(MessageBox.class.getResource("/imagenes/Ha-100px.png")));
    }

    /**
     * Permite validar si la opción elegida es "Ok"
     * @param value //valor obtenido de mensaje con opciones
     * @return un booleano que retorna true si la opción elegida es "Ok"
     */
    public static Boolean validateOption(int value) {

        return (value == JOptionPane.YES_OPTION);
    }

    /**
     * Permite darle estilo al Panel informativo
     * @param parentComponent //contenedor donde se va a mostrar el mensaje
     * @param message
     * @param titulo
     * @param messageType //Tipo del mensaje "Error", "Informativo" y "Alerta"
     */
    public static void messageBasic(Component parentComponent, String message, String titulo, int messageType) {

        JOptionPane.showMessageDialog(parentComponent,
                                                        componenteMessage(message),
                                                        titulo,
                                                        messageType);

    }

    /**
     * Permite darle estilo al Panel informativo de Error
     * @param parentComponent //contenedor donde se va a mostrar el mensaje
     * @param message
     */
    public static void messageBasic(Component parentComponent, String message) {

        JOptionPane.showMessageDialog(parentComponent,
                                                        componenteMessage(message),
                                                        "Error",
                                                        JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Permite crear un JLabel con estilos personalizados par uso en los Paneles informativos
     * @param message // mensaje que se le agregara al JLabel
     * @return JLabel con los estilos definidos
     */
    private static JLabel componenteMessage(String message) {

        JLabel lblMessage = new JLabel(message);
        lblMessage.setForeground(new Color(54, 55, 83));
        lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
        lblMessage.setFont(new Font("Roboto", Font.BOLD, 14));

        return lblMessage;
    }
}
