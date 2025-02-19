import { FaFacebook, FaGithub } from "react-icons/fa"
import { FcGoogle } from "react-icons/fc"

const FormUiExternalServices = () => {
  return (
    <div className="flex items-center justify-center gap-4 flex-wrap mb-5">
        <button type="button" className="cursor-pointer text-sm px-6 py-2 rounded-2xl border-1 border-solid border-gray-500 flex items-center gap-2 hover:bg-gray-200"><FcGoogle /> Iniciar Sesión con Google</button>
        <button type="button" className="cursor-pointer text-sm px-6 py-2 rounded-2xl border-1 border-solid border-gray-500 flex items-center gap-2 hover:bg-gray-200"><FaGithub/> Iniciar Sesión con Github</button>
        <button type="button" className="cursor-pointer text-sm px-6 py-2 rounded-2xl border-1 border-solid border-gray-500 flex items-center gap-2 hover:bg-gray-200"><FaFacebook/> Iniciar Sesión con Facebook</button>
     </div>
  )
}

export default FormUiExternalServices