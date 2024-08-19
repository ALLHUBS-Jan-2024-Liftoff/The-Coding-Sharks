import React, {useState} from "react";
import {Link} from 'react-router-dom';

export const Login = (props) => {
    const [email, setEmail] = useState('');
    const [pass, setPass] = useState('');
    const [currentForm, setCurrentForm] = useState('login');


    const handleSubmit = (e) => {
        e.preventDefault();
        console.log(`Email: ${email} || Password: ${pass}`);

    }



    return (
        <div className="auth-form-container">
            <h2>Log In</h2>
            {/* changed from register to Log In */}
            <form className="login-form" onSubmit={handleSubmit}>
                <label htmlFor="email">email</label>
                <input value={email} onChange={(e) => setEmail(e.target.value)}type="email" placeholder="youremail@gmail.com" id="email" name="email" />
                <label htmlFor="password">password</label>
                <input value={pass} onChange={(e) => setPass(e.target.value)} type="password" placeholder="*************" id="password" name="password" />
                <button type="submit"> Log In</button>
            </form>
        <button className="link-btn" onClick={() => props.onFormSwitch('register')}>No account? Register Here.</button>
            {/* <Link to={"../Register"}>No account? Register Here.</Link> */}

       </div> 
         
    )
}