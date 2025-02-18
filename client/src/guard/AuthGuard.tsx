import { Navigate, Outlet } from "react-router";

const AuthGuard = () => {
  const isAuth = null

  return isAuth ? <Outlet /> : <Navigate to='/login' replace />
}

export default AuthGuard