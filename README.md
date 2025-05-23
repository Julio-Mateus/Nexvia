# 🚍 Nexvia

**Nexvia** es una aplicación móvil desarrollada en Kotlin con Jetpack Compose, pensada para mejorar el transporte público en Yopal, Casanare (Colombia). Permite a los usuarios ver en tiempo real las rutas de busetas, solicitar paradas y mejorar la movilidad urbana.

---

## 📱 Características principales

### Para usuarios:
- Ver ubicación de busetas en tiempo real.
- Solicitar parada en una ubicación específica.
- Ver tiempo estimado de llegada (ETA).
- Registro e inicio de sesión.

### Para conductores:
- Registro con placa y número de ruta.
- Cambiar número de ruta (por rotaciones).
- Ver alertas de parada en tiempo real.
- Compartir ubicación mientras está en ruta.

---

## 🛠️ Tecnologías utilizadas
- Kotlin Multiplatform (en esta versión, solo Android)
- Jetpack Compose + Material 3
- Firebase Authentication
- Firebase Firestore
- Firebase Cloud Messaging (FCM)
- Google Maps SDK
- GitHub Projects (para gestión ágil con Scrum)

---

## 🧩 Estructura del proyecto
```
Nexvia/
├── app/
│   └── src/
│       └── main/
│           └── kotlin/com/nexvia/
│               ├── ui/          # Pantallas Compose
│               ├── viewmodel/   # Lógica de estado
│               ├── model/       # Modelos de datos
│               ├── data/        # Repositorios / Firebase
│               ├── navigation/  # Control de rutas
│               └── utils/       # Utilidades generales
```

---

## 🚧 Metodología Scrum

- 5 sprints principales
- Gestión de tareas en GitHub Projects
- Meta: App funcional para antes de junio

---

## 🤝 Contribuciones

Este es un proyecto académico, pero se aceptan sugerencias o mejoras si deseas colaborar.

---

## 📍 Ciudad de origen

Desarrollado por un estudiante de Tecnólogo en Desarrollo de Software de **Yopal, Casanare – Colombia**.

---

## 🔒 Licencia

MIT – Puedes usar y modificar libremente para fines académicos y de aprendizaje.