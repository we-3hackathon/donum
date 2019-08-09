import React from "react";
import axios from "axios";
import { Redirect } from "react-router-dom";
import Dashboard from "../Dashboard/Dashboard";
import "../Components CSS/Auth/css/main.css";
import "../Components CSS/Auth/css/util.css";


class Login extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      firstname: "",
      email: "",
      password: "",
      loggedIn: false
    };
  }

  render() {
    const { firstname, email, password } = this.state;
    if (this.state.loggedIn) {
      return <Redirect to="/dashboard" />;
    }
    return (
      <div class="limiter">
        <div class="container-login100">
          <div class="wrap-login100">
            <form
              class="login100-form validate-form"
              onSubmit={this.handleSubmit}
            >
              <span class="login100-form-title p-b-34 p-t-27">Log in</span>

              <div
                class="wrap-input100 validate-input"
                data-validate="Enter username"
              >
                <input
                  class="input100"
                  type="text"
                  name="firstname"
                  placeholder="Username"
                  value={firstname}
                  onChange={this.handleChange}
                />
                <span class="focus-input100" data-placeholder=""></span>
              </div>

              <div
                class="wrap-input100 validate-input"
                data-validate="Enter email"
              >
                <input
                  class="input100"
                  type="text"
                  name="email"
                  placeholder="Email"
                  value={email}
                  onChange={this.handleChange}
                />
                <span class="focus-input100" data-placeholder=""></span>
              </div>

              <div
                class="wrap-input100 validate-input"
                data-validate="Enter password"
              >
                <input
                  class="input100"
                  type="password"
                  name="password"
                  placeholder="Password"
                  value={password}
                  onChange={this.handleChange}
                />
                <span class="focus-input100" data-placeholder=""></span>
              </div>

              <div class="contact100-form-checkbox">
                <input
                  class="input-checkbox100"
                  id="ckb1"
                  type="checkbox"
                  name="remember-me"
                />
                <label class="label-checkbox100" for="ckb1">
                  Remember me
                </label>
              </div>

              <div class="container-login100-form-btn">
                <button class="login100-form-btn" type="submit">
                  Login
                </button>
              </div>
              <div class="text-center p-t-90">
                <a class="txt1" href="#">
                  Forgot Password?
                </a>
              </div>
            </form>
          </div>
        </div>
      </div>
    );
  }

  handleChange = event => {
    this.setState({
      [event.target.name]: event.target.value
    });
  };
  handleSubmit = event => {
    event.preventDefault();

    axios
      .get(
        //incorrect way of doing, to be enhanced
        "http://40.121.148.131:8020/login/" +
          this.state.firstname +
          "/" +
          this.state.email +
          "/" +
          this.state.password
      )
      .then(result => {
        console.log(result.data);
        if (result.data == "Login Successful") {
          this.setState({
            loggedIn: true
          });
        } else {
          alert("Test: Fail");
        }
      });
  };
}

export default Login;
