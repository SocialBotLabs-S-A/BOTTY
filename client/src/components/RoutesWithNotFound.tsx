import { ReactNode } from "react";
import { Routes, Route, Navigate } from "react-router";

const RoutesWithNotFound = ({children}: {children: ReactNode}) => {
  return (
    <Routes>
      <Route path="*" element={<Navigate to='/404' />} />
      <Route path="/404" element={<span>Not Found</span>} />
      { children }
    </Routes>
  )
}

export default RoutesWithNotFound
