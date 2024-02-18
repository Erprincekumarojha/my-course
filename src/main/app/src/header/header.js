// Header.js
import React from 'react';
import '../style/header.css'; 
import Logo from "../image/logo.png";

function Header() {
  return (
    <header className="header">
      <div className="logo"><img src={Logo} alt="Your logo"/></div>
      <nav className="nav">
        <ul>
          <li><a href="/">Home</a></li>
          <li><a href="/about">About</a></li>
          <li><a href="/contact">Contact</a></li>
          <li><a href="/login">Login</a></li>
        </ul>
      </nav>
    </header>
  );
}

export default Header;
