import axios from "axios";
import React, { Component } from "react";
import { Map, InfoWindow, Marker, GoogleApiWrapper } from "google-maps-react";

export class GoogleMaps extends Component {
    state = {
      usersLocation: null
    };

    // *** Add componentDidMount to start the async load
    componentDidMount() {
      this.getUserLocation()
      .then(location => {
        this.setState({
            usersLocation: location.map(user => {key: user.id, lat: user.latitude, lng: user.longitude})
      }).catch(error => {
        console.log(error);
      });
      console.log(this.state);
    })
  }


    render() {
      const { location } = this.state;
      console.log(location);
      if (!location) {
        return <div>Loading Map...</div>;
      }
      return (
        <Map google={this.props.google} zoom={14}>
          {location.map(User => (
            <Marker
              key={User.id}
              position={{ lat: User.latitude, lng: User.longitude }}
            />
          ))}
        </Map>
      );
    }

    getUserLocation() {
      // *** *Return* the result of the axios call
      return axios.get("http://40.121.148.131:8000/account-service/get-all").then(UserDetails => {
        return UserDetails.data
      });
    }
  }

export default GoogleApiWrapper({
  apiKey: "AIzaSyBz-en4IzG0aeAxcGWc3Xo0fURt-Fb2-sU"
})(GoogleMaps);
