# Lista para almacenar clientes
clientes = []


def solicitar_datos():
    """Solicita usuario y contraseña."""

    while True:

        usuario = input("Ingrese su nombre de usuario: ").strip()
        contraseña = input("Ingrese su contraseña: ")

        # Validación contraseña
        if contraseña == "":
            print("\n[ERROR] La contraseña no puede estar vacía.")
            continue

        if len(contraseña) < 8 or len(contraseña) > 16:
            print("\n[ERROR] La contraseña debe tener entre 8 y 16 caracteres.")
            continue

        return usuario, contraseña


def validar_credenciales(user, pas):

    usuarios = {
        "admin": "admin-1234",
        "juan": "juan-1234",
        "invitado": "invitado1"
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

        # Solo números
        if not dato.isdigit():
            print("\n[ERROR] Solo se permiten números.")
            continue

        # Exactamente 10 dígitos
        if len(dato) != 10:
            print("\n[ERROR] Debe contener exactamente 10 dígitos.")
            continue

        return dato


def validar_nombre():
    """
    Valida:
    - Que no esté vacío
    - Máximo 12 caracteres
    """

    while True:

        nombre = input("Ingrese el nombre del cliente: ").strip()

        if nombre == "":
            print("\n[ERROR] El nombre no puede estar vacío.")
            continue

        if len(nombre) > 12:
            print("\n[ERROR] El nombre no puede superar los 12 caracteres.")
            continue

        return nombre


def cliente_duplicado(cedula):
    """Verifica si ya existe un cliente con esa cédula."""

    for cliente in clientes:
        if cliente["cedula"] == cedula:
            return True

    return False


def registrar_cliente(usuario_admin):

    print("\n--- Registro de Clientes ---")

    nombre = validar_nombre()

    while True:

        cedula = validar_numeros("Ingrese la cédula (10 dígitos): ")

        if cliente_duplicado(cedula):
            print("\n[ERROR] Ya existe un cliente registrado con esa cédula.")
            continue

        break

    celular = validar_numeros("Ingrese el celular (10 dígitos): ")

    cliente = {
        "nombre": nombre,
        "cedula": cedula,
        "celular": celular,
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
        print(f"Registrado por: {cliente['registrado_por']}")


def menu_admin(usuario):

    while True:

        print("\n===== MENÚ ADMIN =====")
        print("1. Registrar cliente")
        print("2. Ver clientes")
        print("3. Cerrar sesión")
        print("4. Exit")

        opcion = input("Seleccione una opción: ")

        if opcion == "1":
            registrar_cliente(usuario)

        elif opcion == "2":
            mostrar_clientes()

        elif opcion == "3":
            print("\nCerrando sesión...")
            break

        elif opcion == "4":
            print("\nPrograma finalizado.")
            exit()

        else:
            print("\n[ERROR] Opción inválida.")


def menu_usuario(usuario):

    while True:

        print(f"\n===== MENÚ USUARIO ({usuario}) =====")
        print("1. Ver clientes registrados")
        print("2. Cerrar sesión")
        print("3. Exit")

        opcion = input("Seleccione una opción: ")

        if opcion == "1":
            mostrar_clientes()

        elif opcion == "2":
            print("\nCerrando sesión...")
            break

        elif opcion == "3":
            print("\nPrograma finalizado.")
            exit()

        else:
            print("\n[ERROR] Opción inválida.")


def ejecutar_sistema():

    while True:

        print("\n--- Sistema de Inicio de Sesión ---")

        u, p = solicitar_datos()

        es_valido = validar_credenciales(u, p)

        if es_valido:

            print(f"\n[ÉXITO] Acceso concedido. Bienvenido, {u}.")

            if u == "admin":
                menu_admin(u)

            else:
                menu_usuario(u)

        else:
            print("\n[ERROR] Usuario o contraseña incorrectos.")



if __name__ == "__main__":
    ejecutar_sistema()
