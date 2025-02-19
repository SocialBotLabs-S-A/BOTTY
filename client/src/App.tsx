import { Navigate, Route } from "react-router";
import RoutesWithNotFound from "./components/RoutesWithNotFound";
import AuthGuard from "./guard/AuthGuard";
import RegisterForm from "./pages/register/RegisterForm";
import AuthLogin from "./pages/auth/login/AuthLogin";

function App() {
  return (
    <RoutesWithNotFound>
      <Route path="/" element={<Navigate to='/private' />}  />
      <Route path="/login" element={<AuthLogin />} />
      <Route path="/register" element={<RegisterForm/>} />
      <Route element={<AuthGuard />}>
        {/* Aqui van a estar las rutas privadas */}
        <Route path="/private" element={<span>This is private</span>} />
      </Route>
    </RoutesWithNotFound>
  );
}

export default App;
