import React, { Component } from "react";
import { Map, GoogleApiWrapper, Marker } from "google-maps-react";
import PieChart from "./components/PieChart/PieChart";
import ColumnChart from "./components/ColumnChart/ColumnChart";
import MainScatterPlot from "./components/MainScatterPlot/MainScatterPlot";
import Navbar from "./components/Navbar/Navbar";
import axios from "axios";
import Table from "./components/Table/Table";
import ScatterPlot from "./components/ScatterPlot/ScatterPlot";
const mapStyles = {
  width: "100%",
  height: "100%"
};

export class MapContainer extends Component {
  constructor(props) {
    super(props);

    this.state = {
      coordinates: [],
      data: {},
      Prices: [],
      Addresses: [],
      Bedrooms: [],
      Bathrooms: [],
      TV: [],
      Elevator: [],
      Furnished: [],
      SmokingPermitted: [],
      HydroIncluded: [],
      HeatIncluded: [],
      WaterIncluded: [],
      UnitType: [],
      Internet: [],
      Landline: [],
      Yard: [],
      Size: [],
      rows: []
    };
    this.getData();
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
    this.getData();
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

  getData = () => {
     axios
      .get("/chartview")
      .then(response => {
        // this.setData(data);
        this.getRows(response);
        this.setFields(response);
      })
      .catch(error => {
        console.log(error);
      });
  };

  setData = data => {
    this.setState({
      ...this.state,
      data: data
    });
  };

  getRows = response => {
    let rows = [];
    const data = response["data"];

    for (var key in data) {
      let row = null;

      row = data[key];

      rows.push(row);
    }
    this.setState({ ...this.state, rows: rows });
  };

  setFields = response => {
    const data = response["data"];
    let Prices = [];
    let Bedrooms = [];
    let Bathrooms = [];
    let Addresses = [];
    let TV = [];
    let Elevator = [];
    let Furnished = [];
    let SmokingPermitted = [];
    let HydroIncluded = [];
    let HeatIncluded = [];
    let WaterIncluded = [];
    let UnitType = [];
    let Internet = [];
    let Landline = [];
    let Yard = [];
    let Size = [];

    for (var listing in data) {
      Prices.push(data[listing]["Price"]);
      Bedrooms.push(data[listing]["Bedrooms"]);
      Bathrooms.push(data[listing]["Bathrooms"]);
      Addresses.push(data[listing]["Address"]);
      TV.push(data[listing]["Cable/TV Included"]);
      Elevator.push(data[listing]["Elevator in Building"]);
      Furnished.push(data[listing]["Furnished"]);
      SmokingPermitted.push(data[listing]["Smoking Permitted"]);
      HydroIncluded.push(data[listing]["Hydro Included"]);
      HeatIncluded.push(data[listing]["Heat Included"]);
      WaterIncluded.push(data[listing]["Water Included"]);
      UnitType.push(data[listing]["Unit Type"]);
      Internet.push(data[listing]["Internet Included"]);
      Landline.push(data[listing]["Landline Included"]);
      Yard.push(data[listing]["Yard Balcony"]);
      Size.push(data[listing]["Elevator in Building"]);
    }
    this.setState({
      ...this.state,
      Prices: Prices,
      Bedrooms: Bedrooms,
      Bathrooms: Bathrooms,
      Addresses: Addresses,
      TV: TV,
      Elevator: Elevator,
      Furnished: Furnished,
      SmokingPermitted: SmokingPermitted,
      HydroIncluded: HydroIncluded,
      HeatIncluded: HeatIncluded,
      WaterIncluded: WaterIncluded,
      UnitType: UnitType,
      Internet: Internet,
      Landline: Landline,
      Yard: Yard,
      Size: Size
    });
  };

  getPrices = () => {
    const prices = this.state.Prices;
    let plotPoints = [];

    plotPoints.push(["Home", "Price"]);
    prices.forEach(function(price, homeNumber) {
      let alteredPrice = price;
      alteredPrice = alteredPrice.replace(",", "");
      alteredPrice = alteredPrice.replace("$", "");
      if (alteredPrice === "") {
        alteredPrice = 0;
      }
      alteredPrice = parseFloat(alteredPrice);
      plotPoints.push([homeNumber, alteredPrice]);
    });
    return plotPoints;
  };

  buildScatterPlotData = type => {
    let plotPoints = [];

    plotPoints.push(["Home", type]);

    this.state[type].forEach(function(item, index) {
      plotPoints.push([index, parseFloat(item)]);
    });
    return plotPoints;
  };

  buildDataIncluded = type => {
    let plotPoints = [];
    let dict = {};
    plotPoints.push(["X", type]);

    this.state[type].forEach(function(item, index) {
      if (dict.hasOwnProperty(item)) {
        dict[item] += 1;
      } else {
        dict[item] = 1;
      }
    });
    for (var key in dict) {
      console.log(key);
      plotPoints.push([key, dict[key]]);
    }
    return plotPoints;
  };

  render() {
    return (
      <React.Fragment>
        <div style={{ position: "relative", left: "auto", right: "auto" }}>
          <Navbar />
          <div className="container-fluid" style={{ marginTop: "40px" }}>
            <center>
              <div className="row">
                <div className="col">
                  <div className="card shadow p-3 mb-5 bg-white rounded">
                    <h3 style={{ textAlign: "left" }}>Price Distribution</h3>
                    <div className="card-body ">
                      <MainScatterPlot
                        title="Price Distribution Across Homes"
                        vTitle="Price in $CAD"
                        hTitle="Home"
                        plotPoints={this.getPrices()}
                      />
                    </div>
                  </div>
                </div>
              </div>
            </center>
            <center>
              <div className="row">
                <PieChart
                  vTitle="vTitle"
                  hTitle="hTitle"
                  buildDataHandler={this.buildDataIncluded}
                />

                <PieChart
                  vTitle="vTitle"
                  hTitle="hTitle"
                  buildDataHandler={this.buildDataIncluded}
                />
                </div>
                <div className="row">
                <ScatterPlot
                  title="Chart"
                  vTitle="vTitle"
                  hTitle="hTitle"
                  buildDataHandler={this.buildScatterPlotData}
                />

                <PieChart
                  vTitle="vTitle"
                  hTitle="hTitle"
                  buildDataHandler={this.buildDataIncluded}
                />
              </div>
            </center>

            <center>
              <div className="row">
                <div className="col">
                  <div className="card shadow p-3 mb-5 bg-white rounded">
                    <h3 style={{ textAlign: "left" }}>View all Data</h3>
                    <div className="card-body ">
                      <Table rows={this.state.rows} />
                    </div>
                  </div>
                </div>
              </div>
            </center>
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
