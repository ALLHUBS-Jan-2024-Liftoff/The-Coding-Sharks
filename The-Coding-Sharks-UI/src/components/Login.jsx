import React, { useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";

function Login({ setAuthenticated }) {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [message, setMessage] = useState("");

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post(
        "http://localhost:8080/api/user/login",
        {
          username,
          password,
        },
        {
          withCredentials: true,
        }
      );
      setAuthenticated(true);
      setMessage(response.data.message);
    } catch (error) {
      setMessage(error.response?.data?.message || "Login failed");
    }
  };

  return (
    <div style={{ textAlign: 'center', fontFamily: 'Arial, sans-serif' }}>
    <h2 style={{ color: 'powderblue' }}>Welcome to Wanderlust</h2>
    <h3 style={{ color: '	#FFFFE0' }}>Login to plan a trip!</h3>
      <form onSubmit={handleLogin}>
        <input
          type="text"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          placeholder="Username"
        />
        <input
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          placeholder="Password"
        />
        
      <button type="submit" style={{ padding: '10px 20px', margin: '10px', backgroundColor: '#FFFFE0', color: 'black', border: 'none', borderRadius: '5px' }}>Login</button>
      </form>
      <Link to="/register" style={{  color: 'pink'}}>New user? Create an account here</Link>
      {message && <p>{message}</p>}
    </div>
  );
}

export default Login;