
import { z } from 'zod'
import { FormEvent, useState } from "react";
import FormUI from './components/FormUI';


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
    <FormUI error={error} handleSubmit={handleSubmit} />
  )
}

export default AuthLogin