# Lista para almacenar clientes
clientes = []


def solicitar_datos():
    """Solicita usuario y contraseña"""

    while True:

        usuario = input(
            "Ingrese su nombre de usuario: "
        ).strip()

        if usuario == "":
            print("\n[ERROR] El usuario no puede estar vacío.")
            continue

        contraseña = input(
            "Ingrese su contraseña: "
        )

        if contraseña == "":
            print("\n[ERROR] La contraseña no puede estar vacía.")
            continue

        if len(contraseña) < 8 or len(contraseña) > 16:
            print(
                "\n[ERROR] La contraseña debe tener entre 8 y 16 caracteres."
            )
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

    while True:

        dato = input(mensaje)

        if not dato.isdigit():
            print(
                "\n[ERROR] Solo se permiten números."
            )
            continue

        if len(dato) != 10:
            print(
                "\n[ERROR] Debe contener exactamente 10 dígitos."
            )
            continue

        return dato


def validar_cedula_ecuador():

    while True:

        cedula = input(
            "Ingrese la cédula (10 dígitos): "
        )

        if not cedula.isdigit():
            print(
                "\n[ERROR] Solo se permiten números."
            )
            continue

        if len(cedula) != 10:
            print(
                "\n[ERROR] Debe contener exactamente 10 dígitos."
            )
            continue

        provincia = int(cedula[:2])

        if provincia < 1 or provincia > 24:
            print(
                "\n[ERROR] Código de provincia inválido."
            )
            continue

        suma = 0

        for i in range(9):

            digito = int(cedula[i])

            if i % 2 == 0:

                digito *= 2

                if digito > 9:
                    digito -= 9

            suma += digito

        verificador = (
            10 - (suma % 10)
        ) % 10

        if verificador != int(cedula[9]):

            print(
                "\n[ERROR] Cédula inválida."
            )
            continue

        return cedula


def validar_nombre():

    while True:

        nombre = input(
            "Ingrese el nombre del cliente: "
        ).strip()

        if nombre == "":
            print(
                "\n[ERROR] El nombre no puede estar vacío."
            )
            continue

        if len(nombre) > 12:
            print(
                "\n[ERROR] El nombre no puede superar 12 caracteres."
            )
            continue

        if not nombre.replace(
                " ", "").isalpha():

            print(
                "\n[ERROR] Solo se permiten letras."
            )

            continue

        return nombre


def cliente_duplicado(cedula):

    for cliente in clientes:

        if cliente["cedula"] == cedula:
            return True

    return False


def celular_duplicado(celular):

    for cliente in clientes:

        if cliente["celular"] == celular:
            return True

    return False


def registrar_cliente(usuario_admin):

    print(
        "\n--- Registro de Clientes ---"
    )

    nombre = validar_nombre()

    while True:

        cedula = validar_cedula_ecuador()

        if cliente_duplicado(
                cedula):

            print(
                "\n[ERROR] Ya existe un cliente con esa cédula."
            )

            continue

        break


    while True:

        celular = validar_numeros(
            "Ingrese el celular (10 dígitos): "
        )

        if celular_duplicado(
                celular):

            print(
                "\n[ERROR] El número celular ya existe."
            )

            continue

        break


    cliente = {

        "nombre": nombre,
        "cedula": cedula,
        "celular": celular,
        "registrado_por": usuario_admin
    }

    clientes.append(cliente)

    print(
        "\n[ÉXITO] Cliente registrado correctamente."
    )


def mostrar_clientes():

    if len(clientes) == 0:

        print(
            "\nNo existen clientes registrados."
        )

        return


    print(
        "\n===== LISTA DE CLIENTES ====="
    )


    for i, cliente in enumerate(
            clientes,
            start=1):

        print(
            f"\nCliente #{i}"
        )

        print(
            f"Nombre: {cliente['nombre']}"
        )

        print(
            f"Cédula: {cliente['cedula']}"
        )

        print(
            f"Celular: {cliente['celular']}"
        )

        print(
            f"Registrado por: {cliente['registrado_por']}"
        )


def menu_admin(usuario):

    while True:

        print(
            "\n===== MENÚ ADMIN ====="
        )

        print(
            "1. Registrar cliente"
        )

        print(
            "2. Ver clientes"
        )

        print(
            "3. Cerrar sesión"
        )

        print(
            "4. Exit"
        )

        opcion = input(
            "Seleccione una opción: "
        )

        if opcion == "1":

            registrar_cliente(
                usuario
            )

        elif opcion == "2":

            mostrar_clientes()

        elif opcion == "3":

            print(
                "\nCerrando sesión..."
            )

            break

        elif opcion == "4":

            print(
                "\nPrograma finalizado."
            )

            exit()

        else:

            print(
                "\n[ERROR] Opción inválida."
            )


def menu_usuario(usuario):

    while True:

        print(
            f"\n===== MENÚ USUARIO ({usuario}) ====="
        )

        print(
            "1. Ver clientes registrados"
        )

        print(
            "2. Cerrar sesión"
        )

        print(
            "3. Exit"
        )

        opcion = input(
            "Seleccione una opción: "
        )

        if opcion == "1":

            mostrar_clientes()

        elif opcion == "2":

            print(
                "\nCerrando sesión..."
            )

            break

        elif opcion == "3":

            print(
                "\nPrograma finalizado."
            )

            exit()

        else:

            print(
                "\n[ERROR] Opción inválida."
            )


def ejecutar_sistema():

    while True:

        print(
            "\n--- Sistema de Inicio de Sesión ---"
        )

        u, p = solicitar_datos()

        es_valido = validar_credenciales(
            u, p
        )

        if es_valido:

            print(
                f"\n[ÉXITO] Acceso concedido. Bienvenido {u}"
            )

            print(
                "\n[AVISO] A continuación se mostrará el menú interactivo correspondiente a su usuario."
            )

            if u == "admin":

                menu_admin(u)

            else:

                menu_usuario(u)

        else:

            print(
                "\n[ERROR] Usuario o contraseña incorrectos."
            )


if __name__ == "__main__":
    ejecutar_sistema()
