def solicitar_datos():
    """Solicita la entrada del usuario por consola."""
    usuario = input("Ingrese su nombre de usuario: ")
    contraseña = input("Ingrese su contraseña: ")
    return usuario, contraseña

def validar_credenciales(user, pas):
    """
    Compara los datos ingresados con una base de datos predefinida.
    Retorna True si coinciden, False lo contrario.
    """
    # Base de datos predefinida (Simulada)
    usuarios = {
        "admin": "12345",
        "juan": "54321",
        "invitado": "4321"
    }
    
    # Verificamos si el usuario existe y si la contraseña es correcta
    if user in usuarios and usuarios[user] == pas:
        return True
    return False

def ejecutar_sistema():
    """Función principal que orquesta el flujo del programa."""
    print("--- Sistema de Inicio de Sesión ---")
    
    # 1. Solicitar datos
    u, p = solicitar_datos()
    
    # 2. Validar
    es_valido = validar_credenciales(u, p)
    
    # 3. Mostrar resultado
    if es_valido:
        print("\n[ÉXITO] Acceso concedido. Bienvenido, {}.".format(u))
    else:
        print("\n[ERROR] Acceso denegado. Usuario o contraseña incorrectos.")

# Punto de entrada del programa
if __name__ == "__main__":
    ejecutar_sistema()

