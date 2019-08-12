import React from 'react';
import Dashboard from './Components/Dashboard/Dashboard';
import Register from './Components/Auth/Register';
import Login from './Components/Auth/Login';
import GoogleMaps from './Components/Dashboard/GoogleMaps';
import { BrowserRouter as Router, Route } from "react-router-dom";

function App() {

  return (
    <div className="Wrapper">
    <Router>
      <Route path ="/dashboard" component={Dashboard} />
      <Route exact path="/" component={Login} />
      <Route path="/login" component={Login} />
	  <Route path="/register" component={Register} />
      <Route path="/map" component={GoogleMaps} />
    </Router>
    </div>
  );
}

export default App;
