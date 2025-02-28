import { Route } from "react-router";
import RoutesWithNotFound from "./components/RoutesWithNotFound";
import AuthGuard from "./guard/AuthGuard";
import RegisterForm from "./pages/register/RegisterForm";

function App() {
  return (
    <RoutesWithNotFound>
      <Route path="/login" element={<span>Login Page</span>} />
      <Route path="/register" element={<RegisterForm/>} />
      <Route element={<AuthGuard />}>
          {/* Aqui van a estar las rutas privadas */}
      </Route>
    </RoutesWithNotFound>
  );
}

export default App;
