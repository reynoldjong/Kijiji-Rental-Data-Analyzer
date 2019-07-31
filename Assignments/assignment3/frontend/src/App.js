import React, { Component } from "react";
import { Map, GoogleApiWrapper, Marker } from "google-maps-react";
import PieChart from "./components/PieChart/PieChart";
import ColumnChart from "./components/ColumnChart/ColumnChart";
import ScatterPlot from "./components/ScatterPlot/ScatterPlot";
import axios from "axios";
import classes from './App.module.css';
import qs from "qs";

const mapStyles = {
  width: "100%",
  height: "100%"
};

export class MapContainer extends Component {
  constructor(props) {
    super(props);

    this.state = {
      coordinates: []
    };
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
  }

  displayMarkers = () => {
    return this.state.coordinates.map((store, index) => {
      return (
        <Marker
          key={index}
          id={index}
          position={{
            lat: store.latitude,
            lng: store.longitude
          }}
          onClick={() => console.log("You clicked me!")}
        />
      );
    });
  };

  render() {
    return (
      <React.Fragment>
        <div style={{position:'relative', left:'auto', right:'auto'}}>
        <div style={{backgroundColor:'#373373', color:'white', height:'60px', width:'100%',fontWeight:'300' }}>
          <h1 style={{float:'left', margin:'0px', position:'relative', top:'5px'}}>
            Kijiji Data Analyzer  
          </h1>
          </div>
         
          <div className={classes.Box}>

        
          <h1>Data at a glance</h1>
          
          <div className={classes.Charts}>
            <div className={classes.Chart}>
            <PieChart title="Chart" vTitle="vTitle" hTitle="hTitle" />
            </div>
            <div className={classes.Chart}>
            <ScatterPlot title="Chart" vTitle="vTitle" hTitle="hTitle" />
            </div>
            <div className={classes.Chart}>
            <ColumnChart title="Chart" vTitle="vTitle" hTitle="hTitle" />
            </div>
          </div>
          </div>
          
            <Map
              google={this.props.google}
              zoom={8}
              style={mapStyles}
              initialCenter={{ lat: 43.7645, lng: -79.411 }}
            >
              {this.displayMarkers()}
            </Map>
            </div>
     
   
      </React.Fragment>
    );
  }
}

export default GoogleApiWrapper({
  apiKey: "AIzaSyDAcLK0RSSSVNdvAA_TRGAHPPHpBZnIEiw"
})(MapContainer);
