import React, { Component } from "react";
import { Map, GoogleApiWrapper, Marker } from 'google-maps-react';
import axios from "axios";
import qs from "qs";

const mapStyles = {
  width: '100%',
  height: '100%',
};

export class MapContainer extends Component {
    constructor(props) {
      super(props);

      this.state = {
        coordinates: []
      }
    }

    componentDidMount() {
        axios
          .get("/mapview")
          .then(response => {
            // If the get request is successful state (files) is updated
            const data = response["data"]["coordinates"];
            this.setState({
              coordinates: data
            });
          })
          .catch(function(error) {
            console.log(error);
          });
      };
  
    displayMarkers = () => {
      return this.state.coordinates.map((store, index) => {
        return <Marker key={index} id={index} position={{
         lat: store.latitude,
         lng: store.longitude
       }}
       onClick={() => console.log("You clicked me!")} />
      })
    }
  
    render() {
      return (
          <Map
            google={this.props.google}
            zoom={8}
            style={mapStyles}
            initialCenter={{ lat: 43.7645, lng: -79.411}}
          >
            {this.displayMarkers()}
          </Map>
      );
    }
  }

  export default GoogleApiWrapper({
    apiKey: 'AIzaSyDAcLK0RSSSVNdvAA_TRGAHPPHpBZnIEiw'
  })(MapContainer);