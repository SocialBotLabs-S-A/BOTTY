import { ChangeEvent, FormEvent, useState } from 'react';

interface RegisterFormData {
  fullName: string;
  companyName: string;
  email: string;
  country: string;
  phone: number;
  password: string;
}

const defaultValue = {
  fullName: '',
  companyName: '',
  email: '',
  country: '',
  phone: 0,
  password: '',
};

function RegisterForm() {
  const [dataForm, setDataForm] = useState(defaultValue as RegisterFormData);

  const handleChange = (e: ChangeEvent) => {
    const { name, value, type } = e.target as HTMLInputElement;

    switch (type) {
      case 'text':
        {
          const expReg = /^[a-z]*$/i;
          if (expReg.test(value)) {
            setDataForm({ ...dataForm, [name]: value });
          }
        }
        break;
      case 'email':
        {
          const expReg =
            /^(([a-z0-9_]{0,80})|(([a-z0-9_]{1,80})@)|(([a-z0-9_]{1,80})@([a-z]{1,15}|([a-z]{1,15}).|([a-z]{1,15}).([a-z]{1,5}))))$/;
          if (expReg.test(value)) {
            setDataForm({ ...dataForm, [name]: value });
          }
        }
        break;
      case 'tel':
        {
          const expReg = /^[0-9]*$/;
          if (expReg.test(value)) {
            setDataForm({ ...dataForm, [name]: value });
          }
        }
        break;
      case 'password':
        setDataForm({ ...dataForm, [name]: value });
        break;
      default:
        setDataForm(dataForm);
        break;
    }
  };

  const handleSubmit = (e: FormEvent) => {
    e.preventDefault();
    let canSubmit = true;
    Object.keys(dataForm).map((key) => {
      const input = document.querySelector(`[name=${key}]`) as HTMLInputElement;
      if (!input.value) {
        canSubmit = false;
        input.classList.add('border-red-500');
      } else {
        input.classList.remove('border-red-500');
      }
    });

    if (canSubmit) {
      alert('Usuario registrado correctamente!');
    }
  };

  return (
    <div className="m-2 p-2 bg-gray-100 flex flex-col gap-5 rounded-[12px] text-gray-600">
      <h2 className="text-2xl font-bold text-center">Sing Up</h2>
      <form className="flex flex-col gap-7" autoComplete="off" onSubmit={handleSubmit}>
        <div className="flex flex-col gap-2">
          <label className="flex flex-col">
            Full Name
            <input
              placeholder="Full Name"
              onChange={handleChange}
              value={dataForm.fullName}
              className="p-1 border border-gray-300 bg-white outline-gray-400 rounded"
              type="text"
              name="fullName"
            />
          </label>

          <label className="flex flex-col">
            Company Name
            <input
              placeholder="Company"
              onChange={handleChange}
              value={dataForm.companyName}
              className="p-1 border border-gray-300 bg-white outline-gray-400 rounded"
              type="text"
              name="companyName"
            />
          </label>

          <label className="flex flex-col">
            Email
            <input
              placeholder="example@mail.com"
              onChange={handleChange}
              value={dataForm.email}
              className="p-1 border border-gray-300 bg-white outline-gray-400 rounded"
              type="email"
              name="email"
            />
          </label>

          <label className="flex flex-col">
            Country
            <input
              placeholder="Country"
              onChange={handleChange}
              value={dataForm.country}
              className="p-1 border border-gray-300 bg-white outline-gray-400 rounded"
              type="text"
              name="country"
            />
          </label>

          <label className="flex flex-col">
            Phone
            <input
              placeholder="+00 221 334 455"
              onChange={handleChange}
              value={dataForm.phone}
              className="p-1 border border-gray-300 bg-white outline-gray-400 rounded"
              type="tel"
              name="phone"
            />
          </label>

          <label className="flex flex-col">
            Password
            <input
              placeholder="Password"
              onChange={handleChange}
              value={dataForm.password}
              className="p-1 border border-gray-300 bg-white outline-gray-400 rounded"
              type="password"
              name="password"
            />
          </label>
        </div>
        <button
          className="p-1 border border-gray-300 bg-gray-600 text-white rounded cursor-pointer hover:bg-gray-700"
          type="submit"
        >
          Submit
        </button>
      </form>
    </div>
  );
}

export default RegisterForm;
