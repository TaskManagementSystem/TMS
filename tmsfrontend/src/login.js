
import React from 'react';
import './login.css'; 

const Login = () => {
  const handleRegisterClick = () => {
    console.log('Register button clicked');
  };

  const handleLoginClick = () => {
    console.log('Login button clicked');
  };

  return (
    <div>
      <div className="container" id="container">
        <div className="form-container sign-up">
          <form>
            <h1>Create Account</h1>
            <div className="social-icons">
              <a href="#" className="icon"><i className="fab fa-facebook-f"></i></a>
              <a href="#" className="icon"><i className="fab fa-google-plus-g"></i></a>
              <a href="#" className="icon"><i className="fab fa-github"></i></a>
              <a href="#" className="icon"><i className="fab fa-linkedin-in"></i></a>
            </div>
            <span>or use your e-mail for registration</span>
            <input type="text" placeholder="Name" />
            <input type="email" placeholder="E-mail" />
            <input type="password" placeholder="Password" />
            <button onClick={handleRegisterClick}>Sign Up</button>
          </form>
        </div>
        <div className="form-container sign-in">
          <form>
            <h1>Sign In</h1>
            <div className="social-icons">
              <a href="#" className="icon"><i className="fab fa-facebook-f"></i></a>
              <a href="#" className="icon"><i className="fab fa-google-plus-g"></i></a>
              <a href="#" className="icon"><i className="fab fa-github"></i></a>
              <a href="#" className="icon"><i className="fab fa-linkedin-in"></i></a>
            </div>
            <span>or use your e-mail</span>
            <input type="email" placeholder="E-mail" />
            <input type="password" placeholder="Password" />
            <a href="#">Forgot your password?</a>
            <button onClick={handleLoginClick}>Sign In</button>
          </form>
        </div>
        <div className="toggle-container">
          <div className="toggle">
            <div className="toggle-panel toggle-left">
              <h1>Welcome Back!</h1>
              <p style={{ color: 'white' }}>
                To connect your work account please login with your personal
                info
              </p>
              <button className="hidden" id="login">Sign In</button>
            </div>
            <div className="toggle-panel toggle-right">
              <h1>Hello, friend!</h1>
              <p style={{ color: 'white' }}>
                Register with your personal details to use all of system
                features
              </p>
              <button className="hidden" id="register">Sign Up</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Login;
