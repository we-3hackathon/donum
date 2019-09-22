import React from "react";
import axios from "axios";
import { Redirect } from "react-router-dom";
import Cookies from "universal-cookie";
import "../CSS/Auth/Login/main.css";
import "../CSS/Auth/Login/util.css";

class Login extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      firstname: "",
      email: "",
      password: "",
      AUTH_ERROR: "",
      AUTH_STATUS: false
    };
  }

  componentDidMount() {
    document.title = "Donum | Login";
  }

  render() {
    const { firstname, email, password, AUTH_ERROR } = this.state;
    if (this.state.AUTH_STATUS) {
      return <Redirect to="/map" />;
    }
    return (
      <div class="limiter">
        <div class="topnav" id="myTopnav">
          <a href="#">Donum</a>
        </div>

        <div class="container-login100">
          <div class="wrap-login100">
            <form
              class="login100-form validate-form"
              onSubmit={this.handleSubmit}
            >
              <div
                class="wrap-input100 validate-input"
                data-validate="Enter firstname"
              >
                <input
                  class="input100"
                  type="text"
                  name="firstname"
                  placeholder="Firstname"
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

                <span data-placeholder=""></span>
              </div>

              <p class="error_message">{AUTH_ERROR}</p>
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
            </form>
            <br></br>
            <p class="sign-up">
              Don't have an Account?{" "}
              <a href="/register">
                {" "}
                <b>Donum up!</b>
              </a>
            </p>
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
    const { cookies } = this.props;
    event.preventDefault();
    if (
      !(this.state.firstname == "") &&
      !(this.state.email === "") &&
      !(this.state.password === "")
    ) {
      axios
        .get(
          //incorrect way of doing, to be enhanced
          "http://localhost:8020/login/" +
            this.state.firstname +
            "/" +
            this.state.email +
            "/" +
            this.state.password
        )
        .then(result => {
          console.log(result);
          if (result.data != "Login Failed") {
            const cookies = new Cookies();
            let experationDate = new Date();
            experationDate.setTime(experationDate.getTime() + 120 * 60 * 1000);
            cookies.set("token", result.data.nameValuePairs.firstName, {
              path: "/",
              expires: experationDate
            });
            this.setState({
              AUTH_STATUS: true
            });
          } else {
            this.setState({
              AUTH_ERROR: "Login failed"
            });
          }
        });
    } else {
      this.setState({
        AUTH_ERROR: "Don't try it"
      });
    }
  };
}

export default Login;
