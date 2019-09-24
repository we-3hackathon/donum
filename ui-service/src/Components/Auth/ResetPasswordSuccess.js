import React from "react";
import axios from "axios";
import { Redirect } from "react-router-dom";

class ResetPasswordSuccess extends React.Component {
  constructor(props) {
    super(props)
  }

  componentDidMount() {
    document.title = "Donum | Success";
  }

  render() {
    return (
      <div class="limiter">
        <div class="topnav" id="myTopnav">
          <a href="#">Donum</a>
        </div>

        <div class="container-login100">
          <div class="wrap-login100">
          <h1> Password Reset Successful </h1>
          <h5> Cool, You've successfully reset your password, go <a href = "/login"> Login! </a></h5>
          </div>
        </div>
      </div>
    );
  }
}

export default ResetPasswordSuccess;
