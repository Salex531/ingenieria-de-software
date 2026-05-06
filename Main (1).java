import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// Clase Usuario
class Usuario {

    // ENCAPSULAMIENTO
    private String usuario;
    private String contrasena;

    public Usuario(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    // ABSTRACCIÓN
    public boolean validarAcceso(String usuarioIngresado, String contrasenaIngresada) {
        return usuario.equals(usuarioIngresado) &&
               contrasena.equals(contrasenaIngresada);
    }
}

// Clase principal
public class Main {


    static ArrayList<Usuario> usuarios = new ArrayList<>();

    public static void main(String[] args) {

        usuarios.add(new Usuario("admin", "12345"));
        usuarios.add(new Usuario("juan", "54321"));
        usuarios.add(new Usuario("invitado", "4321"));


        ventanaInicio();
    }

    // Primera ventana
    public static void ventanaInicio() {

        JFrame ventana = new JFrame("Sistema");
        ventana.setSize(400, 250);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);

        JLabel texto = new JLabel("BIENVENIDO");
        texto.setFont(new Font("Arial", Font.BOLD, 24));

        JButton boton = new JButton("Ingresar Usuario");

        c.gridx = 0;
        c.gridy = 0;
        ventana.add(texto, c);

        c.gridy = 1;
        ventana.add(boton, c);

        boton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventana.dispose();
                ventanaLogin();
            }
        });

        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

    // Segunda ventana
    public static void ventanaLogin() {

        JFrame login = new JFrame("Ingreso");
        login.setSize(400, 250);
        login.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel lblUsuario = new JLabel("Usuario:");
        JTextField txtUsuario = new JTextField();

        JLabel lblContrasena = new JLabel("Contraseña:");
        JPasswordField txtContrasena = new JPasswordField();

        // CAMBIO: botón ahora dice Ingresar Usuario
        JButton ingresar = new JButton("Ingresar Usuario");

        login.add(lblUsuario);
        login.add(txtUsuario);
        login.add(lblContrasena);
        login.add(txtContrasena);
        login.add(new JLabel(""));
        login.add(ingresar);

        ingresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String usuario = txtUsuario.getText();
                String contrasena = new String(txtContrasena.getPassword());

                boolean acceso = false;

                for (Usuario u : usuarios) {
                    if (u.validarAcceso(usuario, contrasena)) {
                        acceso = true;
                        break;
                    }
                }

                if (acceso) {
                    JOptionPane.showMessageDialog(login,
                            "Usuario ingresado correctamente");
                } else {
                    JOptionPane.showMessageDialog(login,
                            "Error al ingresar");
                }
            }
        });

        login.setLocationRelativeTo(null);
        login.setVisible(true);
    }
}