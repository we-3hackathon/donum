import React from "react";
import axios from "axios";
import '../Components CSS/Auth/Login.css'

class Login extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      firstname: "",
      email: "",
      password: ""
    };
  }

  render() {
    const { firstname, email, password } = this.state;
    return (
      <form onSubmit={this.handleSubmit}>
        <label htmlFor="firstname">Firstname</label>
        <input
          name="firstname"
          type="text"
          placeholder="Enter Firstname"
          value={firstname}
          onChange={this.handleChange}
        />
        <label htmlFor="email">Email</label>
        <input
          name="email"
          type="text"
          placeholder="Enter your email"
          value={email}
          onChange={this.handleChange}
        />
        <label htmlFor="email">Password</label>
        <input
          name="password"
          type="password"
          placeholder="Enter your password"
          value={password}
          onChange={this.handleChange}
        />
        <button type="submit">Login</button>
      </form>
    );
  }

  componentDidMount() {
    axios
      .get(
        "http://localhost:8020/login/${this.state.firstname}/${this.state.email}/${this.state.password}"
      )
      .then(result => {
        console.log(result);
      });
  }

  handleChange = event => {
    this.setState({
      [event.target.name]: event.target.value
    });
  };

  handleSubmit = event => {
    console.log("Submitting");
    console.log(this.state);
  };
}

export default Login;
