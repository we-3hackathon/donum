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
	  AUTH_ERROR: "",
	  AUTH_STATUS: false
    };
  }

  render() {
    const { firstname, email, password, AUTH_ERROR } = this.state;
    if (this.state.AUTH_STATUS) {
      return <Redirect to="/dashboard" />;
    }
    return (

      <div class="limiter">
	  		<div class="topnav" id="myTopnav">
				<a href="#" >Donum</a>
			</div>

        <div class="container-login100">
          <div class="wrap-login100">
            <form
              class="login100-form validate-form"
              onSubmit={this.handleSubmit}
            >


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

                <span  data-placeholder=""></span>
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
              Don't have an Account? <a href="/register"> <b>Donum up!</b></a>
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
    event.preventDefault();

	if(!(this.state.firstname === "") && !(this.state.email=== "") && !(this.state.password === "")){

	axios
      .get(
        //incorrect way of doing, to be enhanced
        "http://40.121.148.131:8000/account-service//" +
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
            AUTH_STATUS: true
          });
        } else {
		  this.setState({
            AUTH_ERROR : "Login failed"
          });
        }
      });
  }else{
	  this.setState({
		  AUTH_ERROR : "Don't try it"
		});
  }
}
}

export default Login;
