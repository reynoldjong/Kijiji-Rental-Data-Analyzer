import React, { Component } from "react";
import { Map, GoogleApiWrapper, Marker } from 'google-maps-react';
import RentalInfoWindow from "./window";
import axios from "axios";

const mapStyles = {
  width: '100%',
  height: '50%',
};

export class MapContainer extends Component {
    constructor(props) {
      super(props);

      this.state = {
        details: []
      }
    }

    componentDidMount() {
        axios
          .get("/mapview")
          .then(response => {
            // If the get request is successful state (files) is updated
            const data = response["data"]["rental"];
            this.setState({
              details: data
            });
          })
          .catch(function(error) {
            console.log(error);
          });
      };
  
       displayMarkers = () => {
         return this.state.details.map((details, index) => {
           return <Marker key={index} id={index} place={details} onClick={this.onMarkerClick} position={{
            lat: details.lat,
            lng: details.lng
          }} />
         })

       }

      onMarkerClick = (props, marker) => {
        this.setState({
          activeMarker: marker,
          selectedAddress: props.place.address,
          selectedPrice: props.place.price,
          selectedUrl: props.place.url,
          selectedId: props,
          showingInfoWindow: true
        });
      };

      onInfoWindowClose = () =>
        this.setState({
          activeMarker: null,
          showingInfoWindow: false
        });

      onMapClicked = () => {
        if (this.state.showingInfoWindow)
          this.setState({
            activeMarker: null,
            showingInfoWindow: false
          });
      };

      handleClick = () => {
        this.setState({ open: true });
      };

      handleClose = (event, reason) => {
        if (reason === 'clickaway') {
          return;
        }
        this.setState({ open: false });
      };
  
    render() {
      return (
        <div>
          <Map
            google={this.props.google}
            onClick={this.onMapClicked}
            zoom={8}
            style={mapStyles}
            initialCenter={{ lat: 43.7645, lng: -79.411}}
          >
            {this.displayMarkers()}
           <RentalInfoWindow
           marker={this.state.activeMarker}
           visible={this.state.showingInfoWindow}
           >
           <div>
           <h1>{this.state.selectedAddress}</h1>
           <p>{this.state.selectedPrice}</p>
           <span>{this.state.selectedUrl}</span>
         </div>
         </RentalInfoWindow>
          </Map>
          </div>
      );
    }
  }

  export default GoogleApiWrapper({
    apiKey: 'AIzaSyDAcLK0RSSSVNdvAA_TRGAHPPHpBZnIEiw'
  })(MapContainer);