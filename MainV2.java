import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// Clase Usuario
class Usuario {

    // ENCAPSULAMIENTO
    private String nombreUsuario;
    private String clave;

    public Usuario(String nombreUsuario, String clave) {
        this.nombreUsuario = nombreUsuario;
        this.clave = clave;
    }

    // ABSTRACCIÓN
    public boolean validarAcceso(String usuarioIngresado, String claveIngresada) {
        return nombreUsuario.equals(usuarioIngresado) &&
               clave.equals(claveIngresada);
    }
}

// Clase Cliente
class Cliente {

    // ENCAPSULAMIENTO
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

// Clase Principal
public class MainV2 {

    static ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    static ArrayList<Cliente> clientes = new ArrayList<>();

    public static void main(String[] args) {

        listaUsuarios.add(new Usuario("admin", "12345"));
        listaUsuarios.add(new Usuario("juan", "54321"));
        listaUsuarios.add(new Usuario("invitado", "4321"));

        ventanaInicio();
    }

    // Ventana Principal
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
                ventana.dispose();
                ventanaClientes();
            }
        });

        btnVerClientes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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

                String usuarioIngresado = campoUsuario.getText();
                String claveIngresada = new String(campoContrasena.getPassword());

                boolean acceso = false;

                for (Usuario u : listaUsuarios) {
                    if (u.validarAcceso(usuarioIngresado, claveIngresada)) {
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
        clientesVentana.setLayout(new GridLayout(6, 2, 10, 10));

        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField();

        JLabel lblCedula = new JLabel("Cédula:");
        JTextField txtCedula = new JTextField();

        JLabel lblTelefono = new JLabel("Teléfono:");
        JTextField txtTelefono = new JTextField();

        JButton btnRegistrar = new JButton("Registrar Cliente");
        JButton btnMostrar = new JButton("Mostrar Clientes");
        JButton btnVolver = new JButton("Volver");

        clientesVentana.add(lblNombre);
        clientesVentana.add(txtNombre);

        clientesVentana.add(lblCedula);
        clientesVentana.add(txtCedula);

        clientesVentana.add(lblTelefono);
        clientesVentana.add(txtTelefono);

        clientesVentana.add(btnRegistrar);
        clientesVentana.add(btnMostrar);

        clientesVentana.add(btnVolver);

        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String nombre = txtNombre.getText();
                String cedula = txtCedula.getText();
                String telefono = txtTelefono.getText();

                Cliente nuevoCliente = new Cliente(nombre, cedula, telefono);

                clientes.add(nuevoCliente);

                JOptionPane.showMessageDialog(clientesVentana,
                        "Cliente registrado correctamente");

                txtNombre.setText("");
                txtCedula.setText("");
                txtTelefono.setText("");
            }
        });

        btnMostrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarClientes();
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
                     "\n-----------------------------\n";
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