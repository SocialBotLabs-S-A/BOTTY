import { FaGithub, FaFacebook, FaHandPointer } from "react-icons/fa";
import { FcGoogle } from "react-icons/fc";
import { FaHandHolding } from "react-icons/fa";
import { z } from 'zod'
import { FormEvent, useState } from "react";


const loginSchema = z.object({
  email: z.string().min(1, 'El email es obligatorio').max(36, 'El email no puede exceder de 32 caracteres').email('El email es invalido'),
  password: z.string().min(8, 'El password debe tener al menos 8 caracteres').max(36, 'El password no puede exceder de 32 caracteres')
})

const AuthLogin = () => {
  const [error, setError] = useState<{ email?: string, password?: string } | null>(null)

  const handleSubmit = (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault()

    setError(null)

    const formObject = new FormData(e.target as HTMLFormElement)
    const formData = Object.fromEntries(formObject)

    const validate = loginSchema.safeParse(formData)

    if (!validate.success) {
      const formatedError = validate.error.format()
      setError({ email: formatedError.email?._errors[0], password: formatedError.password?._errors[0] })
      return
    }

    console.log(formData);
  }

  return (
    <div className="flex items-center justify-center h-screen bg-green-200">
      <div className="w-[1100px] h-[600px] bg-white flex items-center p-5 rounded-2xl">
        <div className="w-[50%] h-[100%] flex flex-col p-5">
          <div className="text-center mt-5 mb-5">
            <h1 className="pb-3 text-3xl font-bold">Iniciar Sesión</h1>
            <h2 className="text-gray-500">Bienvenido de vuelta a SocialBots</h2>
          </div>
          <div className="flex items-center justify-center gap-4 flex-wrap mb-5">
            <button type="button" className="cursor-pointer text-sm px-6 py-2 rounded-2xl border-1 border-solid border-gray-500 flex items-center gap-2 hover:bg-gray-200"><FcGoogle /> Iniciar Sesión con Google</button>
            <button type="button" className="cursor-pointer text-sm px-6 py-2 rounded-2xl border-1 border-solid border-gray-500 flex items-center gap-2 hover:bg-gray-200"><FaGithub/> Iniciar Sesión con Github</button>
            <button type="button" className="cursor-pointer text-sm px-6 py-2 rounded-2xl border-1 border-solid border-gray-500 flex items-center gap-2 hover:bg-gray-200"><FaFacebook/> Iniciar Sesión con Facebook</button>
          </div>
          <form onSubmit={handleSubmit} className="flex flex-col">
            <div className="flex flex-col gap-2 h-[105px]">
              <label htmlFor="email">E-mail</label>
              <input className="px-6 py-2 rounded-2xl border-1 border-solid border-gray-300 focus:outline-none placeholder:text-sm" id="email" name="email" type="text" placeholder="Ingresa tu email" />
              { error?.email && <span className="text-red-500 text-sm">{error.email}</span> }
            </div>
            <div className="flex flex-col gap-2 h-[105px]">
              <label htmlFor="password">Password</label>
              <input className="px-6 py-2 rounded-2xl border-1 border-solid border-gray-300 focus:outline-none placeholder:text-sm" id="password" name="password" type="password" placeholder="Ingresa tu password" />
              { error?.password && <span className="text-red-500 text-sm">{error.password}</span> }
            </div>
            <button className="mt-5 size-[75px] flex items-center justify-center mx-auto cursor-pointer  text-center rounded-full border-1 border-solid border-gray-500 hover:bg-gray-200" type="submit">
              {
                error ? <FaHandPointer className="text-center text-3xl fill-yellow-500" /> : <FaHandHolding className="text-center text-3xl fill-yellow-500"  />
              }
            </button>
          </form>
        </div>
        <div className="w-[50%] h-[100%] bg-amber-200 rounded-2xl">
          <img />
        </div>
      </div>
    </div>
  )
}

export default AuthLogin