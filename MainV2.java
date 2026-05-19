import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class Usuario {

    private String nombreUsuario;
    private String clave;

    public Usuario(String nombreUsuario, String clave) {
        this.nombreUsuario = nombreUsuario;
        this.clave = clave;
    }

    public boolean validarAcceso(String usuarioIngresado,
                                 String claveIngresada) {

        return nombreUsuario.equals(usuarioIngresado) &&
               clave.equals(claveIngresada);
    }
}

class Cliente {

    private String nombre;
    private String cedula;
    private String telefono;

    public Cliente(String nombre, String cedula, String telefono) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public String getTelefono() {
        return telefono;
    }
}

public class MainV2 {

    static ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    static ArrayList<Cliente> clientes = new ArrayList<>();

    static String usuarioActual = "";

    public static void main(String[] args) {

        listaUsuarios.add(new Usuario("admin", "12345"));
        listaUsuarios.add(new Usuario("juan", "54321"));
        listaUsuarios.add(new Usuario("invitado", "4321"));

        ventanaInicio();
    }

    public static void ventanaInicio() {

        JFrame ventana = new JFrame("Sistema");
        ventana.setSize(450, 300);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(new GridLayout(4, 1, 10, 10));

        JLabel texto = new JLabel("BIENVENIDO", SwingConstants.CENTER);
        texto.setFont(new Font("Arial", Font.BOLD, 24));

        JButton btnLogin = new JButton("Ingresar Usuario");
        JButton btnClientes = new JButton("Registro de Clientes");
        JButton btnVerClientes = new JButton("Ver Clientes Registrados");

        ventana.add(texto);
        ventana.add(btnLogin);
        ventana.add(btnClientes);
        ventana.add(btnVerClientes);

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventana.dispose();
                ventanaLogin();
            }
        });

        btnClientes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (usuarioActual.equals("")) {

                    JOptionPane.showMessageDialog(ventana,
                            "Debe iniciar sesión");

                    return;
                }

                if (!usuarioActual.equals("admin")) {

                    JOptionPane.showMessageDialog(ventana,
                            "Solo el administrador puede registrar clientes");

                    return;
                }

                ventana.dispose();
                ventanaClientes();
            }
        });

        btnVerClientes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (usuarioActual.equals("")) {

                    JOptionPane.showMessageDialog(ventana,
                            "Debe iniciar sesión");

                    return;
                }

                mostrarClientes();
            }
        });

        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

    public static void ventanaLogin() {

        JFrame login = new JFrame("Ingreso Usuario");
        login.setSize(400, 300);
        login.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel lblUsuario = new JLabel("Usuario:");
        JTextField campoUsuario = new JTextField();

        JLabel lblContrasena = new JLabel("Contraseña:");
        JPasswordField campoContrasena = new JPasswordField();

        JButton ingresar = new JButton("Ingresar");
        JButton volver = new JButton("Volver");

        login.add(lblUsuario);
        login.add(campoUsuario);

        login.add(lblContrasena);
        login.add(campoContrasena);

        login.add(ingresar);
        login.add(volver);

        ingresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String usuarioIngresado =
                        campoUsuario.getText().trim();

                String claveIngresada =
                        new String(campoContrasena.getPassword());

                if (usuarioIngresado.isEmpty() ||
                    claveIngresada.isEmpty()) {

                    JOptionPane.showMessageDialog(login,
                            "Debe completar todos los campos");

                    return;
                }

                boolean acceso = false;

                for (Usuario u : listaUsuarios) {

                    if (u.validarAcceso(usuarioIngresado,
                            claveIngresada)) {

                        acceso = true;
                        usuarioActual = usuarioIngresado;
                        break;
                    }
                }

                if (acceso) {

                    JOptionPane.showMessageDialog(login,
                            "Usuario ingresado correctamente");

                    login.dispose();
                    ventanaInicio();

                } else {

                    JOptionPane.showMessageDialog(login,
                            "Error al ingresar");
                }
            }
        });

        volver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login.dispose();
                ventanaInicio();
            }
        });

        login.setLocationRelativeTo(null);
        login.setVisible(true);
    }

    public static void ventanaClientes() {

        JFrame clientesVentana = new JFrame("Registro Clientes");
        clientesVentana.setSize(450, 350);
        clientesVentana.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField();

        JLabel lblCedula = new JLabel("Cédula:");
        JTextField txtCedula = new JTextField();

        JLabel lblTelefono = new JLabel("Teléfono:");
        JTextField txtTelefono = new JTextField();

        JButton btnRegistrar = new JButton("Registrar Cliente");
        JButton btnVolver = new JButton("Volver");

        clientesVentana.add(lblNombre);
        clientesVentana.add(txtNombre);

        clientesVentana.add(lblCedula);
        clientesVentana.add(txtCedula);

        clientesVentana.add(lblTelefono);
        clientesVentana.add(txtTelefono);

        clientesVentana.add(btnRegistrar);
        clientesVentana.add(btnVolver);

        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String nombre = txtNombre.getText().trim();
                String cedula = txtCedula.getText().trim();
                String telefono = txtTelefono.getText().trim();

                if (nombre.isEmpty() ||
                    cedula.isEmpty() ||
                    telefono.isEmpty()) {

                    JOptionPane.showMessageDialog(clientesVentana,
                            "Todos los campos son obligatorios");

                    return;
                }

                boolean valido = true;

                for (int i = 0; i < nombre.length(); i++) {

                    char letra = nombre.charAt(i);

                    if (!Character.isLetter(letra) &&
                        letra != ' ') {

                        valido = false;
                        break;
                    }
                }

                if (!valido) {

                    JOptionPane.showMessageDialog(clientesVentana,
                            "El nombre solo debe contener letras");

                    return;
                }

                for (int i = 0; i < cedula.length(); i++) {

                    if (!Character.isDigit(
                            cedula.charAt(i))) {

                        JOptionPane.showMessageDialog(
                                clientesVentana,
                                "La cédula solo debe contener números");

                        return;
                    }
                }

                if (cedula.length() != 10) {

                    JOptionPane.showMessageDialog(
                            clientesVentana,
                            "La cédula debe tener 10 dígitos");

                    return;
                }

                for (int i = 0; i < telefono.length(); i++) {

                    if (!Character.isDigit(
                            telefono.charAt(i))) {

                        JOptionPane.showMessageDialog(
                                clientesVentana,
                                "El teléfono solo debe contener números");

                        return;
                    }
                }

                if (telefono.length() != 10) {

                    JOptionPane.showMessageDialog(
                            clientesVentana,
                            "El teléfono debe tener 10 dígitos");

                    return;
                }

                Cliente nuevoCliente =
                        new Cliente(nombre,
                                cedula,
                                telefono);

                clientes.add(nuevoCliente);

                JOptionPane.showMessageDialog(
                        clientesVentana,
                        "Cliente registrado correctamente");

                txtNombre.setText("");
                txtCedula.setText("");
                txtTelefono.setText("");
            }
        });

        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clientesVentana.dispose();
                ventanaInicio();
            }
        });

        clientesVentana.setLocationRelativeTo(null);
        clientesVentana.setVisible(true);
    }

    public static void mostrarClientes() {

        String lista = "";

        for (Cliente c : clientes) {

            lista += "Nombre: " + c.getNombre() +
                     "\nCédula: " + c.getCedula() +
                     "\nTeléfono: " + c.getTelefono() +
                     "\n*******************************n";
        }

        if (lista.equals("")) {
            lista = "No hay clientes registrados";
        }

        JTextArea area = new JTextArea(lista);
        area.setEditable(false);

        JScrollPane scroll = new JScrollPane(area);

        JOptionPane.showMessageDialog(null,
                scroll,
                "Clientes Registrados",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
