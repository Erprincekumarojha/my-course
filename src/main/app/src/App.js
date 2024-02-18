// App.js
import React from 'react';
import { BrowserRouter as Router, Route , Switch} from 'react-router-dom';
import HomePage from './common/home';
import AboutPage from './common/about';
import ContactPage from './common/contact';
import NotFoundPage from './error/pagenotfound';
import Header from './header/header';
import LoginPage from './common/login';
import UserDashboard from './component/userDashboard';


function App() {
  return (
   <div>
     <Header/>
      <Router>
      <div>
        <Switch>
          <Route exact path="/" component={HomePage} />
          <Route path="/about" component={AboutPage} />
          <Route path="/contact" component={ContactPage} />
          <Route path="/login" component={LoginPage} />
          <Route path="/dashboard" component={UserDashboard} />
          <Route component={NotFoundPage} />
        </Switch>
      </div>
    </Router>
   </div>
  );
}

export default App;
