# Lista para almacenar clientes
clientes = []


def solicitar_datos():
    """Solicita la entrada del usuario por consola."""
    us = input("Ingrese su nombre de usuario: ")
    cont = input("Ingrese su contraseña: ")
    return us, cont


def validar_credenciales(user, pas):

    usuarios = {
        "admin": "12345",
        "juan": "54321",
        "invitado": "4321"
    }

    if user in usuarios and usuarios[user] == pas:
        return True

    return False


def validar_numeros(mensaje):
    """
    Valida que:
    - Solo se ingresen números
    - Tenga exactamente 10 dígitos
    """

    while True:

        dato = input(mensaje)

        # Verifica si contiene solo números
        if not dato.isdigit():
            print("\n[ERROR] Solo se permiten números.")
            continue

        # Verifica longitud
        if len(dato) != 10:
            print("\n[ERROR] Debe contener exactamente 10 dígitos.")
            continue

        return dato


def registrar_cliente(usuario_admin):

    print("\n--- Registro de Clientes ---")

    nombre = input("Ingrese el nombre del cliente: ")

    # VALIDACIONES
    cedula = validar_numeros("Ingrese la cédula (10 dígitos): ")
    celular = validar_numeros("Ingrese el celular (10 dígitos): ")

    correo = input("Ingrese el correo: ")

    cliente = {
        "nombre": nombre,
        "cedula": cedula,
        "celular": celular,
        "correo": correo,
        "registrado_por": usuario_admin
    }

    clientes.append(cliente)

    print("\n[ÉXITO] Cliente registrado correctamente.")


def mostrar_clientes():

    if len(clientes) == 0:
        print("\nNo hay clientes registrados.")
        return

    print("\n===== LISTA DE CLIENTES =====")

    for i, cliente in enumerate(clientes, start=1):

        print(f"\nCliente #{i}")
        print(f"Nombre: {cliente['nombre']}")
        print(f"Cédula: {cliente['cedula']}")
        print(f"Celular: {cliente['celular']}")
        print(f"Correo: {cliente['correo']}")
        print(f"Registrado por: {cliente['registrado_por']}")


def menu_admin(usuario):

    while True:

        print("\n===== MENÚ ADMIN =====")
        print("1. Registrar cliente")
        print("2. Ver clientes")
        print("3. Salir")

        opcion = input("Seleccione una opción: ")

        if opcion == "1":
            registrar_cliente(usuario)

        elif opcion == "2":
            mostrar_clientes()

        elif opcion == "3":
            print("\nCerrando sesión...")
            break

        else:
            print("\n[ERROR] Opción inválida.")


def ejecutar_sistema():

    print("--- Sistema de Inicio de Sesión ---")

    u, p = solicitar_datos()

    es_valido = validar_credenciales(u, p)

    if es_valido:

        print("\n[ÉXITO] Acceso concedido. Bienvenido, {}.".format(u))

        if u == "admin":
            menu_admin(u)

    else:
        print("\n[ERROR] Acceso denegado. Usuario o contraseña incorrectos.")


# Punto de entrada del programa
if __name__ == "__main__":
    ejecutar_sistema()