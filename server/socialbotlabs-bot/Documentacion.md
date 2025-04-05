Documentación para el Equipo Frontend: Integración con el Backend Spring Boot
Versión 1.0 - Por el Equipo Backend

1. Configuración Inicial Requerida
1.1 Variables de Entorno (Frontend)
Definan estas variables en su .env:


```javascript
VUE_APP_API_URL=http://localhost:8088  # URL del backend
VUE_APP_OAUTH_REDIRECT_URI=http://localhost:3000/oauth-callback  # Ruta de redirección post-autenticación
```
1.2 CORS (Backend ya configurado)
El backend permite solicitudes desde:

Orígenes permitidos: http://localhost:3000 (su URL de frontend)

Métodos: Todos (GET, POST, etc.)

Headers: Authorization, Content-Type

2. Flujos de Autenticación Disponibles
2.1 Autenticación Social (OAuth2)
Proveedores Disponibles: Google, GitHub.

Flujo para el Frontend:

Redirigir al usuario al proveedor:

javascript
Copy
// Ejemplo: Redirección a Google
window.location.href = `${API_URL}/oauth2/authorization/google`;
Manejar la redirección post-login:

El backend devuelve al usuario a VUE_APP_OAUTH_REDIRECT_URI con el JWT en el hash URL.

Extraigan el token:

javascript
Copy
const hashParams = new URLSearchParams(window.location.hash.substring(1));
const accessToken = hashParams.get('access_token');
2.2 Autenticación Tradicional (Email/Password)
Registro de Usuario:

javascript
Copy
fetch(`${API_URL}/auth/register-user`, {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({
    email: "user@socialbot.com",
    password: "Password123!",
    repeatPassword: "Password123!",
    fullName: "Juan Pérez",
    // ... otros campos según RegisterRequest
  })
});
Login:

javascript
Copy
// ¡Importante! El backend usa cookies para sesiones tradicionales:
fetch(`${API_URL}/login`, {
  method: 'POST',
  headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
  body: new URLSearchParams({ username, password })
});
3. Manejo del Token JWT
3.1 Almacenamiento
Recomendado: localStorage o cookies HttpOnly (según necesidades de seguridad).

javascript
Copy
localStorage.setItem('access_token', accessToken);
3.2 Envío en Requests
Incluyan el token en el header Authorization:

javascript
Copy
fetch(`${API_URL}/admin/dashboard`, {
  headers: {
    'Authorization': `Bearer ${localStorage.getItem('access_token')}`
  }
});
3.3 Información del Token
Los tokens contienen:

json
Copy
{
  "authorities": ["ROLE_ADMIN", "ROLE_MODERATOR"],
  "username": "juan@socialbot.com",
  "token_type": "access token"
}
Decodifíquenlo para determinar roles y permisos:

javascript
Copy
const parseJwt = (token) => JSON.parse(atob(token.split('.')[1]));
4. Endpoints Clave para el Frontend
Ruta	Método	Acceso Requerido	Descripción
/auth/register-user	POST	Público	Registra nuevo usuario
/client/registered-client	POST	Autenticado	Registra cliente OAuth2 (para apps externas)
/admin/dashboard	GET	Rol: ADMIN	Panel administrativo
/moderator/dashboard	GET	Rol: MODERATOR	Panel de moderación
5. Registro de Cliente OAuth2 (Para Apps Externas)
Si su frontend necesita actuar como cliente OAuth2 (ej: para acceder a APIs de Google/GitHub):

javascript
Copy
fetch(`${API_URL}/client/registered-client`, {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({
    clientId: "my-frontend-app",
    redirectUris: [VUE_APP_OAUTH_REDIRECT_URI],
    authorizationGrantTypes: ["authorization_code"],
    scopes: ["openid", "profile", "email"]
  })
});
6. Gestión de Permisos en el Frontend
Ejemplo: Protección de Rutas:

javascript
Copy
// Componente Vue/React/Angular
const userRoles = parseJwt(token).authorities;
if (!userRoles.includes('ROLE_ADMIN')) {
  redirectTo('/forbidden');
}
7. Consideraciones de Seguridad
Cookies vs LocalStorage:

Usen SameSite=Lax y Secure si trabajan con cookies.

Depuración:

Si reciben 403 Forbidden: Verifiquen roles en el JWT y headers Authorization.

Errores de CORS: Aseguren que el origen del frontend está en allowedOrigins.

Tokens Expirados:

El backend configura tiempos de expiración (por defecto: acceso=30 min, refresh=7 días).

8. Ejemplo de Servicio API (Frontend)
javascript
Copy
// api.js
import axios from 'axios';

const api = axios.create({
  baseURL: process.env.VUE_APP_API_URL,
});

api.interceptors.request.use(config => {
  const token = localStorage.getItem('access_token');
  if (token) config.headers.Authorization = `Bearer ${token}`;
  return config;
});

export const getAdminDashboard = () => api.get('/admin/dashboard');