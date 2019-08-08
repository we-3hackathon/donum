import React from "react";
import Button from "react-bootstrap/Button";
import axios from "axios";
import Select from "react-select";
import '../Components CSS/Auth/Register.css'

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
      bloodgroup: "",
      firstname: "",
      surname: "",
      email: "",
      addressline: "",
      postcode: "",
      password: "",
      confirmpassword: ""
    };
  }

  render() {
    const {
      bloodgroup,
      firstname,
      surname,
      email,
      addressline,
      postcode,
      password,
      confirmpassword
    } = this.state;

    return (
	
      /*
Author: Colorlib
Author URL: https://colorlib.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
*/

	<div class="main-w3layouts wrapper">
		<h1>SignUp</h1>
		<div class="main-agileinfo">
			<div class="agileits-top">
				<form onSubmit={this.handleSubmit}>
					<input class="text " type="text" name="firstname" placeholder="First Name"  value={firstname} onChange={this.handleChange} required=""/>
					<input class="text email" type="email" name="email" placeholder="Email" value={email} onChange={this.handleChange} required=""/>
					<input class="text email" type="text" name="addressline" placeholder="Address Line" value={addressline} onChange={this.handleChange} required=""/>
					<input class="text email" type="text" name="postcode" placeholder="Postcode" value={postcode} onChange={this.handleChange} required=""/>
					<input class="text email" type="text" name="surname" placeholder="Surname" value={surname} onChange={this.handleChange} required=""/>

					<input class="text email" type="text" name="bloodgroup" placeholder="Blood Group" value={bloodgroup} onChange={this.handleChange} required=""/>


					<input class="text" type="password" name="password" placeholder="Password" value={password} onChange={this.handleChange} required=""/>
					<input class="text w3lpass" type="password" name="confirmpassword" placeholder="Confirm Password" value={confirmpassword} onChange={this.handleChange} required=""/>
					<div class="wthree-text">
						<label class="anim">
							<input type="checkbox" class="checkbox" required=""/>
							<span>I Agree To The Terms & Conditions</span>
						</label>
						<div class="clear"> </div>
					</div>
					<input type="submit" value="SIGNUP"/>
				</form>
				<p>Don't have an Account? <a href="#"> Login Now!</a></p>
			</div>
		</div>
		
		<div class="colorlibcopy-agile">
			<p>© 2018 Colorlib Signup Form. All rights reserved | Design by <a href="https://colorlib.com/" target="_blank">Colorlib</a></p>
		</div>
		
		<ul class="colorlib-bubbles">
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
		</ul>
	</div>
    );
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
	event.preventDefault();
    console.log("Submitting");
	 axios
      .get(
		//incorrect way of doing, to be enhanced
        "http://40.121.148.131:8020/create/" + this.state.bloodgroup + "/" + this.state.firstname + "/" + this.state.surname + "/" + this.state.email + "/" + this.state.password + "/" + this.state.addressline  + "/" + this.state.postcode 
      )
      .then(result => {
        console.log(result.data);
		if(result.data.includes("added")){
			alert("Test: Success");
		}else{
			alert("Test: Fail");
		}
      });
    alert(this.state.firstname);
	console.log(this.state);
  };
}

export default Register;
