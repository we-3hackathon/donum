import axios from "axios";
import React, { Component } from "react";
import Cookies from 'universal-cookie';
import { Map, Marker, GoogleApiWrapper } from "google-maps-react";

export class GoogleMaps extends Component {

  constructor(props){
    super(props)
    this.state = {
      UserInfo: null
    }
  }

  componentDidMount() {
    const cookies = new Cookies();
    document.title = 'Donum | Users'
    var self = this;
    if(cookies.get("token")){
      axios
        .get(`http://40.121.148.131:8020/get-all`)
        .then(response => {
          self.setState({ UserInfo: response.data }, () => {
            console.log("state updated to", this.state);
          });
        })
        .catch(function(error) {
          console.log(error);
        });
    }
    this.history.props.push("/login")
  }

  render() {
    const cookies = new Cookies();
    const { UserInfo } = this.state;
    if(!cookies.get("token")){
      this.history.props.push("/login")
    }
    if (!UserInfo) {
      return <div>Loading Map...</div>;
    }
    return (
      <Map google={this.props.google} zoom={5}  initalCenter={{ lat: 51.5074, lng: 0.1277 }}>
        {this.state.UserInfo.map(User => (
          <Marker
            key={User.id}
            position={{ lat: User.latitude, lng: User.longitude }}
          />
        ))}
      </Map>
    );
  }
}

export default GoogleApiWrapper({
  apiKey: "key"
})(GoogleMaps);
