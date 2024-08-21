import React, {useState} from "react";

export const Register = (props) => {
    const [email, setEmail] = useState('');
    const [pass, setPass] = useState('');
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log(email, pass, firstName, lastName);
    }

    return (
    <div className="auth-form-container">
        <h2>Register</h2>
        <form className="register-form" onSubmit={handleSubmit}>
            <label htmlFor="firstName">First name</label>
            <input value={firstName} onChange={(e) => setFirstName(e.target.value)} id="firstName" placeholder="first name" />
            <label htmlFor="lastName">Last name</label>
            <input value={lastName} onChange={(e) => setLastName(e.target.value)} id="lastName" placeholder="last name" />
            <label htmlFor="email">email</label>
            <input value={email} onChange={(e) => setEmail(e.target.value)}type="email" placeholder="youremail@gmail.com" id="email" name="email" />
            <label htmlFor="password">password</label>
            <input value={pass} onChange={(e) => setPass(e.target.value)} type="password" placeholder="*************" id="password" name="password" />
            <button type="submit"> Register</button>
        </form>
        <button className="link-btn" onClick={() => props.onFormSwitch('login')}>
            Have an account? Login here.
        </button>
      </div>
    )
}