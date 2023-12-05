import React, { useState } from "react";
import "./login.css";
import axios from "axios";

const Login = ({ onLogin }) => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [active, setActive] = useState(false);

  const handleRegisterClick = () => {
    setActive(true);
  };

  const handleLoginClick = () => {
    setActive(false);
  };

  const onLoginSubmit = async () => {
    try {
      const response = await axios.post('http://localhost:8080/login',{
      username: this.state.username,
      password: this.state.password
 })
      
      const token = response.data.token; 

      if (onLogin) {
        onLogin(token);
      }
    } catch (error) {
      console.error("Login failed:", error);
    }
  };

  const handleRegisterSubmit = async () => {
    
    try {
      const response = await axios.post("http://localhost:8080/register", {
        email: email,
        password: password,
      });

      
      console.log("Registration successful:", response.data);

      
      setActive(false);
      
    } catch (error) {
      console.error("Registration failed:", error);
      
    }
  };
  return (
    <div>
      <div className={`container ${active ? "active" : ""}`} id="container">
        <div className="form-container sign-up">
          <form>
            <h1>Create Account</h1>
            <div className="social-icons">
              <a href="/" className="icon">
                <i className="fab fa-facebook-f"></i>
              </a>
              <a href="/" className="icon">
                <i className="fab fa-google-plus-g"></i>
              </a>
              <a href="/" className="icon">
                <i className="fab fa-github"></i>
              </a>
              <a href="/" className="icon">
                <i className="fab fa-linkedin-in"></i>
              </a>
            </div>
            <span>or use your e-mail for registration</span>
            <input type="text" placeholder="Name" />
            <input type="email" placeholder="E-mail" />
            <input type="password" placeholder="Password" />
            <button onClick={handleRegisterSubmit} type="button">
              Sign Up
            </button>
          </form>
        </div>
        <div className="form-container sign-in">
          <form>
            <h1>Sign In</h1>
            <div className="social-icons">
              <a href="/" className="icon">
                <i className="fab fa-facebook-f"></i>
              </a>
              <a href="/" className="icon">
                <i className="fab fa-google-plus-g"></i>
              </a>
              <a href="/" className="icon">
                <i className="fab fa-github"></i>
              </a>
              <a href="/" className="icon">
                <i className="fab fa-linkedin-in"></i>
              </a>
            </div>
            <span>or use your e-mail</span>
            <input type="email" placeholder="E-mail" />
            <input type="password" placeholder="Password" />
            <a href="/">Forgot your password?</a>
            <button onClick={onLoginSubmit} type="button">
              Sign In
            </button>
          </form>
        </div>
        <div className="toggle-container">
          <div className="toggle">
            <div className="toggle-panel toggle-left">
              <h1>Welcome Back!</h1>
              <p style={{ color: "white" }}>
                To connect your work account, please login with your personal
                info
              </p>
              <button
                className="hidden"
                onClick={handleLoginClick}
                type="button"
              >
                Sign In
              </button>
            </div>
            <div className="toggle-panel toggle-right">
              <h1>Hello!</h1>
              <p style={{ color: "white" }}>
                Register with your personal details to use all system features
              </p>
              <button
                className="hidden"
                onClick={handleRegisterClick}
                type="button"
              >
                Sign Up
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Login;
