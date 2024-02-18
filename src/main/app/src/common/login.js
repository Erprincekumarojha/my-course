
import React, { useState } from 'react';
import '../style/login.css'; 
import { baseURL, loginAPI } from './services';
import { useHistory } from 'react-router-dom'; 

function LoginPage() {
  
  const [userId, setUserId] = useState('');
  const [password, setPassword] = useState('');
  const [loggedIn, setLoggedIn] = useState(false);
  const history = useHistory();
  let request = {};

  const handleSubmit = async () => {
    try {
      request.userId = userId;
      request.password = password;
      alert("Request login API : "+JSON.stringify(request));
      const response = loginAPI(baseURL, { request});

      console.log(response.data)
      // Assuming your backend returns a token upon successful login
      const { token } = response.data;

      // Store the token in localStorage or session storage for future API calls
      localStorage.setItem('token', token);
    
      // Optionally, you can redirect the user to another page upon successful login
       history.push('/dashboard');
    } catch (error) {
      console.error('Login error:', error.message);
      history.push('/notfound');
    }
  };

  return (
    <div className="login-container">
      {loggedIn ? (
        <div>
          <h2>Welcome, {userId}!</h2>
          <button onClick={() => setLoggedIn(false)}>Logout</button>
        </div>
      ) : (
        <form onSubmit={handleSubmit} className="login-form">
           <h5>User Login Page</h5>
          <label>
            User ID:
            <input
              type="text"
              value={userId}
              onChange={(e) => setUserId(e.target.value)}
            />
          </label>
          <br />
          <label>
            Password:
            <input
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
          </label>
          <br />
          <button type="submit">Login</button>
        </form>
      )}
    </div>
  );
}

export default LoginPage;
