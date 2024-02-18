
import React, { useState } from 'react';
import '../style/login.css'; 

function LoginPage() {
  // State variables to hold the user ID and password
  const [userId, setUserId] = useState('');
  const [password, setPassword] = useState('');
  const [loggedIn, setLoggedIn] = useState(false);

  // Function to handle form submission
  const handleSubmit = (event) => {
    event.preventDefault(); // Prevent default form submission behavior

    // Check if the user ID and password are valid (for demonstration purposes, just check if they're not empty)
    if (userId !== '' && password !== '') {
      // Set loggedIn state to true
      setLoggedIn(true);
      alert('Login successful!');
    } else {
      alert('Please enter a valid user ID and password.');
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
