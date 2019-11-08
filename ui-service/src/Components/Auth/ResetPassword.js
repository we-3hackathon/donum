import React from "react";
import axios from "axios";
import { Redirect } from "react-router-dom";

class ResetPassword extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      password: "",
      confirmPassword: "",
      AUTH_ERROR: ""
    };
  }

  componentDidMount() {
    document.title = "Donum | Reset Password";
  }

  render() {
    const { password, confirmPassword, AUTH_ERROR } = this.state;
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

              <div
                class="wrap-input100 validate-input"
                data-validate="Confirm Password"
              >
                <input
                  class="input100"
                  type="password"
                  name="confirmPassword"
                  placeholder="Confirm Password"
                  value={confirmPassword}
                  onChange={this.handleChange}
                />
                <span class="focus-input100" data-placeholder=""></span>
              </div>

              <p class="error_message">{AUTH_ERROR}</p>

              <div class="container-login100-form-btn">
                <button class="login100-form-btn" type="submit">
                  Reset
                </button>
              </div>
            </form>
            <br></br>
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
    const {resetToken, email} = this.props.match.params
    console.log(resetToken, email);

    if (!(this.state.password === "") && !(this.state.confirmPassword === "")) {
      if (this.state.password === this.state.confirmPassword) {
        axios
          .get("http://localhost:8020/resetpassword/" + resetToken + "/" + this.state.password + "/" + email) // Need to be updated
          .then(result => {
            console.log(result);
            if (result.data == "Password Reset") {
              this.props.history.push("/resetpasswordsuccess");
            } else {
              this.setState({
                AUTH_ERROR: "Internal Error, password not reset."
              });
            }
          });
      } else {
        this.setState({
          AUTH_ERROR: "Passwords do not match!"
        })
      }
    } else {
      this.setState({
        AUTH_ERROR: "Please fill all fields."
      })
    }
  };
}


export default ResetPassword;
