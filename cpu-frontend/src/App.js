import React, { Component } from 'react';
import './App.css';
import Login from './auth/Login';
import SignUp from './auth/SignUp';

class App extends Component {
  render() {
    return (
      <div>
        <SignUp />
        <Login />
      </div>
    );
  }
}

export default App;
