function RegisterForm(){
    return <div className="m-2 p-2 bg-gray-100 flex flex-col gap-5 rounded-[12px] text-gray-600">
    <h2 className="text-2xl font-bold text-center">Sing Up</h2>
    <form className="flex flex-col gap-7" autoComplete="off">
      <div className="flex flex-col gap-2">
        <label className="flex flex-col">Full Name
          <input className="p-1 border border-gray-300 bg-white outline-gray-400 rounded" type="text" name="fullName" />
        </label>

        <label className="flex flex-col">Company Name
          <input className="p-1 border border-gray-300 bg-white outline-gray-400 rounded" type="text" name="companyName" />
        </label>

        <label className="flex flex-col">Email
          <input className="p-1 border border-gray-300 bg-white outline-gray-400 rounded" type="email" name="email" />
        </label>

        <label className="flex flex-col">Country
          <input className="p-1 border border-gray-300 bg-white outline-gray-400 rounded" type="text" name="country" />
        </label>

        <label className="flex flex-col">Phone
          <input className="p-1 border border-gray-300 bg-white outline-gray-400 rounded" type="tel" name="phone" />
        </label>

        <label className="flex flex-col">Password
          <input className="p-1 border border-gray-300 bg-white outline-gray-400 rounded" type="password" name="password" />
        </label>
      </div>
      <button className="p-1 border border-gray-300 bg-gray-600 text-white rounded cursor-pointer hover:bg-gray-700"  type="submit">Submit</button >
    </form>
  </div>;
}

export default RegisterForm