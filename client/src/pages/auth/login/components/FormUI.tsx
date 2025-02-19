import { FaHandPointer } from 'react-icons/fa';
import { FaHandHolding } from 'react-icons/fa';
import FormUiExternalServices from './FormUIExternalServices';

interface FormUiProps {
  handleSubmit: (e: React.FormEvent<HTMLFormElement>) => void;
  error: { email?: string; password?: string } | null;
}

const FormUI = ({ handleSubmit, error }: FormUiProps) => {
  return (
    <div className="flex items-center justify-center h-screen bg-green-200">
      <div className="w-[1100px] h-[600px] bg-white flex items-center p-5 rounded-2xl">
        <div className="w-[50%] h-[100%] flex flex-col p-5">
          <div className="text-center mt-5 mb-5">
            <h1 className="pb-3 text-3xl font-bold">Iniciar Sesión</h1>
            <h2 className="text-gray-500">Bienvenido de vuelta a SocialBots</h2>
          </div>
          <FormUiExternalServices />
          <form onSubmit={handleSubmit} className="flex flex-col">
            <div className="flex flex-col gap-2 h-[105px]">
              <label htmlFor="email">E-mail</label>
              <input
                className="px-6 py-2 rounded-2xl border-1 border-solid border-gray-300 focus:outline-none placeholder:text-sm"
                id="email"
                name="email"
                type="text"
                placeholder="Ingresa tu email"
              />
              {error?.email && <span className="text-red-500 text-sm">{error.email}</span>}
            </div>
            <div className="flex flex-col gap-2 h-[105px]">
              <label htmlFor="password">Password</label>
              <input
                className="px-6 py-2 rounded-2xl border-1 border-solid border-gray-300 focus:outline-none placeholder:text-sm"
                id="password"
                name="password"
                type="password"
                placeholder="Ingresa tu password"
              />
              {error?.password && <span className="text-red-500 text-sm">{error.password}</span>}
            </div>
            <button
              className="mt-5 size-[75px] flex items-center justify-center mx-auto cursor-pointer  text-center rounded-full border-1 border-solid border-gray-500 hover:bg-gray-200"
              type="submit"
            >
              {error ? (
                <FaHandPointer className="text-center text-3xl fill-yellow-500" />
              ) : (
                <FaHandHolding className="text-center text-3xl fill-yellow-500" />
              )}
            </button>
          </form>
        </div>
        <div className="w-[50%] h-[100%] bg-amber-200 rounded-2xl">
          <img />
        </div>
      </div>
    </div>
  );
};

export default FormUI;
