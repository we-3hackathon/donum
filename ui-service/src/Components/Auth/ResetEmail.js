import React from "react";
import axios from "axios";
import { Redirect } from "react-router-dom";

class ResetEmail extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      email: "",
      AUTH_ERROR: ""
    };
  }

  componentDidMount() {
    document.title = "Donum | Login";
  }

  render() {
    const { email, AUTH_ERROR } = this.state;
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

              <p class="error_message">{AUTH_ERROR}</p>

              <div class="container-login100-form-btn">
                <button class="login100-form-btn" type="submit">
                  Send
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
    if (
      !(this.state.email === "")
    ) {
      axios
        .get("http://localhost:8020/reset-password-email/" + this.state.email)
        .then(result => {
          console.log(result);
          if (result.data == "Email Sent.") {
            this.setState({
              AUTH_ERROR: "Email sent, Check your inbox!"
            });
          } else {
            this.setState({
              AUTH_ERROR: "Internal Service Error, Email not sent!"
            });
          }
        });
      }
  };
}

export default ResetEmail;
