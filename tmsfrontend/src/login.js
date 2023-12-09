import React, { useState } from "react";
import "./login.css";
import axios from "axios";

const Login = ({ onLogin }) => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [active, setActive] = useState(false);
  const [emailError, setEmailError] = useState("");
  const [passwordError, setPasswordError] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [submitted, setSubmitted] = useState(false);
  const [submittedR, setSubmittedR] = useState(false);
  const [confirmPasswordError, setConfirmPasswordError] = useState("");

  const validateEmail = () => {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    if (!email) {
      setEmailError("Email is required");
    } else if (!emailRegex.test(email)) {
      setEmailError("Invalid email format");
    } else {
      setEmailError("");
    }
  };

  const validatePassword = () => {
    if (!password) {
      setPasswordError("Password is required");
    } else if (password.length < 8) {
      setPasswordError("Password must be 8 characters or longer");
    } else if (!/[A-Z]/.test(password)) {
      setPasswordError("Password must contain at least one uppercase letter");
    } else if (!/[\W_]/.test(password)) {
      setPasswordError("Password must contain at least one special character");
    } else {
      setPasswordError("");
    }
  };

  const validateConfirmPassword = () => {
    if (password !== confirmPassword) {
      setConfirmPasswordError("Passwords do not match");
    } else {
      setConfirmPasswordError("");
    }
  };

  const handleRegisterClick = () => {
    setActive(true);
  };

  const handleLoginClick = () => {
    setActive(false);
  };

  const onLoginSubmit = async (e) => {
    e.preventDefault();

    setSubmitted(true);
    validateEmail();
    validatePassword();

    if (emailError || passwordError) {
      return;
    }
    try {
      const response = await axios.post(
        "http://localhost:8080/login",
        {
          username: email,
          password: password,
        },
        {
          withCredentials: true,
        }
      );

      const token = response.data.token;

      if (onLogin) {
        onLogin(token);
      }
    } catch (error) {
      console.error("Login failed:", error);
    }
  };

  const onRegisterSubmit = async (e) => {
    e.preventDefault();

    setSubmittedR(true);
    validateEmail();
    validatePassword();

    if (emailError || passwordError) {
      return;
    }
    try {
      const response = await axios.post(
        "http://localhost:8080/register",
        {
          email: email,
          password: password,
        },
        {
          withCredentials: true,
        }
      );

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
            <input
              type="email"
              placeholder="E-mail"
              onBlur={() => submittedR && validateEmail()}
              onChange={(e) => setEmail(e.target.value)}
            />
            <p className="error-message">{emailError}</p>
            <input
              type="password"
              placeholder="Password"
              onBlur={() => submittedR && validatePassword()}
              onChange={(e) => setPassword(e.target.value)}
            />
            <p className="error-message">{passwordError}</p>
            <input
              type="password"
              placeholder="Confirm Password"
              onBlur={() => submittedR && validateConfirmPassword()}
              onChange={(e) => setConfirmPassword(e.target.value)}
            />
            <p className="error-message">{confirmPasswordError}</p>
            <button onClick={onRegisterSubmit} type="submit">
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
            <input
              type="email"
              placeholder="E-mail"
              onBlur={() => submitted && validateEmail()}
              onChange={(e) => setEmail(e.target.value)}
            />
            <p className="error-message">{emailError}</p>
            <input
              type="password"
              placeholder="Password"
              onBlur={() => submitted && validatePassword()}
              onChange={(e) => setPassword(e.target.value)}
            />
            <p className="error-message">{passwordError}</p>
            <a href="/">Forgot your password?</a>
            <button onClick={onLoginSubmit} type="submit">
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
