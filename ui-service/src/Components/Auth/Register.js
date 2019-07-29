import React from "react";
import Button from "react-bootstrap/Button";
import axios from "axios";
import Select from "react-select";

const BloodGroups = [
  { vale: "AB+", label: "AB+" },
  { value: "AB-", label: "AB-" },
  { value: "A+", label: "A+" },
  { value: "A-", label: "A-" },
  { value: "B+", label: "B+" },
  { value: "B-", label: "B-" },
  { value: "O+", label: "O+" },
  { value: "O-", label: "O-" }
];

class Register extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      bloodGroup: "",
      firstname: "",
      surname: "",
      email: "",
      addressLine: "",
      postcode: "",
      password: "",
      confirmPassword: ""
    };
  }

  render() {
    const {
      bloodGroup,
      firstname,
      surname,
      email,
      addressLine,
      postcode,
      password,
      confirmPassword
    } = this.state;

    return (
      <div className= "wrapper">
        <div className="form-wrapper">
          <h1> Register </h1>
          <form onSubmit={this.handleSubmit}>
            <label htmlFor="bloodGroup"> Blood Group
            <Select name="bloodGroup" value={bloodGroup} onChange={this.handleSelectedChange} options={BloodGroups} />
            </label>
            <label htmlFor="firstname">Firstname</label>
            <input
              className="firstname"
              name="firstname"
              type="text"
              placeholder="Enter firstname"
              value={firstname}
              onChange={this.handleChange}
            />
            <label htmlFor="surname">Surname</label>
            <input
              className="surname"
              name="surname"
              type="text"
              placeholder="Enter Surname"
              value={surname}
              onChange={this.handleChange}
            />
            <label htmlFor="email">Email</label>
            <input
              className="email"
              name="email"
              type="text"
              placeholder="Enter your email"
              value={email}
              onChange={this.handleChange}
            />
            <label htmlFor="addressLine">Address Line</label>
            <input
              className="addressLine"
              name="addressLine"
              type="text"
              placeholder="Enter your address line here"
              value={addressLine}
              onChange={this.handleChange}
            />
            <label htmlFor="postcode">Postcode</label>
            <input
              classNmae="postcode"
              name="postcde"
              type="text"
              placeholder="Enter your Postcode here"
              value={postcode}
              onChange={this.handleChange}
            />
            <label htmlFor="password">Password</label>
            <input
              className="password"
              name="password"
              type="text"
              placeholder="Enter your password"
              value={password}
              onChange={this.handleChange}
            />
            <label htmlFor="confirmPassword">Confirm Password</label>
            <input
              className="confirmPassword"
              name="confirmPassword"
              type="text"
              placeholder="Re-enter your password"
              value={confirmPassword}
              onChange={this.handleChange}
            />
            <button type="submit">Register</button>
          </form>
        </div>
      </div>
    );
  }

  componentDidMount() {
    axios
      .get(
        "http://localhost:8020/create/${this.state.bloodGroup}/${this.state.firstname}/${this.state.surname}/${this.state.email}/${this.state.password}/${this.state.addressLine}/${this.state.postcode}"
      )
      .then(result => {
        console.log(result);
      });
  }

  handleSelectedChange = bloodGroup => {
    this.setState({ bloodGroup });
    console.log(`Option selected:`, bloodGroup);
  };

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

export default Register;
