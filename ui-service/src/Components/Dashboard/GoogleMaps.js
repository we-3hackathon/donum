import axios from "axios";
import React, { Component } from "react";
import { Map, Marker, GoogleApiWrapper } from "google-maps-react";

export class GoogleMaps extends Component {
  state = {
    UserInfo: null
  };

  componentDidMount() {
    document.title = 'Donum | Users'
    var self = this;
    axios
      // .get(`http://40.121.148.131:8000/account-service/get-all`)
      .get('http://40.121.148.131:8020/get-all')
      .then(response => {
        self.setState({ UserInfo: response.data }, () => {
          console.log("state updated to", this.state);
        });
      })
      .catch(function(error) {
        console.log(error);
      });
  }

  render() {
    const { UserInfo } = this.state;
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
